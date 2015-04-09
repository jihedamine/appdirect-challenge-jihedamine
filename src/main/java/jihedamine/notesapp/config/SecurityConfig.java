package jihedamine.notesapp.config;

/**
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */

import jihedamine.notesapp.services.impl.OpenIdUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    Environment env;

    @Bean
    public OAuthRestTemplate getAuth() {
        BaseProtectedResourceDetails resourceDetails = new BaseProtectedResourceDetails();
        resourceDetails.setConsumerKey(env.getProperty("appdirect.consumer.key"));
        resourceDetails.setSharedSecret(new SharedConsumerSecretImpl(env.getProperty("appdirect.secret")));
        return new OAuthRestTemplate(resourceDetails);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/login", "/appdirect/**", "/user/**").permitAll()
                .anyRequest().authenticated();
        http
                .logout()
                .logoutSuccessHandler(new RedirectToMarketplaceLogoutSuccessHandler());
        http
                .openidLogin()
                .permitAll()
                .authenticationUserDetailsService(new OpenIdUserDetailsService())
                .attributeExchange("https://www.notesapp.com.*")
                .attribute("email")
                .type("http://axschema.org/contact/email")
                .required(true)
                .and()
                .attribute("firstname")
                .type("http://axschema.org/namePerson/first")
                .required(true)
                .and()
                .attribute("lastname")
                .type("http://axschema.org/namePerson/last")
                .required(true);
    }

    private class RedirectToMarketplaceLogoutSuccessHandler implements LogoutSuccessHandler {

        @Override
        public void onLogoutSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException, ServletException {
            String marketplaceBaseUrl = request.getParameter("url");
            if (marketplaceBaseUrl != null) {
                response.sendRedirect(marketplaceBaseUrl);
            } else {
                response.sendRedirect("/?logout");
            }
        }
    }

}


