package model;

import org.apache.commons.lang3.RandomStringUtils;

public class UserCreateRandomApi {

    private String email;
    private String password;
    private String name;

    public UserCreateRandomApi(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public UserCreateRandomApi() {

    }

    public static UserCreateRandomApi getRandomUser() {
        final String userEmail = RandomStringUtils.randomAlphabetic(8) + "@yandex.ru";
        final String userPassword = RandomStringUtils.randomAlphabetic(8);
        final String userName = RandomStringUtils.randomAlphabetic(8);
        return new UserCreateRandomApi(userEmail, userPassword, userName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}