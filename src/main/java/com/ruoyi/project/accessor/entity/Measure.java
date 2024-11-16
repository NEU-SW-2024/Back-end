package com.ruoyi.project.accessor.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_measure")
public class Measure {
    @TableField("project_id")
    private Integer projectId;
    @TableField("measure_name")
    private String measureName;
    @TableField("GSC")
    private Integer GSC;
}
