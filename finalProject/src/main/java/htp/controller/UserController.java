package htp.controller;


import htp.entities.db_entities.User;
import htp.entities.front_entities.UserF;
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
        User user = userService.findUserById(Long.valueOf(id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteUser(@PathVariable("id") Long userId) {
        userService.fakeDelete(userId);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody @Valid UserF request) {
        User user = userService.findUserById(userId);
        user.setPassword(request.getPassword());
        user.setChanged(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

}
