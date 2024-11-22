package com.ruoyi.project.analysis.domain;

// 内部类：度量表
public class FinalMeasure {
    private String measure_name; // 度量名称
    private int DI; // 度量分数（GSC）

    public String getMeasure_name() {
        return measure_name;
    }

    public void setMeasure_name(String measure_name) {
        this.measure_name = measure_name;
    }

    public int getDI() {
        return DI;
    }

    public void setGsc(int DI) {
        this.DI = DI;
    }
}