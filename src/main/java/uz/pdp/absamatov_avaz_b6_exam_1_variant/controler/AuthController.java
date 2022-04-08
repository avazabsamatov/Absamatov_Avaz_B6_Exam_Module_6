package uz.pdp.absamatov_avaz_b6_exam_1_variant.controler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import uz.pdp.absamatov_avaz_b6_exam_1_variant.dto.UserDto;
import uz.pdp.absamatov_avaz_b6_exam_1_variant.security.JWTFilter;
import uz.pdp.absamatov_avaz_b6_exam_1_variant.security.JWTProvider;
import uz.pdp.absamatov_avaz_b6_exam_1_variant.security.JwtAuthenticationEntryPoint;
import uz.pdp.absamatov_avaz_b6_exam_1_variant.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    JWTProvider jwtProvider;

    @Autowired
    UserService userService;


    @PostMapping("/login")
    public void login(@RequestBody UserDto userDto, HttpServletResponse response){
        UserDetails userDetails = userService.loadUserByUsername(userDto.getUsername());
        String generatedToken = jwtProvider.generatedToken(userDetails.getUsername());

        String refreshToken = jwtProvider.refreshToken(userDetails.getUsername());

        Map<String , String> tokens = new HashMap<>();
        tokens.put("access_token",generatedToken);
        tokens.put("refresh_token",refreshToken);
        try {
            new ObjectMapper().writeValue(response.getOutputStream(), tokens);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
