package br.com.exemplo.testai.cases.person;

import br.com.exemplo.testai.cases.projectMember.ProjectMemberRepository;
import br.com.exemplo.testai.config.exceptions.CustomHasDependentRegistersException;
import br.com.exemplo.testai.config.exceptions.CustomNotFoundException;
import br.com.exemplo.testai.models.Person;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Service
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private ProjectMemberRepository projectMemberRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListAll() {
        Person person = new Person();
        PersonDtoResponse dtoResponse = new PersonDtoResponse(person);
        Page<Person> personPage = new PageImpl<>(Collections.singletonList(person));
        given(personRepository.findAll(any(Pageable.class))).willReturn(personPage);

        Page<PersonDtoResponse> result = personService.listAll(Pageable.unpaged());

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(dtoResponse.getId(), result.getContent().get(0).getId());
    }

    @Test
    void testSave_Create() {
        PersonDtoRequest dtoRequest = new PersonDtoRequest();
        Person person = new Person(dtoRequest);
        Person savedPerson = new Person(dtoRequest);
        savedPerson.setId(1L);

        given(personRepository.save(any(Person.class))).willReturn(savedPerson);

        PersonDtoResponse result = personService.save(dtoRequest);

        assertNotNull(result);
        assertEquals(savedPerson.getId(), result.getId());
    }

    @Test
    void testSave_Update() {
        PersonDtoRequest dtoRequest = new PersonDtoRequest();
        dtoRequest.setId(1L);
        Person existingPerson = new Person();
        existingPerson.setId(1L);
        given(personRepository.existsById(anyLong())).willReturn(true);
        given(personRepository.findById(anyLong())).willReturn(Optional.of(existingPerson));
        given(personRepository.saveAndFlush(any(Person.class))).willReturn(existingPerson);

        PersonDtoResponse result = personService.save(dtoRequest);

        assertNotNull(result);
        assertEquals(existingPerson.getId(), result.getId());
    }

    @Test
    void testGetByCpf_Found() {
        Person person = new Person();
        person.setCpf("12345678900");
        PersonDtoResponse dtoResponse = new PersonDtoResponse(person);

        given(personRepository.findByCpf(anyString())).willReturn(Optional.of(person));

        PersonDtoResponse result = personService.getByCpf("12345678900");

        assertNotNull(result);
        assertEquals(dtoResponse.getCpf(), result.getCpf());
    }

    @Test
    void testGetByCpf_NotFound() {
        given(personRepository.findByCpf(anyString())).willReturn(Optional.empty());

        CustomNotFoundException thrown = assertThrows(CustomNotFoundException.class, () -> {
            personService.getByCpf("12345678900");
        });

        assertEquals("Nenhum registro encontrado com o CPF informado (12345678900).", thrown.getMessage());
    }

    @Test
    void testDelete_Success() {
        Person person = new Person();
        person.setId(1L);
        List<ProjectMember> projectMemberList = Collections.emptyList();

        given(personRepository.findById(anyLong())).willReturn(Optional.of(person));
        given(projectMemberRepository.findByPersonId(anyLong())).willReturn(projectMemberList);

        String result = personService.delete(1L);

        assertEquals("Registro excluído com sucesso.", result);
        verify(personRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testDelete_Failure() {
        Person person = new Person();
        person.setId(1L);
        List<ProjectMember> projectMemberList = Collections.singletonList(new ProjectMember());

        given(personRepository.findById(anyLong())).willReturn(Optional.of(person));
        given(projectMemberRepository.findByPersonId(anyLong())).willReturn(projectMemberList);

        CustomHasDependentRegistersException thrown = assertThrows(CustomHasDependentRegistersException.class, () -> {
            personService.delete(1L);
        });

        assertEquals("Pessoa (1) vinculada a projetos registrados. Exclusão não permitida.", thrown.getMessage());
    }
}