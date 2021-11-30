package com.programing.cprograming.Model;

public class CodeModel {

    public String getCodetitle() {
        return codetitle;
    }

    public void setCodetitle(String codetitle) {
        this.codetitle = codetitle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getCodePic() {
        return codePic;
    }

    public void setCodePic(String codePic) {
        this.codePic = codePic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String codetitle;
    private String code;

    public CodeModel() {
    }

    private String output;

    public String getOutputPic() {
        return outputPic;
    }

    public void setOutputPic(String outputPic) {
        this.outputPic = outputPic;
    }

    public String getYoutuvelink() {
        return youtuvelink;
    }

    public void setYoutuvelink(String youtuvelink) {
        this.youtuvelink = youtuvelink;
    }

    public CodeModel(String codetitle, String code, String output, String codePic, String description, String outputPic, String youtuvelink) {
        this.codetitle = codetitle;
        this.code = code;
        this.output = output;
        this.codePic = codePic;
        this.description = description;
        this.outputPic = outputPic;
        this.youtuvelink = youtuvelink;
    }

    private String codePic;
    private String description;



    private String youtuvelink;

    private String outputPic;

}
