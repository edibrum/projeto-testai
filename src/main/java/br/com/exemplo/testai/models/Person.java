package br.com.exemplo.testai.models;

import br.com.exemplo.testai.cases.person.PersonDtoRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "pessoa")
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    private static final Long seralVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String name;

    @Column(name = "data_nascimento", nullable = false)
    private Date birthDate;

    @Column(name = "cpf", length = 14, nullable = false, unique = true)
    private String cpf;

    @Column(name = "funcionario", nullable = false)
    private Boolean employee;

    @Column(name = "gerente", nullable = false)
    private Boolean manager;


    public Person(PersonDtoRequest dtoRequest) {
        setName(dtoRequest.getName());
        setBirthDate(dtoRequest.getBirthDate());
        setCpf(dtoRequest.getCpf());
        setEmployee(dtoRequest.getEmployee());
        setManager(dtoRequest.getManager());
    }

}
