package com.devcloud.mall.filter;

import com.devcloud.mall.common.ResultCode;
import com.devcloud.mall.exception.JwtException;
import com.devcloud.mall.utils.HttpResponseUtil;
import com.devcloud.mall.utils.JwtUtil;
import com.devcloud.mall.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author 吴员外
 * @date 2022/10/24 21:36
 * <p>
 * 请求拦截过滤器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        //token为空则放行，后面还有别的过滤器
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            //不return过滤器走后后面的过滤器还会继续执行下面代码
            return;
        }
        //根据token获取用户id
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            HttpResponseUtil.error(response, ResultCode.JWT_ILLEGAL, "Token不合法");
            return;
        }

        //从redis获取用户信息
        UserDetails loginUser = redisCache.getCacheObject(userId);
        if (Objects.isNull(loginUser)) {
            HttpResponseUtil.error(response, ResultCode.JWT_ILLEGAL, "用户认证已过期");
            return;
        }

        //存入SecurityContextHolder

        /*调用三个参数的构造方法，此构造函数仅应由对产生受信任 (即isAuthenticated() = true)
         身份验证令牌感到满意的AuthenticationManager或AuthenticationProvider实现使用。*/
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        //放行
        filterChain.doFilter(request, response);
    }

}
