package br.com.exemplo.testai.cases.project;

import br.com.exemplo.testai.models.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Page<Project> findByStartDate(Date startDate, Pageable pagination);
}