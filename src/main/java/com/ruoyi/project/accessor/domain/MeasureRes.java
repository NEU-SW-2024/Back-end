package com.ruoyi.project.accessor.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class MeasureRes {
    /*项目ID*/
    private Integer projectId;
    /*功能点分数总和*/
    private Integer UPF;
    /*调整系数*/
    private Float VAF;
    /*调整后的功能点数*/
    private Float DFP;
    /*GSC总和*/
    private Integer GSC;
    /**
     * 项目状态：0待评估1待审核2完成3待计算'
     */
    @Getter
    private Integer status;
    private Float cf;
    private Float S;
}
