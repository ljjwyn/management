package com.instructional.system.management.model;

import lombok.Data;

/**
 * @author ljjwyn
 */
@Data
public class Scores {
    private String studentId;
    private String name;
    private String grade;
    private String classId;
    private Integer chinese;
    private Integer chineseRank;
    private Integer math;
    private Integer mathRank;
    private Integer english;
    private Integer englishRank;
    private Integer physics;
    private Integer physicsRank;
    private Integer chemistry;
    private Integer chemistryRank;
    private Integer biology;
    private Integer biologyRank;
    private Integer total;
    private Integer totalRank;
}
