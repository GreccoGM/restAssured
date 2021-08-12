package org.gerenciador.utils;

import io.restassured.http.ContentType;

import org.gerenciador.dados.LoginDados;
import static io.restassured.RestAssured.given;

public class Login extends BaseApi{

    LoginDados loginDados = new LoginDados();

    public String autenticarAdministrador()
    {
        String tokenAdmin =
                given()
                        .basePath("/v1")
                        .body(loginDados.dadosAdmin())
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
                        .body(loginDados.dadosUsuario())
                        .contentType(ContentType.JSON)
                .when()
                        .post("/auth")
                .then()
                        .extract().path("data.token")
                ;
        return tokenUsuario;
    }
}
