package com.ctfer.wiki.Controller;

import com.ctfer.wiki.req.EbookSaveReq;
import com.ctfer.wiki.resp.CommonResp;
import com.ctfer.wiki.req.EbookQueryReq;
import com.ctfer.wiki.resp.EbookQueryResp;
import com.ctfer.wiki.resp.PageResp;
import com.ctfer.wiki.service.EbookService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

   @GetMapping("/list")
    public CommonResp list(EbookQueryReq req){   // EbookReq req请求参数
       CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
       PageResp<EbookQueryResp> list = ebookService.list(req);
       resp.setContent(list);

       return resp;
   }

    @PostMapping("/save")
    public CommonResp save(@RequestBody  EbookSaveReq req){   // EbookReq req请求参数 @RequestBody 表示 application/json格式的提交数据
        CommonResp  resp = new CommonResp<>();
        ebookService.save(req);

        return resp;
    }
}
