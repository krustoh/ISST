package cn.edu.zju.isst.identity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.util.WebUtils;

import cn.edu.zju.isst.dao.StudentUserDao;
import cn.edu.zju.isst.entity.StudentUser;

public class UserIdentity {
    private static final String secret = "vq8ukG8MKrNC7XqsbIbd7PxvX81ufNz9";
    
    public static String encryptToken(long timestamp, Object...params) {
        StringBuilder sb = new StringBuilder(secret);
        for (Object value : params) {
            sb.append(value);
        }
        sb.append(timestamp);
        return DigestUtils.md5Hex(sb.toString());
    }
    
    public static StudentUser read(HttpServletRequest request, StudentUserDao studentUserDao) {
        StudentUser user = (StudentUser) request.getSession().getAttribute("user");
        if (null == user) {
            Cookie cookie = WebUtils.getCookie(request, "user_token");
            if (null != cookie) {
                String[] s = new String(Base64.decodeBase64(cookie.getValue())).split(":", 2);
                if (s.length == 2) {
                    user = studentUserDao.find(Integer.valueOf(s[0]).intValue());
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
                user = new StudentUser();
                request.getSession().setAttribute("user", user);
            }
        }
        
        return user;
    }
    
    public static void login(HttpServletRequest request, HttpServletResponse response, StudentUser user, boolean isRememberMe) {
        request.getSession().setAttribute("user", user);
        if (isRememberMe) {
            Cookie cookie = new Cookie("user_token", generateLoginToken(request, user));
            cookie.setMaxAge(31536000);
            response.addCookie(cookie);
        }
    }
    
    private static String generateLoginToken(HttpServletRequest request, StudentUser user) {
        String token = new StringBuilder()
        .append(user.getId())
        .append(":")
        .append(generateLoginEncryptedToken(request, user))
        .toString();
        return new String(Base64.encodeBase64(token.getBytes()));
    }
    
    private static String generateLoginEncryptedToken(HttpServletRequest request, StudentUser user) {
        return DigestUtils.md5Hex(
                new StringBuilder()
                .append(request.getRemoteAddr())
                .append(user.getId())
                .append(user.getUsername())
                .append(user.getPassword())
                .toString()
            );
    }
    
    public static StudentUser logout(HttpServletRequest request, HttpServletResponse response) {
        StudentUser user = (StudentUser) request.getSession().getAttribute("user");
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("user_token", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        
        return user;
    }
}
