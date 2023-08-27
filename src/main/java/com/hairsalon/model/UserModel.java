package com.hairsalon.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserModel {
    private Integer id;
    private String userName;
    private String email;
    private boolean enableLogin;
    private String password;
    private Map<String, Object> roleCustom;
}
