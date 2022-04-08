package uz.pdp.absamatov_avaz_b6_exam_1_variant.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {
        final String expired = (String) request.getAttribute("expired");
        System.out.println(expired);
        if (expired != null){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,expired);
        }else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid login details");
        }
    }
}
