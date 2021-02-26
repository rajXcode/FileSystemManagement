package com.technologi.techtest1.repository;/*
 * @created 22/02/2021 - 15:25
 * @project FileSystemManagement
 * @author rajender
 * rajender created on 22/02/2021 inside the package - com.technologi.techtest1.repository
 */

import com.technologi.techtest1.model.FileSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @FileSystemRepository repository having methods declared to perform transaction
 */

@Repository
public interface FileSystemRepository extends JpaRepository<FileSystem, Long> {
    @Query("SELECT f FROM FileSystem f WHERE f.relativepath LIKE %:relativepath%")
    List<FileSystem> getFilesOrFoldersByRelativePath(@Param("relativepath") String relativepath);
}
