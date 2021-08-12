package org.gerenciador.testes;

import org.gerenciador.utils.MapDados;
import org.gerenciador.dados.ViagemDados;

import org.apache.http.HttpStatus;
import org.gerenciador.utils.BaseApi;
import org.gerenciador.utils.Login;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ViagemTeste extends BaseApi {

    public static String tokenUsuario;
    public static String tokenAdmin;
    final Integer idViagemAlterada = null;
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

        given()
                .spec(reqSpec)
                .header("Authorization", this.tokenUsuario)
        .when()
                .get("/viagens")
        .then()
                .spec(resSpec)
                .statusCode(HttpStatus.SC_OK)
                .body(is(not(nullValue())))
                .body("data.find{it.id == "+idViagem+"}.acompanhante", is("Marques")) //"+ idViagemEditada +"
                .body("data.find{it.id == "+idViagem+"}.localDeDestino", equalTo("Canoas"))
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
