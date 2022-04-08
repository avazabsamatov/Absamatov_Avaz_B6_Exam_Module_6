package uz.pdp.absamatov_avaz_b6_exam_1_variant.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.absamatov_avaz_b6_exam_1_variant.repository.UserRepository;
import uz.pdp.absamatov_avaz_b6_exam_1_variant.service.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    JWTProvider jwtProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        UserDetails userDetails = getUserDetails(request);
        if(userDetails !=null){
            if(userDetails.isEnabled()
            && userDetails.isAccountNonExpired()
            && userDetails.isAccountNonLocked()
            && userDetails.isCredentialsNonExpired()) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);


    }

    private UserDetails getUserDetails(HttpServletRequest request) {
        try {
            String tokenClient = request
                    .getHeader("Authorization");
            if(tokenClient !=null){
                if(tokenClient.startsWith("Bearer ")){
                   tokenClient = tokenClient.substring(7);
                   if(jwtProvider.validateToken(tokenClient,request)){
                       String usernameFromToken = jwtProvider.getUsernameFromToken(tokenClient);
                       return userService.loadUserByUsername(usernameFromToken);
                   }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }return null;
    }
}
