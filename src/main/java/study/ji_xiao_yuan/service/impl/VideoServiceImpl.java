package study.ji_xiao_yuan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import study.ji_xiao_yuan.dao.VideoDao;
import study.ji_xiao_yuan.entity.pojo.Stage;
import study.ji_xiao_yuan.entity.pojo.Video;
import study.ji_xiao_yuan.service.VideoService;

import java.util.List;

/**
 * @author Persolute
 * @version 1.0
 * @description Video ServiceImpl
 * @email 1538520381@qq.com
 * @date 2023/12/7 13:29
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoDao, Video> implements VideoService {
    /*
     * @author Persolute
     * @version 1.0
     * @description 根据阶段id获取视频列表
     * @email 1538520381@qq.com
     * @date 2023/12/7 20:40
     */
    @Override
    public List<Video> getByStageId(Long stageId) {
        LambdaQueryWrapper<Video> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Video::getStageId, stageId);
        return this.list(lambdaQueryWrapper);
    }

    /*
     * @author Persolute
     * @version 1.0
     * @description 获取指定阶段中视频数
     * @email 1538520381@qq.com
     * @date 2023/12/11 14:54
     */
    @Override
    public int countInStage(Long stageId) {
        LambdaQueryWrapper<Video> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Video::getStageId, stageId);
        return this.count(lambdaQueryWrapper);
    }

    /*
     * @author Persolute
     * @version 1.0
     * @description 获取指定阶段中指定顺序的视频
     * @email 1538520381@qq.com
     * @date 2023/12/11 15:04
     */
    @Override
    public Video getVideoByOrderInStage(Long stageId, Integer order) {
        LambdaQueryWrapper<Video> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Video::getStageId, stageId).eq(Video::getOrder, order);
        return this.getOne(lambdaQueryWrapper);
    }
}
