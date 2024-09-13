package br.com.exemplo.testai.cases.projectMember;

import br.com.exemplo.testai.config.enums.validators.EnumValidation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
@Schema(description = "Responsável por transportar e validar informações de entrada.")
public class ProjectMemberDtoRequest {

    @Schema(description = "Identificador do registro no Banco PostgreSQL", type = "Long", example = "111", required = false)
    @Positive
    private Long id;

    @Schema(description = "Identificador do Projeto", type = "Long", example = "35", required = true)
    @Positive
    @NotNull
    private Long projectId;

    @Schema(description = "Identificador da Pessoa", type = "Long", example = "22", required = true)
    @Positive
    @NotNull
    private Long personId;

    @Schema(description = "Data de Entrada no Projeto", type = "Date", example = "12/12/2002", required = true)
    @NotNull
    private Date startDate;

    @Schema(description = "Data de Saída do Projeto", type = "Date", example = "12/12/2002", required = true)
    private Date endDate;

    @Schema(description = "Função desempenhada no projeto no período", type = "String", example = "Product Owner", required = true)
    @EnumValidation(values = {"Product Owner", "Scrum Master", "Suporte ao Cliente", "Desenvolvedor", "Analista"}, message = "Valores aceitos: Product Owner, Scrum Master, Suporte ao Cliente, Desenvolvedor, Analista", isRequired = false)
    private String role;

    @Schema(description = "Ativo no Projeto", type = "Boolean", example = "TRUE", required = true)
    @NotNull
    private Boolean active;
}
