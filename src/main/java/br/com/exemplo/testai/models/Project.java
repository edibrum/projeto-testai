package br.com.exemplo.testai.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "projeto")
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 200, nullable = false)
    private String name;

    @Column(name = "data_inicio")
    private Date startDate;

    @Column(name = "data_previsao_fim")
    private Date expectedEndDate;

    @Column(name = "data_fim")
    private Date endDate;

    @Column(name = "descricao", length = 5000)
    private String description;

    @Column(name = "status", length = 45)
    private String status;

    @Column(name = "orcamento")
    private Double budget;

    @Column(name = "risco", length = 45)
    private String risk;

    @Column(name = "id_gerente", nullable = false)
    private Long managerId;

}
