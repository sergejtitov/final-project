package com.htp.controller;


import com.htp.controller.request.ApplicationResult;

import com.htp.domain.model.Application;
import com.htp.exceptions.CustomValidationException;
import com.htp.services.ApplicationService;
import com.htp.services.search_criteria.ApplicationSpecification;
import com.htp.services.search_criteria.SearchCriteria;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/audit/applications")
public class AuditController {

    private final ApplicationService applicationService;
    private final ConversionService conversionService;

    public AuditController(ApplicationService applicationService, ConversionService conversionService) {
        this.applicationService = applicationService;
        this.conversionService = conversionService;
    }


    @ApiOperation(value = "Get full application by Id")
    @ApiResponses({
            @ApiResponse(code =200, message = "Application successfully found"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 404, message = "Applications not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({@ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")})
    @GetMapping(value = "/{id}")
    public ResponseEntity<Application> getApplicationById(@ApiParam("Application Path Id") @PathVariable String id) {
        long applicationId;
        Application application;
        try {
            applicationId = Long.parseLong(id);
            application = applicationService.findById(applicationId);
        } catch (NumberFormatException e){
            throw new CustomValidationException("Illegal path!");
        }
        return new ResponseEntity<>(application, HttpStatus.OK);
    }

    @ApiOperation(value = "Get applications with Search criteria")
    @ApiResponses({
            @ApiResponse(code =200, message = "Applications successfully found"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 404, message = "Applications not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({@ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")})
    @GetMapping
    public ResponseEntity<List<ApplicationResult>> getSearchedApplications (@RequestParam(value = "delimiter = ,") String request){
        List<ApplicationResult> applicationResultList = new ArrayList<>();
        ApplicationSpecification builder = new ApplicationSpecification();
        Pattern pattern= Pattern.compile("(\\w+?)([:<>=])(\\w+?),");
        Matcher matcher = pattern.matcher(request+",");
        while (matcher.find()){
            builder.add(new SearchCriteria(matcher.group(1),matcher.group(2), matcher.group(3)));
        }
        List<Application> applicationList = applicationService.findAll(builder);
        for (Application application : applicationList){
            ApplicationResult applicationResult = conversionService.convert(application, ApplicationResult.class);
            applicationResultList.add(applicationResult);
        }
        return new ResponseEntity<>(applicationResultList, HttpStatus.OK);
    }

}
