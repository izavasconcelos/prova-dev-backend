package com.izavasconcelos.desafio.analytics.app;

import com.izavasconcelos.desafio.analytics.annotation.AppConfig;
import com.izavasconcelos.desafio.analytics.controller.DataController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import static java.nio.file.StandardWatchEventKinds.*;

public class Main {
    private static final String LOCAL_PATH = "data/in/";

    public static void main(String[] args) throws IOException {
        watcherFileSystems();
    }

    public static void watcherFileSystems() throws IOException {
        ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
        DataController dataController = (DataController) appContext.getBean("dataController");
        Logger logger = LoggerFactory.getLogger(Main.class);

        WatchService watcher = FileSystems.getDefault().newWatchService();

        Path localPath = Files.createDirectories(Paths.get(LOCAL_PATH));
        localPath.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        dataController.executeDataAnalysis();

        while (true) {
            WatchKey wk;
            try {
                wk = watcher.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (WatchEvent<?> event : wk.pollEvents()) {
                if (event.kind() == OVERFLOW) continue;
                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path file = ev.context();
                Path localFile = localPath.resolve(file);
                logger.info("File create, modify or delete: " + localFile);

                dataController.executeDataAnalysis();
            }

            if (!wk.reset()) {
                break;
            }
        }

    }
}
