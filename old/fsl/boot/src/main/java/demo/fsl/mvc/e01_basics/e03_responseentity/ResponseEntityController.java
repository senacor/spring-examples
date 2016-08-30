package demo.fsl.mvc.e01_basics.e03_responseentity;

import demo.fsl.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * Created by fsubasi on 28.01.2016.
 * In this example we see how to return a ResponseEntity
 * ResponseEntity carries metadata about a response.
 * In addition to setting the response body object, we can set
 * the headers and status code.
 */
@RestController
@RequestMapping("/responseEntity")
public class ResponseEntityController {

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public ResponseEntity<User> responseEntityGET(@PathVariable("userId") int userId){
        // Use userId to get the user from somewhere, here we just return a mock object
        User user = User.createUser();

        // If we want to modify the headers
        // HttpHeaders headers = new HttpHeaders();
        // headers.set(....)
        // ResponseEntity<User> response = new ResponseEntity<>(user, headers, HttpStatus.OK);

        // If we do not want to modify headers and status code is OK
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> responseEntityExample(@RequestBody User user){
        // Do something with user ...................
        // ..........................
        HttpHeaders headers = new HttpHeaders();
        // Create a mock location
        URI locationUri = URI.create("http://localhost/responseEntity/users/2");
        headers.setLocation(locationUri);
        // create a ResponseEntity with custom headers and status code
        ResponseEntity<?> userResponseEntity = new ResponseEntity<>(headers, HttpStatus.CREATED);
        // we could also send the created user object back, assuming 'savedUser' is the user with its id set
        // ResponseEntity<User> userResponseEntity = new ResponseEntity<>(savedUser, headers, HttpStatus.CREATED);

        return userResponseEntity;
    }
}
