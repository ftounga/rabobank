package com.example.rabobank.enumeration;

public enum RecordImportStatus {

    SUCCEED("SUCCEED"),
    FAILED("FAILED"),
    PENDING("PENDING");

    private String value;

    RecordImportStatus(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    public static RecordImportStatus fromValue(String status) {
        switch (status) {
            case "SUCCEED":
                return SUCCEED;
            case "FAILED":
                return FAILED;
            case "PENDING":
                return PENDING;
            default:
                return null;
        }
    }
}
