package org.gerenciador.utils;

import org.gerenciador.utils.GeraradorDados;

import java.util.*;


public class MapDados extends GeraradorDados {

    public Map cadastrarViagem(){
        Map<String,Object> viagem = new HashMap<String, Object>();
            viagem.put("acompanhante",getAcompanhante());
            viagem.put("dataPartida", getDataDiferencaDias(4));
            viagem.put("dataRetorno", getDataDiferencaDias(7));
            viagem.put("localDeDestino",getDestino());
            viagem.put("regiao",getRegiao());

        return viagem;
    }

    public Map editarViagem(){
        Map<String,Object> viagem = new HashMap<String, Object>();
        viagem.put("acompanhante","Marques");
        viagem.put("dataPartida", getDataDiferencaDias(-2));
        viagem.put("dataRetorno", getDataDiferencaDias(5));
        viagem.put("localDeDestino","Canoas");
        viagem.put("regiao",getRegiao());

        return viagem;
    }


}
