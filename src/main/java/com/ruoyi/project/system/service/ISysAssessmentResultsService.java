package com.ruoyi.project.system.service;

import com.ruoyi.project.system.domain.SysAssessmentResults;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 评估结果服务接口
 *
 * @author ruoyi
 */
public interface ISysAssessmentResultsService {

    /**
     * 插入新的评估结果
     * @param assessmentResults 评估结果对象，包含项目ID、标准ID、开发服务费用等信息
     * @return 插入操作成功与否的状态
     */
    boolean insertAssessmentResults(SysAssessmentResults assessmentResults);

    /**
     * 根据ID查询单个评估结果
     * @param resId 评估结果ID
     * @return 评估结果对象
     */
    SysAssessmentResults selectAssessmentResultsById(Long resId);

    /**
     * 查询所有评估结果
     * @return 评估结果列表
     */
    List<SysAssessmentResults> selectAllAssessmentResults();

    /**
     * 更新评估结果
     * @param assessmentResults 评估结果对象，包含更新的项目ID、标准ID、开发服务费用等信息
     * @return 更新操作成功与否的状态
     */
    boolean updateAssessmentResults(SysAssessmentResults assessmentResults);

    /**
     * 根据ID删除评估结果
     * @param resId 评估结果ID
     * @return 删除操作成功与否的状态
     */
    boolean deleteAssessmentResultsById(Long resId);
}