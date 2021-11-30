package com.programing.cprograming.Model;

public class SocialModel {

    private String uName;
    private String uPic;
    private String postPic;
    private String muid;


    public String getMuid() {
        return muid;
    }

    public void setMuid(String muid) {
        this.muid = muid;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPic() {
        return uPic;
    }

    public void setuPic(String uPic) {
        this.uPic = uPic;
    }

    public String getPostPic() {
        return postPic;
    }

    public void setPostPic(String postPic) {
        this.postPic = postPic;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public SocialModel() {
    }

    public SocialModel(String uName, String uPic, String postPic, String postText,String muid) {
        this.uName = uName;
        this.uPic = uPic;
        this.postPic = postPic;
        this.postText = postText;
        this.muid = muid;
    }

    private String postText;

}
