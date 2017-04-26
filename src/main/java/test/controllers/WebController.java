package test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import test.logic.UserData;
import test.logic.UserDataDAO;

import javax.validation.Valid;

@Controller
public class WebController extends WebMvcConfigurerAdapter{
    @Autowired
    private UserDataDAO userDataDAO;



    @PostMapping("/add")
    public String addUser(@Valid UserData userData, BindingResult bindingResult, ModelMap map) {
        if (bindingResult.hasErrors()) {
            return "userform";
        }

        map.addAttribute("addeduser", userData);
        userDataDAO.addUserData(userData);
        return "userform";
    }
    @GetMapping("/")
    public String showUserform(UserData userData) {

        return "userform";
    }
    @GetMapping("/ajax")
    public String showUserformAjax(UserData userData) {

        return "userformajax";
    }

    @RequestMapping(value="/create")
    @ResponseBody
    public String create(String name, String nickname, int age) {
        try {
            UserData userData = new UserData(name, age);
            userDataDAO.addUserData(userData);
        }
        catch (Exception ex) {
            return "Error creating the UserData: " + ex.toString();
        }
        return "UserData succesfully created!";
    }


    @RequestMapping(value="/delete")
    @ResponseBody
    public String delete(long id) {
        try {
            UserData userData = userDataDAO.getById(id);
            userDataDAO.removeUserData(userData);
        }
        catch (Exception ex) {
            return "Error deleting the UserData: " + ex.toString();
        }
        return "UserData succesfully deleted!";
    }


    @RequestMapping(value="/get-by-name")
    @ResponseBody
    public String getByName(String name) {
        String riderProfileId;
        try {
            UserData userData = userDataDAO.getByName(name);
            riderProfileId = String.valueOf(userData.getId());
        }
        catch (Exception ex) {
            return "UserData not found: " + ex.toString();
        }
        return "The UserData id is: " + riderProfileId;
    }


    @RequestMapping(value="/update")
    @ResponseBody
    public String updateName(long id, String name, int age) {
        try {
            UserData userData = userDataDAO.getById(id);
            userData.setName(name);
            userData.setAge(age);
            userDataDAO.updateUserData(userData);
        }
        catch (Exception ex) {
            return "Error updating the UserData: " + ex.toString();
        }
        return "UserData succesfully updated!";
    }


}
