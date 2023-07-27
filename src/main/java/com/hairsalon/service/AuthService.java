package com.hairsalon.service;

import com.hairsalon.entity.LoginRequest;
import com.hairsalon.entity.Permission;
import com.hairsalon.entity.ResponseObject;
import com.hairsalon.model.OptionModel;
import com.hairsalon.model.PermissionModel;
import com.hairsalon.model.RoleDetailModel;
import com.hairsalon.model.UserModel;
import com.hairsalon.respository.IUser;
import com.hairsalon.respository.imp.PermissionImp;
import com.hairsalon.respository.imp.RoleImp;
import com.hairsalon.security.jwt.JWTUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    IUser userRepository;

    @Autowired
    JWTUtils jwtUtils;

    @Autowired
    RoleImp roleRepository;

    @Autowired
    PermissionImp permissionImp;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        List<Integer> roleIds = new ArrayList<>();
        List<RoleDetailModel> roleDetailModels = new ArrayList<>();
        RoleDetailModel roleDetailModel = new RoleDetailModel();
        String jwt = "";
        Map<String, Object> result = new TreeMap<>();
        UserModel userModel = userRepository.findByUserName(loginRequest.getUsername());
        if (userModel == null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("ERROR", "Tài khoản không tồn tại", ""));
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        if (userDetails != null) {
            roleIds = roleRepository.findAllRoleIdByUserId(userDetails.getId());
            for (Integer roleId : roleIds) {
                roleDetailModel = roleRepository.findRoleDetailsByRoleId(roleId);
                roleDetailModels.add(roleDetailModel);
            }
            // Summary of role list
            RoleDetailModel roleDetailResult = new RoleDetailModel();

            roleDetailResult = roleDetailModels.get(0);
            Map<String, Object> optionMaps = new LinkedHashMap<>();
            List<Permission> permissions = permissionImp.findAll();
            if (roleDetailModels.size() >= 1) {
                // check each role's permission
                for (int i = 0; i < roleDetailModels.size(); i++) {
                    List<OptionModel> optionsModels = roleDetailModels.get(i).getOptions();
                    for (int j = 0; j < optionsModels.size(); j++) {
                        int perNeededLength = roleDetailResult.getOptions().get(j).getPermissions().size();
                        int perAllLegth = permissions.size();
                        Map<String, Object> permissionsMap = new LinkedHashMap<>();
                        if(perAllLegth == perNeededLength) {
                            for (int k = 0; k < permissions.size(); k++) {
                                PermissionModel permissionNeeded = roleDetailResult.getOptions().get(j).getPermissions()
                                        .get(k);
                                if (permissions.get(k).getId() == permissionNeeded.getId()) {
                                    permissionsMap.put(permissions.get(k).getPermissionType(), true);
                                }else {
                                    permissionsMap.put(permissions.get(k).getPermissionType(), false);
                                }
                            }
                        }else {
                            for (int k = 0; k < perAllLegth; k++) {
                                boolean isSelect = false;
                                for(int m = 0; m < perNeededLength; m++) {
                                    if( permissions.get(k).getId() == roleDetailResult.getOptions().get(j).getPermissions().get(m).getId()) {
                                        permissionsMap.put(permissions.get(k).getPermissionType(), true);
                                        isSelect = true;
                                    }
                                }
                                if(!isSelect) {
                                    permissionsMap.put(permissions.get(k).getPermissionType(), false);
                                }
                            }
                        }
                        optionMaps.put(optionsModels.get(j).getName(), permissionsMap);
                    }
                }
            }

            userModel.setRoleCustom(optionMaps);
            jwt = jwtUtils.generateJwtToken(userDetails);
            result.put("accessToken", jwt);
            result.put("userInfo", userModel);
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Đăng nhập hoàn tất", result));
    }

}
