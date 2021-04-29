package com.instructional.system.management.server;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.instructional.system.management.config.MybatisPlusConfig;
import com.instructional.system.management.mapper.ScoresMapper;
import com.instructional.system.management.model.Scores;
import com.instructional.system.management.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ljjwyn
 */
@Component
public class ScoresServer {
    @Autowired
    private ScoresMapper scoresMapper;

    public int getAllScoresCount(String exam){
        MybatisPlusConfig.myTableNameScores.set(exam);
        QueryWrapper<Scores> scoresQueryWrapper = new QueryWrapper<>();
        return scoresMapper.selectCount(scoresQueryWrapper);
    }

    public Result getTopTailStudents(String exam, String subject){
        /**TODO 目前只有一次考试成绩所以写死，后期传参动态切换表*/
        exam = "scores_1";
        Map<String,List<Scores>> recordTopTail = new HashMap<>();
        /**获取前10后10名信息*/
        MybatisPlusConfig.myTableNameScores.set(exam);
        QueryWrapper<Scores> scoresQueryWrapperTop = new QueryWrapper<>();
        scoresQueryWrapperTop.orderByAsc(subject);
        List<Scores> scoresList = scoresMapper.selectList(scoresQueryWrapperTop);
        int count = scoresList.size();
        recordTopTail.put("topScores", scoresList.subList(0,10));
        recordTopTail.put("tailScores", scoresList.subList(count-10, count));
        return Result.success(recordTopTail);
    }
}
