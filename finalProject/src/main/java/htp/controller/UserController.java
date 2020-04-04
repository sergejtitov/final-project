package htp.controller;


import htp.domain.model.User;
import htp.controller.request.UserFront;
import htp.exceptions.CustomValidationException;
import htp.services.UserDetailsServiceImpl;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;



@RestController
@CrossOrigin
@RequestMapping(value = "/users")
public class UserController {
    private UserDetailsServiceImpl userService;

    public UserController(UserDetailsServiceImpl userDao) {
        this.userService = userDao;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@ApiParam("User Path Id") @PathVariable String id) {
        User user;
        long userId;
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
            userService.fakeDelete(userIdLong);
        } catch (NumberFormatException e){
            throw new CustomValidationException("Illegal path!");
        }
        return new ResponseEntity<>(userIdLong, HttpStatus.OK);
    }


    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> updateUser(@PathVariable("id") String userId,
                                           @RequestBody @Valid UserFront request) {
        long userIdLong;
        User user;
        try {
            userIdLong = Long.parseLong(userId);
            user = userService.findUserById(userIdLong);
            user.setPassword(request.getPassword());
            user.setChanged(new Timestamp(System.currentTimeMillis()));
        } catch (NumberFormatException e){
            throw new CustomValidationException("Illegal path!");
        }
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

}
