//http://localhost:8080/spring_pr_war_exploded/
//hotswap
//tag resources webapp spring path
//life cycle hibernate bean
//pamiętać, że w dao zmieniłem entitymenagera tek że testy teraz nie będą działać
//http://slawekturowicz.blogspot.com/2011/01/transakcyjnosc-w-springu.html

package pl.insert.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pl.insert.model.User;
import pl.insert.service.UserService;

import java.util.List;


@Controller
public class UserRestController {


    @Autowired
    private UserService userService;


    @RequestMapping(value = "/")//, method = RequestMethod.GET)
    @ResponseBody
    public List<User> helloWorld(){
        List<User> users = (List<User>) userService.getUserList();
        //User user = userService.findUserById((long) 1);

        //return user;
        return users;
    }



    //http://localhost:8080/spring_pr_war_exploded/add-user-request-param?name=parameterName&surname=parameterSurname
    @RequestMapping(value = "/add-user-request-param")
    @ResponseBody
    public User addUserFromParams(@RequestParam String name, @RequestParam String surname){
        User user = new User();
        user.setName(name);
        user.setSurname(surname);

        userService.addUser(user);
        return user;
    }

    @RequestMapping(value = "/add-user-request-body", method = RequestMethod.POST)
    @ResponseBody
    public String addUserFromRequest(@RequestBody User userFromRequest){

        User newUser = new User();
        String operationStatus = null;

        try{
            newUser.setName(userFromRequest.getName());
            newUser.setSurname(userFromRequest.getSurname());

            userService.addUser(newUser);
            operationStatus = "success" ;
        }catch(Exception e){
            operationStatus = "fail";
            e.printStackTrace();
        }

        return operationStatus;
    }


}
