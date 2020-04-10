package htp.controller;


import htp.domain.model.User;
import htp.controller.request.UserFront;
import htp.security.util.PrincipalUtils;
import htp.services.UserDetailsServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Timestamp;


@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/users")
public class UserController {
    public static final String USER_DELETED = "User Successfully deleted";

    private UserDetailsServiceImpl userService;

    public UserController(UserDetailsServiceImpl userDao) {
        this.userService = userDao;
    }

    @ApiOperation(value = "Show information about user")
    @ApiResponses({
            @ApiResponse(code =200, message = "User successfully found"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping
    public ResponseEntity<User> getUserById(@ApiIgnore Principal principal) {
        String login = PrincipalUtils.getUsername(principal);
        User performer = userService.findByLogin(login);
        return new ResponseEntity<>(performer, HttpStatus.OK);
    }

    @ApiOperation(value = "Fake delete user")
    @ApiResponses({
            @ApiResponse(code =200, message = "User successfully deleted"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteUser(@ApiIgnore Principal principal) {
        String login = PrincipalUtils.getUsername(principal);
        User performer = userService.findByLogin(login);
        userService.fakeDelete(performer.getUserId());
        return new ResponseEntity<>(USER_DELETED, HttpStatus.OK);
    }


    @ApiOperation(value = "Change password")
    @ApiResponses({
            @ApiResponse(code =200, message = "Password successfully changed"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional(rollbackFor = Exception.class)
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> updateUser(@RequestBody @Valid UserFront request,
                                           @ApiIgnore Principal principal) {
        String login = PrincipalUtils.getUsername(principal);
        User performer = userService.findByLogin(login);
        performer.setPassword(request.getPassword());
        performer.setChanged(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(userService.updateUser(performer), HttpStatus.OK);
    }

}
