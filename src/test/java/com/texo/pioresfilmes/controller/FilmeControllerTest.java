package com.texo.pioresfilmes.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FilmeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void deveRetornarSucessoQuandoBuscarFilme() throws Exception {
        mockMvc.perform(get("/filmes"))
                .andExpect(status().isOk());
    }
}
