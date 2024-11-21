package com.ruoyi.project.accessor.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.accessor.domain.*;
import com.ruoyi.project.accessor.mapper.*;
import com.ruoyi.project.accessor.service.AccessorService;
import com.ruoyi.project.system.domain.SysProject;
import com.ruoyi.project.system.mapper.SysProjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccessorServiceImpl implements AccessorService {
    @Resource
    private FeatMapper featMapper;
    @Resource
    private FeatScoreMapper featScoreMapper;
    @Resource
    private MeasureMapper measureMapper;
    @Resource
    private MeasureResMapper measureResMapper;
    @Resource
    private SysProjectMapper sysProjectMapper;

    @Override
    public float getDFP(Integer projectId) {
        Float DFP = measureResMapper.selectDFP(projectId);
        return DFP != null ? DFP.floatValue() : 0.0f;
    }
    /**
     * 保存用户输入的功能点名称、功能点描述、分类标签、复杂度(每个功能点有一个)
     */
    @Override
    public AjaxResult saveFunc(List<FeatDAO> featDAOS) {
        Integer projectId = featDAOS.get(0).getProjectId();
        List<Integer> existedFeats = featMapper.selectList(projectId);
        if (existedFeats.size() > 0) {
        // 说明已经保存过一次功能点了 把旧的功能点都删了
            featMapper.deleteByProjectId(projectId);
        }
        List<FeatScore> featScores = new ArrayList<>();
        for (FeatDAO featDAO : featDAOS) {
            String tag = featDAO.getTag();
            System.out.println(tag);
            Integer diff = featDAO.getDiff();
            Integer scoreId = featScoreMapper.selectScoreId(tag, diff);
            FeatScore featScore = featScoreMapper.selectByScoreId(scoreId);
            featScores.add(featScore);
            Feat feat = new Feat();
            feat.setProjectId(featDAO.getProjectId());
            feat.setFeatName(featDAO.getFuncName());
            feat.setFeatDescr(featDAO.getFuncDescr());
            feat.setScoreId(scoreId);
            featMapper.insert(feat);
        }
        return AjaxResult.success(featScores);
    }

    /**
     * 计算并返回项目的未调整功能点UPF
     */
    @Override
    public AjaxResult getUPF(Integer projectId) {
        int sum = calculateUFPSum(projectId);
        return AjaxResult.success(sum);
    }

    /**
     * 返回表1中的所有数据
     */
    @Override
    public AjaxResult getFunc(Integer projectId) {
        List<Feat> feats = featMapper.selectByProjectId(projectId);
        return AjaxResult.success(feats);
    }

    /**
     * 保存度量 并计算所有结果
     */
    @Override
    public AjaxResult saveMeasure(List<Measure> measures) {
        Integer projectId = measures.get(0).getProjectId();
        MeasureRes measureResForCF = measureResMapper.selectByProjectId(projectId);
        float cf = measureResForCF.getCf();
        List<Measure> measure1 = measureMapper.selectByProjectId(projectId);
        if (measure1.size() > 0) {
        // 需要删除掉数据库保存的measureRes
            measureMapper.deleteByProjectId(projectId);
        }
        int GSC = 0;
        for (Measure measure : measures) {
            measureMapper.insert(measure);
            GSC += measure.getDI();
        }
        float VAF = (float) (0.65 + 0.01 * GSC);
        int UFPsum = calculateUFPSum(projectId);
        float AFP = UFPsum * VAF;
        if (cf==1.25F){
            cf=1.26F;
        }
        float s = UFPsum * cf;
        MeasureRes measureRes = new MeasureRes();
        measureRes.setDFP(AFP);
        measureRes.setVAF(VAF);
        measureRes.setStatus(1);
        measureRes.setGSC(GSC);
        measureRes.setProjectId(projectId);
        measureRes.setUPF(UFPsum);
        measureRes.setCf(cf);
        measureRes.setS(s);
        MeasureRes measureRes1 = measureResMapper.selectByProjectId(projectId);
        if (measureRes1 != null) {
            measureResMapper.deleteByProjectId(projectId);
        }
        measureResMapper.insert(measureRes);
        return AjaxResult.success(measureRes);
    }
    @Override
    public AjaxResult getAll(Integer projectId) {
        List<Feat> feats = featMapper.selectByProjectId(projectId);
        MeasureRes measureRes = measureResMapper.selectByProjectId(projectId);
        List<Measure> measures = measureMapper.selectByProjectId(projectId);
        AllResult allResult = new AllResult();
        allResult.setMeasures(measures);
        allResult.setFeats(feats);
        allResult.setMeasureRes(measureRes);
        List<FeatScore> featScores = new ArrayList<>();
        for (Feat feat : feats) {
            featScores.add(featScoreMapper.selectByScoreId(feat.getScoreId()));
        }
        allResult.setFeatScores(featScores);
        return AjaxResult.success(allResult);
    }

    /**
     * 计算UFP总和
     */
    private int calculateUFPSum(Integer projectId){
        List<Integer> scoreIds = featMapper.selectList(projectId);
        List<FeatScore> featScores = new ArrayList<>();
        for (Integer scoreId : scoreIds) {
            featScores.add(featScoreMapper.selectByScoreId(scoreId));
        }
        int res = 0;
        for (FeatScore featScore : featScores) {
            res+=featScore.getScore();
        }
        return res;
    }

    @Override
    public AjaxResult getProjects() {
        List<SysProject> sysProjectMapperList = sysProjectMapper.selectProjectList(null);
        List<SysProject> projects = sysProjectMapperList.stream().filter(u -> u.getAccessorId().equals(SecurityUtils.getUserId())).collect(Collectors.toList());
        for (SysProject project : projects) {
            Integer projectId = Integer.valueOf(project.getProjectId().toString());
            MeasureRes measureRes1 = measureResMapper.selectByProjectId(projectId);
            if (measureRes1==null){
                String projectStatus = project.getProjectStatus();
                float CF = -1F;
                switch (projectStatus) {
                    case "项目立项":
                        CF = 2F;
                        break;
                    case "项目招标":
                        CF = 1.5F;
                        break;
                    case "项目早期":
                        CF = 1.25F;
                        break;
                    case "项目中期":
                        CF = 1.26F;
                        break;
                    case "项目完成":
                        CF = 1.0F;
                        break;
                }
                MeasureRes measureRes = new MeasureRes();
                measureRes.setProjectId(Integer.valueOf(project.getProjectId().toString()));
                measureRes.setStatus(0);
                measureRes.setCf(CF);
                measureRes.setVAF(-1F);
                measureRes.setUPF(-1);
                measureRes.setDFP(-1F);
                measureRes.setGSC(-1);
                measureRes.setS(-1F);
                measureResMapper.insert(measureRes);
            }
        }
        return AjaxResult.success(projects);
    }

    @Override
    public AjaxResult getStatus(Integer projectId) {
        Integer status = measureResMapper.selectStatusByProjectId(projectId);
        System.out.println(AjaxResult.success(status));
        return AjaxResult.success(status);
    }
    //通过项目id获取项目名称
    @Override
    public String getProjectNameById(Integer projectId) {
        SysProject sysProject = sysProjectMapper.selectProjectById(Long.valueOf(projectId));
        return sysProject.getName();
    }
    @Override
    public AjaxResult getProject(Integer projectId) {
        SysProject sysProject = sysProjectMapper.selectProjectById(Long.valueOf(projectId));
        return AjaxResult.success(sysProject);
    }

    @Override
    public AjaxResult getPendingProjects() {
        List<ProjectMeasureRes> projectList = new ArrayList<>();
        List<MeasureRes> projects = measureResMapper.selectAll();

        System.out.println(projects);


        System.out.println("Total projects: " + projects.size());
        for (MeasureRes project : projects) {
            Integer status = measureResMapper.selectStatusByProjectId(project.getProjectId());
            System.out.println("Project ID: " + project.getProjectId() + ", Status: " + status);
            if (status != null && status == 1) {
                // ... 其余代码不变
                ProjectMeasureRes dto = new ProjectMeasureRes();
                dto.setProjectId(project.getProjectId());
                String name = getProjectNameById(project.getProjectId());
                dto.setName(name);
                dto.setProjectStatus(project.getStatus());
                dto.setDFP(project.getDFP());
                projectList.add(dto);
            }
        }
        return AjaxResult.success(projectList);
    }


}