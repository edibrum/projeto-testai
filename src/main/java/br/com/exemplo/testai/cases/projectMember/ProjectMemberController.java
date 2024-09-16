package br.com.exemplo.testai.cases.projectMember;

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
@RequestMapping("/project-member")
public class ProjectMemberController {

    @Autowired
    private ProjectMemberService service;

    @GetMapping("/list-all")
    @Operation(summary = "Listar Membros de Projetos", description = "Listar Membros de Projetos")
    public Page<ProjectMemberDtoResponse> listarTodos(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 5) Pageable paginacao) {
        return service.listAll(paginacao);
    }


    @GetMapping("/get-by-id/{id}")
    @Operation(summary = "Buscar Vínculo Membro&Projeto pelo ID", description = "Buscar Vínculo Membro&Projeto pelo ID")
    public ProjectMemberDtoResponse getById(
            @Parameter(name = "id", required = true)
            @PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/save")
    @Transactional
    @Operation(summary = "Cadastrar Vínculo Membro&Projeto", description = "Cadastrar Vínculo Membro&Projeto")
    public ProjectMemberDtoResponse save(
            @Parameter(name = "ProjectMemberDtoRequest", required = true)
            @Valid @RequestBody ProjectMemberDtoRequest dtoRequest) {
        return service.save(dtoRequest);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    @Operation(summary = "Apagar Vínculo Membro&Projeto", description = "Apagar Vínculo Membro&Projeto")
    public String delete(
            @Parameter(name = "id", required = true)
            @PathVariable Long id) {
        return service.delete(id);
    }

}