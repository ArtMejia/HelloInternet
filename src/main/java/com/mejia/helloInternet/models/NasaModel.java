package com.mejia.helloInternet.models;

import com.fasterxml.jackson.annotation.JsonInclude;

public class NasaModel {

    private String date;
    private String title;
    private String explanation;
    private String media_type;
    private String service_version;
    private String url;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String hdurl;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String copyright;

//    @JsonInclude(JsonInclude.Include.NON_DEFAULT) to not include primitive data types
//    private int apodNumber;



    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getMedia_type() {
        return media_type;
    }

    public String getService_version() {
        return service_version;
    }

    public String getUrl() {
        return url;
    }

    public String getHdurl() {
        return hdurl;
    }

    public String getCopyright() {
        return copyright;
    }
}