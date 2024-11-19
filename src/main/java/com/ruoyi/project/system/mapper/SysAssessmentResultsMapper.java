package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.SysAssessmentResults;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 评估结果表 assessment_results 的Mapper接口
 *
 * @author ruoyi
 */
@Mapper
public interface SysAssessmentResultsMapper {

    /**
     * 插入评估结果
     * @param sysAssessmentResults 评估结果对象
     * @return 插入的行数
     */
    int insertAssessmentResults(SysAssessmentResults sysAssessmentResults);

    /**
     * 根据ID查询评估结果
     * @param resId 评估结果ID
     * @return 评估结果对象
     */
    SysAssessmentResults selectAssessmentResultsById(@Param("resId") Long resId);

    /**
     * 查询所有评估结果
     * @return 评估结果列表
     */
    List<SysAssessmentResults> selectAllAssessmentResults();

    /**
     * 更新评估结果
     * @param sysAssessmentResults 评估结果对象
     * @return 更新的行数
     */
    int updateAssessmentResults(SysAssessmentResults sysAssessmentResults);

    /**
     * 根据ID删除评估结果
     * @param resId 评估结果ID
     * @return 删除的行数
     */
    int deleteAssessmentResultsById(@Param("resId") Long resId);
}