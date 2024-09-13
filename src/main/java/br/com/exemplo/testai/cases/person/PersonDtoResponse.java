package br.com.exemplo.testai.cases.person;

import br.com.exemplo.testai.models.Person;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "Responsável por transportar informações de saída.")
public final class PersonDtoResponse {

    private Long id;
    private String name;
    private Date birthDate;
    private String cpf;
    private Boolean employee;
    private Boolean manager;

    public PersonDtoResponse(Person entity) {
        setId(entity.getId());
        setName(entity.getName());
        setBirthDate(entity.getBirthDate());
        setCpf(entity.getCpf());
        setEmployee(entity.getEmployee());
        setManager(entity.getManager());
    }

}
