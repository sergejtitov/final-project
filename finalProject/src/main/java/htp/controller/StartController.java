package htp.controller;


import htp.domain.model.User;
import htp.controller.request.UserFront;
import htp.services.UserDetailsServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;


import javax.validation.Valid;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping(value = "/registration")
public class StartController {

    private final ConversionService conversionService;

    private UserDetailsServiceImpl userService;

    public StartController(UserDetailsServiceImpl userDao, ConversionService conversionService) {
        this.userService = userDao;
        this.conversionService = conversionService;
    }

    @ApiOperation(value = "Create new User")
    @ApiResponses({
            @ApiResponse(code =201, message = "User successfully created"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@RequestBody @Valid UserFront request){
        User user = conversionService.convert(request, User.class);
        return new ResponseEntity<>(userService.saveUser(Objects.requireNonNull(user)), HttpStatus.OK);
    }

}
