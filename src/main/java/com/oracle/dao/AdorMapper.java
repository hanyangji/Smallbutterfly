package com.oracle.dao;

import java.util.List;

import com.oracle.entity.Ador;

public interface AdorMapper {
    int insert(List<Ador> list);

    int insertSelective(Ador record);
    
    int deleteBatch(List<Ador> list);
}