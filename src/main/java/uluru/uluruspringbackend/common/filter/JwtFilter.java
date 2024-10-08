package uluru.uluruspringbackend.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uluru.uluruspringbackend.common.response.CommonResponse;
import uluru.uluruspringbackend.common.security.token.JwtTokenProvider;

import java.io.IOException;
import java.util.Collections;

@Slf4j
@Component
@NoArgsConstructor
public class JwtFilter implements Filter {


    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String requestURI = httpRequest.getRequestURI();

        // 필터 로직을 수행하지 않고 다음 필터로 이동
        if (!FilterConfig.isFilteringUri(requestURI)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 인증이 필요한 Route의 경우 필터 로직 수행
        // Bearer Token이 들어온 경우만을 가정하고 Authorization Header를 Split
        String token = httpRequest.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.split(" ")[1];
            if (jwtTokenProvider.validateAccessToken(token)) {
                // 인증 정보를 SecurityContext에 저장
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(jwtTokenProvider.getTokenInfo(token), null, Collections.emptyList());

                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        // 토큰이 유효하지 않은 경우 CommonResponse로 응답
        ObjectMapper objectMapper = new ObjectMapper();
        CommonResponse commonResponse = new CommonResponse(false, HttpStatus.UNAUTHORIZED, "Invalid Access Token", null);
        String jsonResponse = objectMapper.writeValueAsString(commonResponse);

        httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpResponse.setContentType("application/json");
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.getWriter().write(jsonResponse);

    }
}
