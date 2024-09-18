package br.com.exemplo.testai.cases.project;

import br.com.exemplo.testai.cases.projectMember.ProjectMemberRepository;
import br.com.exemplo.testai.config.exceptions.CustomExceptionErrorMessage;
import br.com.exemplo.testai.config.exceptions.CustomHasDependentRegistersException;
import br.com.exemplo.testai.config.exceptions.CustomNotFoundException;
import br.com.exemplo.testai.models.Project;
import br.com.exemplo.testai.models.ProjectMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    public Page<ProjectDtoResponse> listAll(Pageable paginacao){
        Page<Project> entityList = repository.findAll(paginacao);

        return entityList.map(ProjectDtoResponse::new);
    }

    public ProjectDtoResponse save(ProjectDtoRequest dtoRequest) {

        if (dtoRequest.getId() != null && !repository.existsById(dtoRequest.getId())) {
            throw new RuntimeException("Nenhum registro encontrado com o ID informado (" + dtoRequest.getId() + ").");
        }

        if (dtoRequest.getId() != null && repository.existsById(dtoRequest.getId())) {
            //CASO DE EDIÇÃO - update()
            return update(dtoRequest);
        }

        Project newEntity = new Project(dtoRequest);
        Project saved = repository.save(newEntity);

        return new ProjectDtoResponse(saved);

    }

    private ProjectDtoResponse update(ProjectDtoRequest dtoRequest) {
        Project existingRegister = repository.findById(dtoRequest.getId()).orElse(null);

        if (existingRegister == null) {
            throw new RuntimeException("Nenhum registro encontrado com o ID informado (" + dtoRequest.getId() + ").");
        }

        existingRegister.setStartDate(dtoRequest.getStartDate());
        existingRegister.setExpectedEndDate(dtoRequest.getExpectedEndDate());
        existingRegister.setEndDate(dtoRequest.getEndDate());
        existingRegister.setDescription(dtoRequest.getDescription());
        existingRegister.setStatus(dtoRequest.getStatus());
        existingRegister.setBudget(dtoRequest.getBudget());
        existingRegister.setRisk(dtoRequest.getRisk());
        existingRegister.setManagerId(dtoRequest.getManagerId());

        Project saved = repository.saveAndFlush(existingRegister);

        return new ProjectDtoResponse(saved);
    }

    public ProjectDtoResponse getById(Long id) {
        Project existingRegister = repository.findById(id).orElse(null);

        if (existingRegister == null) {
            throw new CustomNotFoundException("Nenhum registro encontrado com o ID informado (" + id + ").");
        }

        return new ProjectDtoResponse(existingRegister);

    }

    public String delete(Long id) {
        Project existingRegister = repository.findById(id).orElse(null);

        if (existingRegister == null) {
            throw new CustomNotFoundException("Nenhum registro encontrado com o ID informado (" + id + ").");
        }

        //REGRA DE NEGÓCIO DO CLIENTE: projeto com status iniciado, em andamento ou encerrado não deve ser excluído;
        List<String> statusDeleteBlocking = Arrays.asList("Iniciado", "Em Andamento", "Encerrado");

        if (statusDeleteBlocking.contains(existingRegister.getStatus())) {
            throw new CustomExceptionErrorMessage("Atenção: Projeto com status Iniciado, Em Andamento ou Encerrado não deve ser excluído.");
        }

        List<ProjectMember> projectMemberList = projectMemberRepository.findByProjectId(existingRegister.getId());

        if (projectMemberList == null || !projectMemberList.isEmpty()) {
            throw new CustomHasDependentRegistersException("Projeto (" + id + ") já possui membros vinculados. Exclusão não permitida.");
        }
// TODO: verificar com PO/SCRUM sobre este ponto - exclusão quando o Projeto tem membros (pessoas) vinculados a ele
//        if (projectMemberList == null && !projectMemberList.isEmpty()) {
//            projectMemberList.forEach(pm -> {
//                projectMemberRepository.deleteById(pm.getId());
//            });
//        }

        try {
            repository.deleteById(id);
            return "Registro excluído com sucesso.";
        } catch (Exception e) {
            throw new CustomExceptionErrorMessage("Erro ao excluir o registro" + ", Message: " + e.getMessage());
        }

    }

}