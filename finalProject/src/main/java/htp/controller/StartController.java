package htp.controller;


import htp.entities.db_entities.Roles;
import htp.entities.db_entities.User;
import htp.entities.front_entities.UserF;
import htp.services.UserDetailsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping(value = "/registration")
public class StartController {

    private UserDetailsServiceImpl userService;

    public StartController(UserDetailsServiceImpl userDao) {
        this.userService = userDao;
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@RequestBody @Valid UserF request){
        User user = new User();
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setChanged(user.getCreated());
        Set<Roles> roles = new HashSet<>();
        roles.add(new Roles("ROLE_USER", user));
        user.setRoles(roles);
        user.setIsdeleted(false);
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }
}
