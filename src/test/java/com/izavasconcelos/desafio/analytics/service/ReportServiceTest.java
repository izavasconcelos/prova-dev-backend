package com.izavasconcelos.desafio.analytics.service;

import com.izavasconcelos.desafio.analytics.annotation.AppConfig;
import com.izavasconcelos.desafio.analytics.controller.DataController;
import com.izavasconcelos.desafio.analytics.dao.SalesDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class ReportServiceTest {
    @Autowired
    private ReportService reportService;
    @Autowired
    private DataController dataController;

    private List<String> file = new ArrayList<>();
    @Before
    public void initializer() {
        file.add("001ç1234567891234çDiegoç50000");
        file.add("003ç19ç[1-34-100,2-33-1.50,3-40-0.10]çIza");
        file.add("003ç07ç[1-34-10,2-33-0.50,3-40-0.10]çJoao");
        file.add("002ç2345675433444345çEduardoPereiraçRural");
        file.add("002ç2345775432444345çMariaPereiraçRural");
        file.add("001ç1239567891234çMarinaç30000");

        for (String s:file) {
            dataController.getLayout(s);
        }
    }

    @Test
    public void getAmountClientsTest() {
        int clients = reportService.amountOfCustomers();
        Assert.assertEquals(2, clients);
    }

    @Test
    public void getAmountSalesmanTest() {
        int salesman = reportService.amountOfSalesman();
        Assert.assertEquals(2, salesman);
    }

    @Test
    public void expensiveSaleIdTest() {
        String id = reportService.expensiveSaleId();
        Assert.assertEquals("19", id);
    }

    @Test
    public void worstSalesmanTest() {
        String name = reportService.getWorstSalesman();
        Assert.assertEquals("Joao", name);
    }

    @Test
    public void createReportTest() {
        String report = reportService.reportDataAnalysis();
        Assert.assertEquals("Amount Clients:2, Amount Salesman:2, Id Most Expensive Sale:19, Worst Salesman:Joao", report);
    }

    @Test
    public void createEmptyReportTest() {
        dataController.executeDataAnalysis();
        String report = reportService.reportDataAnalysis();
        Assert.assertEquals("Amount Clients:0, Amount Salesman:0, Id Most Expensive Sale:none, Worst Salesman:none", report);
    }

}
