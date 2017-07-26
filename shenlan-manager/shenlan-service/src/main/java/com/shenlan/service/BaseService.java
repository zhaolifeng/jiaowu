package com.shenlan.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shenlan.api.IBaseService;



@Service
@Transactional(readOnly = true)
public abstract class BaseService<T> implements IBaseService<T>
{
	
}
