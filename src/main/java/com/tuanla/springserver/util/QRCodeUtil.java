//package vn.telsoft.acm.util;
//
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Image;
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.PdfContentByte;
//import com.itextpdf.text.pdf.PdfReader;
//import com.itextpdf.text.pdf.PdfStamper;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.FileOutputStream;
//import java.text.SimpleDateFormat;
//import java.util.Base64;
//import java.util.Date;
//import java.util.GregorianCalendar;
//
//public class QRCodeUtil {
//
//	public static void main(String[] args) {
//		String strImageBase64 = "iVBORw0KGgoAAAANSUhEUgAAALQAAAC0AQAAAAAVtjufAAACwUlEQVR42u3WMbKtKBAGYExwC5jI1iTRLUAikMgWILG3pgmkE0ICjxtMlW/qWn3yOZSB9QWITfMrab+Of8jXv/5/dzOEUNchLmSWknDUoZw7E0bZdqyqAep+8kxkuowtZWU/"
//				+ "8UiHUjeIun7m5dIhk5Wn+IlDOUTVpbSo7tJw/6nDv8P/pz6/eWvnaG2fp0qp/6r/756nvrhiBkj2IpKjfo6VcTM0vzN1TA1151edLjarZON0cdxTlpOXu3ZRnwpQBxvplueFMl3MwlE/Z30N4U7HFld9aNRtpjoucmpZ3e2xL28e4sJGOIhIIeqoUTdkXreLp1Q"
//				+ "Jpaqh3iD3PoxSbsFC5qiHrFoykqpebCoA9ZRi320w231sNmvcDU/NuhQnSF401ON4x5UtbLPHKgqgHoqLwvZmgRLnRaNukz1FiZOZt7w/+uTNIZwiRd6cO8lmNOqRUl55VX4e+9VQt2YqrhiinKeEctQ9PxbuenAQufIAqPd18iqCH7v1W9Qz7wUmInNDN4CGujU"
//				+ "zWQc/OIBKCMc9xV32Shhd8ugA9WMhY11W5aIy4pEDb97f5STjOQFE5aVG3V0zP6ZQt0NFkRrqkA7JeFzJotK5a9S9JLqFAKHYdj3O6Zv3oBEXG1wyUwoB9zsUcLeXAvo8jzq/udnJYKjOi0gJHn3y5hcjvWOp1H7n/cyi3o55WYf+dJuce+TYm9/2hr5M7qrkp26"
//				+ "oGzrknXsid5XyqlGP/ZNgA9hjNIw96vzmee7rZLsc62TW5/fuxcN9u5IJ3Zyh4pEDb254ZfNwt6uHvJG4t+IVOENG95P5HPce9OIQeTzpzg/czRDHkic/xKkFaKj3OGWzuMtFVW8wQP3nP2p0njB+DYbxD7wn09b8rENwrn3grnJb5WB/ggl3KHXd+wZam7e6Aepm"
//				+ "uFM6pzsSUeXUUP/+t3/963/5H+ha6+eNuRXrAAAAAElFTkSuQmCC";
//		String imsi = "452010250038871";
//		String serial = "8401991202538871";
//		String pathTempPdf = "D:\\TELSOFT\\Project\\ESIM_APP\\convert text to qr_code\\QR.pdf";
//		String pathResultPdf = "D:\\TELSOFT\\Project\\ESIM_APP\\convert text to qr_code\\QRTest2.pdf";
//		String pathFont = "D:\\TELSOFT\\Project\\ESIM_APP\\convert text to qr_code\\tahoma.ttf";
//
//		try {
//			modifyPdf(imsi, serial, strImageBase64, pathTempPdf, pathResultPdf, pathFont, GregorianCalendar.getInstance().getTime());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public static void modifyPdf(String strIMSI, String strSerial, String imageString, String pathTemplatePdf,
//			String pathResultPdf, String pathFont, Date date) throws Exception {
//		// Create PdfReader instance.
//		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//		PdfReader pdfReader = new PdfReader(pathTemplatePdf);
//		GregorianCalendar.getInstance().getTime().getTime();
//		// set ten file: serial_20200928161303776.pdf
//		String pathFile = pathResultPdf + strSerial + "_" + df.format(date) + ".pdf";
//		// Create PdfStamper instance.
//		PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(pathFile), (char) 3);
//		float w = pdfStamper.getReader().getPageSize(1).getWidth();
//		float h = pdfStamper.getReader().getPageSize(1).getHeight();
//
//		// Create BaseFont instance.
//		BaseFont baseFont = BaseFont.createFont(pathFont, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//
//		// Get the number of pages in pdf.
//		int pages = pdfReader.getNumberOfPages();
//
//		// Iterate the pdf through pages.
//		for (int i = 1; i <= pages; i++) {
//			// Contain the pdf data.
//			PdfContentByte pageContentByte = pdfStamper.getOverContent(i);
//
//			pageContentByte.beginText();
//			pageContentByte.setColorFill(BaseColor.BLUE);
//			pageContentByte.setFontAndSize(baseFont, 16);
//			// Thong tin SIM
//			pageContentByte.setTextMatrix(362, 734);
//			// pageContentByte.setTextMatrix(365, 730);
//			pageContentByte.showText(strSerial);
//			// create byte from string
//			byte[] imageByte;
//			imageByte = Base64.getDecoder().decode(imageString);
//			ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
//			// create a buffered image
//			BufferedImage bufferedImage = ImageIO.read(bis);
//			bis.close();
//			Image image = Image.getInstance(bufferedImage, null);
//			image.scaleAbsolute(180, 180);
//			image.setAbsolutePosition(340, 490);
//			pageContentByte.addImage(image);
//		}
//		pdfStamper.close();
////			System.out.println("Create PDF success !");
//
//	}
//}