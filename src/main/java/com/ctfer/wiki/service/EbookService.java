package com.ctfer.wiki.service;

import com.ctfer.wiki.domain.Ebook;
import com.ctfer.wiki.mapper.EbookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EbookService {

    @Autowired
    private EbookMapper ebookMapper;

    public List<Ebook> list(){

        return ebookMapper.selectByExample(null);
    }
}
