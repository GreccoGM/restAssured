package org.gerenciador.Dados;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Dados {

    public Map cadastrarViagem(){
        Map<String,Object> viagem = new HashMap<String, Object>();
            viagem.put("acompanhante","Gloria Grecco");
            viagem.put("dataPartida", getDataDiferencaDias(-1));
            viagem.put("dataRetorno", getDataDiferencaDias(15));
            viagem.put("localDeDestino","Canoas");
            viagem.put("regiao",getRegiao());

        return viagem;
    }

    public Map editarViagem(){
        Map<String,Object> viagem = new HashMap<String, Object>();
        viagem.put("acompanhante","Marques");
        viagem.put("dataPartida", getDataDiferencaDias(-1));
        viagem.put("dataRetorno", getDataDiferencaDias(15));
        viagem.put("localDeDestino","POA");
        viagem.put("regiao",getRegiao());

        return viagem;
    }

    public static String getDataDiferencaDias(Integer qtdDias){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, qtdDias);

        return getDataFormatada(cal.getTime());
    }

    public static String getDataFormatada(Date data){
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        return format.format(data);
    }

    public static String getRegiao(){
        String[] regioes = {"Sul","Nordeste","Norte","Centro-Oeste","Sudeste"};
        String regiao;

        Random randomico = new Random();
        Integer posicao = randomico.nextInt(regioes.length);
        regiao = regioes[posicao];

        return regiao;
    }
}
