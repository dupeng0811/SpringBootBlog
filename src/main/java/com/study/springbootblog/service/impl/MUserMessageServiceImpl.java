package com.study.springbootblog.service.impl;

import com.study.springbootblog.entity.MUserMessage;
import com.study.springbootblog.mapper.MUserMessageMapper;
import com.study.springbootblog.service.IMUserMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Dupeng
 * @since 2021-10-12
 */
@Service
public class MUserMessageServiceImpl extends ServiceImpl<MUserMessageMapper, MUserMessage> implements IMUserMessageService {

}
