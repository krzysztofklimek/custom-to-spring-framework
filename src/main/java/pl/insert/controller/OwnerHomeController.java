package pl.insert.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import pl.insert.dto.OwnerDto;
import pl.insert.model.Owner;
import pl.insert.service.OwnerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class OwnerHomeController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(WebRequest request, Model model){
        OwnerDto ownerDto = new OwnerDto();
        model.addAttribute("ownerDto", ownerDto);

        return "home";
    }

    @RequestMapping(value="/register")
    public String register(OwnerDto ownerFromForm){

        OwnerDto ownerDto = new OwnerDto();
        ownerDto.setName(ownerFromForm.getName());
        ownerDto.setSurname(ownerFromForm.getSurname());
        ownerDto.setEmail(ownerFromForm.getEmail());
        ownerDto.setPassword(ownerFromForm.getPassword());
        ownerDto.setConfirmPassword(ownerFromForm.getConfirmPassword());


        Owner owner = new Owner();
        owner.setName(ownerDto.getName());
        owner.setSurname(ownerDto.getSurname());
        owner.setEmail(ownerDto.getEmail());
        owner.setPassword(passwordEncoder.encode(ownerDto.getPassword()));
        owner.setEnabled(true);

        ownerService.addOwner(owner);

        return "home";
    }

    @RequestMapping(value="/hello-world")
    public String helloWorld(){
        return "HelloWorld";
    }

    @RequestMapping(value="/fail")
    public String fail(){
        return "fail";
    }


//    @RequestMapping(value="/logout", method = RequestMethod.GET)
//    public String logout(HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null)
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//
//        return "home";
//    }

}
