package com.ruoyi.project.accessor.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_feat_score")
public class FeatScore {
    @TableId(type = IdType.AUTO)
    private Integer scoreId;
    @TableField("feat_tag")
    private String featTag;
    @TableField("feat_diff")
    private String featDiff;
    @TableField("score")
    private Integer score;
}