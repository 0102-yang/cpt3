package ltd.cpt3.service.login;

import io.jsonwebtoken.Claims;
import ltd.cpt3.util.JwtUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author yang
 */
@Service
public class JwtWebSecurityFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");

        token = token != null ? token : "";

        Optional<Claims> claims = JwtUtils.getClaims(token);

        if (claims.isPresent()) {
            Claims c = claims.get();
            String principal = c.getSubject();

            // 验证成功,填充已被认证的证书
            JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(principal, token);
            authenticationToken.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }

}
