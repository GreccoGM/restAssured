package org.gerenciador.Utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;

import static io.restassured.RestAssured.given;

public class Login extends BaseApi{

    public String autenticarAdministrador()
    {
        String tokenAdmin =
                given()
                        .basePath("/v1")
                        .body("{\n" +
                                "\"email\": \"admin@email.com\",\n" +
                                "\"senha\": \"654321\"\n" +
                                "}")
                        .contentType(ContentType.JSON)
                .when()
                        .post("/auth")
                .then()
                        .extract().path("data.token")
                ;
        return tokenAdmin;
    }

    public String autenticarUsuario()
    {
        String tokenUsuario =
                given()
                        .basePath("/v1")
                        .body("{\n" +
                                "\"email\": \"usuario@email.com\",\n" +
                                "\"senha\": \"123456\"\n" +
                                "}")
                        .contentType(ContentType.JSON)
                .when()
                        .post("/auth")
                .then()
                        .extract().path("data.token")
                ;
        return tokenUsuario;
    }
}
