package com.tuanla.springserver.util;

import org.apache.commons.lang3.RandomUtils;
import org.jsmpp.bean.*;
import org.jsmpp.session.BindParameter;
import org.jsmpp.session.SMPPSession;
import org.jsmpp.util.AbsoluteTimeFormatter;
import org.jsmpp.util.TimeFormatter;

import java.io.IOException;
import java.util.*;

public class SmsService {
    private static TimeFormatter timeFormatter = new AbsoluteTimeFormatter();
    private static final String HOST_SMS = "10.38.30.77";
    private static final int PORT_SMS = 5001;
    private static final String USERNAME_SMS = "MDSsoft";
    private static final String PASSWORD_SMS = "MDSsoft";
    private static final long receiveTimeout = 100000;
    private static final int enquireTime = 100000;
    private SMPPSession session;

    public String sendMessageSMS(String source, String dest, String content) throws Exception {
        if (session == null || !session.getSessionState().isBound()) bind(source);
        Alphabet alphabet = Alphabet.ALPHA_DEFAULT;
        String encode = "";
        int splitSize = 150;
        int l1 = content.length();
        int l2 = content.getBytes("UTF-8").length;
        if (l1 != l2) {
            alphabet = Alphabet.ALPHA_UCS2;
            encode = "UTF-16BE";
            splitSize = 70;
        }
        String msgReturn = "";
        if (content.length() <= splitSize) {
            String messageId = session.submitShortMessage("VMA", TypeOfNumber.ALPHANUMERIC,
                    NumberingPlanIndicator.UNKNOWN, source, TypeOfNumber.INTERNATIONAL, NumberingPlanIndicator.ISDN,
                    dest, new ESMClass(), (byte) 0, (byte) 1, timeFormatter.format(new Date()), null,
                    new RegisteredDelivery(SMSCDeliveryReceipt.DEFAULT), (byte) 0,
                    new GeneralDataCoding(alphabet, MessageClass.CLASS1, false), (byte) 0,
                    encode.equals("") ? content.getBytes() : content.getBytes(encode), new OptionalParameter[0]);
            msgReturn += messageId;
        } else {
            List<byte[]> strMessageSegment = splitByWidth(encode.equals("") ? content.getBytes() : content.getBytes(encode), splitSize);
            OptionalParameter sarMsgRefNum = OptionalParameters.newSarMsgRefNum((short) RandomUtils.nextInt());
            OptionalParameter sarTotalSegments = OptionalParameters.newSarTotalSegments(strMessageSegment.size());
            for (int i = 0; i < strMessageSegment.size(); i++) {
                final int seqNum = i + 1;
                byte[] message = strMessageSegment.get(i);
                OptionalParameter sarSegmentSeqnum = OptionalParameters.newSarSegmentSeqnum(seqNum);
                String messageId = session.submitShortMessage("VMA", TypeOfNumber.ALPHANUMERIC,
                        NumberingPlanIndicator.UNKNOWN, source, TypeOfNumber.INTERNATIONAL,
                        NumberingPlanIndicator.ISDN, dest, new ESMClass(), (byte) 0, (byte) 1,
                        timeFormatter.format(new Date()), null, new RegisteredDelivery(SMSCDeliveryReceipt.DEFAULT),
                        (byte) 0, new GeneralDataCoding(alphabet, MessageClass.CLASS1, false), (byte) 0, message,
                        sarMsgRefNum, sarSegmentSeqnum, sarTotalSegments);
                msgReturn += messageId + ";";
            }
        }
        return msgReturn;
    }

    private void bind(String source) throws IOException {
        if (session != null) {
            unbind();
        }
        session = new SMPPSession();
        session.setTransactionTimer(receiveTimeout);
        session.setEnquireLinkTimer(enquireTime);
        session.connectAndBind(HOST_SMS, PORT_SMS, new BindParameter(BindType.BIND_TX, USERNAME_SMS, PASSWORD_SMS, source,
                TypeOfNumber.UNKNOWN, NumberingPlanIndicator.UNKNOWN, source), receiveTimeout);
    }

    public void unbind() {
        if (session != null) {
            session.unbindAndClose();
            session = null;
        }
    }

    private static List<byte[]> splitByWidth(byte[] strContent, int width) throws Exception {

        if (strContent == null || strContent.length == 0) {
            return Collections.EMPTY_LIST;
        }
        if (width == 0 || strContent.length <= width) {
            return Arrays.asList(strContent);
        }

        List<byte[]> segment = new ArrayList<byte[]>();

        int NumSeg = strContent.length / width + 1;

        int startPos = 0;
        for (int i = 0; i < NumSeg - 1; i++) {
            byte[] data = new byte[((width * (i + 1))) - startPos];
            System.arraycopy(strContent, startPos, data, 0, data.length);
            segment.add(data);
            startPos = (i + 1) * width;
        }
        if (startPos < strContent.length) {
            byte[] data = new byte[strContent.length - startPos];
            System.arraycopy(strContent, startPos, data, 0, data.length);
            segment.add(data);
        }
        return segment;
    }

    public SMPPSession getSession() {
        return session;
    }

    public void setSession(SMPPSession session) {
        this.session = session;
    }
}
