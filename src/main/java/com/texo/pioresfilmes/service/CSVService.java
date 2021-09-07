package com.texo.pioresfilmes.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.texo.pioresfilmes.domain.Filme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;

@Component
public class CSVService {

    @Autowired
    FilmeService filmeService;

    public void importCsv() throws FileNotFoundException {

        File csv = ResourceUtils.getFile("classpath:movielistTest.csv");
        Reader reader = new BufferedReader(new FileReader(csv.getAbsolutePath()));

        CsvToBean csvReader = new CsvToBeanBuilder(reader)
                .withType(Filme.class)
                .withSeparator(',')
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .build();


        List<Filme> filmes = csvReader.parse();

        filmeService.saveAll(filmes);
    }
}
