package br.com.exemplo.testai.models;

import br.com.exemplo.testai.cases.project.ProjectDtoRequest;
import br.com.exemplo.testai.cases.projectMember.ProjectMemberDtoRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "membros")
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMember implements Serializable {
        //TODO: alinhar sobre usar um ID pro relacionamento, uma vez que poderemos ter o mesmo profissional
        // em diferentes periodos desempenhando diferentes funcoes, inclusive (cenário de uma promoção, por exemplo)
        private static final Long seralVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY ,cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "id_projeto", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY ,cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "id_pessoa", nullable = false)
    private Person person;

    //  TODO: alinhar sobre estes novos possíveis campos com o cliente
    @Column(name = "data_entrada_no_projeto", nullable = false)
    private Date startDate;

    @Column(name = "data_saida_no_projeto")
    private Date endDate;

    @Column(name = "funcao_no_projeto")
    private String role;

    @Column(name = "vinculo_ativo", nullable = false)
    private Boolean active;

    public ProjectMember(ProjectMemberDtoRequest dtoRequest) {
        setStartDate(dtoRequest.getStartDate());
        setEndDate(dtoRequest.getEndDate());
        setRole(dtoRequest.getRole());
        setActive(dtoRequest.getActive());
    }

}
