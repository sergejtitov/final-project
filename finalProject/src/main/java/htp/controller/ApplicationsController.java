package htp.controller;


import htp.controller.request.Confirmation;
import htp.domain.model.Application;
import htp.controller.request.ApplicationFront;
import htp.controller.request.ApplicationResult;
import htp.domain.model.User;
import htp.exceptions.CustomValidationException;
import htp.security.util.PrincipalUtils;
import htp.services.ApplicationService;
import htp.services.UserDetailsServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping(value = "/applications")
public class ApplicationsController {

    private final UserDetailsServiceImpl userService;
    private final ApplicationService applicationService;
    private final ConversionService conversionService;


    @ApiOperation(value = "Create Application")
    @ApiResponses({
            @ApiResponse(code =201, message = "Application successfully created"),
            @ApiResponse(code = 400, message = "Invalid application Id supplied"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 404, message = "Application not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({@ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")})
    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApplicationResult> createUser(@RequestBody @Valid ApplicationFront request, @ApiIgnore Principal principal){
        Application application = conversionService.convert(request, Application.class);
        String login = PrincipalUtils.getUsername(principal);
        User performer = userService.findByLogin(login);
        Objects.requireNonNull(application).setUserId(performer.getUserId());
        application = applicationService.save(application);
        ApplicationResult applicationResult = conversionService.convert(application, ApplicationResult.class);
        return new ResponseEntity<>(applicationResult, HttpStatus.OK);
    }

    @ApiOperation(value = "Confirmation final amount of Application")
    @ApiResponses({
            @ApiResponse(code =200, message = "Application successfully updated"),
            @ApiResponse(code = 400, message = "Invalid application Id supplied"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 404, message = "Application not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")})
    public ResponseEntity<ApplicationResult> confirmApplication(@PathVariable("id") String applicationId,
                                                          @RequestBody @Valid Confirmation request, @ApiIgnore Principal principal) {
        double confirmAmount;
        long updatedApplicationId;
        Application updatedApplication;
        ApplicationResult applicationResult;
        try{
            confirmAmount = Double.parseDouble(request.getFinalAmount());
            updatedApplicationId = Long.parseLong(applicationId);
            String login = PrincipalUtils.getUsername(principal);
            User performer = userService.findByLogin(login);
            updatedApplication = applicationService.update(updatedApplicationId, confirmAmount, performer.getUserId());
            applicationResult = conversionService.convert(updatedApplication, ApplicationResult.class);
        } catch (NumberFormatException e){
            throw new CustomValidationException("Illegal path!");
        }
        return new ResponseEntity<>(applicationResult, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all user's applications")
    @ApiResponses({
            @ApiResponse(code =200, message = "Application successfully got"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 404, message = "Applications not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping
    @ApiImplicitParams({@ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")})
    public ResponseEntity<List<ApplicationResult>> getUsersApplications ( @ApiIgnore Principal principal){
        String login = PrincipalUtils.getUsername(principal);
        User performer = userService.findByLogin(login);

        List<Application> usersApplications = applicationService.findApplicationByUserId(performer.getUserId());

        List<ApplicationResult> usersApplicationResults = new ArrayList<>();
        for (Application application : usersApplications){
            ApplicationResult applicationResult = conversionService.convert(application, ApplicationResult.class);
            usersApplicationResults.add(applicationResult);
        }

        return new ResponseEntity<>(usersApplicationResults, HttpStatus.OK);
    }

    @ApiOperation(value = "Get Application by its Id")
    @ApiResponses({
            @ApiResponse(code =201, message = "Application successfully got"),
            @ApiResponse(code = 400, message = "Invalid application Id supplied"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 404, message = "Application not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping(value = "/{id}")
    @ApiImplicitParams({@ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")})
    public ResponseEntity<Application> getApplicationById(@ApiParam("Application Path Id") @PathVariable String id, @ApiIgnore Principal principal){
        long applicationId;
        try {
            applicationId = Long.parseLong(id);
        } catch (NumberFormatException e){
            throw new CustomValidationException("Illegal path!");
        }
        String login = PrincipalUtils.getUsername(principal);
        User performer = userService.findByLogin(login);
        return new ResponseEntity<>(applicationService.findById(applicationId, performer.getUserId()), HttpStatus.OK);
    }
}
