package com.hairsalon.service;

import com.hairsalon.model.OptionModel;
import com.hairsalon.model.PermissionModel;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Service("customRoleService")
public class CustomRoleService {
    public static final String CREATE = "Create";
    public static final String UPDATE = "Update";
    public static final String DELETE = "Delete";

    public static String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7, token.length());
        }

        return null;
    }

    public boolean canCreate(String option, UserDetailsImp userDetail) {
        return authorization(option, userDetail, CREATE);
    }

    public boolean canUpdate(String option, UserDetailsImp userDetail) {
        return authorization(option, userDetail, UPDATE);
    }

    public boolean canDelete(String option, UserDetailsImp userDetail) {
        return authorization(option, userDetail, DELETE);
    }

    public boolean isTheSameUser(Integer empId, UserDetailsImp userDetail) {
        if (userDetail.getId().equals(empId)) {
            return true;
        }
        return false;
    }

    public boolean authorization(String option, UserDetailsImp userDetail, String action) {
        // loop through options in role
        for (OptionModel optionModel : userDetail.getRoles().getOptions()) {
            // If same with option => // loop through permission in option
            if (optionModel.getName().equals(option)) {
                for (PermissionModel p : optionModel.getPermissions()) {
                    if (p.getName().equals(action)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
