package htp.controller;


import htp.dao.ApplicationRepository;
import htp.entities.db_entities.Application;
import htp.entities.front_entities.ApplicationFront;
import htp.entities.front_entities.ApplicationResult;
import htp.utils.Parsers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@CrossOrigin
@RequestMapping(value = "/applications")
public class ApplicationsController {

    ApplicationRepository applicationService;

    public ApplicationsController(ApplicationRepository applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApplicationResult> createUser(@RequestBody @Valid ApplicationFront request){
        Application application = Parsers.createApplication(request);
        application = applicationService.save(application);
        ApplicationResult applicationResult = Parsers.createApplicationResult(application);
        return new ResponseEntity<>(applicationResult, HttpStatus.OK);
    }
}
