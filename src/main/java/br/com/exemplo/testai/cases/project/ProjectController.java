package br.com.exemplo.testai.cases.project;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @GetMapping("/list-all")
    @Operation(summary = "Listar Projetos", description = "Listar Projetos")
    public Page<ProjectDtoResponse> listarTodos(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 5) Pageable paginacao) {
        return service.listAll(paginacao);
    }

    @GetMapping("/get-by-id/{id}")
    @Operation(summary = "Buscar Projeto pelo ID", description = "Buscar Projeto pelo ID")
    public ProjectDtoResponse getById(
            @Parameter(name = "id", required = true)
            @PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/save")
    @Transactional
    @Operation(summary = "Cadastrar Projeto", description = "Cadastrar Projeto")
    public ProjectDtoResponse save(
            @Parameter(name = "ProjectDtoRequest", required = true)
            @Valid @RequestBody ProjectDtoRequest dtoRequest) {
        return service.save(dtoRequest);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    @Operation(summary = "Apagar Registro Projeto", description = "Apagar Registro Projeto")
    public String delete(
            @Parameter(name = "id", required = true)
            @PathVariable Long id) {
        return service.delete(id);
    }

}