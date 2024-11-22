package com.ruoyi.project.system.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 评估细节结果表 assessment_detail_results 的实体类
 *
 * @author ruoyi
 */
public class SysAssessmentDetailResults extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 评估结果ID */
    private Long resId;

    /** 关联项目的ID，标识当前评估结果所属的项目 */
    private Long projectId;

    /** 关联评估标准的ID，标识当前评估结果所属的标准 */
    private Long stdId;

    /** 项目评估的总造价，包含人工成本、风险成本、质量成本等 */
    private BigDecimal totalCost;

    /** 人工成本，用于项目开发的直接人力费用 */
    private BigDecimal laborCost;

    /** 风险附加成本，根据风险因子计算得出的额外成本 */
    private BigDecimal riskCost;

    /** 质量附加成本，根据质量因子计算得出的额外成本 */
    private BigDecimal qualityCost;

    /** 开发服务费用，开发工具和外包服务相关的直接费用 */
    private BigDecimal devServiceCost;

    /** 调整后开发服务费用，考虑优化或额外因素调整的开发费用 */
    private BigDecimal adjustedDevServiceCost;

    /** 评估结果的建议说明，例如优化成本或资源分配的建议 */
    private String resSugg;

    /** 评估结果记录的创建时间 */
    private Date createdAt;

    /** 最近一次更新评估结果的时间 */
    private Date updatedAt;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("resId", getResId())
                .append("projectId", getProjectId())
                .append("stdId", getStdId())
                .append("totalCost", getTotalCost())
                .append("laborCost", getLaborCost())
                .append("riskCost", getRiskCost())
                .append("qualityCost", getQualityCost())
                .append("devServiceCost", getDevServiceCost())
                .append("adjustedDevServiceCost", getAdjustedDevServiceCost())
                .append("resSugg", getResSugg())
                .append("createdAt", getCreatedAt())
                .append("updatedAt", getUpdatedAt())
                .toString();
    }

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

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getRiskCost() {
        return riskCost;
    }

    public void setRiskCost(BigDecimal riskCost) {
        this.riskCost = riskCost;
    }

    public BigDecimal getQualityCost() {
        return qualityCost;
    }

    public void setQualityCost(BigDecimal qualityCost) {
        this.qualityCost = qualityCost;
    }

    public BigDecimal getDevServiceCost() {
        return devServiceCost;
    }

    public void setDevServiceCost(BigDecimal devServiceCost) {
        this.devServiceCost = devServiceCost;
    }

    public BigDecimal getAdjustedDevServiceCost() {
        return adjustedDevServiceCost;
    }

    public void setAdjustedDevServiceCost(BigDecimal adjustedDevServiceCost) {
        this.adjustedDevServiceCost = adjustedDevServiceCost;
    }

    public String getResSugg() {
        return resSugg;
    }

    public void setResSugg(String resSugg) {
        this.resSugg = resSugg;
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
}