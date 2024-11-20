package com.ruoyi.project.system.mapper;


import com.ruoyi.project.system.domain.SysAssessmentStd;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface AssessmentStandardMapper {
    List<SysAssessmentStd> selectStandardList(SysAssessmentStd std);
    SysAssessmentStd selectStandardById(@Param("stdId") Integer stdId);
    int insertStandard(SysAssessmentStd std);
    int updateStandard(SysAssessmentStd std);
    int deleteStandardById(@Param("stdId") Long stdId);
}