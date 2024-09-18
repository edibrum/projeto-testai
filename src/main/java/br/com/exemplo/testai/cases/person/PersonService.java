package br.com.exemplo.testai.cases.person;

import br.com.exemplo.testai.cases.projectMember.ProjectMemberRepository;
import br.com.exemplo.testai.config.exceptions.CustomExceptionErrorMessage;
import br.com.exemplo.testai.config.exceptions.CustomHasDependentRegistersException;
import br.com.exemplo.testai.config.exceptions.CustomNotFoundException;
import br.com.exemplo.testai.models.Person;
import br.com.exemplo.testai.models.ProjectMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    public Page<PersonDtoResponse> listAll(Pageable paginacao){
        Page<Person> entityList = repository.findAll(paginacao);

        return entityList.map(PersonDtoResponse::new);
    }

    public PersonDtoResponse save(PersonDtoRequest dtoRequest) {

        if (dtoRequest.getId() != null && !repository.existsById(dtoRequest.getId())) {
            throw new CustomNotFoundException("Nenhum registro encontrado com o ID informado (" + dtoRequest.getId() + ").");
        }

        if (dtoRequest.getId() != null && repository.existsById(dtoRequest.getId())) {
            //CASO DE EDIÇÃO - update()
            return update(dtoRequest);
        }

        Person newEntity = new Person(dtoRequest);
        Person saved = repository.save(newEntity);

        return new PersonDtoResponse(saved);

    }

    private PersonDtoResponse update(PersonDtoRequest dtoRequest) {
        Person existingRegister = repository.findById(dtoRequest.getId()).orElse(null);

        if (existingRegister == null) {
            throw new CustomNotFoundException("Nenhum registro encontrado com o ID informado (" + dtoRequest.getId() + ").");
        }

            existingRegister.setEmployee(dtoRequest.getEmployee());
            existingRegister.setManager(dtoRequest.getManager());
            Person saved = repository.saveAndFlush(existingRegister);

        return new PersonDtoResponse(saved);
    }

    public PersonDtoResponse getByCpf(String cpf) {
        Person existingRegister = repository.findByCpf(cpf).orElse(null);

        if (existingRegister == null) {
            throw new CustomNotFoundException("Nenhum registro encontrado com o CPF informado (" + cpf + ").");
        }

        return new PersonDtoResponse(existingRegister);

    }

    public String delete(Long id) {
        Person existingRegister = repository.findById(id).orElse(null);

        if (existingRegister == null) {
            throw new CustomNotFoundException("Nenhum registro encontrado com o ID informado (" + id + ").");
        }

        List<ProjectMember> projectMemberList = projectMemberRepository.findByPersonId(existingRegister.getId());

        if (projectMemberList == null || !projectMemberList.isEmpty()) {
            throw new CustomHasDependentRegistersException("Pessoa (" + id + ") vinculada a projetos registrados. Exclusão não permitida.");
        }
// TODO: verificar com PO/SCRUM sobre este ponto - exclusão quando a Pessoa tem vínculo com projetos
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