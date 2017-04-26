package test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test.logic.AjaxResponseBody;
import test.logic.UserData;
import test.logic.UserDataDAO;

import javax.validation.Valid;
import java.util.stream.Collectors;

/**
 * Created by Serj on 26.04.2017.
 */
@RestController
public class AjaxController {
    @Autowired
    private UserDataDAO userDataDAO;

    @PostMapping("/addWithAjax")
    public ResponseEntity<?> addUserWIthAjax(@Valid @RequestBody UserData userData,
                                             Errors errors) {
        AjaxResponseBody result = new AjaxResponseBody();
        if (errors.hasErrors()) {
            result.setMessage(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }

        userDataDAO.addUserData(userData);
        result.setMessage("Success. Last added data:");
        result.setUserData(userData);
        return ResponseEntity.ok(result);
    }
}
