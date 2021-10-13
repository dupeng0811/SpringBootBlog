package com.study.springbootblog.service.impl;

import com.study.springbootblog.entity.MPost;
import com.study.springbootblog.mapper.MPostMapper;
import com.study.springbootblog.service.IMPostService;
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
public class MPostServiceImpl extends ServiceImpl<MPostMapper, MPost> implements IMPostService {

}
