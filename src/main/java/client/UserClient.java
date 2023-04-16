package client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import model.LoginUser;
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

    public String getToken(String email, String password) {
        Response login = loginUser(email, password);
        String token = login
                .then()
                .extract()
                .body()
                .path("accessToken");
        if (token != null) {
            return token.substring(7);
        } else return null;
    }

    public void deleteUserForRegistrationTest(String email, String password) {
        String token = getToken(email, password);
        if (token != null)
            given()
                    .auth()
                    .oauth2(token).delete(USER_AUTH)
                    .then().log().all();

    }

    public Response loginUser(String email, String password) {
        LoginUser jsonBody = new LoginUser(email, password);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(jsonBody)
                        .when()
                        .post(USER_LOGIN);
        return response;
    }


}