package com.ctfer.wiki.service;

import com.ctfer.wiki.domain.Test;
import com.ctfer.wiki.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public List<Test> list(){

        return testMapper.list();
    }
}
