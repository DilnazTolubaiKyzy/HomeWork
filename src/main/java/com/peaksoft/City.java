package com.peaksoft;

public class City {
    private int id;
    private String name;
    private Mer mer;



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

    public Mer getMer() {
        return mer;
    }

    public void setMer(Mer mer) {
        this.mer = mer;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mer=" + mer +
                '}';
    }
}
