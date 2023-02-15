package com.crackcode.zahangir.annotations;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
@CrossOrigin(origins = "*", methods = {POST, GET, PUT, PATCH, DELETE, OPTIONS})
public @interface ApiController {
}
