package com.ruoyi.project.system.domain;

public class CalculationRequest {
    private Long projectId;
    private Long stdId;

    // getter和setter方法
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getStdId() {
        return stdId;
    }

    public void setStdId(Long stdId) {
        this.stdId = stdId;
    }
}