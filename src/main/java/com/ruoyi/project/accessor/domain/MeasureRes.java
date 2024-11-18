package com.ruoyi.project.accessor.domain;

import lombok.Data;

@Data
public class MeasureRes {
    private Integer projectId;
    private Integer UPF;
    private Float VAF;
    private Float AFP;
    private Integer GSCTotal;
    /**
     * 项目状态：0待评估1待审核2完成'
     */
    private Integer status;
    private Float cf;
}
