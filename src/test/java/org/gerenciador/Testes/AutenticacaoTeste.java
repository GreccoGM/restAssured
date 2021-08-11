package org.gerenciador.Testes;

import org.gerenciador.Utils.BaseApi;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AutenticacaoTeste extends BaseApi {
    @Test
    public void validarAdministrador()
    {
        given()
           .spec(reqSpec)
           .body("{\n" +
                   "\"email\": \"admin@email.com\",\n" +
                   "\"senha\": \"654321\"\n" +
                 "}")
        .when()
            .post("/auth")
        .then()
            .spec(resSpec)
            .body("data.token",is(not(nullValue())))
        ;
    }

    @Test
    public void validarUsuario()
    {
        given()
            .spec(reqSpec)
            .body("{\n" +
                  "\"email\": \"usuario@email.com\",\n" +
                  "\"senha\": \"123456\"\n" +
                  "}")
        .when()
            .post("/auth")
        .then()
            .spec(resSpec)
            .body("data.token",is(not(nullValue())))
        ;
    }
}
