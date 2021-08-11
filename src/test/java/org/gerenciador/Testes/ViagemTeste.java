package org.gerenciador.Testes;

import org.apache.http.HttpStatus;
import org.gerenciador.Dados.Dados;
import org.gerenciador.Utils.BaseApi;
import org.gerenciador.Utils.Login;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ViagemTeste extends BaseApi {

    public static String tokenUsuario;
    public static String tokenAdmin;
    //public ArrayList<Integer> idViagemEditada = new ArrayList();
    public static Integer tamanho;

    Dados dados = new Dados();

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
              .header("Authorization", tokenAdmin)
              .body(dadosViagem)
       .when()
              .post("/viagens")
      .then()
              .spec(resSpec)
              .statusCode(HttpStatus.SC_CREATED)
              .body(is(not(nullValue())))
              .body("data.id", is(not(empty())))
              //.log().all()
      ;
    }

@Test
    public void validarCamposObrigatoriosCadastroViagem(){
        given()
              .spec(reqSpec)
              .header("Authorization", tokenAdmin)
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
                .header("Authorization", tokenUsuario)
        .when()
                .get("/viagens")
        .then()
                .spec(resSpec)
                .statusCode(HttpStatus.SC_OK)
                .body("data.findAll{it.id == "+getIdViagem()+"}.regiao",  anyOf(is("Sul"),
                                                                                             is("Nordeste"),
                                                                                             is("Norte"),
                                                                                             is("Centro-Oeste"),
                                                                                            is("Sudeste")))
                /*.body("data[0].regiao", anyOf(containsString("Su"),
                                                                   containsString("Nodeste"),
                                                                   containsString("Note"),
                                                                   containsString("Cetro-Oeste"),
                                                                   containsString("Sueste")))*/
        ;
    }

@Test
    public void validarQuantidadeRetornoDeViagens(){
        given()
                .spec(reqSpec)
                .header("Authorization", tokenUsuario)
        .when()
                .get("/viagens")
        .then()
                .spec(resSpec)
                .statusCode(HttpStatus.SC_OK)
                .body(is(not(nullValue())))
                .body("data", hasSize(contadorViagens()))
        ;
    }

@Test
    public void validarSucessoEdicaoViagem(){
        Map dadosViagem = dados.editarViagem();

        Integer idViagem = getIdViagem();
            given()
                    .spec(reqSpec)
                    .header("Authorization", tokenAdmin)
                    .pathParam("id", idViagem)
                    .body(dadosViagem)
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
                .header("Authorization", tokenUsuario)
        .when()
                .get("/viagens")
        .then()
                .spec(resSpec)
                .statusCode(HttpStatus.SC_OK)
                .body(is(not(nullValue())))
                .body("data.findAll{it.id == 1}.acompanhante", hasItem("Maques")) //"+ idViagemEditada +"
        ;

    }

@Test
    public void validarSucessoExclusaoViagem(){
        given()
                .spec(reqSpec)
                .header("Authorization", tokenAdmin)
                .pathParam("id", getIdViagem())
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
                .header("Authorization", tokenAdmin)
                .pathParam("id", 999)
        .when()
                .delete("/viagens/{id}")
        .then()
                .spec(resSpec)
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .body("message", equalTo("The entity must not be null!; nested exception is java.lang.IllegalArgumentException: The entity must not be null!"))
        ;
    }

    private Integer contadorViagens(){
        ArrayList<String>  contadorV =
          given()
                .spec(reqSpec)
                .header("Authorization", tokenUsuario)
            .when()
                    .get("/viagens")
            .then()
                    .spec(resSpec)
                    .extract().path("data.id")
            ;
        tamanho = contadorV.size();
        return tamanho;
    }

    public Integer getIdViagem(){
        Integer idViagem;
            ArrayList<Integer> idViagens =
                given()
                    .spec(reqSpec)
                    .header("Authorization", tokenUsuario)
                .when()
                    .get("/viagens")
                .then()
                    .spec(resSpec)
                    .statusCode(HttpStatus.SC_OK)
                    .extract().path("data.id")
               ;

            Integer tamanho = contadorViagens();

            Random randomico = new Random();
            Integer posicao = randomico.nextInt(tamanho);
            idViagem = idViagens.get(posicao);

        System.out.println(idViagens);
        System.out.println(idViagem);

            return idViagem;
    }

}
