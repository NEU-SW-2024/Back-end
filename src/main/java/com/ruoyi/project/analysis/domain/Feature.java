package com.ruoyi.project.analysis.domain;
public class Feature {
    private String feat_name; // 功能点名称
    private String comment; // 功能点描述

    public String getFeat_name() {
        return feat_name;
    }

    public void setFeat_name(String feat_name) {
        this.feat_name = feat_name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}