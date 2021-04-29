package com.instructional.system.management.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ljjwyn
 */
@Data
public class ScoresRecord {
    private String classId;
    private int excellent;
    private int good;
    private int fail;
    private int total;
    private Double chineseAverage;
    private Double mathAverage;
    private Double englishAverage;
    private Double physicsAverage;
    private Double chemistryAverage;
    private Double biologyAverage;
    private Double totalAverage;
    private int topRank;
    private String topStudentId;
    private int topScores;
}
