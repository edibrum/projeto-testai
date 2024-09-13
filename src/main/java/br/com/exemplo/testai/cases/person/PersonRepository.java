package br.com.exemplo.testai.cases.person;

import br.com.exemplo.testai.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Page<Person> findByBirthDate(Date birthDate, Pageable pagination);

}