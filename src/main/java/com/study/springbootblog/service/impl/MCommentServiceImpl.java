package com.study.springbootblog.service.impl;

import com.study.springbootblog.entity.MComment;
import com.study.springbootblog.mapper.MCommentMapper;
import com.study.springbootblog.service.IMCommentService;
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
public class MCommentServiceImpl extends ServiceImpl<MCommentMapper, MComment> implements IMCommentService {

}
