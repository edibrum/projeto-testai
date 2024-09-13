package br.com.exemplo.testai.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "pessoa")
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String name;

    @Column(name = "data_nascimento", nullable = false)
    private Date birthDate;

    @Column(name = "cpf", length = 14, nullable = false)
    private String cpf;

    @Column(name = "funcionario", nullable = false)
    private Boolean employee;

    @Column(name = "gerente", nullable = false)
    private Boolean manager;

}
