package htp.controller;


import htp.entities.db_entities.Application;
import htp.entities.db_entities.User;
import htp.entities.front_entities.ApplicationFront;
import htp.entities.front_entities.ApplicationResult;
import htp.entities.front_entities.UserF;
import htp.utils.Parsers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping(value = "/applications")
public class ApplicationsController {

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApplicationResult> createUser(@RequestBody @Valid ApplicationFront request){
        Application application = Parsers.createApplication(request);
        return new ResponseEntity<>(new ApplicationResult(), HttpStatus.OK);
    }
}
