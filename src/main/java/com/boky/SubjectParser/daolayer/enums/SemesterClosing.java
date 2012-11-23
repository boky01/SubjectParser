package com.boky.SubjectParser.daolayer.enums;

public enum SemesterClosing {
    FELEVKOZI("Félévközi"), VIZSGA("Vizsga"), ALAIRAS("Aláírás"), UNKNOW("Ismeretlen");

    private String name;

    private SemesterClosing(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
