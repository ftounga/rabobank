package com.example.rabobank.enumeration;

public enum FileExtension {

    XML("xml"),
    CSV("csv"),
    UNKNOWN("unknown");

    private String extension;

    FileExtension(String extension){
        this.extension = extension;
    }

    public String getExtension(){
        return this.extension;
    }

    public static FileExtension fromExtension(String extension){

        switch (extension){
            case "xml":
                return XML;
            case "csv":
                return CSV;
            default:
                return UNKNOWN;
        }

    }
}
