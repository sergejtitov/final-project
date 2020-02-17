package htp.Controller;

import htp.dao.SpringImpl.UserRepSpringImp;
import htp.entities.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

//import java.util.List;
//import java.util.stream.Collectors;

@Controller
public class UserController {
    private UserRepSpringImp userDao;

    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String printHello(@RequestParam("query") String query, ModelMap model) {
        //List<User> search = userDao.findAll();
        //model.addAttribute("userName",
        //        StringUtils.join(search.stream().map(User::getLogin).collect(Collectors.toList()), ","));
        return "hello";
    }
}
