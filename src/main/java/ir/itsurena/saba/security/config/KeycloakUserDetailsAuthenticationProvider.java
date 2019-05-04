package ir.itsurena.saba.security.config;

import ir.itsurena.saba.security.dao.UMSService;
import ir.itsurena.saba.security.dao.UserDao;
import ir.itsurena.saba.security.model.Person;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Component
public class KeycloakUserDetailsAuthenticationProvider extends KeycloakAuthenticationProvider {


    @Autowired
    private UserDao cache ;
    @Autowired
    private UMSService  umsService ;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication != null) {
            KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) super.authenticate(authentication);
            if (token == null) {
                return null;
            }
            String userName =
                    ((KeycloakAuthenticationToken) authentication).
                    getAccount().
                    getKeycloakSecurityContext().
                    getToken().
                    getPreferredUsername();

            Collection<SimpleGrantedAuthority> oldAuthorities = (Collection<SimpleGrantedAuthority>)SecurityContextHolder.
                            getContext().
                            getAuthentication().
                            getAuthorities();

            List<SimpleGrantedAuthority> authorityList = umsService.getAuthoritiesByUserName(userName);
            authorityList.addAll(oldAuthorities);
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                            SecurityContextHolder.getContext().getAuthentication().getCredentials(),
                            authorityList));
            SimpleKeycloakAccount simpleKeycloakAccount=(SimpleKeycloakAccount) authentication.getDetails();
          Set<String> roles = simpleKeycloakAccount.getRoles();
            Person person=new Person(authorityList,roles,userName);
            cache.addUser(userName,person);

            return  SecurityContextHolder.getContext().getAuthentication();
        }
        return SecurityContextHolder.getContext().getAuthentication();
    }


}