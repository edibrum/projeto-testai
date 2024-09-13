package br.com.exemplo.testai.cases.person;

import br.com.exemplo.testai.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    //TODO: implementar métodos save(), getByCpf(), list(), delete()

}