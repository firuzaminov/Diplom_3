package client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.UserCreateRandomApi;

import static io.restassured.RestAssured.given;

public class UserClient extends Client {


    public ValidatableResponse createUser(UserCreateRandomApi user) {
        return given()
                .spec(getSpec().log().all())
                .when()
                .body(user)
                .post(USER_REGISTER)
                .then().log().all();
    }

    @Step("Deleting user")
    public ValidatableResponse deleteUser(String token) {
        return given()
                .header("Authorization", token)
                .spec(getSpec()).log().all()
                .when()
                .delete(USER_AUTH)
                .then().log().all();
    }
}