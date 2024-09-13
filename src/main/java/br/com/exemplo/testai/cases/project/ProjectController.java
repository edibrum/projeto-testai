package br.com.exemplo.testai.cases.project;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService service;

    //TODO: implementar rotas
    // @PostMapping("/save") save(),
    // @GetMapping("/getById") getById(),
    // @GetMapping("/list") list(),
    // @DeleteMapping("/delete") delete()

}