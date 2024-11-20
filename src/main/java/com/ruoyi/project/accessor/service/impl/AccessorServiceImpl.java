package com.ruoyi.project.accessor.service.impl;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.accessor.domain.*;
import com.ruoyi.project.accessor.mapper.*;
import com.ruoyi.project.accessor.service.AccessorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    private ProjectMapper projectMapper;
    /**
     * 保存用户输入的功能点名称、功能点描述、分类标签、复杂度(每个功能点有一个)
     */
    @Override
    public AjaxResult saveFunc(List<FeatDAO> featDAOS) {
        List<FeatScore> featScores = new ArrayList<>();
        for (FeatDAO featDAO : featDAOS) {
            String tag = featDAO.getTag();
            System.out.println(tag);
            Integer diff = featDAO.getDiff();
            Integer scoreId = featScoreMapper.selectScoreId(tag,diff);
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
    public AjaxResult saveMeasure(Float cf, List<Measure> measures) {
        // TODO 在保存之前看数据库里有没有这行数据 如果有就删了重新保存
        Integer projectId = measures.get(0).getProjectId();
        List<MeasureRes> measureRes1 = measureResMapper.selectByProjectId(projectId);
        if (measureRes1.size()>0) {
            // 需要删除掉数据库保存的measureRes
            measureResMapper.deleteByProjectId(projectId);
        }
        int GSC = 0;
        for (Measure measure : measures) {
            measureMapper.insert(measure);
            GSC+=measure.getDI();
        }
        float VAF = (float) (0.65+0.01*GSC);
        int UFPsum = calculateUFPSum(projectId);
        float AFP = UFPsum * VAF;
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
        measureResMapper.insert(measureRes);
        return AjaxResult.success(measureRes);
    }

    @Override
    public AjaxResult getAll(Integer projectId) {
        List<Feat> feats = featMapper.selectByProjectId(projectId);
        List<MeasureRes> measureRes = measureResMapper.selectByProjectId(projectId);
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

    @Override
    public AjaxResult getProjects(Integer accessorId) {
        List<Project> projects = projectMapper.selectProjectsByAccessorId(accessorId);
        return AjaxResult.success(projects);
    }

    @Override
    public AjaxResult getAssessment(Integer projectId) {
        List<MeasureRes> measureRes = measureResMapper.selectByProjectId(projectId);
        return AjaxResult.success(measureRes);
    }

    @Override
    public AjaxResult getStatus(Integer projectId) {
        Integer status = measureResMapper.selectStatusByProjectId(projectId);
        return AjaxResult.success(status);
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
}
