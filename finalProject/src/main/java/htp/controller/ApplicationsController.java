package htp.controller;


import htp.dao.CreditInfoRepository;
import htp.dao.ProductRepository;
import htp.entities.db_entities.Application;
import htp.entities.front_entities.ApplicationFront;
import htp.processors.ApplicationProcessor;
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

    private ProductRepository productService;
    private CreditInfoRepository creditInfoService;

    public ApplicationsController(ProductRepository productService, CreditInfoRepository creditInfoService) {
        this.productService = productService;
        this.creditInfoService = creditInfoService;
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Application> createUser(@RequestBody @Valid ApplicationFront request){
        Application application = Parsers.createApplication(request);
        ApplicationProcessor processor = new ApplicationProcessor(productService, creditInfoService);
        application = processor.start(application);
        return new ResponseEntity<>(application, HttpStatus.OK);
    }
}
