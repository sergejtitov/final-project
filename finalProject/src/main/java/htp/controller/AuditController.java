package htp.controller;


import htp.controller.request.ApplicationResult;
import htp.controller.request.SearchCriteria;
import htp.controller.request.UserFrontAdmin;
import htp.domain.model.Application;
import htp.domain.model.User;
import htp.exceptions.CustomValidationException;
import htp.services.ApplicationService;
import htp.utils.ApplicationSpecificationBuilder;
import htp.utils.Parsers;
import io.swagger.annotations.ApiParam;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@CrossOrigin
@RequestMapping("/audit/applications")
public class AuditController {
    public static final Integer LIMIT = 10;

    ApplicationService applicationService;
    private final ConversionService conversionService;

    public AuditController(ApplicationService applicationService, ConversionService conversionService) {
        this.applicationService = applicationService;
        this.conversionService = conversionService;
    }

/*    @GetMapping
    public ResponseEntity<List<ApplicationResult>> getApplications(@RequestParam String offset) {
        List<ApplicationResult> applicationResultList = new ArrayList<>();
        try {
            int offsetInt = Integer.parseInt(offset);
            List<Application> applicationList = applicationService.findAll(LIMIT,offsetInt);
            for (Application application : applicationList){
                ApplicationResult applicationResult = conversionService.convert(application, ApplicationResult.class);
                applicationResultList.add(applicationResult);
            }
        } catch (NumberFormatException e){
            throw new CustomValidationException("Illegal path!");
        }
        return new ResponseEntity<>(applicationResultList, HttpStatus.OK);
    }*/

/*    @GetMapping(value = "/{id}")
    public ResponseEntity<Application> getApplicationById(@ApiParam("Application Path Id") @PathVariable String id) {
        Long applicationId;
        Application application;
        try {
            applicationId = Long.parseLong(id);
            application = applicationService.findById(applicationId, );
        } catch (NumberFormatException e){
            throw new CustomValidationException("Illegal path!");
        }
        return new ResponseEntity<>(application, HttpStatus.OK);
    }*/

    @GetMapping
    public ResponseEntity<List<ApplicationResult>> getSearchedApplications (@RequestParam(value = "search") String request){
        List<ApplicationResult> applicationResultList = new ArrayList<>();
        ApplicationSpecificationBuilder builder = new ApplicationSpecificationBuilder();
        Pattern pattern= Pattern.compile("(\\w+?)([:<>=])(\\w+?),");
        Matcher matcher = pattern.matcher(request+",");
        while (matcher.find()){
            builder.add(matcher.group(1),matcher.group(2), matcher.group(3));
        }
        Specification<Application> spec = builder.build();
        List<Application> applicationList = applicationService.findAll(spec);
        for (Application application : applicationList){
            ApplicationResult applicationResult = conversionService.convert(application, ApplicationResult.class);
            applicationResultList.add(applicationResult);
        }
        return new ResponseEntity<>(applicationResultList, HttpStatus.OK);
    }
}
