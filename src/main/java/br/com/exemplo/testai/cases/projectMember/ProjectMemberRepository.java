package br.com.exemplo.testai.cases.projectMember;

import br.com.exemplo.testai.models.ProjectMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {

    Page<ProjectMember> findByStartDate(Date startDate, Pageable pagination);

    List<ProjectMember> findByPersonId(Long personId);

    List<ProjectMember> findByProjectId(Long projectId);

}