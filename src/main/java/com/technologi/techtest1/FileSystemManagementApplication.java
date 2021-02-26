package com.technologi.techtest1;

import com.technologi.techtest1.services.FileSystemWatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileSystemManagementApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemManagementApplication.class);

    @Autowired
    public FileSystemWatchService watcherService;

    public static void main(String[] args) {
        SpringApplication.run(FileSystemManagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("I'm running !");

        new Thread(watcherService, "watcher-service").start();
    }
}

