package com.example.dcuniverse;

public class XMLitem {
    Long id;
    String title;
    String enclosure;
    String link;
    String Date;

    public XMLitem(Long id, String title, String enclosure, String link, String date) {
        this.id = id;
        this.title = title;
        this.enclosure = enclosure;
        this.link = link;
        Date = date;
    }

    public XMLitem(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
