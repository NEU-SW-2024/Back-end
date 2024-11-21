package com.ruoyi.project.system.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 评估结果表 assessment_results 的实体类
 *
 * @author ruoyi
 */
public class SysAssessmentResults extends BaseEntity
{
    private static final long serialVersionUID = 2L;

    /** 评估结果ID */
    private Long resId;

    /** 关联项目的ID，标识当前评估结果所属的项目 */
    private Long projectId;

    /** 关联评估标准的ID，标识当前评估结果所属的标准 */
    private Long stdId;

    /** 项目的开发服务费用 */
    private BigDecimal projectSDC;

    /** 项目调整后的开发服务费用 */
    private BigDecimal projectESDC;

    /** 评估结果记录的创建时间 */
    private Date createdAt;

    /** 最近一次更新评估结果的时间 */
    private Date updatedAt;


    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getStdId() {
        return stdId;
    }

    public void setStdId(Long stdId) {
        this.stdId = stdId;
    }

    public BigDecimal getProjectSDC() {
        return projectSDC;
    }

    public void setProjectSDC(BigDecimal projectSDC) {
        this.projectSDC = projectSDC;
    }

    public BigDecimal getProjectESDC() {
        return projectESDC;
    }

    public void setProjectESDC(BigDecimal projectESDC) {
        this.projectESDC = projectESDC;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("resId", getResId())
                .append("projectId", getProjectId())
                .append("stdId", getStdId())
                .append("projectSDC", getProjectSDC())
                .append("projectESDC", getProjectESDC())
                .append("createdAt", getCreatedAt())
                .append("updatedAt", getUpdatedAt())
                .toString();
    }
}