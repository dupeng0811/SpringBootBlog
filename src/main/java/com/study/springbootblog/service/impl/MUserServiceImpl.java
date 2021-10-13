package com.study.springbootblog.service.impl;

import com.study.springbootblog.entity.MUser;
import com.study.springbootblog.mapper.MUserMapper;
import com.study.springbootblog.service.IMUserService;
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
public class MUserServiceImpl extends ServiceImpl<MUserMapper, MUser> implements IMUserService {

}
