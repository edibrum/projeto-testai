package br.com.exemplo.testai.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "membros")
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMember {
    //TODO: alinhar sobre usar um ID pro relacionamento, uma vez que poderemos ter o mesmo profissional
    // em diferentes periodos desempenhando diferentes funcoes, inclusive (cenário de uma promoção, por exemplo)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_projeto", nullable = false)
    private Long projectId;

    @Column(name = "id_pessoa", nullable = false)
    private Long personId;

    //  TODO: alinhar sobre estes novos possíveis campos com o cliente
    @Column(name = "data_entrada_no_projeto", nullable = false)
    private Date startDate;

    @Column(name = "data_saida_no_projeto")
    private Date endDate;

    @Column(name = "funcao_no_projeto")
    private String role;

    @Column(name = "vinculo_ativo", nullable = false)
    private Boolean active;

}
