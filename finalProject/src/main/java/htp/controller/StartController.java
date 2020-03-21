package htp.controller;


import htp.entities.db_entities.User;
import htp.entities.front_entities.UserF;
import htp.services.UserDetailsServiceImpl;
import htp.utils.Parsers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


import javax.validation.Valid;

@RestController
@CrossOrigin
public class StartController {

    private UserDetailsServiceImpl userService;

    public StartController(UserDetailsServiceImpl userDao) {
        this.userService = userDao;
    }

    @PostMapping(value = "/registration")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@RequestBody @Valid UserF request){
        User user = Parsers.createUser(request);
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

}
