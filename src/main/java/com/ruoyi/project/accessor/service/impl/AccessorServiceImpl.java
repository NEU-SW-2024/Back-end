package com.ruoyi.project.accessor.service.impl;

import com.ruoyi.project.accessor.domain.*;
import com.ruoyi.project.accessor.mapper.FeatMapper;
import com.ruoyi.project.accessor.mapper.FeatScoreMapper;
import com.ruoyi.project.accessor.mapper.MeasureMapper;
import com.ruoyi.project.accessor.mapper.MeasureResMapper;
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
    /**
     * 保存用户输入的功能点名称、功能点描述、分类标签、复杂度(每个功能点有一个)
     */
    @Override
    public Result saveFunc(List<FeatDAO> featDAOS) {
        for (FeatDAO featDAO : featDAOS) {
            String tag = featDAO.getTag();
            System.out.println(tag);
            Integer diff = featDAO.getDiff();
            Integer scoreId = featScoreMapper.selectScoreId(tag,diff);
            Feat feat = new Feat();
            feat.setProjectId(featDAO.getProjectId());
            feat.setFeatName(featDAO.getFuncName());
            feat.setFeatDescr(featDAO.getFuncDescr());
            feat.setScoreId(scoreId);
            featMapper.insert(feat);
        }
        return Result.success();
    }

    /**
     * 计算并返回项目的未调整功能点UPF
     */
    @Override
    public Result getUPF(Integer projectId) {
        int sum = calculateUFPSum(projectId);
        return Result.success(sum);
    }

    /**
     * 返回表1中的所有数据
     */
    @Override
    public Result getFunc(Integer projectId) {
        List<Feat> feats = featMapper.selectByProjectId(projectId);
        return Result.success(feats);
    }

    /**
     * 保存度量 并计算所有结果
     */
    @Override
    public Result saveMeasure(Float cf, List<Measure> measures) {
        Integer projectId = measures.get(0).getProjectId();
        int GSCsum = 0;
        for (Measure measure : measures) {
            measureMapper.insert(measure);
            GSCsum+=measure.getGSC();
        }
        float VAF = (float) (0.65+0.01*GSCsum);
        int UFPsum = calculateUFPSum(projectId);
        float AFP = UFPsum * VAF;
        MeasureRes measureRes = new MeasureRes();
        measureRes.setAFP(AFP);
        measureRes.setVAF(VAF);
        measureRes.setStatus(1);
        measureRes.setGSCTotal(GSCsum);
        measureRes.setProjectId(projectId);
        measureRes.setUPF(UFPsum);
        measureRes.setCf(cf);
        measureResMapper.insert(measureRes);
        return Result.success(measureRes);
    }

    @Override
    public Result getAll(Integer projectId) {
        List<Feat> feats = featMapper.selectByProjectId(projectId);
        List<MeasureRes> measureRes = measureResMapper.selectByProjectId(projectId);
        List<Measure> measures = measureMapper.selectByProjectId(projectId);
        AllResult allResult = new AllResult();
        allResult.setMeasures(measures);
        allResult.setFeats(feats);
        allResult.setMeasureRes(measureRes);
        return Result.success(allResult);
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
