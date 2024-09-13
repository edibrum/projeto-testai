package br.com.exemplo.testai.cases.projectMember;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project-member")
public class ProjectMemberController {

    @Autowired
    private ProjectMemberService service;

    //TODO: implementar rotas
    // @PostMapping("/save") save(),
    // @GetMapping("/getById") getById(),
    // @GetMapping("/list") list(),
    // @DeleteMapping("/delete") delete()

}