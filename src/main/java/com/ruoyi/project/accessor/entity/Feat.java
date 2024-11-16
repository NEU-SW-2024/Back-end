package com.ruoyi.project.accessor.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_feat")
public class Feat {
    @TableField("project_id")
    private Integer projectId;
    @TableField("feat_name")
    private String featName;
    @TableField("feat_descr")
    private String featDescr;
    @TableField("score_id")
    private Integer scoreId;
}
