package com.mlconti.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {
    
    @GetMapping("/")
    public String index() {
        return "Olá,mundo!";
    }
    @GetMapping("/outroEP")
    public String index2() {
        return "outro endpoint";
    }

    @PostMapping("/")
    public String indexPros(){
       return "Agora via Post";
    }

    @GetMapping("/outroEP/{parametro}")
    public String index2Parametro(@PathVariable Integer parametro ){
        return "Você passou " + parametro + " como parâmetro!";
    }

    @GetMapping(value = "/geraJSON",produces = "application/json")
    public String retornaJSON(){
        return "{\"nome\" : \"Micael\"}";
    }
}
