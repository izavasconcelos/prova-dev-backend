package com.izavasconcelos.desafio.analytics.app;

import com.izavasconcelos.desafio.analytics.annotation.AppConfig;
import com.izavasconcelos.desafio.analytics.controller.DataController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

public class Main {
    private static final String LOCAL_PATH = "src/main/java/com/izavasconcelos/desafio/analytics/data/in/";

    public static void main(String[] args) throws IOException {
        execute();
    }

    public static void execute() throws IOException {

        ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
        DataController dataController = (DataController) appContext.getBean("dataController");

        Path pastaOrigem = Files.createDirectories(Paths.get(LOCAL_PATH));

        WatchService watcher = FileSystems.getDefault().newWatchService();

        pastaOrigem.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        dataController.extractInfoDataFile();
        dataController.writeDataFile();

        while (true) {
            WatchKey wk;
            try {
                System.out.printf("Aguardando arquivos em %s", pastaOrigem);
                wk = watcher.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (WatchEvent<?> event : wk.pollEvents()) {

                if (event.kind() == OVERFLOW) continue;

                //pega nome do arquivo criado
                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path nomeArquivo = ev.context();
                Path arquivoOrigem = pastaOrigem.resolve(nomeArquivo);
                System.out.printf("Arquivo criado: %s\n", arquivoOrigem);

                dataController.extractInfoDataFile();
                dataController.writeDataFile();
            }

            if (!wk.reset()) {
                break;
            }
        }

    }
}
