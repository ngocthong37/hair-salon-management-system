package com.hairsalon.service;

import com.hairsalon.model.OptionModel;
import com.hairsalon.model.RoleDetailModel;
import com.hairsalon.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsImp implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsImp.class);

    private Integer id;
    private String userName;
    private String email;
    private String password;
    private RoleDetailModel roles;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImp build(UserModel userModel) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        RoleDetailModel roleDetailModel = null;
        try {
            roleDetailModel = userModel.getRoleDetailModel();
            for (OptionModel op: roleDetailModel.getOptions()) {
                authorities.add(new SimpleGrantedAuthority(op.getName()));
            }
            LOGGER.info(authorities.toString());
        } catch (Exception e) {
            LOGGER.error("Have error at build(): ", e);
        }
        return new UserDetailsImp(userModel.getId(), userModel.getUserName(), userModel.getEmail(), userModel.getPassword(),
                roleDetailModel, authorities);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return userName;
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

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImp user = (UserDetailsImp) o;
        return Objects.equals(id, user.id);
    }

}
