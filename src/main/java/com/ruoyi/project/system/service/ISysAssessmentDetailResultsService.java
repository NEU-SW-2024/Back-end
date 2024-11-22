package com.ruoyi.project.system.service;

import com.ruoyi.project.system.domain.SysAssessmentDetailResults;
import com.ruoyi.project.system.domain.SysAssessmentStd;

import java.math.BigDecimal;
import java.util.List;

public interface ISysAssessmentDetailResultsService {
    /**
     * 插入新的评估细节结果
     * @param assessmentDetailResults 评估细节结果对象
     * @return 插入操作成功与否的状态
     */
    boolean insertAssessmentDetailResults(SysAssessmentDetailResults assessmentDetailResults);

    /**
     * 根据ID查询单个评估细节结果
     * @param resId 评估结果ID
     * @return 评估细节结果对象
     */
    SysAssessmentDetailResults selectAssessmentDetailResultsById(Long resId);

    /**
     * 查询所有评估细节结果
     * @return 评估细节结果列表
     */
    List<SysAssessmentDetailResults> selectAllAssessmentDetailResults();

    /**
     * 更新评估细节结果
     * @param assessmentDetailResults 评估细节结果对象
     * @return 更新操作成功与否的状态
     */
    boolean updateAssessmentDetailResults(SysAssessmentDetailResults assessmentDetailResults);

    /**
     * 根据ID删除评估细节结果
     * @param resId 评估结果ID
     * @return 删除操作成功与否的状态
     */
    boolean deleteAssessmentDetailResultsById(Long resId);

    BigDecimal calculateAE(SysAssessmentStd std, float dfp);

    BigDecimal calculateLaborCost(SysAssessmentStd std, float dfp);

    BigDecimal calculateRiskCost(SysAssessmentStd std, BigDecimal projectSDC);

    BigDecimal calculateQualityCost(SysAssessmentStd std, BigDecimal projectSDC);

    BigDecimal calculateDevServiceCost(SysAssessmentStd std);

    BigDecimal calculateadJustedDevServiceCost(SysAssessmentStd std);
}