package com.example.myfpl.models;

import com.example.myfpl.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CommunityModel implements Serializable {
    private int image;
    private String name;
    private String url;

    public CommunityModel() {
    }

    public CommunityModel(int image, String name, String url) {
        this.image = image;
        this.name = name;
        this.url = url;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public static List<CommunityModel> getData(){
        List<CommunityModel> list = new ArrayList<>();
        list.add(new CommunityModel(R.drawable.img_coderpoly, "Coder Poly", "https://www.facebook.com/coderpoly"));
        list.add(new CommunityModel(R.drawable.img_footballclub, "Football Club", "https://www.facebook.com/fpolyfootball"));
        list.add(new CommunityModel(R.drawable.img_bemindclb, "CLB Bemind", "https://www.facebook.com/Bemindclub"));
        list.add(new CommunityModel(R.drawable.img_mediapoly, "Fpoly Media", "https://www.facebook.com/fpolymedia"));
        list.add(new CommunityModel(R.drawable.img_polymodel, "FPoly Model", "https://www.facebook.com/clbmodelfpolyhcm"));
        list.add(new CommunityModel(R.drawable.img_fpolyartist, "CLB Nghệ thuật", "https://www.facebook.com/CLB.NGHE.THUAT.FPOLY"));
        list.add(new CommunityModel(R.drawable.img_fplcommunity, "CĐ Sinh viên", "https://www.facebook.com/groups/congdongsinhvienfpoly"));
        list.add(new CommunityModel(R.drawable.img_fanpage, "Fanpage Poly", "https://www.facebook.com/caodang.fptpoly"));
        return list;
    }
}
