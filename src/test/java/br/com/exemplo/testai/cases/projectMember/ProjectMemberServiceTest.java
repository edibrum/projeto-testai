package br.com.exemplo.testai.cases.projectMember;

import br.com.exemplo.testai.cases.person.PersonDtoRequest;
import br.com.exemplo.testai.cases.person.PersonDtoResponse;
import br.com.exemplo.testai.cases.person.PersonRepository;
import br.com.exemplo.testai.cases.person.PersonService;
import br.com.exemplo.testai.cases.project.ProjectDtoResponse;
import br.com.exemplo.testai.cases.project.ProjectRepository;
import br.com.exemplo.testai.config.exceptions.CustomExceptionErrorMessage;
import br.com.exemplo.testai.config.exceptions.CustomHasDependentRegistersException;
import br.com.exemplo.testai.config.exceptions.CustomNotFoundException;
import br.com.exemplo.testai.models.Person;
import br.com.exemplo.testai.models.Project;
import br.com.exemplo.testai.models.ProjectMember;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@Service
public class ProjectMemberServiceTest {

    @InjectMocks
    private ProjectMemberService projectMemberService;

    @Mock
    private ProjectMemberRepository projectMemberRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListAll() {
        Project project = new Project();
        Person person = new Person();
        ProjectMember projectMember = new ProjectMember();
        projectMember.setProject(project);
        projectMember.setPerson(person);

        ProjectMemberDtoResponse dtoResponse = new ProjectMemberDtoResponse(projectMember);
        Page<ProjectMember> projectMemberPage = new PageImpl<>(Collections.singletonList(projectMember));
        given(projectMemberRepository.findAll(any(Pageable.class))).willReturn(projectMemberPage);

        Page<ProjectMemberDtoResponse> result = projectMemberService.listAll(Pageable.unpaged());

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(dtoResponse.getId(), result.getContent().get(0).getId());
    }

    @Test
    void testSave_Create() {
        ProjectMemberDtoRequest dtoRequest = new ProjectMemberDtoRequest();
        dtoRequest.setProjectId(1111L);
        dtoRequest.setPersonId(1111L);

        Project project = new Project();
        project.setId(1111L);

        Person person = new Person();
        person.setId(1111L);

        when(projectRepository.findById(1111L)).thenReturn(Optional.of(project));
        when(personRepository.findById(1111L)).thenReturn(Optional.of(person));

        ProjectMember projectMember = new ProjectMember(dtoRequest);
        projectMember.setProject(project);
        projectMember.setPerson(person);
        projectMember.setId(999L);

        when(projectMemberRepository.save(any(ProjectMember.class))).thenReturn(projectMember);

        ProjectMemberDtoResponse result = projectMemberService.save(dtoRequest);

        assertNotNull(result);
        assertEquals(projectMember.getId(), result.getId());
        verify(projectRepository).findById(1111L);
        verify(personRepository).findById(1111L);
        verify(projectMemberRepository).save(any(ProjectMember.class));
        assertNotNull(result);
        assertEquals(projectMember.getId(), result.getId());
    }

    @Test
    void testSave_Update() {
        Project project = new Project();
        Person person = new Person();

        ProjectMemberDtoRequest dtoRequest = new ProjectMemberDtoRequest();
        dtoRequest.setId(888L);
        dtoRequest.setProjectId(project.getId());
        dtoRequest.setPersonId(person.getId());

        ProjectMember existingProjectMember = new ProjectMember();
        existingProjectMember.setProject(project);
        existingProjectMember.setPerson(person);
        existingProjectMember.setId(888L);

        given(projectMemberRepository.existsById(anyLong())).willReturn(true);
        given(projectMemberRepository.findById(anyLong())).willReturn(Optional.of(existingProjectMember));
        given(projectMemberRepository.saveAndFlush(any(ProjectMember.class))).willReturn(existingProjectMember);

        ProjectMemberDtoResponse result = projectMemberService.save(dtoRequest);

        assertNotNull(result);
        assertEquals(existingProjectMember.getId(), result.getId());
    }

    @Test
    void testGetById_Success() {
        Project project = new Project();
        Person person = new Person();
        ProjectMember projectMember = new ProjectMember();
        projectMember.setProject(project);
        projectMember.setPerson(person);
        projectMember.setId(9999L);

        when(projectMemberRepository.findById(9999L)).thenReturn(Optional.of(projectMember));

        ProjectMemberDtoResponse result = projectMemberService.getById(9999L);

        assertNotNull(result);
        assertEquals(9999L, result.getId());
    }

    @Test
    void testGetById_NotFound() {
        when(projectMemberRepository.findById(anyLong())).thenReturn(Optional.empty());

        CustomNotFoundException thrown = assertThrows(CustomNotFoundException.class, () -> {
            projectMemberService.getById(1L);
        });

        assertEquals("Nenhum registro encontrado com o ID informado (1).", thrown.getMessage());
    }

    @Test
    void testDelete_Success() {
        ProjectMember projectMember = new ProjectMember();
        projectMember.setId(1L);
        projectMember.setStartDate(new Date(System.currentTimeMillis() - 10000)); // Data passada

        when(projectMemberRepository.findById(1L)).thenReturn(Optional.of(projectMember));

        String result = projectMemberService.delete(1L);

        assertEquals("Registro excluído com sucesso.", result);
        verify(projectMemberRepository).deleteById(1L);
    }

    @Test
    void testtestDelete_Failure_NotFound() {
        when(projectMemberRepository.findById(anyLong())).thenReturn(Optional.empty());

        CustomNotFoundException thrown = assertThrows(CustomNotFoundException.class, () -> {
            projectMemberService.delete(1L);
        });

        assertEquals("Nenhum registro encontrado com o ID informado (1).", thrown.getMessage());
    }

    @Test
    void testDelete_Failure_StartDateOnProject() {
        ProjectMember projectMember = new ProjectMember();
        projectMember.setId(1L);
        projectMember.setStartDate(new Date(System.currentTimeMillis() + 10000)); // Data futura

        when(projectMemberRepository.findById(1L)).thenReturn(Optional.of(projectMember));

        CustomExceptionErrorMessage thrown = assertThrows(CustomExceptionErrorMessage.class, () -> {
            projectMemberService.delete(1L);
        });

        assertEquals("O vínculo do colaborador com o projeto teve início em " + projectMember.getStartDate() + ". Inserir data de fim do vínculo ao invés de excluir neste caso.", thrown.getMessage());
    }

    @Test
    void testDelete_Failure() {
        ProjectMember projectMember = new ProjectMember();
        projectMember.setId(1L);
        projectMember.setStartDate(new Date(System.currentTimeMillis() - 10000)); // Data passada

        when(projectMemberRepository.findById(1L)).thenReturn(Optional.of(projectMember));
        doThrow(new RuntimeException("Erro ao excluir")).when(projectMemberRepository).deleteById(anyLong());

        CustomExceptionErrorMessage thrown = assertThrows(CustomExceptionErrorMessage.class, () -> {
            projectMemberService.delete(1L);
        });

        assertEquals("Erro ao excluir o registro, Message: Erro ao excluir", thrown.getMessage());
    }
}