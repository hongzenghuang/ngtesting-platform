package cn.linkr.testspace.websocket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import cn.linkr.testspace.bean.ApplicationScopeBean;
import cn.linkr.testspace.entity.EvtClient;
import cn.linkr.testspace.entity.SysUser;
import cn.linkr.testspace.util.Constant;
import cn.linkr.testspace.util.SpringContextHolder;

public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

    private static Logger logger = LoggerFactory.getLogger(WebSocketHandshakeInterceptor.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {

        ApplicationScopeBean scopeBean = SpringContextHolder.getBean(ApplicationScopeBean.class);

        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession httpSession = servletRequest.getServletRequest().getSession(true);
            
            String test = (String) httpSession.getAttribute("TEST");
            
            SysUser user = null;
            if (httpSession.getAttribute(Constant.HTTP_SESSION_USER_KEY) != null) {
            	user = (SysUser) httpSession.getAttribute(Constant.HTTP_SESSION_USER_KEY);
            }
            
            if (user != null) {
                attributes.put(Constant.WEBSOCKET_CLIENT_KEY, user.getId().toString());
                attributes.put("somthing", "somthing");
                return true;
            }
        }
        
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception exception) {
    }

}
