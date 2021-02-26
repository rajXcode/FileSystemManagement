package com.technologi.techtest1.services;/*
 * @created 22/02/2021 - 14:07
 * @project FileSystemManagement
 * @author rajender
 * rajender created on 22/02/2021 inside the package - com.technologi.techtest1.services
 */

import com.technologi.techtest1.model.FileSystem;
import com.technologi.techtest1.repository.FileSystemRepository;
import com.technologi.techtest1.utils.OSUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * @FileSystemWatchService WatcherService to watch the changes on the file system and managing database transactions
 */

@Service
public class FileSystemWatchService implements Runnable {

    private final WatchService watcher;
    private final Map<WatchKey, Path> keys;
    private Path dir = null;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemWatchService.class);

    @Autowired
    FileSystemRepository repository;

    /**
     * Creates a WatchService
     */
    public FileSystemWatchService() throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey, Path>();
    }

    /**
     * Registering the given directory with the WatchService; This function will be called by FileVisitor
     */
    private void registerDirectory(Path dir) throws IOException {
        WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        keys.put(key, dir);
    }

    /**
     * Registering the given directory, and all its sub-directories, with the WatchService.
     */
    private void walkAndRegisterDirectories(final Path start) throws IOException {
        // registering directory and sub-directories
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                registerDirectory(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * Processing all events for keys queued to the watcher
     */
    public void processEvents() {
        for (; ; ) {

            // wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            Path dir = keys.get(key);
            if (dir == null) {
                System.err.println("WatchKey not recognized!!");
                continue;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                @SuppressWarnings("rawtypes")
                WatchEvent.Kind kind = event.kind();

                // Context for directory entry event is the file name of entry
                @SuppressWarnings("unchecked")
                Path name = ((WatchEvent<Path>) event).context();
                Path child = dir.resolve(name);

                // printing out event
                System.out.format("%s: %s\n", event.kind().name(), child);

                // if directory is created, and watching recursively, then register it and its sub-directories
                if (kind == ENTRY_CREATE && !name.toString().startsWith(".")) {
                    try {
                        if (Files.isDirectory(child)) {
                            walkAndRegisterDirectories(child);
                        }
                        repository.save(new FileSystem(name.toString(), child.toString()));
                    } catch (IOException x) {
                        x.printStackTrace();
                    }
                }
                if (kind == ENTRY_DELETE && !name.startsWith(".")) {
                    List<FileSystem> listOfFileAndFolders = new ArrayList<>();
                    listOfFileAndFolders = repository.getFilesOrFoldersByRelativePath(child.toString());

                    if (!listOfFileAndFolders.isEmpty())
                        repository.deleteAll(listOfFileAndFolders);
                }
            }

            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);
                // all directories are inaccessible
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
    }

    @Override
    public void run() {
        for (; ; ) {
            LOGGER.info("Watcher Service Running ...");
            try {
                Thread.currentThread().sleep(5000);
                switch (OSUtils.getOS()) {
                    case MAC:
                        dir = Paths.get(System.getProperty("user.home"), "Downloads");
                        break;
                    case WINDOWS:
                        dir = Paths.get("c:/");
                        break;
                    case LINUX:
                        dir = Paths.get("/tmp");
                        break;
                }
                readFileAndEntryInDB();
                walkAndRegisterDirectories(dir);
                processEvents();
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void readFileAndEntryInDB() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "src/main/resources/filesystem.txt"));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                String[] tokens = line.split("/");
                repository.save(new FileSystem(tokens[tokens.length - 1], System.getProperty("user.home") + line));
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
