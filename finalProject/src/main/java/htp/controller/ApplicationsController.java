package htp.controller;


import htp.controller.request.Confirmation;
import htp.dao.ApplicationRepository;
import htp.domain.model.Application;
import htp.controller.request.ApplicationFront;
import htp.controller.request.ApplicationResult;
import htp.exceptions.CustomValidationException;
import htp.services.ApplicationService;
import htp.utils.Parsers;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping(value = "/applications")
public class ApplicationsController {

    ApplicationService applicationService;
    private final ConversionService conversionService;


    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApplicationResult> createUser(@RequestBody @Valid ApplicationFront request){
        Application application = conversionService.convert(request, Application.class);
        application = applicationService.save(application);
        ApplicationResult applicationResult = conversionService.convert(application, ApplicationResult.class);
        return new ResponseEntity<>(applicationResult, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ApplicationResult> confirmApplication(@PathVariable("id") String applicationId,
                                                          @RequestBody @Valid Confirmation request) {
        double confirmAmount;
        long updatedApplicationId;
        Application updatedApplication;
        ApplicationResult applicationResult;
        try{
            confirmAmount = Double.parseDouble(request.getFinalAmount());
            updatedApplicationId = Long.parseLong(applicationId);
            updatedApplication = applicationService.update(updatedApplicationId, confirmAmount);
            applicationResult = conversionService.convert(updatedApplication, ApplicationResult.class);
        } catch (NumberFormatException e){
            throw new CustomValidationException("Illegal path!");
        }
        return new ResponseEntity<>(applicationResult, HttpStatus.OK);
    }
}
