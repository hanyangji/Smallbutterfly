package com.oracle.serviceimple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.dao.AderMapper;
import com.oracle.entity.Ader;
import com.oracle.service.AderService;
@Service
public class AderServiceimple implements AderService {

	@Autowired
	private AderMapper ad;
	
//	@Override
//	public int insert(Ader ader) {
//		return ad.insert(ader);
//	}


}
