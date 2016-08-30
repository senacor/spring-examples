package demo.fsl.restdocs;

import demo.fsl.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by fsubasi on 01.02.2016.
 */
@RestController
public class MainController {
    @Autowired
    User user;

    /**
     * This is the method that is documented with RestDocs
     * @return
     */
    @RequestMapping(value = "/restDocs/{userId}", method = RequestMethod.GET)
    public User showUser(){
        return user;
    }
}
