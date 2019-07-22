//http://localhost:8080/spring_pr_war_exploded/
//hotswap
//tag resources webapp spring path
//life cycle hibernate bean
//pamiętać, że w dao zmieniłem entitymenagera tek że testy teraz nie będą działać
//http://slawekturowicz.blogspot.com/2011/01/transakcyjnosc-w-springu.html

package pl.insert.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.insert.service.UserService;

@RestController
public class UserRestController {


    @Autowired
    private UserService userService;


    @RequestMapping(value = "/123")//, method = RequestMethod.GET)
    @ResponseBody
    public String helloWorld(){
        //List<User> users = (List<User>) userService.getUserList();
        //User user = userService.findUserById((long) 1);

        //return user;
        return userService.findUserById((long) 1).toString();
    }


}
