package pl.insert.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import pl.insert.dto.OwnerDto;
import pl.insert.model.Owner;
import pl.insert.service.OwnerService;

@Controller
public class OwnerHomeController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @RequestMapping(value = "/")//, method = RequestMethod.GET)
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

}
