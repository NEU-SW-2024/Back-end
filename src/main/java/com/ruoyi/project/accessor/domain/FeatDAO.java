package com.ruoyi.project.accessor.domain;

import lombok.Data;

/**
 * 录入功能点 页面二：步骤条格式1，2 前端传过来的数据
 */
@Data
public class FeatDAO {
    // 项目ID
    private Integer projectId;
    // 功能点名称
    public String funcName;
    // 功能点描述
    public String funcDescr;
    // 分类标签
    public String tag;
    // 复杂度
    public Integer diff;
}
