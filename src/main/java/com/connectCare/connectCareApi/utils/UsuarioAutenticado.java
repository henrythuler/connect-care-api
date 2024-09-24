package com.connectCare.connectCareApi.utils;

import com.connectCare.connectCareApi.exceptions.NaoAutorizadoException;
import com.connectCare.connectCareApi.models.entities.UserInfoDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UsuarioAutenticado {

    public static UserInfoDetails getUsuarioAutenticado(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null && auth.isAuthenticated()){
            return (UserInfoDetails) auth.getPrincipal();
        }else{
            throw new NaoAutorizadoException();
        }

    }

}
