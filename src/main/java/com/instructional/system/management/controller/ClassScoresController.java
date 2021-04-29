package com.instructional.system.management.controller;

import com.instructional.system.management.server.ClassScoresServer;
import com.instructional.system.management.utils.Result;
import com.instructional.system.management.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author ljjwyn
 */
@Controller
@RequestMapping("/classscores")
public class ClassScoresController {
    @Autowired
    private ClassScoresServer classScoresServer;

    @RequestMapping(value = "getrecord", method = RequestMethod.POST)
    @ResponseBody
    public Result getAClassScoresRecord(@RequestBody Map<String,String> requestParam, HttpSession httpSession){
        if(httpSession.getAttribute("token")==null || httpSession.getAttribute("token").toString().isEmpty()){
            return Result.failure(ResultCode.TOKEN_INVALID);
        }
        String classId = requestParam.get("classId");
        if(classId != null && !classId.isEmpty()){
            return classScoresServer.getClassScores(classId, httpSession);
        }else {
            return Result.failure(ResultCode.PARAM_BLANK);
        }
    }
}
