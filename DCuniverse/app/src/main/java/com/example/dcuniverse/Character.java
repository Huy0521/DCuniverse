package com.example.dcuniverse;

import java.io.Serializable;
import java.util.ArrayList;

public class Character implements Serializable {
    String Avatar;
    String name;
    String side;
    String power;
    ArrayList<String> comicin;
    String soP;
    String skill;
    String detail;
    int top;



    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public Character() {
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getComicin() {
        return comicin;
    }

    public void setComicin(ArrayList<String> comicin) {
        this.comicin = comicin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getSoP() {
        return soP;
    }

    public void setSoP(String soP) {
        this.soP = soP;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    @Override
    public String toString() {
        return "Character{" +
                "Avatar='" + Avatar + '\'' +
                ", name='" + name + '\'' +
                ", side='" + side + '\'' +
                ", power=" + power +
                ", comicin=" + comicin +
                ", soP='" + soP + '\'' +
                ", skill='" + skill + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
