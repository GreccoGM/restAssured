package org.gerenciador.utils;

import org.gerenciador.utils.GeraradorDados;
import org.json.JSONObject;

import java.util.*;


public class MapDados extends GeraradorDados {

    public String dadosViagemObj(){
        return (new JSONObject())
                .put("acompanhante", "Gloria")
                .put("dataPartida", "2021-05-18")
                .put("dataRetorno", "2021-05-25")
                .put("localDeDestino","Santa Catarina")
                .put("regiao", "Sul")
                .toString();
    }

    public Map cadastrarViagemMap(){
        Map<String,Object> viagem = new HashMap<String, Object>();
            viagem.put("acompanhante",getAcompanhenteFaker());
            viagem.put("dataPartida", getDataDiferencaDias(4));
            viagem.put("dataRetorno", getDataDiferencaDias(7));
            viagem.put("localDeDestino",getDestino());
            viagem.put("regiao",getRegiao());

        return viagem;
    }

    public Map editarViagem(){
        Map<String,Object> viagem = new HashMap<String, Object>();
        viagem.put("acompanhante",getAcompanhanteEdicao());
        viagem.put("dataPartida", getDataDiferencaDias(-2));
        viagem.put("dataRetorno", getDataDiferencaDias(5));
        viagem.put("localDeDestino","Canoas");
        viagem.put("regiao",getRegiao());

        return viagem;
    }


}
