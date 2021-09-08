package com.texo.pioresfilmes.controller;

import com.texo.pioresfilmes.domain.Premiacao;
import com.texo.pioresfilmes.domain.Resultado;
import com.texo.pioresfilmes.service.PremiacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PremiacaoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PremiacaoService service;

    @Test
    public void deveRetornarResultadoComMenorMaiorIntevaloDePremiacao() throws Exception {

        List<Premiacao> premiacaoMax = Arrays.asList(new Premiacao("Producer 1",1,2020,2021));
        List<Premiacao> premiacaoMin = Arrays.asList(new Premiacao("Producer 2",10,2010,2020));

        Resultado expected = new Resultado(premiacaoMax, premiacaoMin);

        when(service.apurarResultado()).thenReturn(expected);

        mockMvc.perform(get("/premiacoes"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.max[0].producer").value("Producer 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.max[0].interval").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.min[0].producer").value("Producer 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.min[0].interval").value(10))
                .andReturn();

        verify(service).apurarResultado();
    }
}
