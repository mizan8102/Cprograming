package com.programing.cprograming.Model;

public class ProblemModel {
    private  String data;

    public String getDataTitle() {
        return dataTitle;
    }

    public void setDataTitle(String dataTitle) {
        this.dataTitle = dataTitle;
    }

    private String dataTitle;
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ProblemModel(String data, String dataTitle) {
        this.data = data;
        this.dataTitle=dataTitle;
    }

    public ProblemModel() {
    }


}
