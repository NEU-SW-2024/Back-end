package com.ruoyi.project.system.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.xss.Xss;
import com.ruoyi.framework.web.domain.BaseEntity;


public class SysProject extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /** 项目ID */
    private Long projectId;

    /** 租户ID */
    private Long tenantId;

    /** 项目名称 */
    private String name;

    /** 项目描述 */
    private String description;

    /** 项目内容 */
    private String projectContent;

    /** 评估师ID */
    private Long accessorId;

    /** 审核师ID */
    private Long auditorId;

    /** 项目状态 */
    private String projectStatus;

    /** 项目预计持续时间 */
    private Long estimatedTime;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectContent() {
        return projectContent;
    }

    public void setProjectContent(String projectContent) {
        this.projectContent = projectContent;
    }

    public Long getAccessorId() {
        return accessorId;
    }

    public void setAccessorId(Long accessorId) {
        this.accessorId = accessorId;
    }

    public Long getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Long auditorId) {
        this.auditorId = auditorId;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Long getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Long estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("projectId", getProjectId())
            .append("tenantId", getTenantId())
            .append("name", getName())
            .append("description", getDescription())
            .append("projectContent", getProjectContent())
            .append("accessorId", getAccessorId())
            .append("auditorId", getAuditorId())
            .append("projectStatus", getProjectStatus())
            .append("estimatedTime", getEstimatedTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
