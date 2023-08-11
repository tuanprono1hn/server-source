package com.tuanla.springserver.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.io.File;

public class QRGenerate {
    public static void main(String[] args) throws Exception {
        //saveToFile(generateEAN13BarcodeImage(StringUtils.generateRandomString("number", 12)), "image", "jpg");
        saveToFile(generateQRCodeImage("00020101021238540010A00000072701240006970407011099662288660208QRIBFTTA530370454061000005802VN62090805Hello630449F"), "qr", "png");
    }

    public static BitMatrix generateQRCodeImage(String qrText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter.encode(qrText, BarcodeFormat.QR_CODE, 200, 200);
        //MatrixToImageWriter.toBufferedImage(bitMatrix);
        return bitMatrix;
    }

    public static BitMatrix generateEAN13BarcodeImage(String barcodeText) throws Exception {
        EAN13Writer barcodeWriter = new EAN13Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.EAN_13, 300, 150);
        return bitMatrix;
    }

    public static void saveToFile(BitMatrix bitMatrix, String fileName, String fileType) throws Exception {
        if (fileName == null || fileName.isEmpty()) {
            fileName = "image";
        }
        if (fileType == null || fileType.isEmpty()) {
            fileType = "jpg";
        }
        File outputFile = new File(fileName.concat(".").concat(fileType));
        ImageIO.write(MatrixToImageWriter.toBufferedImage(bitMatrix), fileType, outputFile);
    }
}
