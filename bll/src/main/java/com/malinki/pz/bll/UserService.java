package com.malinki.pz.bll;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.malinki.pz.lib.UserResponse;
import com.malinki.pz.lib.UserUVM;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserService {
    private Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private UserOperations userOperations;

    @Autowired
    private SessionTable sessionTable;

    public SessionStorage login(String requestBody, HttpServletResponse response){
        UserResponse userResponse = userOperations.loginUser(parseToUserUVM(requestBody));
        int result = userResponse.getResult();
        response.setStatus(result);

        if(result == HttpServletResponse.SC_OK)
            return sessionTable.getUserSession(userResponse.getUserUVM());
        else
            return null;
    }

    public void register(String requestBody, HttpServletResponse response){
        int result = userOperations.registerUser(parseToUserUVM(requestBody));
        response.setStatus(result);
    }

    public void logout(String requestBody){
        userOperations.logoutUser(parseToUserUVM(requestBody));
    }

    private UserUVM parseToUserUVM(String requestBody) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        UserUVM user = null;

        try {
            user = mapper.readValue(requestBody, UserUVM.class);
        } catch (IOException e) {
            logger.log(Level.ERROR, e.toString());
        }

        return user;
    }
}
