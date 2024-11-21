package com.ruoyi.project.system.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.DecimalMin;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 造价标准表 assessment_standard
 *
 * @author ruoyi
 */
public class SysAssessmentStd extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 标准ID */
    private Long stdId;

    /** 标准名称 */
    private String stdName;

    /** 标准类型 */
    private String stdType;

    /** 标准状态（1启用 0停用） */
    private Boolean stdStatus;

    /** 标准PDR取值 */
    private Double pdrValue;

    /** 风险因子 */
    private Double rskFactor;

    /** 质量因子 */
    private Double qualityFactor;

    /** 软件复杂度因子 */
    private Double swf;

    /** 开发复杂度因子 */
    private Double rdf;

    /** 人月折算系数 */
    private Double conversionFactor;

    /** 软件开发基准人月费率 **/
    private double costRate;

    /** 非人力成本 */
    private Double dnc;

    public Long getStdId()
    {
        return stdId;
    }

    public void setStdId(Long stdId)
    {
        this.stdId = stdId;
    }

    @NotBlank(message = "标准名称不能为空")
    @Size(min = 0, max = 100, message = "标准名称长度不能超过100个字符")
    public String getStdName()
    {
        return stdName;
    }

    public void setStdName(String stdName)
    {
        this.stdName = stdName;
    }

    @NotBlank(message = "标准类型不能为空")
    @Size(min = 0, max = 50, message = "标准类型长度不能超过50个字符")
    public String getStdType()
    {
        return stdType;
    }

    public void setStdType(String stdType)
    {
        this.stdType = stdType;
    }

    public Boolean getStdStatus()
    {
        return stdStatus;
    }

    public void setStdStatus(Boolean stdStatus)
    {
        this.stdStatus = stdStatus;
    }

    @NotNull(message = "PDR取值不能为空")
    @DecimalMin(value = "0.0", message = "PDR取值必须大于0")
    public Double getPdrValue()
    {
        return pdrValue;
    }

    public void setPdrValue(Double pdrValue)
    {
        this.pdrValue = pdrValue;
    }

    @NotNull(message = "风险因子不能为空")
    @DecimalMin(value = "1.0", message = "风险因子必须大于等于1")
    public Double getRskFactor()
    {
        return rskFactor;
    }

    public void setRskFactor(Double rskFactor)
    {
        this.rskFactor = rskFactor;
    }

    @NotNull(message = "质量因子不能为空")
    @DecimalMin(value = "1.0", message = "质量因子必须大于等于1")
    public Double getQualityFactor()
    {
        return qualityFactor;
    }

    public void setQualityFactor(Double qualityFactor)
    {
        this.qualityFactor = qualityFactor;
    }

    @NotNull(message = "软件复杂度因子不能为空")
    @DecimalMin(value = "1.0", message = "软件复杂度因子必须大于等于1")
    public Double getSwf()
    {
        return swf;
    }

    public void setSwf(Double swf)
    {
        this.swf = swf;
    }

    @NotNull(message = "开发复杂度因子不能为空")
    @DecimalMin(value = "1.0", message = "开发复杂度因子必须大于等于1")
    public Double getRdf()
    {
        return rdf;
    }

    public void setRdf(Double rdf)
    {
        this.rdf = rdf;
    }

    @NotNull(message = "人月折算系数不能为空")
    @DecimalMin(value = "0.0", message = "人月折算系数必须大于0")
    public Double getConversionFactor()
    {
        return conversionFactor;
    }

    public void setConversionFactor(Double conversionFactor)
    {
        this.conversionFactor = conversionFactor;
    }

    @NotNull(message = "软件开发基准人月费率不能为空")
    @DecimalMin(value = "0.0", message = "软件开发基准人月费率必须大于0")
    public double getCostRate() {
        return costRate;
    }

    public void setCostRate(double costRate) {
        this.costRate = costRate;
    }

    @NotNull(message = "非人力成本不能为空")
    @DecimalMin(value = "0.0", message = "非人力成本必须大于等于0")
    public Double getDnc()
    {
        return dnc;
    }

    public void setDnc(Double dnc)
    {
        this.dnc = dnc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("stdId", getStdId())
                .append("stdName", getStdName())
                .append("stdType", getStdType())
                .append("stdStatus", getStdStatus())
                .append("pdrValue", getPdrValue())
                .append("rskFactor", getRskFactor())
                .append("qualityFactor", getQualityFactor())
                .append("swf", getSwf())
                .append("rdf", getRdf())
                .append("conversionFactor", getConversionFactor())
                .append("costRate", getCostRate())
                .append("dnc", getDnc())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}