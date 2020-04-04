package htp.controller;


import htp.domain.model.User;
import htp.controller.request.UserFront;
import htp.services.UserDetailsServiceImpl;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


import javax.validation.Valid;
import java.util.Objects;

@RestController
@CrossOrigin
public class StartController {

    private final ConversionService conversionService;

    private UserDetailsServiceImpl userService;

    public StartController(UserDetailsServiceImpl userDao, ConversionService conversionService) {
        this.userService = userDao;
        this.conversionService = conversionService;
    }

    @PostMapping(value = "/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@RequestBody @Valid UserFront request){
        User user = conversionService.convert(request, User.class);
        return new ResponseEntity<>(userService.saveUser(Objects.requireNonNull(user)), HttpStatus.OK);
    }

}
