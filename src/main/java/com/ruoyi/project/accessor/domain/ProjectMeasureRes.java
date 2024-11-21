package com.ruoyi.project.accessor.domain;

// DTO类用于封装返回的数据
public class ProjectMeasureRes {
    private Integer projectId;
    private String name;
    private int projectStatus;
    private Float DFP; // 调整后的功能点数

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(int projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Float getDFP() {
        return DFP;
    }

    public void setDFP(Float DFP) {
        this.DFP = DFP;
    }
// 省略构造函数、getter和setter方法
}