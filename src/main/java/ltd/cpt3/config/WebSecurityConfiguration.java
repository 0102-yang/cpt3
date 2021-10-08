package ltd.cpt3.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import ltd.cpt3.service.login.JwtWebSecurityFilter;
import ltd.cpt3.service.login.LoginFailureHandler;
import ltd.cpt3.service.login.LoginSuccessfulHandler;
import ltd.cpt3.service.user.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.TreeMap;

/**
 * @author yang
 * @date Oct 06 2021 3:02 PM
 */
@Component
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    public WebSecurityConfiguration(UserDetailsServiceImpl userService, LoginSuccessfulHandler successfulHandler, LoginFailureHandler failureHandler, JwtWebSecurityFilter filter) {
        this.userService = userService;
        this.successfulHandler = successfulHandler;
        this.failureHandler = failureHandler;
        this.filter = filter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final UserDetailsServiceImpl userService;

    private final LoginSuccessfulHandler successfulHandler;

    private final LoginFailureHandler failureHandler;

    private final JwtWebSecurityFilter filter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 禁用Session和CSRF
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();
        http.rememberMe().disable();

        // Authorize configuration.
        http.authorizeRequests().antMatchers("/register").permitAll().antMatchers("/doctors", "/user/**").authenticated();

        http.exceptionHandling().authenticationEntryPoint((httpServletRequest, httpServletResponse, e) -> {
            ObjectMapper mapper = new ObjectMapper();
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            TreeMap<String, Object> values = new TreeMap<>();
            values.put("message", "Need login");

            PrintWriter printWriter = httpServletResponse.getWriter();
            printWriter.write(mapper.writeValueAsString(values));
            printWriter.close();
        });

        // Login configuration.
        http.formLogin().loginProcessingUrl("/login").successHandler(this.successfulHandler).failureHandler(this.failureHandler);

        http.addFilterAfter(this.filter, UsernamePasswordAuthenticationFilter.class);
    }

}
