package htp.controller;

import htp.dao.UserRepository;
import htp.dao.spring_impl.UserRepSpringImp;
import htp.entities.db_entities.Roles;
import htp.entities.db_entities.User;
import htp.entities.front_entities.UserF;
import htp.entities.front_entities.UserFAdmin;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@CrossOrigin
@RequestMapping(value = "/users")
public class UserController {
    public static final Integer LIMIT = 10;
    private UserRepository userDao;

    public UserController(UserRepSpringImp userDao) {
        this.userDao = userDao;
    }

//    @GetMapping
//    public ResponseEntity<List<User>> getUsers() {
//        return new ResponseEntity<>(userDao.findAll(5,0), HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(@RequestParam Integer offset) {
        return new ResponseEntity<>(userDao.findAll(LIMIT,offset*LIMIT), HttpStatus.OK);
    }


    @ApiOperation(value = "Get user from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting user"),
            @ApiResponse(code = 400, message = "Invalid User ID supplied"),
            @ApiResponse(code = 401, message = "UnAuthorized User"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@ApiParam("User Path Id") @PathVariable Long id) {
        User user = userDao.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteUser(@PathVariable("id") Long userId) {
        userDao.fakeDelete(userId);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@RequestBody @Valid UserFAdmin request){
        User user = new User();
        user.setUserId(2L);
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setChanged(user.getCreated());
        Set<Roles> role = new HashSet<>();
        user.setRoles(role);
        user.setIsdeleted(false);
        User savedUser = userDao.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @ApiOperation(value = "Update user password")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful user update"),
            @ApiResponse(code = 400, message = "Invalid User ID supplied"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody UserF request) {
        User user = userDao.findById(userId);
        user.setPassword(request.getPassword());
        user.setChanged(new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(userDao.update(user), HttpStatus.OK);
    }

}
