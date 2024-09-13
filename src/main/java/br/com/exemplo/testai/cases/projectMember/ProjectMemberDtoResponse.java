package br.com.exemplo.testai.cases.projectMember;

import br.com.exemplo.testai.models.ProjectMember;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "Responsável por transportar informações de saída.")
public final class ProjectMemberDtoResponse {

    private Long id;
    private Long projectId;
    private Long personId;
    private Date startDate;
    private Date endDate;
    private String role;
    private Boolean active;

    public ProjectMemberDtoResponse(ProjectMember entity) {
        setId(entity.getId());
        setProjectId(entity.getProjectId());
        setPersonId(entity.getPersonId());
        setStartDate(entity.getStartDate());
        setEndDate(entity.getEndDate());
        setRole(entity.getRole());
        setActive(entity.getActive());
    }

}
