package ltd.cpt3.service.login;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * @author yang
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;

    private final Object token;

    public JwtAuthenticationToken(Object principal, Object token) {
        super(null);
        this.principal = principal;
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

}
