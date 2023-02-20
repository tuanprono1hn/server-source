package com.tuanla.springserver.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.client.utils.DateUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;

public class TestMain {
    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final String subFilename = simpleDateFormat.format(new Date());
    public static final String outputPDF = "/Users/tuanla/Downloads/" + subFilename + "iTextHelloWorld.pdf";

    public static void main(String[] args) throws Exception {
        String[] DATE_PATTERN = {"yyyyMMddHHmmss", "dd/MM/yyyy HH:mm:ss", "dd/MM/yyyy", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss"};
        System.out.println(DateFormatUtils.formatUTC(DateUtils.parseDate(new Date().toString(), DATE_PATTERN), "yyyyMMddHHmmss"));

        //lay ngay dau tuan, dau thang
        String pattern = "yyyy-MM-dd";
        Date currentD = new Date();
        System.out.println(DateUtils.formatDate(currentD, pattern));

        DayOfWeek firstDayOfWeek = WeekFields.of(Locale.CHINESE).getFirstDayOfWeek();
        DayOfWeek lastDayOfWeek = DayOfWeek.of(((firstDayOfWeek.getValue() + 5) % DayOfWeek.values().length) + 1);
        LocalDate firstDL = LocalDate.now(/* tz */).with(TemporalAdjusters.previousOrSame(firstDayOfWeek));
        LocalDate lastDL = LocalDate.now(/* tz */).with(TemporalAdjusters.nextOrSame(lastDayOfWeek));
        System.out.println(firstDL.toString());
        System.out.println(lastDL.toString());

        //first and last day of week
        Date firstDoW = new SimpleDateFormat(pattern).parse(firstDL.toString());
        Date lastDoW = new SimpleDateFormat(pattern).parse(lastDL.toString());
        System.out.println(firstDoW);
        System.out.println(lastDoW);

        //first and last day of month
        Date firstDoM = new SimpleDateFormat(pattern).parse(firstDL.withDayOfMonth(1).toString());
        Date lastDoM = new SimpleDateFormat(pattern).parse(firstDL.withDayOfMonth(firstDL.getMonth().length(firstDL.isLeapYear())).toString());
        System.out.println(firstDoM);
        System.out.println(lastDoM);


        Calendar aCalendar = Calendar.getInstance();
        aCalendar.add(Calendar.MONTH, -1);
        aCalendar.set(Calendar.DATE, 1);
        Date firstDateOfPreviousMonth = aCalendar.getTime();
        aCalendar.set(Calendar.DATE, aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date lastDateOfPreviousMonth = aCalendar.getTime();
        System.out.println(firstDateOfPreviousMonth);
        System.out.println(lastDateOfPreviousMonth);


        //While loop
//        do {
//            TimeUnit.SECONDS.sleep(10);
//            System.out.println("OK");
//        } while (true);

//        String age = "$COL between add_months(sysdate,-2*12) and add_months(sysdate,-1*12)";
//        String fr = age.substring(age.indexOf("and"));
//        fr=fr.substring(fr.indexOf("-")+1,fr.indexOf("*"));
//        String to = age.substring(0, age.indexOf("and"));
//        to=to.substring(to.indexOf("-")+1,to.indexOf("*"));
//        System.out.println(fr);
//        System.out.println(to);

//        List<String> lstStr = new ArrayList<>();
//        lstStr.add("ok1");
//        lstStr.add("ok2");
//        lstStr.add("ok3");
//        String result = lstStr.stream().map(str -> String.format("'%s'", str)).collect(Collectors.joining(","));
//        List<String> parts = Arrays.asList(result.split(",")).stream().map(str -> str.replaceAll("'","")).collect(Collectors.toList());
//
//        System.out.println(result);
//        System.out.println(parts.toString());


        //Load the input Excel file
//        try {
//            Workbook workbook = new Workbook();
//            workbook.loadFromFile("/Users/tuanla/Downloads/Bao_cao_thong_tin_chi_tiet_ket_qua_quang_cao_theo_chien_dich_20211025 (5).xlsx");
//
//            //Fit to page
//            workbook.getConverterSetting().setSheetFitToPage(true);
//
//            //Save as PDF document
//            workbook.saveToFile("/Users/tuanla/Downloads/ExcelToPDF.pdf", FileFormat.PDF);
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }


//        String filePath = "/Users/tuanla/Downloads/testBL.txt";
//        String input = null;
//        Scanner sc = new Scanner(new File(filePath));
//        FileWriter writer = new FileWriter("/Users/tuanla/Downloads/outputTestBl.txt");
//        Set set = new HashSet();
//        while (sc.hasNextLine()) {
//            input = sc.nextLine();
//            if (set.add(input)) {
//                writer.append(input + "\n");
//            }
//        }
//        writer.flush();
//        System.out.println("Contents added............");
//        boolean deleteFile = FileUtil.deleteFile("/Users/tuanla/Downloads/outputTestBl.txt");
//        System.out.println("File deleted..." + deleteFile);


        //START printing pdf
//        try {
//            List<String> lstString = new ArrayList<>();
//            lstString.add("test01");
//            lstString.add("test02");
//
//            String fileName = "/Users/tuanla/Downloads/testPDF.pdf";
//            String jasperPath = "/Users/tuanla/Downloads/Blank_A4_Landscape.jrxml";
//            genPDF(null, jasperPath, lstString, fileName);
//
////            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
////            InputStream inputStream = classloader.getResourceAsStream(jasperPath);
////            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
////            Map<String, Object> parametros = new HashMap<String, Object>();
////            parametros.put("name", "Adrián");
////            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros);
////            JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
        //END printing pdf

        //PlainDate gregorian = PlainDate.nowInSystemTime();
        //ChineseCalendar cc = gregorian.transform(ChineseCalendar.axis());
        //System.out.println(cc.getDayOfMonth());

        //insertTable();
        //telnetCheck();
        //executeSSHCommand("app", "app12345", "10.11.10.234", 22, "telnet 10.11.10.234 22");
    }


//    private static void insertText() throws Exception {
//        //add text to pdf
//        Document document = new Document();
//        PdfWriter.getInstance(document, new FileOutputStream(outputPDF));
//
//        document.open();
//        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
//        Chunk chunk = new Chunk("Hello World", font);
//
//        document.add(chunk);
//        document.close();
//    }
//
//    private static void insertImage() throws Exception {
//        //add image
//        Path path = Paths.get(ClassLoader.getSystemResource("images/icon/folder.png").toURI());
//
//        Document document2 = new Document();
//        PdfWriter.getInstance(document2, new FileOutputStream(outputPDF));
//        document2.open();
//        Image img = Image.getInstance(path.toAbsolutePath().toString());
//        document2.add(img);
//
//        document2.close();
//    }
//
//    private static void insertTable() throws Exception {
//        Document document = new Document();
//        PdfWriter.getInstance(document, new FileOutputStream(outputPDF));
//
//        document.open();
//
//        PdfPTable table = new PdfPTable(3);
//        addTableHeader(table);
//        addRows(table);
//        addCustomRows(table);
//
//        document.add(table);
//        document.close();
//
//    }
//
//    private static void addTableHeader(PdfPTable table) {
//        Stream.of("column header 1", "column header 2", "column header 3")
//                .forEach(columnTitle -> {
//                    PdfPCell header = new PdfPCell();
//                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
//                    header.setBorderWidth(2);
//                    header.setPhrase(new Phrase(columnTitle));
//                    table.addCell(header);
//                });
//    }
//
//    private static void addRows(PdfPTable table) {
//        table.addCell("row 1, col 1");
//        table.addCell("row 1, col 2");
//        table.addCell("row 1, col 3");
//    }
//
//    private static void addCustomRows(PdfPTable table) throws URISyntaxException, BadElementException, IOException {
//        Path path = Paths.get("/Users/tuanla/Downloads/aorus.jpeg");
//        Image img = Image.getInstance(path.toAbsolutePath().toString());
//        img.scalePercent(10);
//
//        PdfPCell imageCell = new PdfPCell(img);
//        table.addCell(imageCell);
//
//        PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
//        horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(horizontalAlignCell);
//
//        PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
//        verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
//        table.addCell(verticalAlignCell);
//    }
}
