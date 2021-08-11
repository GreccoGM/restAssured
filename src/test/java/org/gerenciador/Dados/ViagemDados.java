package org.gerenciador.Dados;

import org.gerenciador.Utils.Login;
import org.gerenciador.Utils.BaseApi;

import java.util.ArrayList;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class ViagemDados extends BaseApi {

    Login autenticacao = new Login();
    String tokenUsuario = autenticacao.autenticarUsuario();
    String tokenAdmin = autenticacao.autenticarAdministrador();

    public ArrayList<Integer> reqId(){

        ArrayList<Integer>  idViagens =
          given()
                .spec(reqSpec)
                .header("Authorization", this.tokenUsuario)
          .when()
                .get("/viagens")
          .then()
                .spec(resSpec)
                .extract().path("data.id")
            ;

        return idViagens;
    }

    public Integer contadorViagens(){
        ArrayList<Integer> viagens = reqId();
        Integer tamanho = viagens.size();

        return tamanho;
    }

    public Integer getIdUltimaViagemCadastrada(){
        Integer idUltimaViagem;
        ArrayList<Integer> viagens = reqId();

        Integer ultimaPosicao = (viagens.size()-1);
        idUltimaViagem = viagens.get(ultimaPosicao);

/*System.out.println(ultimaPosicao);
System.out.println(viagens);
System.out.println(idUltimaViagem);*/

        return idUltimaViagem;
    }

    public Integer getIdViagem(){
        Integer idAleatorioViagem;
        ArrayList<Integer> viagens = reqId();

        Integer tamanho = contadorViagens();

        Random randomico = new Random();
        Integer posicao = randomico.nextInt(tamanho);
        idAleatorioViagem = viagens.get(posicao);

/*System.out.println(idViagens);
System.out.println(idViagem);*/

        return idAleatorioViagem;
    }
}
