package br.com.exemplo.testai.models;

import br.com.exemplo.testai.cases.project.ProjectDtoRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "projeto")
@NoArgsConstructor
@AllArgsConstructor
public class Project implements Serializable {

    private static final Long seralVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
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

    @Column(name = "status", length = 45, nullable = false)
    private String status;

    @Column(name = "orcamento")
    private Double budget;

    @Column(name = "risco", length = 45)
    private String risk;

    @Column(name = "id_gerente", nullable = false)
    private Long managerId;

    public Project(ProjectDtoRequest dtoRequest) {
        setName(dtoRequest.getName());
        setStartDate(dtoRequest.getStartDate());
        setExpectedEndDate(dtoRequest.getExpectedEndDate());
        setEndDate(dtoRequest.getEndDate());
        setDescription(dtoRequest.getDescription());
        setStatus(dtoRequest.getStatus());
        setBudget(dtoRequest.getBudget());
        setRisk(dtoRequest.getRisk());
        setManagerId(dtoRequest.getManagerId());
    }

}
