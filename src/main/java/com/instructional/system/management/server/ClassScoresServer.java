package com.instructional.system.management.server;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.instructional.system.management.config.MybatisPlusConfig;
import com.instructional.system.management.mapper.ScoresRecordMapper;
import com.instructional.system.management.model.ScoresRecord;
import com.instructional.system.management.utils.Result;
import com.instructional.system.management.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * @author ljjwyn
 */
@Component
public class ClassScoresServer {

    @Autowired
    private ScoresRecordMapper scoresRecordMapper;
    public Result getClassScores(String classId, HttpSession session){
        QueryWrapper<ScoresRecord> scoresRecordQueryWrapper = new QueryWrapper<>();
        MybatisPlusConfig.myTableNameScoresRecord.set("scores_record_1");
        scoresRecordQueryWrapper.like("classId",classId);
        ScoresRecord scoresRecord = scoresRecordMapper.selectOne(scoresRecordQueryWrapper);
        if(scoresRecord == null){
            return Result.failure(ResultCode.SQL_SELECT_BLANK);
        }
        if("total".equals(classId) && !"root-token".equals(session.getAttribute("token").toString())){
            return Result.failure(ResultCode.AUTHORITY_ERROR);
        }
        return Result.success(scoresRecord);
    }
}
