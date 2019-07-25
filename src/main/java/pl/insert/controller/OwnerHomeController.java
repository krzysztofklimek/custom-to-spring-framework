package pl.insert.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.insert.dto.OwnerDto;
import pl.insert.model.Owner;
import pl.insert.service.OwnerService;

import javax.validation.Valid;


@Controller
public class OwnerHomeController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @RequestMapping(value = {"/", "/register"}, method = RequestMethod.GET)
    public String home(WebRequest request, Model model){
        OwnerDto ownerDto = new OwnerDto();
        model.addAttribute("ownerDto", ownerDto);

        return "home";
    }


    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ModelAndView register(@Valid @ModelAttribute OwnerDto ownerFromForm, BindingResult bindingResult){

//        if (bindingResult.hasErrors()) {
//            return "add";
//        }


        if(bindingResult.hasErrors())
            return new ModelAndView("home", "ownerDto", ownerFromForm);
            //System.out.println(bindingResult.getAllErrors().toString());

        else {

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


            return new ModelAndView("home", "ownerDto", ownerFromForm);
        }

    }





//    @RequestMapping(value="/register", method = RequestMethod.POST)
//    public String register(@Valid @ModelAttribute OwnerDto ownerFromForm, BindingResult bindingResult){
//
////        if (bindingResult.hasErrors()) {
////            return "add";
////        }
//
//
//        if(bindingResult.hasErrors())
//            System.out.println(bindingResult.getAllErrors().toString());
//
//        else {
//
//            OwnerDto ownerDto = new OwnerDto();
//            ownerDto.setName(ownerFromForm.getName());
//            ownerDto.setSurname(ownerFromForm.getSurname());
//            ownerDto.setEmail(ownerFromForm.getEmail());
//            ownerDto.setPassword(ownerFromForm.getPassword());
//            ownerDto.setConfirmPassword(ownerFromForm.getConfirmPassword());
//
//
//            Owner owner = new Owner();
//            owner.setName(ownerDto.getName());
//            owner.setSurname(ownerDto.getSurname());
//            owner.setEmail(ownerDto.getEmail());
//            owner.setPassword(passwordEncoder.encode(ownerDto.getPassword()));
//            owner.setEnabled(true);
//
//            ownerService.addOwner(owner);
//
//        }
//
//        return "home";
//    }

    @RequestMapping(value="/hello-world")
    public String helloWorld(){
        return "HelloWorld";
    }

    @RequestMapping(value="/fail")
    public String fail(){
        return "fail";
    }

}
