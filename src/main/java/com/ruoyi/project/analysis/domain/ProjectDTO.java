package com.ruoyi.project.analysis.domain;

import java.util.List;

public class ProjectDTO {
    //项目表
    private Long project_id; // 项目id
    private Long tenant_id; // 租户id
    private String name; // 项目名称
    private String description; // 项目描述
    private String accessor; // 评估师nickname
    private String auditor; // 审核员nickname
    private String project_status; // 项目状态
    private String create_time; // 创建时间

    //功能点表
    private List<Feature> features; // 功能点

    //度量表
    private List<Measure> measures; // 度量表

    //评估结果表（第二组）
    private int UPF; // 功能点分数总和 (UPF)
    private Double VAF; // 调整系数 (VAF)
    private Double DFP; // 调整后的功能点数 (DFP)
    private int GSC; // DI总和 (GSC)
    private String status; // 状态
    private Double S; // 规模变更调整因子(S)

    //评估结果表（第三组）
    private Double total_cost; // 总造价
    private Double labor_cost; // 人工成本
    private Double risk_cost; // 风险附加成本
    private Double quality_cost; // 质量附加成本
    private Double dev_service_cost; // 开发服务费用
    private Double adjusted_dev_service_cost; // 调整后开发服务费用
    private String created_at; // 评估结果记录的创建时间

    public Boolean getReport_status() {
        return report_status;
    }

    public void setReport_status(Boolean report_status) {
        this.report_status = report_status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Double getAdjusted_dev_service_cost() {
        return adjusted_dev_service_cost;
    }

    public void setAdjusted_dev_service_cost(Double adjusted_dev_service_cost) {
        this.adjusted_dev_service_cost = adjusted_dev_service_cost;
    }

    public Double getDev_service_cost() {
        return dev_service_cost;
    }

    public void setDev_service_cost(Double dev_service_cost) {
        this.dev_service_cost = dev_service_cost;
    }

    public Double getQuality_cost() {
        return quality_cost;
    }

    public void setQuality_cost(Double quality_cost) {
        this.quality_cost = quality_cost;
    }

    public Double getRisk_cost() {
        return risk_cost;
    }

    public void setRisk_cost(Double risk_cost) {
        this.risk_cost = risk_cost;
    }

    public Double getLabor_cost() {
        return labor_cost;
    }

    public void setLabor_cost(Double labor_cost) {
        this.labor_cost = labor_cost;
    }

    public Double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(Double total_cost) {
        this.total_cost = total_cost;
    }

    public Double getS() {
        return S;
    }

    public void setS(Double s) {
        S = s;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getGSC() {
        return GSC;
    }

    public void setGSC(int GSC) {
        this.GSC = GSC;
    }

    public Double getDFP() {
        return DFP;
    }

    public void setDFP(Double DFP) {
        this.DFP = DFP;
    }

    public Double getVAF() {
        return VAF;
    }

    public void setVAF(Double VAF) {
        this.VAF = VAF;
    }

    public int getUPF() {
        return UPF;
    }

    public void setUPF(int UPF) {
        this.UPF = UPF;
    }

    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getProject_status() {
        return project_status;
    }

    public void setProject_status(String project_status) {
        this.project_status = project_status;
    }

    public String getAccessor() {
        return accessor;
    }

    public void setAccessor(String accessor) {
        this.accessor = accessor;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTenant_id() {
        return tenant_id;
    }

    public void setTenant_id(Long tenant_id) {
        this.tenant_id = tenant_id;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    //我们自己的表
    private Boolean report_status; // 报告生成状态



}
