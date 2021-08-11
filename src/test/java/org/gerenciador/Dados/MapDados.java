package org.gerenciador.Dados;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class MapDados {

    public Map cadastrarViagem(){
        Map<String,Object> viagem = new HashMap<String, Object>();
            viagem.put("acompanhante",getAcompanhante());
            viagem.put("dataPartida", getDataDiferencaDias(-1));
            viagem.put("dataRetorno", getDataDiferencaDias(2));
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
    private String getAcompanhante(){
        String acompanhante[] = {"Jacira", "Eveline", "Naymara", "Rodrigo", "Matheus", "Gabriel"};
        String nomeAcompanhente;

        Random randomico = new Random();
        Integer posicao = randomico.nextInt(acompanhante.length);

        nomeAcompanhente = acompanhante[posicao];

        return nomeAcompanhente;
    }

    private String getDestino(){
        String destino[] = {"Porto Alegre", "Gramado", "Uruguaiana", "Pelotas", "Santa Maria", "Torres"};
        String localDestino;

        Random randomico = new Random();
        Integer posicao = randomico.nextInt(destino.length);

        localDestino = destino[posicao];

        return localDestino;
    }

    private String getDataDiferencaDias(Integer qtdDias){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, qtdDias);

        return getDataFormatada(cal.getTime());
    }

    private String getDataFormatada(Date data){
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
