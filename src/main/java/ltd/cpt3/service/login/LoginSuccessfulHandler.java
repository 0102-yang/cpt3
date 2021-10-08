package ltd.cpt3.service.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import ltd.cpt3.entity.dto.UserDto;
import ltd.cpt3.util.JwtUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * @author yang
 */
@Service
public class LoginSuccessfulHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper mapper;

    public LoginSuccessfulHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // 设置响应头
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        // 获取用户以及创建token
        UserDto user = (UserDto) authentication.getPrincipal();
        String username = user.getUsername();
        long expiredDays = 7;
        String token = JwtUtils.createToken(username, new TreeMap<>(), expiredDays, TimeUnit.DAYS);

        TreeMap<String, Object> values = new TreeMap<>();
        values.put("message", "Login success");
        values.put("username", user.getUsername());
        values.put("token", token);

        PrintWriter printWriter = response.getWriter();
        printWriter.write(this.mapper.writeValueAsString(values));
        printWriter.close();
    }

}
