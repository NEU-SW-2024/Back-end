package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.SysAssessmentDetailResults;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 评估细节结果Mapper接口
 *
 * @author ruoyi
 */
@Mapper
public interface SysAssessmentDetailResultsMapper {

    /**
     * 插入评估细节结果
     * @param sysAssessmentDetailResults 评估细节结果对象
     * @return 插入的行数
     */
    int insertAssessmentDetailResults(SysAssessmentDetailResults sysAssessmentDetailResults);

    /**
     * 根据ID查询评估细节结果
     * @param resId 评估结果ID
     * @return 评估细节结果对象
     */
    SysAssessmentDetailResults selectAssessmentDetailResultsById(@Param("resId") Long resId);

    /**
     * 查询所有评估细节结果
     * @return 评估细节结果列表
     */
    List<SysAssessmentDetailResults> selectAllAssessmentDetailResults();

    /**
     * 更新评估细节结果
     * @param sysAssessmentDetailResults 评估细节结果对象
     * @return 更新的行数
     */
    int updateAssessmentDetailResults(SysAssessmentDetailResults sysAssessmentDetailResults);

    /**
     * 根据ID删除评估细节结果
     * @param resId 评估结果ID
     * @return 删除的行数
     */
    int deleteAssessmentDetailResultsById(@Param("resId") Long resId);
}