package dataFactory;

import pojo.UserPojo;

public class UserDataFactory {

    public static UserPojo createUser(){
        UserPojo userPojo = new UserPojo();
        userPojo.setUsuarioLogin("luffy");
        userPojo.setUsuarioSenha("luffy");

        return userPojo;
    }
}
