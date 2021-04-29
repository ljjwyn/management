package com.instructional.system.management.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.instructional.system.management.config.MybatisPlusConfig;
import com.instructional.system.management.mapper.ScoresMapper;
import com.instructional.system.management.mapper.StudentsMapper;
import com.instructional.system.management.model.PageSearchInput;
import com.instructional.system.management.model.Scores;
import com.instructional.system.management.model.Students;
import com.instructional.system.management.model.TopTailScoresInput;
import com.instructional.system.management.server.PageSearch;
import com.instructional.system.management.server.ScoresServer;
import com.instructional.system.management.utils.Result;
import com.instructional.system.management.utils.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ljjwyn
 */
@RestController
@Validated
@RequestMapping("/student")
public class StudentsController {
    private static final Logger log = LoggerFactory.getLogger(StudentsController.class);

    @Autowired
    private StudentsMapper studentsMapper;

    @Autowired
    private ScoresMapper scoresMapper;

    @Autowired
    private PageSearch pageSearch;

    @Autowired
    private ScoresServer scoresServer;

    @RequestMapping(value = "getastudent", method = RequestMethod.POST)
    @ResponseBody
    public Result getAStudent(@RequestBody Map<String,String> requestParameter){
        String studentId = requestParameter.get("studentId");
        if(studentId != null && !studentId.isEmpty()){
            QueryWrapper<Students> studentsQueryWrapper = new QueryWrapper<>();
            MybatisPlusConfig.myTableNameScores.set("scores_1");
            studentsQueryWrapper.like("studentId",studentId);
            Students students = studentsMapper.selectOne(studentsQueryWrapper);
            if (!students.getStudentId().isEmpty()){
                log.info("检索到学生:",students.getName());
                return Result.success(students);
            }else {
                return Result.failure(ResultCode.SQL_SELECT_BLANK);
            }
        }else {
            return Result.failure(ResultCode.PARAM_BLANK);
        }
    }

    @RequestMapping(value = "getascores", method = RequestMethod.POST)
    @ResponseBody
    public Result getAScores(@RequestBody Map<String,String> requestParameter){
        String studentId = requestParameter.get("studentId");
        if(studentId != null && !studentId.isEmpty()){
            QueryWrapper<Scores> scoresQueryWrapper = new QueryWrapper<>();
            MybatisPlusConfig.myTableNameScores.set("scores_1");
            scoresQueryWrapper.like("studentId",studentId);
            Scores scores = scoresMapper.selectOne(scoresQueryWrapper);
            if (!scores.getStudentId().isEmpty()){
                log.info("检索到学生:",scores.getName());
                return Result.success(scores);
            }else {
                return Result.failure(ResultCode.SQL_SELECT_BLANK);
            }
        }else {
            return Result.failure(ResultCode.PARAM_BLANK);
        }
    }

    @RequestMapping(value = "pagesearch", method = RequestMethod.POST)
    @ResponseBody
    public Result pageSearch(@RequestBody PageSearchInput pageSearchInput){
        String searchType = pageSearchInput.getSearchType();
        if(searchType != null && !searchType.isEmpty()){
            int page = pageSearchInput.getPage();
            if("scores".equals(searchType)){
                return Result.success(pageSearch.pageSearchScores(page));
            }else if("students".equals(searchType)){
                return Result.success(pageSearch.pageSearchStudents(page));
            }
            return Result.failure(ResultCode.PARAM_INVALID);
        }
        return Result.failure(ResultCode.PARAM_BLANK);
    }

    @RequestMapping(value = "gettoptail", method = RequestMethod.POST)
    @ResponseBody
    public Result getTopTail(@RequestBody TopTailScoresInput topTailScoresInput, HttpSession httpSession){
        if(httpSession.getAttribute("token")==null || httpSession.getAttribute("token").toString().isEmpty()){
            return Result.failure(ResultCode.TOKEN_INVALID);
        }else if(!"root-token".equals(httpSession.getAttribute("token").toString())){
            return Result.failure(ResultCode.AUTHORITY_ERROR);
        }
        String exam = topTailScoresInput.getExam();
        String subject = topTailScoresInput.getSubject();
        if(!exam.isEmpty() && !subject.isEmpty()){
            return scoresServer.getTopTailStudents(exam, subject);
        }
        return Result.failure(ResultCode.PARAM_BLANK);
    }
}
