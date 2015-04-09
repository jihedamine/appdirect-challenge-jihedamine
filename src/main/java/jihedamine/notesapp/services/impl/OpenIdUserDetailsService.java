package jihedamine.notesapp.services.impl;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAuthenticationToken;

/**
 * @author Jihed Amine Maaref jihedamine@gmail.com
 */
public class OpenIdUserDetailsService implements AuthenticationUserDetailsService<OpenIDAuthenticationToken> {

    @Override
    public UserDetails loadUserDetails(OpenIDAuthenticationToken openIDAuthenticationToken) throws UsernameNotFoundException {
        return new User(openIDAuthenticationToken.getName(), "",
                AuthorityUtils.createAuthorityList("ROLE_USER"));
    }

}