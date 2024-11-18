package com.ruoyi.project.accessor.mapper;

import com.ruoyi.project.accessor.domain.FeatScore;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FeatScoreMapper {
    List<FeatScore> selectOne(String tag, Integer diff);

    FeatScore selectByScoreId(Integer scoreId);

    @Select("select score_id from vue.tb_feat_score where feat_tag=#{tag} and feat_diff=#{diff}")
    Integer selectScoreId(String tag, Integer diff);
}
