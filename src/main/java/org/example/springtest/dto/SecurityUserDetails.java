package org.example.springtest.dto;

import org.example.springtest.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class SecurityUserDetails implements UserDetails {
    private User user;

    public SecurityUserDetails(User user) {
        this.user = user;
    }

    /**
     * Make logic with many roles of user (In db add table to save many to many logic)
     **/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        String roleString = this.user.getRoleName();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleString);
        grantedAuthorityList.add(grantedAuthority);
        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Make one more column in db to add Enabled/Disabled logic
     **/
    @Override
    public boolean isEnabled() {
        return true;
    }

}
