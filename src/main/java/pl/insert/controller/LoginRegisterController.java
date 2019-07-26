package pl.insert.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import org.springframework.web.servlet.ModelAndView;
import pl.insert.dto.OwnerDto;
import pl.insert.exception.EmailExistsException;
import pl.insert.exception.NotMatchingPassword;
import pl.insert.model.Owner;
import pl.insert.service.OwnerService;

import javax.validation.Valid;


@Controller
public class LoginRegisterController {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @RequestMapping(value = {"/", "/register"}, method = RequestMethod.GET)
    public String home(WebRequest request, Model model) {
        OwnerDto ownerDto = new OwnerDto();
        model.addAttribute("ownerDto", ownerDto);

        return "home";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@Valid @ModelAttribute OwnerDto ownerDto, BindingResult bindingResult) {

        Owner owner = null;

        if (bindingResult.hasErrors())
            return new ModelAndView("home", "ownerDto", ownerDto);


        else{
            try {
                owner = ownerService.createValidatedOwner(ownerDto);
            } catch (NotMatchingPassword notMatchingPassword) {
                bindingResult.rejectValue("confirmPassword", "error.owner", "do not match");
            } catch (EmailExistsException e) {
                bindingResult.rejectValue("email", "error.owner", "is already used");
            }

            if (bindingResult.hasErrors())
                return new ModelAndView("home", "ownerDto", ownerDto);

            else{
                ownerService.addOwner(owner);
                return new ModelAndView("home", "ownerDto", ownerDto);
            }
        }


    }


    @RequestMapping(value = "/hello-world")
    public String helloWorld() {
        return "HelloWorld";
    }

    @RequestMapping(value = "/fail")
    public String fail() {
        return "fail";
    }

}
