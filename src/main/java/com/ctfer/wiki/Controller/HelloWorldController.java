package com.ctfer.wiki.Controller;

import com.ctfer.wiki.domain.Test;
import com.ctfer.wiki.service.TestService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloWorldController {

    @Resource
    private TestService testService;    // TestService 对应 HelloWordController

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

   @GetMapping("/test/list")
    public List<Test> testList() {
        return testService.list();  // /test/list 访问testService中的list()方法
   }
}
