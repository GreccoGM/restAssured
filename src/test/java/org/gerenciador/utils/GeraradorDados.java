package org.gerenciador.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.Date;

public class GeraradorDados {

    public String getAcompanhante(){
        String acompanhante[] = {"Jacira", "Eveline", "Naymara", "Rodrigo", "Matheus", "Gabriel"};
        String nomeAcompanhente;

        Random randomico = new Random();
        Integer posicao = randomico.nextInt(acompanhante.length);

        nomeAcompanhente = acompanhante[posicao];

        return nomeAcompanhente;
    }

    public String getDestino(){
        String destino[] = {"Porto Alegre", "Gramado", "Uruguaiana", "Pelotas", "Santa Maria", "Torres"};
        String localDestino;

        Random randomico = new Random();
        Integer posicao = randomico.nextInt(destino.length);

        localDestino = destino[posicao];

        return localDestino;
    }

    public static String getRegiao(){
        String[] regioes = {"Sul","Nordeste","Norte","Centro-Oeste","Sudeste"};
        String regiao;

        Random randomico = new Random();
        Integer posicao = randomico.nextInt(regioes.length);
        regiao = regioes[posicao];

        return regiao;
    }

    public String getDataDiferencaDias(Integer qtdDias){
        Calendar dataAtual = Calendar.getInstance();
        dataAtual.add(Calendar.DATE, qtdDias);

        return getDataFormatada(dataAtual.getTime());
    }

    public String getDataFormatada(Date data){
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd");

    //System.out.println(format.format(data));

        return format.format(data);
    }

}
