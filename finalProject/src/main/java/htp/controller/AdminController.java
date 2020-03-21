package htp.controller;

import htp.entities.db_entities.User;
import htp.entities.front_entities.UserFAdmin;
import htp.services.UserDetailsServiceImpl;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import htp.utils.Parsers;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = "/admin")
public class AdminController {
    public static final Integer LIMIT = 10;

    private UserDetailsServiceImpl userService;

    public AdminController(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(@RequestParam Integer offset) {
        return new ResponseEntity<>(userService.findAll(LIMIT,offset*LIMIT), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@ApiParam("User Path Id") @PathVariable String id) {
        User user = userService.findUserById(Long.valueOf(id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteUser(@PathVariable("id") String userId) {
        userService.deleteUser(Long.valueOf(userId));
        return new ResponseEntity<>(Long.valueOf(userId), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@RequestBody @Valid UserFAdmin request){
        User user = new User();
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setChanged(user.getCreated());
        user.setRoles(Parsers.getSetOfRoles(request.getRole(), user));
        user.setIsdeleted(false);
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> updateUser(@PathVariable("id") String userId,
                                           @RequestBody UserFAdmin request) {
        User user = userService.findUserById(Long.valueOf(userId));
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setChanged(new Timestamp(System.currentTimeMillis()));
        user.setRoles(Parsers.getSetOfRoles(request.getRole(), user));
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

}
