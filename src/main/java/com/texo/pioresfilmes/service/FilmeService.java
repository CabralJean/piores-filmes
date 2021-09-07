package com.texo.pioresfilmes.service;

import com.texo.pioresfilmes.domain.Filme;
import com.texo.pioresfilmes.repository.FilmeCustomRepository;
import com.texo.pioresfilmes.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private FilmeCustomRepository filmeCustomRepository;

    public List<Filme> findAll(){
        return filmeRepository.findAll();
    }

    public Filme save(Filme filme) {
        return filmeRepository.save(filme);
    }

    public void saveAll(List<Filme> filmes) {
        filmeRepository.saveAll(filmes);
    }

    public List<Filme> list() {
        return filmeRepository.findAll();
    }
}
