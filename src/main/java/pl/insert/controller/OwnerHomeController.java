package pl.insert.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.insert.model.Owner;

@Controller
public class OwnerHomeController {

    @RequestMapping(value = "/")//, method = RequestMethod.GET)
    public String home(){
        return "home";
    }

    @RequestMapping(value="/register")
    public String register(Owner owner){

        System.out.println(owner.getName());

        return "home";
    }


}
