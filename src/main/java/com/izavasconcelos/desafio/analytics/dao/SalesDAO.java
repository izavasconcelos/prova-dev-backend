package com.izavasconcelos.desafio.analytics.dao;

import com.izavasconcelos.desafio.analytics.controller.DataController;
import com.izavasconcelos.desafio.analytics.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SalesDAO {
    private final static String FILE_OUT_PATH = "src/main/java/com/izavasconcelos/desafio/analytics/data/out/";
    private final static String FILE_OUT_NAME = "analysis.done.dat";
    private final static String FILE_IN_PATH = "src/main/java/com/izavasconcelos/desafio/analytics/data/in/";
    private final static String FILE_IN_NAME = "analysis.done.dat";

    private List<String> dataList;

    @Autowired
    private DataController dataController;
    @Autowired
    private ReportService reportService;

    public SalesDAO() {
        this.dataList = new ArrayList<>();
    }

    public boolean writeDataAnalysisOutputFile() {
        try {
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(FILE_OUT_PATH + FILE_OUT_NAME));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            outputStreamWriter.write(reportService.reportDataAnalysis());
            outputStreamWriter.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public List<String> getDataList() {

        try {
            File dir = new File(FILE_IN_PATH);
            if(dir.isDirectory()){
                for(File file : dir.listFiles()){
                    FileInputStream fileInputStream = new FileInputStream(file);
                    DataInputStream dataInputStream = new DataInputStream(fileInputStream);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));

                    String strLine;

                    while((strLine = bufferedReader.readLine()) != null) {
                        dataList.add(strLine);
                    }
                    dataInputStream.close();
                }
            }
            return dataList;

        } catch (IOException e) {
            System.out.println("exception");
            return Collections.emptyList();
        }
    }

}
