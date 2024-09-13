package br.com.exemplo.testai.cases.project;

import br.com.exemplo.testai.models.Project;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "Responsável por transportar informações de saída.")
public final class ProjectDtoResponse {

    private Long id;
    private String name;
    private Date startDate;
    private Date expectedEndDate;
    private Date endDate;
    private String description;
    private String status;
    private Double budget;
    private String risk;
    private Long managerId;

    public ProjectDtoResponse(Project entity) {
        setId(entity.getId());
        setName(entity.getName());
        setStartDate(entity.getStartDate());
        setExpectedEndDate(entity.getExpectedEndDate());
        setEndDate(entity.getEndDate());
        setDescription(entity.getDescription());
        setStatus(entity.getStatus());
        setBudget(entity.getBudget());
        setRisk(entity.getRisk());
        setManagerId(entity.getManagerId());
    }

}
