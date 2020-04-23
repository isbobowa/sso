package cas.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cas.entity.Domain;
import cas.entity.User;
import cas.enums.Method;
import cas.service.DomainService;
import cas.service.UserService;
import cas.util.JwtUtil;
import cas.util.Result.Result;
import cas.util.Result.ResultCodeEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/cas")
public class SsoCheck {

    @Autowired
    private DomainService domainService;
    @Autowired
    private UserService userService;

    /**
     * 统一处理login请求
     * @param username 用户名
     * @param password 密码
     */
    @PostMapping
    public Result<Map<String,Object>> checkLogin (String username, String password) {
        log.info("统一登录校验");
        User user = userService.login(username, password);
        if (user != null) {
        	 log.info("登录认证成功");
            //封装参数
            Map<String, Object> param = new HashMap<>();
            List<Domain> domains = domainService.selectAll();
            List<String> domainUrl = new ArrayList<>(domains.size());
            domains.forEach(domain->{
                domainUrl.add(domain.getDomain()+"/addcookie");
            });
            //生成jwt
            String cookieName = "jian";
            String cookieValue = new JwtUtil(user.toString(),null).creatJwt();
            param.put("cookieurl",domainUrl);
            param.put("cookieName", cookieName);
            param.put("cookieValue",cookieValue);
            Result<Map<String, Object>> result = new Result<>(ResultCodeEnum.AUTHSUCCESS);
            result.setData(param);
            return result;
        }
        return new Result<>(ResultCodeEnum.UNAUTHORIZEd,"账号或密码错误");
    }

    /**
     * 添加需要清除的cookie
     */
    @GetMapping("/loginout")
    public String loginOut (HttpServletRequest request) {
        String callbackFuncation = request.getParameter("callback");
        log.info("start clear");
        List<Domain> domains = domainService.selectAll();
        List<String> domainUrl = new ArrayList<>(domains.size());
        domains.forEach(domain->{
            domainUrl.add(domain.getDomain()+"/clear");
        });
        String resultMsg = JSON.toJSONString(domainUrl);
        return callbackFuncation+"("+resultMsg+")";
    }

    /**
     * 验证cookie是否通过
     * @param cookieName cookie名称
     * @param cookieValue cookie内容
     * @return 是否认证成功
     */
    @GetMapping("/authcookies")
    public boolean checkAuthCookies (String cookieName, String cookieValue) {
        boolean isUpdate = new JwtUtil(null,cookieValue).freeJwt();
        if ("jian".equals(cookieName)) {
            if (isUpdate) {
                log.info("cookie验证通过");
                return true;
            }
        }
        return false;
    }


}
