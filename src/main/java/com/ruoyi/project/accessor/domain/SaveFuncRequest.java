package com.ruoyi.project.accessor.domain;

import lombok.Data;

import java.util.List;

@Data
public class SaveFuncRequest {
    /**
     * 录入功能列表
     */
    private List<FeatDAO> featDAOS;
}