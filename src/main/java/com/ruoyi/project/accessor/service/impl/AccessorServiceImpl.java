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
        return Result.success(featScores);
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
        List<FeatScore> featScores = new ArrayList<>();
        for (Feat feat : feats) {
            featScores.add(featScoreMapper.selectByScoreId(feat.getScoreId()));
        }
        allResult.setFeatScores(featScores);
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


//    /**
//     * (批量)更新用户输入的功能
//     * 与保存共用同一个请求
//     */
//    @Override
//    public Result updateFunc(List<FeatDAO> daos) {
//        if(Objects.isNull(daos)){
//            throw new RuntimeException("请求失败，请稍后重试");
//        }
//        daos.forEach(featDAO -> {
//            Feat feat = getFeatBySoreId(featDAO);
//            featMapper.updateById(feat);
//        });
//        return Result.success("更改信息成功");
//    }

//    /**
//     * 删除用户录入的功能点
//     * @param request
//     * @return
//     */
//    @Override
//    public Result deleteFunc(DeleteFuncRequest request) {
//        String featName = request.getFeatName();
//        if(Objects.isNull(featName) || featName.equals("")){
//            throw new RuntimeException("功能点名称不能为空");
//        }
//        featMapper.deleteByFeatName(featName);
//        return Result.success("删除成功");
//    }

//    /**
//     * 根据度量名称
//     * @param measureName
//     * @return
//     */
//    @Override
//    public Result deleteMeasure(String measureName) {
//        if(Objects.isNull(measureName) || measureName.equals("")){
//            throw new RuntimeException("度量名称不能为空");
//        }
//        measureMapper.deleteMeasure(measureName);
//        return Result.success("删除成功");
//    }

//    /**
//     * 更新度量名称
//     * @param saveMeasureRequest
//     * @return
//     */
//    @Override
//    public Result updateMeasures(SaveMeasureRequest saveMeasureRequest) {
//        List<Measure> measures = saveMeasureRequest.getMeasures();
//        if(Objects.isNull(measures) || measures.size() == 0){
//            throw new RuntimeException("度量不能为空");
//        }
//        UpdateWrapper<Measure> updateWrapper = new UpdateWrapper();
//        measures.forEach(measure -> {
//            updateWrapper.eq("measure_name", measure.getMeasureName());
//            // todo 测试看需不需要
//            updateWrapper.eq("GSC",measure.getGSC());
//            measureMapper.update(measure,updateWrapper);
//        });
//        return null;
//    }

//
//    /**
//     * 查询Feat
//     */
//    public Feat getFeatBySoreId(FeatDAO featDAO){
//        String tag = featDAO.getTag();
//        Integer diff = featDAO.getDiff();
//        QueryWrapper<FeatScore> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("feat_tag", tag).eq("feat_diff", diff);
//        FeatScore featScore = featScoreMapper.selectOne(queryWrapper);
//        Integer scoreId = featScore.getScoreId();
//        Feat feat = new Feat();
//        feat.setProjectId(featDAO.getProjectId());
//        feat.setFeatName(featDAO.getFuncName());
//        feat.setFeatDescr(featDAO.getFuncDescr());
//        feat.setScoreId(scoreId);
//        return feat;
//    }

}