package org.project.entity;

public class DadosTable {

    private int id;
    private String dado1;

    public DadosTable() {}

    public DadosTable(String dado1) {
        this.dado1 = dado1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDado1() {
        return dado1;
    }

    public void setDado1(String dado1) {
        this.dado1 = dado1;
    }
}
