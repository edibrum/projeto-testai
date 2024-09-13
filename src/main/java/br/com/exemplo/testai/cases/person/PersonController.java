package br.com.exemplo.testai.cases.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService service;

    //TODO: implementar rotas
    // @PostMapping("/save") save(),
    // @GetMapping("/getByCpf") getByCpf(),
    // @GetMapping("/list") list(),
    // @DeleteMapping("/delete") delete()

}