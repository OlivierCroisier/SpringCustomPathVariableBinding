package net.thecodersbreakfast.spring.web;

import net.thecodersbreakfast.spring.web.util.Base64PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Base64Controller {

    @GetMapping("/decode/{base64}")
    public String decodeBase64(@Base64PathVariable("base64") String base64) {
        return base64;
    }

}
