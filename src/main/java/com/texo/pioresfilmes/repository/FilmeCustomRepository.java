package com.texo.pioresfilmes.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class FilmeCustomRepository {

    private final EntityManager em;

    public FilmeCustomRepository(EntityManager em) {
        this.em = em;
    }

    public List<Object[]> buscarPremiados(){

        List<Object[]> resultado;

        String query = "select y.year, y.producers " +
                " from Filme as y where y.producers in(\n" +
                " select f.producers " +
                " from Filme as f " +
                " group by f.producers " +
                " having count(f.producers) > 1\n" +
                ")\n" +
                "and y.producers != '' order by y.producers, y.year";

        Query q = em.createQuery(query);

        resultado = q.getResultList();

        return resultado;
    }
}