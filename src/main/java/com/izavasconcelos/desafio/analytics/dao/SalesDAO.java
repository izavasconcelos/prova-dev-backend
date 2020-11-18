package com.izavasconcelos.desafio.analytics.dao;

import com.izavasconcelos.desafio.analytics.service.AnalyticsService;

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
import java.util.Map;

public class SalesDAO {
    private final static String FILE_OUT_PATH = "src/main/java/com/izavasconcelos/desafio/analytics/data/out/";
    private final static String FILE_OUT_NAME = "analysis.done.dat";
    private final static String FILE_IN_PATH = "src/main/java/com/izavasconcelos/desafio/analytics/data/in/";
    private final static String FILE_IN_NAME = "analysis.done.dat";


    private AnalyticsService analyticsService;

    public SalesDAO() {
        this.analyticsService = new AnalyticsService();
    }

    public boolean write() {
        try {
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(FILE_OUT_PATH + FILE_OUT_NAME));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            outputStreamWriter.write(""+totalCustomers()+","+totalSalesman()+","+expensiveSale());
            outputStreamWriter.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean read() {
        try {
            File dir = new File(FILE_IN_PATH);
            if(dir.isDirectory()){
                for(File file : dir.listFiles()){
                    FileInputStream fileInputStream = new FileInputStream(file);
                    DataInputStream dataInputStream = new DataInputStream(fileInputStream);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));

                    String strLine;

                    while((strLine = bufferedReader.readLine()) != null) {
                        analyticsService.getLayout(strLine);
                    }
                    dataInputStream.close();
                }
            }
            return true;

        } catch (IOException e) {
            System.out.println("exception");
            return false;
        }
    }

    public int totalCustomers() {
        return analyticsService.getCountCustomers();
    }

    public int totalSalesman() {
        return analyticsService.getCountSalesman();
    }

    public Map<String, Double> expensiveSale() {
        return analyticsService.getExpensiveSaleId();
    }
}
