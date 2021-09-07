package com.texo.pioresfilmes.controller;

import com.texo.pioresfilmes.domain.Resultado;
import com.texo.pioresfilmes.service.PremiacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/premiacoes")
public class PremiacaoController {

    @Autowired
    PremiacaoService premiacaoService;

    @GetMapping
    public ResponseEntity<Resultado> premiacao(){
        return ResponseEntity.ok().body(premiacaoService.apurarResultado());
    }
}