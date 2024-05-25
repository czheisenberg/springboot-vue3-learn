package com.ctfer.wiki.Controller;

import com.ctfer.wiki.domain.Ebook;
import com.ctfer.wiki.resp.CommonResp;
import com.ctfer.wiki.service.EbookService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

   @GetMapping("/list")
    public CommonResp list(String name){
       CommonResp<List<Ebook>> resp = new CommonResp<>();
       List<Ebook> list = ebookService.list(name);
       resp.setContent(list);

       return resp;
   }
}
