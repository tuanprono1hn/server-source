package vn.telsoft.acm.util;

import org.jsmpp.extra.NegativeResponseException;
import org.jsmpp.util.AbsoluteTimeFormatter;
import org.jsmpp.util.TimeFormatter;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class SmsPoolService {
    private static final Logger Log = LoggerFactory.getLogger(SmsPoolService.class);
    private static TimeFormatter timeFormatter = new AbsoluteTimeFormatter();
    private static SmsPoolService INSTANCE;

    public static synchronized SmsPoolService getInstance(JSONObject config) {
        return INSTANCE;
    }

    public static String getDescriptiveMessage(Throwable ex) {
        if (ex instanceof NegativeResponseException) {
            final Map<Integer, String> errors = new HashMap<>();
            errors.put(0x00000000, "No Error");
            errors.put(0x00000001, "Message too long");
            errors.put(0x00000002, "Command length is invalid");
            errors.put(0x00000003, "Command ID is invalid or not supported");
            errors.put(0x00000004, "Incorrect bind status for given command");
            errors.put(0x00000005, "Already bound");
            errors.put(0x00000006, "Invalid Priority Flag");
            errors.put(0x00000007, "Invalid registered delivery flag");
            errors.put(0x00000008, "System error");
            errors.put(0x0000000A, "Invalid source address");
            errors.put(0x0000000B, "Invalid destination address");
            errors.put(0x0000000C, "Message ID is invalid");
            errors.put(0x0000000D, "Bind failed");
            errors.put(0x0000000E, "Invalid password");
            errors.put(0x0000000F, "Invalid System ID");
            errors.put(0x00000011, "Cancelling message failed");
            errors.put(0x00000013, "Message recplacement failed");
            errors.put(0x00000014, "Message queue full");
            errors.put(0x00000015, "Invalid service type");
            errors.put(0x00000033, "Invalid number of destinations");
            errors.put(0x00000034, "Invalid distribution list name");
            errors.put(0x00000040, "Invalid destination flag");
            errors.put(0x00000042, "Invalid submit with replace request");
            errors.put(0x00000043, "Invalid esm class set");
            errors.put(0x00000044, "Invalid submit to ditribution list");
            errors.put(0x00000045, "Submitting message has failed");
            errors.put(0x00000048, "Invalid source address type of number ( TON )");
            errors.put(0x00000049, "Invalid source address numbering plan ( NPI )");
            errors.put(0x00000050, "Invalid destination address type of number ( TON )");
            errors.put(0x00000051, "Invalid destination address numbering plan ( NPI )");
            errors.put(0x00000053, "Invalid system type");
            errors.put(0x00000054, "Invalid replace_if_present flag");
            errors.put(0x00000055, "Invalid number of messages");
            errors.put(0x00000058, "Throttling error");
            errors.put(0x00000061, "Invalid scheduled delivery time");
            errors.put(0x00000062, "Invalid Validty Period value");
            errors.put(0x00000063, "Predefined message not found");
            errors.put(0x00000064, "ESME Receiver temporary error");
            errors.put(0x00000065, "ESME Receiver permanent error");
            errors.put(0x00000066, "ESME Receiver reject message error");
            errors.put(0x00000067, "Message query request failed");
            errors.put(0x000000C0, "Error in the optional part of the PDU body");
            errors.put(0x000000C1, "TLV not allowed");
            errors.put(0x000000C2, "Invalid parameter length");
            errors.put(0x000000C3, "Expected TLV missing");
            errors.put(0x000000C4, "Invalid TLV value");
            errors.put(0x000000FE, "Transaction delivery failure");
            errors.put(0x000000FF, "Unknown error");
            errors.put(0x00000100, "ESME not authorised to use specified servicetype");
            errors.put(0x00000101, "ESME prohibited from using specified operation");
            errors.put(0x00000102, "Specified servicetype is unavailable");
            errors.put(0x00000103, "Specified servicetype is denied");
            errors.put(0x00000104, "Invalid data coding scheme");
            errors.put(0x00000105, "Invalid source address subunit");
            errors.put(0x00000106, "Invalid destination address subunit");
            errors.put(0x0000040B, "Insufficient credits to send message");
            errors.put(0x0000040C, "Destination address blocked by the ActiveXperts SMPP Demo Server");

            String error = errors.get(((NegativeResponseException) ex).getCommandStatus());
            if (ex.getMessage() != null && !ex.getMessage().isEmpty()) {
                error += " (exception message: '" + ex.getMessage() + "')";
            }
            return error;
        } else if (ex.getCause() != null) {
            return getDescriptiveMessage(ex.getCause());
        }

        return ex.getMessage();
    }

    public static <E extends Enum<E>> E getEnumValue(String value, Class<E> enumType, E defaultValue) {
        if (value != null) {
            try {
                return E.valueOf(enumType, value);
            } catch (IllegalArgumentException e) {
                // Ignore
            }
        }
        return defaultValue;
    }

    public static int getIntDefaultValue(JSONObject config, String name, int defaultValue) {
        if (config.has(name)) {
            return config.getInt(name);
        }
        return defaultValue;
    }

    public static String getDefaultValue(JSONObject config, String name, String defaultValue) {
        if (config.has(name)) {
            return config.getString(name);
        }
        return defaultValue;
    }
}