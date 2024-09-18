package br.com.exemplo.testai.cases.project;

import br.com.exemplo.testai.cases.person.PersonDtoResponse;
import br.com.exemplo.testai.config.exceptions.CustomExceptionErrorMessage;
import br.com.exemplo.testai.config.exceptions.CustomHasDependentRegistersException;
import br.com.exemplo.testai.config.exceptions.CustomNotFoundException;
import br.com.exemplo.testai.models.Person;
import br.com.exemplo.testai.models.Project;
import br.com.exemplo.testai.models.ProjectMember;
import br.com.exemplo.testai.cases.projectMember.ProjectMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class ProjectServiceTest {

    @InjectMocks
    private ProjectService projectService;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMemberRepository projectMemberRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListAll() {
        Project project = new Project();
        ProjectDtoResponse dtoResponse = new ProjectDtoResponse(project);
        Page<Project> projectPage = new PageImpl<>(Collections.singletonList(project));
        given(projectRepository.findAll(any(Pageable.class))).willReturn(projectPage);

        Page<ProjectDtoResponse> result = projectService.listAll(Pageable.unpaged());

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(dtoResponse.getId(), result.getContent().get(0).getId());
    }

    @Test
    void testSave_NewProject() {
        ProjectDtoRequest dtoRequest = new ProjectDtoRequest();
        Project newProject = new Project(dtoRequest);
        when(projectRepository.save(any(Project.class))).thenReturn(newProject);

        ProjectDtoResponse result = projectService.save(dtoRequest);

        assertNotNull(result);
        verify(projectRepository).save(any(Project.class));
    }

    @Test
    void testGetById_Success() {
        Project project = new Project();
        project.setId(1L);
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        ProjectDtoResponse result = projectService.getById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetById_NotFound() {
        when(projectRepository.findById(anyLong())).thenReturn(Optional.empty());

        CustomNotFoundException thrown = assertThrows(CustomNotFoundException.class, () -> {
            projectService.getById(1L);
        });

        assertEquals("Nenhum registro encontrado com o ID informado (1).", thrown.getMessage());
    }

    @Test
    void testDelete_Success() {
        Project project = new Project();
        project.setId(1L);
        project.setStatus("Planejado");

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(projectMemberRepository.findByProjectId(1L)).thenReturn(Collections.emptyList());

        String result = projectService.delete(1L);

        assertEquals("Registro excluído com sucesso.", result);
        verify(projectRepository).deleteById(1L);
    }

    @Test
    void testDelete_ProjectNotFound() {
        when(projectRepository.findById(anyLong())).thenReturn(Optional.empty());

        CustomNotFoundException thrown = assertThrows(CustomNotFoundException.class, () -> {
            projectService.delete(1L);
        });

        assertEquals("Nenhum registro encontrado com o ID informado (1).", thrown.getMessage());
    }

    @Test
    void testDelete_ProjectWithMembers() {
        Project project = new Project();
        project.setId(1L);
        project.setStatus("Planejado");

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(projectMemberRepository.findByProjectId(1L)).thenReturn(Collections.singletonList(new ProjectMember()));

        CustomHasDependentRegistersException thrown = assertThrows(CustomHasDependentRegistersException.class, () -> {
            projectService.delete(1L);
        });

        assertEquals("Projeto (1) já possui membros vinculados. Exclusão não permitida.", thrown.getMessage());
    }

    @Test
    void testDelete_ProjectWithBlockingStatus() {
        Project project = new Project();
        project.setId(1L);
        project.setStatus("Em Andamento");

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        CustomExceptionErrorMessage thrown = assertThrows(CustomExceptionErrorMessage.class, () -> {
            projectService.delete(1L);
        });

        assertEquals("Atenção: Projeto com status Iniciado, Em Andamento ou Encerrado não deve ser excluído.", thrown.getMessage());
    }
}