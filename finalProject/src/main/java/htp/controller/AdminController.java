package htp.controller;

import htp.domain.model.User;
import htp.controller.request.UserFrontAdmin;
import htp.exceptions.CustomValidationException;
import htp.services.UserDetailsServiceImpl;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import htp.utils.Parsers;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Objects;


@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping(value = "/admin")
public class AdminController {
    public static final Integer LIMIT = 10;

    private UserDetailsServiceImpl userService;

    private final ConversionService conversionService;


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

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@RequestBody @Valid UserFrontAdmin request){
        User user = conversionService.convert(request, User.class);
        User savedUser = userService.saveUser(Objects.requireNonNull(user));
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

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
