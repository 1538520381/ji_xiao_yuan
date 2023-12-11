package study.ji_xiao_yuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import study.ji_xiao_yuan.entity.pojo.Video;

import java.util.List;

/**
 * @author Persolute
 * @version 1.0
 * @description Video Service
 * @email 1538520381@qq.com
 * @date 2023/12/7 13:28
 */
public interface VideoService extends IService<Video> {
    List<Video> getByStageId(Long stageId);

    int countInStage(Long stageId);

    Video getVideoByOrderInStage(Long stageId, Integer order);
}
