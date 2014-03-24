package cn.edu.zju.isst.identity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.util.WebUtils;

import cn.edu.zju.isst.dao.UserDao;
import cn.edu.zju.isst.entity.User;

public class UserIdentity {
    
    public static User read(HttpServletRequest request, UserDao userDao) {
        User user = (User) request.getSession().getAttribute("user");
        if (null == user) {
            Cookie cookie = WebUtils.getCookie(request, "user_token");
            if (null != cookie) {
                String[] s = new String(Base64.decodeBase64(cookie.getValue())).split(":", 2);
                if (s.length == 2) {
                    user = userDao.find(Integer.valueOf(s[0]).intValue());
                    if (null != user) {
                        if (generateLoginEncryptedToken(request, user).equals(s[1])) {
                            request.getSession().setAttribute("user", user);
                        } else {
                            user = null;
                        }
                    }
                }
            }
            
            if (null == user) {
                user = new User();
                request.getSession().setAttribute("user", user);
            }
        }
        
        return user;
    }
    
    public static void login(HttpServletRequest request, HttpServletResponse response, User user, boolean isRememberMe) {
        request.getSession().setAttribute("user", user);
        if (isRememberMe) {
            Cookie cookie = new Cookie("user_token", generateLoginToken(request, user));
            cookie.setMaxAge(31536000);
            response.addCookie(cookie);
        }
    }
    
    private static String generateLoginToken(HttpServletRequest request, User user) {
        String token = new StringBuilder()
        .append(user.getId())
        .append(":")
        .append(generateLoginEncryptedToken(request, user))
        .toString();
        return new String(Base64.encodeBase64(token.getBytes()));
    }
    
    private static String generateLoginEncryptedToken(HttpServletRequest request, User user) {
        return DigestUtils.md5Hex(
                new StringBuilder()
                .append(request.getRemoteAddr())
                .append(user.getId())
                .append(user.getUsername())
                .append(user.getPassword())
                .toString()
            );
    }
    
    public static User logout(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("user_token", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        
        return user;
    }
}
