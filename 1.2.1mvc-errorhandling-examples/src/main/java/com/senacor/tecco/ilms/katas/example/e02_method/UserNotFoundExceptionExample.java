package com.senacor.tecco.ilms.katas.example.e02_method;

import com.senacor.tecco.ilms.katas.common.exceptions.ErrorMessageComposer;
import com.senacor.tecco.ilms.katas.common.model.User;
import com.senacor.tecco.ilms.katas.common.response.BaseResponse;
import com.senacor.tecco.ilms.katas.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by amishra on 10.03.16.
 * -------------------------------------------------------------------------------------------
 * Class to Raise UserNotFound Exception.
 * -------------------------------------------------------------------------------------------
 * Hit the URL corresponding to this class to raise the custom exception "UserNotFoundException" when user is not found.
 */

@Controller
@RequestMapping("/users")
public class UserNotFoundExceptionExample {

    private UserService userB = new UserService();

    @Autowired
    ApplicationContext context;

    @RequestMapping(value = "getUserById", method = RequestMethod.GET)
    @ResponseBody
    public String getUserById(@RequestParam(value = "id", required = true) String id) {
        User user = getUserFromID(Integer.parseInt(id));
        return user.toString();
    }

    public User getUserFromID(int uId)
    {
        User user1 = userB.getUserFromID(uId);
        if (user1 == null) {
            throw new UserNotFoundException("User with ID " + uId + " not found");
        }
        else {
            return user1;
        }
    }

    // @ExceptionHandler declares an exception handler for the specified
    // exception types. Only exceptions thrown in the same controller
    // will be intercepted by the error handler method.
    @ExceptionHandler(UserNotFoundException.class)// NOTE TO SELF: If the annotation value is not used,
    // then the exception types listed as method arguments are used
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public BaseResponse throwError(UserNotFoundException e){
        return ErrorMessageComposer.throwException(e, "user_not_found");
    }
}
