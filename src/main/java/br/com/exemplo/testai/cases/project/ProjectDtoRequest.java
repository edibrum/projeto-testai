package br.com.exemplo.testai.cases.project;

import br.com.exemplo.testai.config.enums.validators.EnumValidation;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "Responsável por transportar e validar informações de entrada.")
public final class ProjectDtoRequest {

    @Schema(description = "Identificador do registro no Banco PostgreSQL", type = "Long", example = "111", required = false)
    private Long id;

    @Schema(description = "Nome do Projeto (máximo de 200 carácteres)", type = "String", example = "App Testai", required = true)
    @NotNull
    @NotEmpty
    @Size(max = 200)
    private String name;

    @Schema(description = "Data de Início do Projeto", type = "Date", example = "12/12/2002", required = true)
    private Date startDate;

    @Schema(description = "Data Estimada de Término do Projeto", type = "Date", example = "12/12/2002", required = true)
    private Date expectedEndDate;

    @Schema(description = "Data de Término do Projeto", type = "Date", example = "12/12/2002", required = true)
    private Date endDate;

    @Schema(description = "Descrição do Projeto (máximo de 5000 carácteres)", type = "String", example = "Projeto de novo app para ...", required = true)
    private String description;

    @Schema(description = "Status do Projeto (máximo de 45 carácteres)", type = "String", example = "Em Andamento", required = true)
    @NotNull
    @NotEmpty
    @Size(max = 45)
    @EnumValidation(values = {"Em Análise", "Análise Realizada", "Análise Aprovada", "Iniciado", "Planejado", "Em Andamento", "Encerrado", "Cancelado"}, message = "Valores aceitos: Em Análise, Análise Realizada, Análise Aprovada, Iniciado, Planejado, Em Andamento, Encerrado, Cancelado", isRequired = false)
    private String status;

    @Schema(description = "Orçamento do Projeto", type = "Double", example = "20.000,00", required = true)
    @Positive
    private Double budget;

    @Schema(description = "Risco do Projeto (máximo de 45 carácteres)", type = "String", example = "Baixo", required = true)
    @Size(max = 45)
    @EnumValidation(values = {"Baixo", "Médio", "Alto"}, message = "Valores aceitos: Baixo, Médio, ou Alto", isRequired = false)
    private String risk;

    @Schema(description = "Identificador do Gerente do Projeto", type = "Long", example = "912", required = true)
    @NotNull
    private Long managerId;
}
