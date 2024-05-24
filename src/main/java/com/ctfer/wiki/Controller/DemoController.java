package com.ctfer.wiki.Controller;

import com.ctfer.wiki.domain.Demo;
import com.ctfer.wiki.service.DemoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    @Resource
    private DemoService demoService;

   @GetMapping("/demo/list")
    public List<Demo> demoList() {
        return demoService.list();  // /test/list 访问testService中的list()方法
   }
}
