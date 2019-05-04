package ir.itsurena.saba.security.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Person implements Serializable {

    private List<SimpleGrantedAuthority> simpleGrantedAuthorities;
    private Set<String> roles;
   private String userName;


    public Person() {
    }

    public Person(List<SimpleGrantedAuthority> simpleGrantedAuthorities, Set<String> roles, String userName) {
        this.simpleGrantedAuthorities = simpleGrantedAuthorities;
        this.roles = roles;
        this.userName = userName;
    }

    public List<SimpleGrantedAuthority> getSimpleGrantedAuthorities() {
        return simpleGrantedAuthorities;
    }

    public void setSimpleGrantedAuthorities(List<SimpleGrantedAuthority> simpleGrantedAuthorities) {
        this.simpleGrantedAuthorities = simpleGrantedAuthorities;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "simpleGrantedAuthorities=" + simpleGrantedAuthorities.toString() +
                ", roles=" + roles.toString() +
                ", userName='" + userName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(simpleGrantedAuthorities, person.simpleGrantedAuthorities) &&
                Objects.equals(roles, person.roles) &&
                Objects.equals(userName, person.userName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(simpleGrantedAuthorities, roles, userName);
    }
}
