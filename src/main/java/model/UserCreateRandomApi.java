package model;

import org.apache.commons.lang3.RandomStringUtils;

public class UserRandomApi {

    private String email;
    private String password;
    private String name;

    public UserRandomApi(String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public UserRandomApi() {

    }

    public static UserRandomApi getRandomUser() {
        final String userEmail = RandomStringUtils.randomAlphabetic(8) + "@yandex.ru";
        final String userPassword = RandomStringUtils.randomAlphabetic(8);
        final String userName = RandomStringUtils.randomAlphabetic(8);
        return new UserRandomApi(userEmail, userPassword, userName);
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}