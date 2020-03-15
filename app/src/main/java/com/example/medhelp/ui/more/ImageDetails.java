package com.example.medhelp.ui.more;

public class ImageDetails {
    private String imgDesciption;
    private String imgUrl;
public ImageDetails(){}

    public ImageDetails(String imgDesciption, String imgUrl) {
    if(imgDesciption.trim().equals("")){
        imgDesciption="No Description";
    }
        this.imgDesciption = imgDesciption;
        this.imgUrl = imgUrl;
    }

    public String getImgDesciption() {
        return imgDesciption;
    }

    public void setImgDesciption(String imgDesciption) {
        this.imgDesciption = imgDesciption;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
