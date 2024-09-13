package br.com.exemplo.testai.cases.projectMember;


import br.com.exemplo.testai.models.ProjectMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProjectMemberService {

    @Autowired
    private ProjectMemberRepository repository;

    //TODO: implementar métodos save(), getById(), list(), delete()
}
