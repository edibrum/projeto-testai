package br.com.exemplo.testai.cases.person;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import javax.validation.Valid;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping("/list-all")
    @Operation(summary = "Listar Pessoas", description = "Listar Pessoas")
    public Page<PersonDtoResponse> listarTodos(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 5) Pageable paginacao) {
                return service.listAll(paginacao);
    }

    @GetMapping("/get-by-cpf/{cpf}")
    @Operation(summary = "Buscar Pessoa pelo CPF", description = "Buscar Pessoa pelo CPF")
    public PersonDtoResponse getByCpf(
            @Parameter(name = "cpf", required = true)
            @PathVariable String cpf) {
        return service.getByCpf(cpf);
    }

    @PostMapping("/save")
    @Transactional
    @Operation(summary = "Cadastrar Pessoa", description = "Cadastrar Pessoa")
    public PersonDtoResponse save(
            @Parameter(name = "PersonDtoRequest", required = true)
            @Valid @RequestBody PersonDtoRequest dtoRequest) {
        return service.save(dtoRequest);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    @Operation(summary = "Apagar Registro Pessoa", description = "Apagar Registro Pessoa")
    public String delete(
            @Parameter(name = "id", required = true)
            @PathVariable Long id) {
        return service.delete(id);
    }

}