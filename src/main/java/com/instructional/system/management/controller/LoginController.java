package com.instructional.system.management.controller;

import com.instructional.system.management.model.RequestParam;
import com.instructional.system.management.server.LoginServer;
import com.instructional.system.management.utils.Result;
import com.instructional.system.management.utils.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ljjwyn
 */
@Controller
@RequestMapping("/user")
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginServer loginServer;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result userLogin(HttpSession session, @RequestBody RequestParam requestParam) {
        String userPassword = requestParam.getUserPassword();
        String userAccount = requestParam.getUserAccount();

        if(userPassword.isEmpty() || userAccount.isEmpty()){
            return Result.failure(ResultCode.LOGIN_ACCOUNT_BLANK);
        }else {
            log.warn("登录的sessionid:{}", session.getId());
            return loginServer.verifyAccount(userAccount, userPassword, session);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public Result userLogout(HttpSession session) {
        session.removeAttribute("user");
        return Result.success(ResultCode.SUCCESS);
    }
}
