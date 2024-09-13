package br.com.exemplo.testai.cases.person;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

@Data
@Schema(description = "Responsável por transportar e validar informações de entrada.")
public final class PersonDtoRequest {

    @Schema(description = "Identificador do registro no Banco PostgreSQL", type = "Long", example = "111", required = false)
    private Long id;

    @Schema(description = "Nome (máximo de 100 carácteres)", type = "String", example = "Maria Carvalho", required = true)
    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String name;

    @Schema(description = "Data de Nascimento", type = "Date", example = "12/12/2002", required = true)
    @NotNull
    private Date birthDate;

    @Schema(description = "CPF (máximo de 14 carácteres)", type = "String", example = "111.111.111-11", required = true)
    @CPF
    private String cpf;

    @Schema(description = "Funcionário", type = "Boolean", example = "TRUE", required = true)
    @NotNull
    private Boolean employee;

    @Schema(description = "Gerente", type = "Boolean", example = "TRUE", required = true)
    @NotNull
    private Boolean manager;

}
