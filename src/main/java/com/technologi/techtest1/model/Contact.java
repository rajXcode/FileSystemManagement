package com.technologi.techtest1.model;/*
 * @created 22/02/2021 - 14:53
 * @project FileSystemManagement
 * @author rajender
 * rajender created on 22/02/2021 inside the package - com.technologi.techtest1.model
 */

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Contact {

    @NotEmpty(message = "Search field can not be empty")
    private String search;

    public Contact() {

    }

    public Contact(@NotEmpty(message = "Search field can not be empty") String search) {
        this.search = search;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @Override
    public String toString() {
        return "Contact [search=" + search + "]";
    }

}