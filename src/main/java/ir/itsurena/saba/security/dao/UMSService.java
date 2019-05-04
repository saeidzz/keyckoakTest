package ir.itsurena.saba.security.dao;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UMSService {


    public List<SimpleGrantedAuthority> getAuthoritiesByUserName(String userName){
        ArrayList<SimpleGrantedAuthority> auths=new ArrayList<>();
        auths.add(new SimpleGrantedAuthority(userName+1));
        auths.add(new SimpleGrantedAuthority(userName+2));
        auths.add(new SimpleGrantedAuthority(userName+3));
        auths.add(new SimpleGrantedAuthority(userName+4));
        return auths;
    }



}
