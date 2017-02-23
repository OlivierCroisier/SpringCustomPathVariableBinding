package net.thecodersbreakfast.spring.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(Base64Controller.class)
public class Base64ControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void controllerDecodesBase64() throws Exception {
        String message = "Hello World";

        // Encode the message
        Charset charset = StandardCharsets.UTF_8;
        byte[] bytes = Base64.getEncoder().encode(message.getBytes(charset));
        String encoded = new String(bytes, charset);

        // Call the base64 decoder service
        mvc.perform(get("/decode/" + encoded))
                .andExpect(status().is2xxSuccessful())
                .andExpect(message::equals);
    }

}
