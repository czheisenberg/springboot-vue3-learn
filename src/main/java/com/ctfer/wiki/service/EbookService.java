package com.ctfer.wiki.service;

import com.ctfer.wiki.domain.Ebook;
import com.ctfer.wiki.domain.EbookExample;
import com.ctfer.wiki.mapper.EbookMapper;
import com.ctfer.wiki.req.EbookReq;
import com.ctfer.wiki.resp.EbookResp;
import com.ctfer.wiki.util.CopyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {

    @Autowired
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req){
        EbookExample ebookExample = new EbookExample();
        // Criteria 相当于 where条件
        EbookExample.Criteria criteria = ebookExample.createCriteria();

        // adnNameLike like模糊匹配
        criteria.andNameLike("%"+req.getName()+"%");

         List<Ebook> ebooklist = ebookMapper.selectByExample(ebookExample);

//        List<EbookResp> respList = new ArrayList<>();
//        for(Ebook ebook : ebooklist){
////            EbookResp ebookResp = new EbookResp();
////            BeanUtils.copyProperties(ebook, ebookResp);
//              // 对象复制
//            EbookResp ebookResp = CopyUtil.copy(ebook, EbookResp.class);
//
//            respList.add(ebookResp);
//        }
//        列表复制
        List<EbookResp> list = CopyUtil.copyList(ebooklist, EbookResp.class);

        return list;
    }
}
