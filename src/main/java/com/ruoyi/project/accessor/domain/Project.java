package com.ruoyi.project.accessor.domain;

import lombok.Data;

@Data
public class Project {
    //项目ID
    private Integer projectId;
    // 租户ID
    private Integer tenantId;
    // 项目名称
    private String name;
    //项目描述
    private String description;
    // 项目内容
    private String projectContent;
    // 评估师ID
    private Integer accessorId;
    // 审核师ID
    private Integer auditorId;
    // 项目状态
    private String projectStatus;
    // 项目预计持续时间
    private Integer estimatedTime;
    // 备注
    private String remark;
}
