package com.ruoyi.project.accessor.service;

import com.ruoyi.project.accessor.domain.FeatDAO;
import com.ruoyi.project.accessor.domain.Measure;
import com.ruoyi.framework.web.domain.AjaxResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccessorService {
    AjaxResult saveFunc(List<FeatDAO> featDAOS);

    AjaxResult getUPF(Integer projectId);

    AjaxResult getFunc(Integer projectId);

    AjaxResult saveMeasure(Float cf, List<Measure> measures);

    AjaxResult getAll(Integer projectId);

    AjaxResult getProjects(Integer accessorId);

    AjaxResult getAssessment(Integer projectId);

    AjaxResult getStatus(Integer projectId);

    float getDFP(Integer projectId);

    AjaxResult getPendingProjects();

    //通过id获得项目名称
    String getProjectNameById(Integer projectId);

//    Result updateFunc(List<FeatDAO> featDAOS);
//
//    Result deleteFunc(DeleteFuncRequest request);

//    Result deleteMeasure(String measureName);
//
//    Result updateMeasures(SaveMeasureRequest saveMeasureRequest);
//
//    Feat getFeatBySoreId(FeatDAO featDAO);
}
