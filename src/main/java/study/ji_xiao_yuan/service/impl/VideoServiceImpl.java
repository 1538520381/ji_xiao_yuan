package study.ji_xiao_yuan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import study.ji_xiao_yuan.dao.VideoDao;
import study.ji_xiao_yuan.entity.pojo.Video;
import study.ji_xiao_yuan.service.VideoService;

/**
 * @author Persolute
 * @version 1.0
 * @description Video ServiceImpl
 * @email 1538520381@qq.com
 * @date 2023/12/7 13:29
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoDao, Video> implements VideoService {
}
