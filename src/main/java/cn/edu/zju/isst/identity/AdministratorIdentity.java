package cn.edu.zju.isst.identity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.util.WebUtils;

import cn.edu.zju.isst.dao.AdministratorDao;
import cn.edu.zju.isst.entity.Administrator;

public class AdministratorIdentity {
    
    public static Administrator read(HttpServletRequest request, AdministratorDao administratorDao) {
        Administrator administrator = (Administrator) request.getSession().getAttribute("administrator");
        if (null == administrator) {
            Cookie cookie = WebUtils.getCookie(request, "administrator_token");
            if (null != cookie) {
                String[] s = new String(Base64.decodeBase64(cookie.getValue())).split(":", 2);
                if (s.length == 2) {
                    administrator = administratorDao.find(Integer.valueOf(s[0]).intValue());
                    if (null != administrator) {
                        if (generateLoginEncryptedToken(request, administrator).equals(s[1])) {
                            request.getSession().setAttribute("administrator", administrator);
                        } else {
                            administrator = null;
                        }
                    }
                }
            }
            
            if (null == administrator) {
                administrator = new Administrator();
                request.getSession().setAttribute("administrator", administrator);
            }
        }
        
        return administrator;
    }
    
    public static void login(HttpServletRequest request, HttpServletResponse response, Administrator administrator, boolean isRememberMe) {
        request.getSession().setAttribute("administrator", administrator);
        if (isRememberMe) {
            Cookie cookie = new Cookie("administrator_token", generateLoginToken(request, administrator));
            cookie.setMaxAge(31536000);
            response.addCookie(cookie);
        }
    }
    
    private static String generateLoginToken(HttpServletRequest request, Administrator administrator) {
        String token = new StringBuilder()
        .append(administrator.getId())
        .append(":")
        .append(generateLoginEncryptedToken(request, administrator))
        .toString();
        return new String(Base64.encodeBase64(token.getBytes()));
    }
    
    private static String generateLoginEncryptedToken(HttpServletRequest request, Administrator administrator) {
        return DigestUtils.md5Hex(
                new StringBuilder()
                .append(request.getRemoteAddr())
                .append(administrator.getId())
                .append(administrator.getUsername())
                .append(administrator.getPassword())
                .toString()
            );
    }
    
    public static void logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("administrator");
        Cookie cookie = new Cookie("administrator_token", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
