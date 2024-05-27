package com.ctfer.wiki.service;

import com.ctfer.wiki.domain.Ebook;
import com.ctfer.wiki.domain.EbookExample;
import com.ctfer.wiki.mapper.EbookMapper;
import com.ctfer.wiki.req.EbookReq;
import com.ctfer.wiki.req.PageReq;
import com.ctfer.wiki.resp.EbookResp;
import com.ctfer.wiki.resp.PageResp;
import com.ctfer.wiki.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {
    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    @Autowired
    private EbookMapper ebookMapper;

    public PageResp<EbookResp> list(EbookReq req){
        EbookExample ebookExample = new EbookExample();
        // Criteria 相当于 where条件
        EbookExample.Criteria criteria = ebookExample.createCriteria();

        // adnNameLike like模糊匹配
//        if(...) 判断是否存在name参数,存在返回true否则返回false, !-> 不为空则返回对应查询的数据，为空返回所有数据
        if(!ObjectUtils.isEmpty(req.getName())){
            criteria.andNameLike("%"+req.getName()+"%");
        }


        //        PageHelper.startPge(1,3) 分页从第1页开始，每页3条，注意：只会查询与它紧挨着的第一个sql查询，若有其他查询，将失效
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Ebook> ebooklist = ebookMapper.selectByExample(ebookExample);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebooklist); // 3. ebooklist
        LOG.info("总条数: {}", pageInfo.getTotal());   // 4.getTotal()
        LOG.info("总页数: {}", pageInfo.getPages());
//        分页的四个要素 PageHelper.startPage(1, 3)中的：1.开始页数，2.每页页数
//        3. ebooklist, 4. getTotal()

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

        PageResp<EbookResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }
}
