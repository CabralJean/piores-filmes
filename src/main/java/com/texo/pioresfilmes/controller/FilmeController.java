package com.texo.pioresfilmes.controller;


import com.texo.pioresfilmes.domain.Filme;
import com.texo.pioresfilmes.repository.FilmeCustomRepository;
import com.texo.pioresfilmes.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    FilmeService filmeService;

    private final FilmeCustomRepository filmeCustomRepository;

    public FilmeController(FilmeCustomRepository filmeCustomRepository) {
        this.filmeCustomRepository = filmeCustomRepository;
    }

    @GetMapping
    public ResponseEntity<List<Filme>> findAll(){
        List<Filme> movs = filmeService.findAll();
        return ResponseEntity.ok().body(movs);
    }
}

