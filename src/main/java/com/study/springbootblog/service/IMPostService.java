package com.study.springbootblog.service;

import com.study.springbootblog.entity.MPost;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Dupeng
 * @since 2021-10-12
 */
public interface IMPostService extends IService<MPost> {
    void initIndexWeekRank();
}
