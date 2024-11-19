package com.ruoyi.project.system.service.impl;

import com.ruoyi.project.system.domain.SysAssessmentResults;
import com.ruoyi.project.system.mapper.SysAssessmentResultsMapper;
import com.ruoyi.project.system.service.ISysAssessmentResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 评估结果服务实现类
 *
 * @author ruoyi
 */
@Service
public class SysAssessmentResultsServiceImpl implements ISysAssessmentResultsService {

    // 自动注入评估结果Mapper
    @Autowired
    private SysAssessmentResultsMapper assessmentResultsMapper;

    /**
     * 插入新的评估结果
     * @param assessmentResults 评估结果对象，包含项目ID、标准ID、开发服务费用等信息
     * @return 插入操作成功与否的状态
     */
    @Override
    public boolean insertAssessmentResults(SysAssessmentResults assessmentResults) {
        // 调用Mapper的方法插入新的评估结果
        int result = assessmentResultsMapper.insertAssessmentResults(assessmentResults);
        return result > 0;
    }

    /**
     * 根据ID查询单个评估结果
     * @param resId 评估结果ID
     * @return 评估结果对象
     */
    @Override
    public SysAssessmentResults selectAssessmentResultsById(Long resId) {
        // 调用Mapper的方法根据ID查询评估结果
        return assessmentResultsMapper.selectAssessmentResultsById(resId);
    }

    /**
     * 查询所有评估结果
     * @return 评估结果列表
     */
    @Override
    public List<SysAssessmentResults> selectAllAssessmentResults() {
        // 调用Mapper的方法查询所有评估结果
        return assessmentResultsMapper.selectAllAssessmentResults();
    }

    /**
     * 更新评估结果
     * @param assessmentResults 评估结果对象，包含更新的项目ID、标准ID、开发服务费用等信息
     * @return 更新操作成功与否的状态
     */
    @Override
    public boolean updateAssessmentResults(SysAssessmentResults assessmentResults) {
        // 调用Mapper的方法更新评估结果
        int result = assessmentResultsMapper.updateAssessmentResults(assessmentResults);
        return result > 0;
    }

    /**
     * 根据ID删除评估结果
     * @param resId 评估结果ID
     * @return 删除操作成功与否的状态
     */
    @Override
    public boolean deleteAssessmentResultsById(Long resId) {
        // 调用Mapper的方法根据ID删除评估结果
        int result = assessmentResultsMapper.deleteAssessmentResultsById(resId);
        return result > 0;
    }
}