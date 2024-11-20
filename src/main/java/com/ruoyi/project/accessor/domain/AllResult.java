package com.ruoyi.project.accessor.domain;

import lombok.Data;

import java.util.List;

/**
 * 最后需要返回的所有数据
 */
@Data
public class AllResult {
private List<Feat> feats;
private MeasureRes measureRes;
private List<Measure> measures;
private List<FeatScore> featScores;
}