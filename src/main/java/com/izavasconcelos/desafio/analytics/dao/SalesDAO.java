package com.izavasconcelos.desafio.analytics.dao;

import com.izavasconcelos.desafio.analytics.controller.DataController;
import com.izavasconcelos.desafio.analytics.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class SalesDAO {
    private final static String OUTPUT_PATH = "data/out/";
    private final static String FILE_OUTPUT_NAME = "report.done.dat";
    private final static String INPUT_PATH = "data/in/";
    public final static Logger logger = LoggerFactory.getLogger(SalesDAO.class);
    private List<String> dataList;

    @Autowired
    private DataController dataController;
    @Autowired
    private ReportService reportService;

    public SalesDAO() {
        this.dataList = new ArrayList<>();
    }

    public void writeDataAnalysisOutputFile() {
        try {
            File directory = new File(OUTPUT_PATH);
            if(!directory.isDirectory()) {
                directory.mkdirs();
            }
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(OUTPUT_PATH + FILE_OUTPUT_NAME));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            outputStreamWriter.write(reportService.reportDataAnalysis());
            outputStreamWriter.close();

        } catch (IOException e) {
            logger.error("Error output", e);
        }
    }

    public List<String> getFileList() {
        try {
            File directory = new File(INPUT_PATH);
            if(!directory.isDirectory()) {
                directory.mkdirs();
            }
            List<File> allFiles = Files.find(Paths.get(INPUT_PATH), 10000,
                    (p, a) -> p.toString().toLowerCase().endsWith(".dat"))
                    .map(Path::toFile)
                    .collect(Collectors.toList());

                for(File file : allFiles){
                    FileInputStream fileInputStream = new FileInputStream(file);
                    DataInputStream dataInputStream = new DataInputStream(fileInputStream);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
                    String strLine;
                    while((strLine = bufferedReader.readLine()) != null) {
                        dataList.add(strLine);
                    }
                    dataInputStream.close();
                }

            return dataList;

        } catch (IOException e) {
            logger.error("error input", e);
            return Collections.emptyList();
        }
    }

}
