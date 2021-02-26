package com.technologi.techtest1;

import com.technologi.techtest1.model.FileSystem;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class FileSystemManagementApplicationTest {

    private FileSystem fileSystem = null;
    private String testFileName = "test";

    @Before
    public void init() {
        fileSystem = new FileSystem();
        fileSystem.setName(testFileName);
    }

    /**
     * This method ensures the values set in filesystem is same getting with getter methods
     */
    @Test
    public void testGetter() {
        Assertions.assertEquals(fileSystem.getName(), testFileName);
    }

}

