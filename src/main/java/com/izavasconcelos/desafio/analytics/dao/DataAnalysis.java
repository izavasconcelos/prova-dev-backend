package com.izavasconcelos.desafio.analytics.dao;

import com.izavasconcelos.desafio.analytics.controller.AnalyticsController;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class DataAnalysis {
    private final static String FILE_OUT_PATH = "src/main/java/com/izavasconcelos/desafio/analytics/data/out/";
    private final static String FILE_OUT_NAME = "analysis.done.dat";
    private final static String FILE_IN_PATH = "src/main/java/com/izavasconcelos/desafio/analytics/data/in/";
    private final static String FILE_IN_NAME = "analysis.done.dat";


    private AnalyticsController analyticsController;

    public DataAnalysis() {
        this.analyticsController = new AnalyticsController();
    }

    public boolean write() {
        try {
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(FILE_OUT_PATH + FILE_OUT_NAME));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            outputStreamWriter.write("003");
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
                System.out.println("sou um dir");
                for(File file : dir.listFiles()){
                    FileInputStream fileInputStream = new FileInputStream(file);
                    DataInputStream dataInputStream = new DataInputStream(fileInputStream);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));

                    String strLine;

                    while((strLine = bufferedReader.readLine()) != null) {
                        analyticsController.getLayout(strLine);
                        System.out.println(strLine);
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

    public void create() {

    }
}
