package com.guy.login.spring.security;

import com.google.gson.*;
import com.guy.login.domain.AppUser;
import com.guy.login.repository.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

@Configuration
public class DBAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    JsonDeserializer<Date> deser = new JsonDeserializer<Date>() {
        public Date deserialize(JsonElement json, Type typeOfT,
                                JsonDeserializationContext context) throws JsonParseException {
            return json == null ? null : new Date(json.getAsLong());
        }
    };

    static final Logger LOGGER = LoggerFactory.getLogger(DBAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LOGGER.info("========================== DBAuthenticationProvider ");
        String name = authentication.getName().toLowerCase();;
        String password = authentication.getCredentials().toString();
        LOGGER.info("username : {}",name);

        if (checkAuthentication(name, password)) {
            LOGGER.info("DB Cretential Success");
            return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
        } else {
            LOGGER.error("DB Cretential not found !!");
            return null;
        }
    }


    private boolean checkAuthentication(String userName, String rawPassword) {
        try {
            AppUser user = userRepository.findByEmail(userName);
            if(null == user){
                return false;
            }else{
                return bCryptPasswordEncoder.matches(rawPassword,user.getPassword());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
