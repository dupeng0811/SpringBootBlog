package com.study.springbootblog.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.springbootblog.entity.MPost;
import com.study.springbootblog.mapper.MPostMapper;
import com.study.springbootblog.service.IMPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.study.springbootblog.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 初始化首页的周评论排行榜
     */
    @Override
    public void initIndexWeekRank() {
        //缓存最近7天的文章评论数量
        List<MPost> last7DayPosts = this.list(new QueryWrapper<MPost>()
                .ge("created", DateUtil.offsetDay(new Date(), -7).toJdkDate())
                .select("id, title, user_id, comment_count, view_count, created"));
        for (MPost post : last7DayPosts) {
            String key = "day_rank:" + DateUtil.format(post.getCreated(), DatePattern.PURE_DATE_PATTERN);
            //设置有效期
            long between = DateUtil.between(new Date(), post.getCreated(), DateUnit.DAY);
            long expireTime = (7 - between) * 24 * 60 * 60;
            //缓存文章到set中，评论数量作为排行标准
            redisUtil.zSet(key, post.getId(), post.getCommentCount());
            //设置有效期
            redisUtil.expire(key, expireTime);
            //缓存文章基本信息（hash结构）
            this.hashCachePostIdAndTitle(post);
        }
        //7天阅读相加。
        this.zUnionAndStoreLast7DaysForLastWeekRank();
    }

    /**
     * hash结构缓存文章标题和id
     * @param post
     */
    private void hashCachePostIdAndTitle(MPost post) {
        boolean isExist = redisUtil.hasKey("rank_post_" + post.getId());
        if(!isExist) {
            long between = DateUtil.between(new Date(), post.getCreated(), DateUnit.DAY);
            long expireTime = (7 - between) * 24 * 60 * 60;
            //缓存文章基本信息（hash结构）
            redisUtil.hset("rank_post_" + post.getId(), "post:id", post.getId(), expireTime);
            redisUtil.hset("rank_post_" + post.getId(), "post:title", post.getTitle(), expireTime);
            //redisUtil.hset("rank_post_" + post.getId(), "post:comment_count", post.getCommentCount(), expireTime);
        }
    }

    /**
     * 把最近7天的文章评论数量统计一下
     * 用于首页的7天评论排行榜
     */
    public void zUnionAndStoreLast7DaysForLastWeekRank() {
        String prifix = "day_rank:";
        List<String> keys  = new ArrayList<>();
        String key = prifix + DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN);
        for(int i = -7 ; i < 0; i++) {
            Date date = DateUtil.offsetDay(new Date(), i).toJdkDate();
            keys.add(prifix + DateUtil.format(date, DatePattern.PURE_DATE_PATTERN));
        }
        redisUtil.zUnionAndStore(key, keys, "last_week_rank");
    }
}