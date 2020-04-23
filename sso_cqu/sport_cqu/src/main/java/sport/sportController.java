package sport;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sport.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class sportController {
    @GetMapping("/sport")
    public void visit(HttpServletRequest request,HttpServletResponse response) {
    	 log.info("访问体育网");
    	 StringBuffer URL = request.getRequestURL();
         try {
			response.sendRedirect("http://localhost:8080/ssocheck?url="+URL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @GetMapping("/sportvisit")
    public ModelAndView sportvisit(HttpServletRequest request,HttpServletResponse response) {
	    Cookie [] cookies = request.getCookies();
	    boolean isUpdate=false;
        if (cookies != null && cookies.length > 0) {
		    for (Cookie cookie: cookies) {
		        if ("jian".equals(cookie.getName())) {
		            isUpdate = new JwtUtil(null,cookie.getValue()).freeJwt();
		            if(isUpdate)
		            {
		           	 log.info("成功访问重大教务处");
		        	 return new ModelAndView("sport");
		            }
		        }
		    }
        }
	    return new ModelAndView("loginout");
    }
    @GetMapping("/sport/addcookie")
    public void addCookies (@RequestParam("cookieName") String cookieName, @RequestParam("cookieValue") String cookieValue, HttpServletResponse response) {
        log.info("添加cookie");
        Cookie cookie = new Cookie(cookieName,cookieValue);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
        @GetMapping("/sport/clear")
        public void clear(HttpServletRequest request,HttpServletResponse response) {
            //获得域名
            log.info("clear掉ip为："+request.getRemoteHost()+"的cookie");
            Cookie [] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0) {
	            for (Cookie cookie: cookies) {
	            	log.info("我要删除cookie了");
	                if ("jian".equals(cookie.getName())) {
	                    cookie.setValue(null);
	                    cookie.setMaxAge(0);
	                    response.addCookie(cookie);
	                }
	            }
            }
        }
        @GetMapping("/loginout")
        public ModelAndView loginout(HttpServletRequest request,HttpServletResponse response) {
        	return new ModelAndView("loginout");
        }
        
    
}
