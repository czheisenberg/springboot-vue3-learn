package com.ctfer.wiki.service;

import com.ctfer.wiki.domain.Demo;
import com.ctfer.wiki.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoService {

    @Autowired
    private DemoMapper demoMapper;

    public List<Demo> list(){

        return demoMapper.selectByExample(null);
    }
}
