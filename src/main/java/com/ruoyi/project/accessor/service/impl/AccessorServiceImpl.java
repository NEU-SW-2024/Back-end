package com.ruoyi.project.accessor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.project.accessor.entity.*;
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
            Integer diff = featDAO.getDiff();
            QueryWrapper<FeatScore> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("feat_tag", tag).eq("feat_diff", diff);
            FeatScore featScore = featScoreMapper.selectOne(queryWrapper);
            Integer scoreId = featScore.getScoreId();
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
        QueryWrapper<Feat> featQueryWrapper = new QueryWrapper<>();
        featQueryWrapper.eq("project_id",projectId);
        List<Feat> feats = featMapper.selectList(featQueryWrapper);
        return Result.success(feats);
    }

    /**
     * 保存度量 并计算所有结果
     */
    @Override
    public Result saveMeasure(List<Measure> measures) {
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
        measureResMapper.insert(measureRes);
        return Result.success(measureRes);
    }

    @Override
    public Result getAll(Integer projectId) {
        // TODO
        return null;
    }

    /**
     * 计算UFP总和
     */
    private int calculateUFPSum(Integer projectId){
        QueryWrapper<Feat> featQueryWrapper = new QueryWrapper<>();
        featQueryWrapper.eq("project_id",projectId);
        List<Feat> feats = featMapper.selectList(featQueryWrapper);
        List<Integer> scoreIds = new ArrayList<>();
        for (Feat feat : feats) {
            scoreIds.add(feat.getScoreId());
        }
        QueryWrapper<FeatScore> featScoreQueryWrapper = new QueryWrapper<>();
        featScoreQueryWrapper.in("score_id",scoreIds);
        List<FeatScore> featScores = featScoreMapper.selectList(featScoreQueryWrapper);
        int res = 0;
        for (FeatScore featScore : featScores) {
            res+=featScore.getScore();
        }
        return res;
    }
}
