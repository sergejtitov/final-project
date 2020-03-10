package htp.controller;

import htp.dao.UserRepository;
import htp.dao.spring_impl.UserRepSpringImp;
import htp.entities.db_entities.User;
import htp.entities.front_entities.UserF;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;

@RestController
@CrossOrigin
@RequestMapping(value = "/")
public class StartController {

    private UserRepository userDao;

    public StartController(UserRepSpringImp userDao) {
        this.userDao = userDao;
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@RequestBody @Valid UserF request){
        User user = new User();
        user.setUserId(1L);
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setChanged(user.getCreated());
        user.setRole("ROLE_USER");
        user.setIsdeleted(false);
        User savedUser = userDao.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }
}
