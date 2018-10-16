package com.github.victormun.petmanager.database;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "pet")
public class PetEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String breed;
    private String type;
    private String url;
    private Date birthdate;

    @Ignore
    public PetEntry(String name, String breed, String type, String url, Date birthdate) {
        this.name = name;
        this.breed = breed;
        this.type = type;
        this.url = url;
        this.birthdate = birthdate;
    }

    public PetEntry(int id, String name, String breed, String type, String url, Date birthdate) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.type = type;
        this.url = url;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
