package htp.controller;

import htp.domain.model.User;
import htp.controller.request.UserFrontAdmin;
import htp.exceptions.CustomValidationException;
import htp.services.UserDetailsServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;

import htp.utils.Parsers;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Objects;

@Slf4j
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping(value = "/admin")
public class AdminController {
    public static final Integer LIMIT = 10;

    private UserDetailsServiceImpl userService;

    private final ConversionService conversionService;

    @ApiOperation(value = "Show all users")
    @ApiResponses({
            @ApiResponse(code =200, message = "Users successfully found"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({@ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")})
    @GetMapping
    public ResponseEntity<Page<User>> getUsers(@RequestParam String offsetString) {
        int offset;
        try {
            offset = Integer.parseInt(offsetString);
        } catch (NumberFormatException e){
            throw new CustomValidationException("Illegal path!");
        }
        return new ResponseEntity<>(userService.findAll(LIMIT,offset), HttpStatus.OK);
    }

    @ApiOperation(value = "Find user by ID")
    @ApiResponses({
            @ApiResponse(code =200, message = "User successfully found"),
            @ApiResponse(code = 400, message = "Invalid user Id supplied"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({@ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")})
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@ApiParam("User Path Id") @PathVariable String id) {
        long userId;
        User user;
        try {
            userId = Long.parseLong(id);
            user = userService.findUserById(userId);
        } catch (NumberFormatException e){
            throw new CustomValidationException("Illegal path!");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete user by Id")
    @ApiResponses({
            @ApiResponse(code =200, message = "User successfully deleted"),
            @ApiResponse(code = 400, message = "Invalid user Id supplied"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({@ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteUser(@PathVariable("id") String userId) {
        long userIdLong;
        try {
            userIdLong = Long.parseLong(userId);
            userService.deleteUser(userIdLong);
        } catch (NumberFormatException e){
            throw new CustomValidationException("Illegal path!");
        }
        return new ResponseEntity<>(userIdLong, HttpStatus.OK);
    }

    @ApiOperation(value = "Create user")
    @ApiResponses({
            @ApiResponse(code =201, message = "User successfully created"),
            @ApiResponse(code = 400, message = "Invalid user Id supplied"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({@ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")})
    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@RequestBody @Valid UserFrontAdmin request){
        User user = conversionService.convert(request, User.class);
        User savedUser = userService.saveUser(Objects.requireNonNull(user));
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @ApiOperation(value = "Update user")
    @ApiResponses({
            @ApiResponse(code =200, message = "User successfully updated"),
            @ApiResponse(code = 400, message = "Invalid user Id supplied"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({@ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")})
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> updateUser(@PathVariable("id") String userId,
                                           @RequestBody UserFrontAdmin request) {
        long userIdLong;
        User user;
        try {
            userIdLong = Long.parseLong(userId);
            user = userService.findUserById(userIdLong);
            user.setLogin(request.getLogin());
            user.setPassword(request.getPassword());
            user.setChanged(new Timestamp(System.currentTimeMillis()));
            user.setRoles(Parsers.getSetOfRoles(request.getRole(), user));
        } catch (NumberFormatException e){
            throw new CustomValidationException("Illegal path!");
        }
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

}
