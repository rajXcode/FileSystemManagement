package com.technologi.techtest1.model;/*
 * @created 22/02/2021 - 14:53
 * @project FileSystemManagement
 * @author rajender
 * rajender created on 22/02/2021 inside the package - com.technologi.techtest1.model
 */

import javax.persistence.*;


@Entity
@Table(name = "filesystem")
public class FileSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "relativepath")
    private String relativepath;

    public FileSystem() {
    }

    public FileSystem(String name, String relativepath) {
        this.name = name;
        this.relativepath = relativepath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelativepath() {
        return relativepath;
    }

    public void setRelativepath(String relativepath) {
        this.relativepath = relativepath;
    }

    @Override
    public String toString() {
        return "FileSystem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", relativepath='" + relativepath + '\'' +
                '}';
    }
}