package com.auk.sdt100.files.demo.model;

import java.io.Serializable;
import java.util.Objects;

public class DemoObject implements Serializable {

    private int id;
    private String name;

    public DemoObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public DemoObject() {}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DemoObject that)) return false;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "DemoObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
