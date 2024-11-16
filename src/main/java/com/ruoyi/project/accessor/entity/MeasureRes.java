package com.ruoyi.project.accessor.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_measure_res")
public class MeasureRes {
    @TableField("project_id")
    private Integer projectId;
    @TableField("UPF")
    private Integer UPF;
    @TableField("VAF")
    private Float VAF;
    @TableField("AFP")
    private Float AFP;
    @TableField("GSC_total")
    private Integer GSCTotal;
    /**
     * 项目状态：0待评估1待审核2完成'
     */
    @TableField("status")
    private Integer status;
}
