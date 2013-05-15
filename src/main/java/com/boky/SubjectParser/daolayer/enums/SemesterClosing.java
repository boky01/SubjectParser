package com.boky.SubjectParser.daolayer.enums;

public enum SemesterClosing {
    FELEVKOZI("Félévközi"), VIZSGA("Vizsga"), ALAIRAS("Aláírás"), UNKNOW("Ismeretlen");

    private String value;

    private SemesterClosing(String name) {
        this.value = name;
    }

    public String getValue() {
        return value;
    }

}
