package br.com.exemplo.testai.cases.projectMember;


import br.com.exemplo.testai.cases.person.PersonRepository;
import br.com.exemplo.testai.cases.project.ProjectRepository;
import br.com.exemplo.testai.models.Person;
import br.com.exemplo.testai.models.Project;
import br.com.exemplo.testai.models.ProjectMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProjectMemberService {

    @Autowired
    private ProjectMemberRepository repository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private PersonRepository personRepository;

    public Page<ProjectMemberDtoResponse> listAll(Pageable paginacao){
        Page<ProjectMember> entityList = repository.findAll(paginacao);

        return entityList.map(ProjectMemberDtoResponse::new);
    }

    public ProjectMemberDtoResponse save(ProjectMemberDtoRequest dtoRequest) {

        if (dtoRequest.getId() != null && !repository.existsById(dtoRequest.getId())) {
            throw new RuntimeException("Nenhum registro encontrado com o ID informado (" + dtoRequest.getId() + ").");
        }

        if (dtoRequest.getId() != null && repository.existsById(dtoRequest.getId())) {
            //CASO DE EDIÇÃO - update()
            return update(dtoRequest);
        }

        Project project = projectRepository.findById(dtoRequest.getProjectId()).orElse(null);
        Person person = personRepository.findById(dtoRequest.getPersonId()).orElse(null);

        if (project == null || person == null) {
            throw new RuntimeException("Registros não encontrados com os IDs informados (Projeto: " +
                    dtoRequest.getProjectId() + ", Pessoa: " + dtoRequest.getPersonId() + ").");
        }

        ProjectMember newEntity = new ProjectMember(dtoRequest);
            newEntity.setProject(project);
            newEntity.setPerson(person);

        ProjectMember saved = repository.save(newEntity);

        return new ProjectMemberDtoResponse(saved);

    }

    private ProjectMemberDtoResponse update(ProjectMemberDtoRequest dtoRequest) {
        ProjectMember existingRegister = repository.findById(dtoRequest.getId()).orElse(null);

        if (existingRegister == null) {
            throw new RuntimeException("Nenhum registro encontrado com o ID informado (" + dtoRequest.getId() + ").");
        }

        existingRegister.setStartDate(dtoRequest.getStartDate());
        existingRegister.setEndDate(dtoRequest.getEndDate());
        existingRegister.setRole(dtoRequest.getRole());
        existingRegister.setActive(dtoRequest.getActive());

        ProjectMember saved = repository.saveAndFlush(existingRegister);

        return new ProjectMemberDtoResponse(saved);
    }

    public ProjectMemberDtoResponse getById(Long id) {
        ProjectMember existingRegister = repository.findById(id).orElse(null);

        if (existingRegister == null) {
            throw new RuntimeException("Nenhum registro encontrado com o ID informado (" + id + ").");
        }

        return new ProjectMemberDtoResponse(existingRegister);

    }

    public String delete(Long id) {
        ProjectMember existingRegister = repository.findById(id).orElse(null);

        if (existingRegister == null) {
            throw new RuntimeException("Nenhum registro encontrado com o ID informado (" + id + ").");
        }

        if (existingRegister.getStartDate() != null && existingRegister.getStartDate().after(new Date())) {
            String data = existingRegister.getStartDate().toString();
            throw new RuntimeException("O vínculo do colaborador com o projeto teve início em " + data +"." +
                    "Inserir data de fim do vínculo ao invés de excluir neste caso.");
        }

        try {
            repository.deleteById(id);
            return "Registro excluído com sucesso.";
        } catch (Exception e) {
            return "Erro ao excluir o registro" + ", cause: " + e.getCause() + ", message: " + e.getMessage();
        }

    }

}
