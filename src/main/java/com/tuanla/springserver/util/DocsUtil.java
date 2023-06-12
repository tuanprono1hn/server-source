package com.tuanla.springserver.util;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class DocsUtil {
    public static void main(String[] args) throws Exception {
        String rootFolder = "D:\\Truyện\\hoa son tai khoi\\401-600\\";
        Collection<File> all = new ArrayList<File>();
        Collection<String> listFile = new ArrayList<String>();
        addTree(new File(rootFolder), all, listFile);
        for (String strIn : listFile) {
            docxToPdf(rootFolder.concat(strIn), rootFolder.concat(strIn.replace("docx", "pdf")));
        }
        System.out.println(listFile);
//        String inputPath = "D:\\Truyện\\hoa son tai khoi\\401-600\\402.docx";
//        String outputPath = "D:\\Truyện\\hoa son tai khoi\\401-600\\402.pdf";
//        docxToPdf(inputPath, outputPath);
    }

    static void addTree(File file, Collection<File> all, Collection<String> listFile) {
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                all.add(child);
                addTree(child, all, listFile);
                if (child.getName().contains("docx")) {
                    listFile.add(child.getName());
                }
            }
        }
    }

    public static void docxToPdf(String docPath, String pdfPath) throws Exception {
        File inputWord = new File(docPath);
        File outputFile = new File(pdfPath);
        try {
            InputStream is = new FileInputStream(inputWord);
            OutputStream os = new FileOutputStream(outputFile);
            IConverter converter = LocalConverter.builder().build();
            converter.convert(is).as(DocumentType.DOCX).to(os).as(DocumentType.PDF).execute();
            os.close();
            is.close();
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
