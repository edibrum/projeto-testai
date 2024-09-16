package br.com.exemplo.testai.cases.person;

import br.com.exemplo.testai.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public Page<PersonDtoResponse> listAll(Pageable paginacao){
        Page<Person> entityList = repository.findAll(paginacao);

        return entityList.map(PersonDtoResponse::new);
    }

    public PersonDtoResponse save(PersonDtoRequest dtoRequest) {

        if (dtoRequest.getId() != null && !repository.existsById(dtoRequest.getId())) {
            throw new RuntimeException("Nenhum registro encontrado com o ID informado (" + dtoRequest.getId() + ").");
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
            throw new RuntimeException("Nenhum registro encontrado com o ID informado (" + dtoRequest.getId() + ").");
        }

            existingRegister.setEmployee(dtoRequest.getEmployee());
            existingRegister.setManager(dtoRequest.getManager());
            Person saved = repository.saveAndFlush(existingRegister);

        return new PersonDtoResponse(saved);
    }

    public PersonDtoResponse getByCpf(String cpf) {
        Person existingRegister = repository.findByCpf(cpf).orElse(null);

        if (existingRegister == null) {
            throw new RuntimeException("Nenhum registro encontrado com o CPF informado (" + cpf + ").");
        }

        return new PersonDtoResponse(existingRegister);

    }

    public String delete(Long id) {
        Person existingRegister = repository.findById(id).orElse(null);

        if (existingRegister == null) {
            throw new RuntimeException("Nenhum registro encontrado com o ID informado (" + id + ").");
        }

        try {
            repository.deleteById(id);
            return "Registro excluído com sucesso.";
        } catch (Exception e) {
            return "Erro ao excluir o registro" + ", cause: " + e.getCause() + ", message: " + e.getMessage();
        }

    }

}