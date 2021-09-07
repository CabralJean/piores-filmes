package com.texo.pioresfilmes.service;

import com.texo.pioresfilmes.domain.Premiacao;
import com.texo.pioresfilmes.domain.Resultado;
import com.texo.pioresfilmes.repository.FilmeCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PremiacaoService {

    @Autowired
    private FilmeCustomRepository filmeCustomRepository;

    public Resultado apurarResultado() {
        List<Premiacao> relList = retornarIntervaloIndicacoes();

        List<Premiacao> max = retornarPremiacaoComMenorIntervalo(relList);
        List<Premiacao> min = retornarPremiacaoComMaiorIntervalo(relList);

        return new Resultado(max,min);
    }

    private List<Premiacao> retornarIntervaloIndicacoes(){
        List<Object[]> produtoresIndicados = filmeCustomRepository.buscarPremiados();
        List<Premiacao> relList = new ArrayList<>();
        Integer intervaloEntreIndicacoes;
        Integer anoIndicacaoAnt = 0;
        String produtorAnt = null;

        for (Object[] ob : produtoresIndicados) {
            Premiacao data = new Premiacao();
            Object[] aux = ob;

            Integer anoIndicacaoProx = (Integer) aux[0];
            String produtorProx = (String) aux[1];

            if (produtorProx.equals(produtorAnt)) {
                intervaloEntreIndicacoes = anoIndicacaoProx - anoIndicacaoAnt;
                data.setProducer(produtorProx);
                data.setInterval(intervaloEntreIndicacoes);
                data.setPreviousWin(anoIndicacaoAnt);
                data.setFollowingWin(anoIndicacaoProx);
                relList.add(data);
            }
            anoIndicacaoAnt = anoIndicacaoProx;
            produtorAnt = produtorProx;
        }
        return relList;
    }

    private List<Premiacao> retornarPremiacaoComMenorIntervalo(List<Premiacao> relList){
        List<Premiacao> ordenadaMin = relList.stream().sorted().collect(Collectors.toList());
        List<Premiacao> premiacaoList = new ArrayList<>();
        Integer numeroAuxiliarMin = 100;

        for (int j = 0; j < ordenadaMin.size(); j++) {

            if ((Integer) ordenadaMin.get(j).getInterval() <= numeroAuxiliarMin) {
                premiacaoList.add(ordenadaMin.get(j));
                numeroAuxiliarMin = (Integer) ordenadaMin.get(j).getInterval();
            }
        }
        return premiacaoList;
    }

    private List<Premiacao> retornarPremiacaoComMaiorIntervalo(List<Premiacao> relList){
        List<Premiacao> premiacaoList = new ArrayList<>();
        List<Premiacao> ordenadaMin = relList.stream().sorted().collect(Collectors.toList());
        Collections.reverse(ordenadaMin);
        List<Premiacao> ordenadaMax = ordenadaMin;
        Integer numeroAuxiliarMax = 0;

        if(ordenadaMin.size() > 1) {

            for (int j = 0; j < ordenadaMax.size(); j++) {
                if ((Integer) ordenadaMin.get(j).getInterval() >= numeroAuxiliarMax) {
                    premiacaoList.add(ordenadaMax.get(j));
                    numeroAuxiliarMax = (Integer) ordenadaMin.get(j).getInterval();
                }
            }
        }
        return premiacaoList;
    }
}
