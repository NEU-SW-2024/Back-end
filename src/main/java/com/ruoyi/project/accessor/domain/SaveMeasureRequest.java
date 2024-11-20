package com.ruoyi.project.accessor.domain;

import lombok.Data;

import java.util.List;

@Data
public class SaveMeasureRequest {
    /**
     * 度量列表
     */
    private List<Measure> measures;
}
