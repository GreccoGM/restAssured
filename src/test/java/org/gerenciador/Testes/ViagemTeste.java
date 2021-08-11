package org.gerenciador.Testes;

import org.gerenciador.Dados.MapDados;
import org.gerenciador.Dados.ViagemDados;

import org.apache.http.HttpStatus;
import org.gerenciador.Utils.BaseApi;
import org.gerenciador.Utils.Login;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ViagemTeste extends BaseApi {

    public static String tokenUsuario;
    public static String tokenAdmin;
    MapDados dados = new MapDados();
    ViagemDados dadosViagem = new ViagemDados();

    @BeforeClass
    public static void gerarToken(){
      Login autenticacao = new Login();
      tokenUsuario = autenticacao.autenticarUsuario();
      tokenAdmin = autenticacao.autenticarAdministrador();
    }

@Test
    public void validarSucessoCadastroViagem(){
        Map dadosViagem = dados.cadastrarViagem();

        given()
              .spec(reqSpec)
              .header("Authorization", this.tokenAdmin)
              .body(dadosViagem)
        .when()
              .post("/viagens")
        .then()
              .spec(resSpec)
              .statusCode(HttpStatus.SC_CREATED)
              .body(is(not(nullValue())))
              .body("data.id", is(not(empty())))
              .log().all()
      ;
    }

@Test
    public void validarCamposObrigatoriosCadastroViagem(){
        given()
              .spec(reqSpec)
              .header("Authorization", this.tokenAdmin)
              .body("{}")
       .when()
              .post("/viagens")
       .then()
              .spec(resSpec)
              .statusCode(HttpStatus.SC_BAD_REQUEST)
              .body("errors", hasSize(2))
              .body("message", is ("Validation failed for object='viagemDto'. Error count: 2"))
               // .log().all()
      ;
    }

///.....
@Test
    public void validarRegiaoViagem(){
        given()
                .spec(reqSpec)
                .header("Authorization", this.tokenUsuario)
        .when()
                .get("/viagens")
        .then()
                .spec(resSpec)
                .statusCode(HttpStatus.SC_OK)
                .body("data.find{it.id == "+dadosViagem.getIdUltimaViagemCadastrada()+"}.regiao",  anyOf(containsString("Sul"),
                                                                                                            containsString("Nordeste"),
                                                                                                            containsString("Norte"),
                                                                                                            containsString("Centro-Oeste"),
                                                                                                            containsString("Sudeste")))
        ;
    }

@Test
    public void validarQuantidadeRetornoDeViagens(){
        given()
                .spec(reqSpec)
                .header("Authorization", this.tokenUsuario)
        .when()
                .get("/viagens")
        .then()
                .spec(resSpec)
                .statusCode(HttpStatus.SC_OK)
                .body(is(not(nullValue())))
                .body("data", hasSize(dadosViagem.contadorViagens()))
        ;
    }

@Test
    public void validarSucessoEdicaoViagem(){
        Map dadosViagemB = dados.editarViagem();

        Integer idViagem = dadosViagem.getIdViagem();

            given()
                    .spec(reqSpec)
                    .header("Authorization", this.tokenAdmin)
                    .pathParam("id", idViagem)
                    .body(dadosViagemB)
           .when()
                    .put("/viagens/{id}")
           .then()
                    .spec(resSpec)
                    .statusCode(HttpStatus.SC_NO_CONTENT)
           ;
    }

@Test
    public void validarEdicaoViagem(){

        given()
                .spec(reqSpec)
                .header("Authorization", this.tokenUsuario)
        .when()
                .get("/viagens")
        .then()
                .spec(resSpec)
                .statusCode(HttpStatus.SC_OK)
                .body(is(not(nullValue())))
                .body("data.find{it.id == 2}.acompanhante", is("Marques")) //"+ idViagemEditada +"
        ;

    }

@Test
    public void validarSucessoExclusaoViagem(){
        given()
                .spec(reqSpec)
                .header("Authorization", this.tokenAdmin)
                .pathParam("id", dadosViagem.getIdViagem())
        .when()
                .delete("/viagens/{id}")
        .then()
                .spec(resSpec)
                .statusCode(HttpStatus.SC_NO_CONTENT)
        ;
    }

@Test
    public void excluirViagemComIdInexistente(){
        given()
                .spec(reqSpec)
                .header("Authorization", this.tokenAdmin)
                .pathParam("id", 999)  //...
        .when()
                .delete("/viagens/{id}")
        .then()
                .spec(resSpec)
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .body("message", equalTo("The entity must not be null!; nested exception is java.lang.IllegalArgumentException: The entity must not be null!"))
        ;
    }
}
