package study.ji_xiao_yuan.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import study.ji_xiao_yuan.entity.pojo.Video;
import study.ji_xiao_yuan.entity.result.R;
import study.ji_xiao_yuan.service.VideoService;

import java.io.File;

/**
 * @author Persolute
 * @version 1.0
 * @description Video Controller
 * @email 1538520381@qq.com
 * @date 2023/12/7 13:31
 */
@Slf4j
@RestController
@RequestMapping("/video")
public class VideoController {
    @Value("${ji_xiao_yuan.video_path}")
    private String basePath;
    @Autowired
    private VideoService videoService;

    /*
     * @author Persolute
     * @version 1.0
     * @description 新增视频
     * @email 1538520381@qq.com
     * @date 2023/12/11 13:57
     */
    @PostMapping
    public R<String> save(@RequestBody Video video) {
        video.setOrder(videoService.countInStage(video.getStageId()));
        videoService.save(video);
        return R.success("新增视频成功");
    }

    /*
     * @author Persolute
     * @version 1.0
     * @description 删除视频
     * @email 1538520381@qq.com
     * @date 2023/12/11 14:24
     */
    @DeleteMapping("/{videoId}")
    public R<String> delete(@PathVariable Long videoId) {
        Video video = videoService.getById(videoId);
        if (video == null) {
            return R.error("视频不存在");
        }
        videoService.removeById(videoId);
        new File(basePath + video.getPath()).delete();
        return R.success("删除视频成功");
    }

    /*
     * @author Persolute
     * @version 1.0
     * @description 修改视频
     * @email 1538520381@qq.com
     * @date 2023/12/11 14:35
     */
    @PutMapping
    public R<String> update(@RequestBody Video video) {
        if (videoService.updateById(video)) {
            return R.success("修改视频成功");
        } else {
            return R.error("修改视频失败");
        }
    }

    /*
     * @author Persolute
     * @version 1.0
     * @description 修改视频顺序
     * @email 1538520381@qq.com
     * @date 2023/12/11 15:11
     */
    @PutMapping("/{videoId}/{flag}")
    public R<String> updateVideoOrder(@PathVariable Long videoId, @PathVariable Integer flag) {
        Video video = videoService.getById(videoId);
        if (video == null) {
            return R.error("修改失败，视频不存在");
        }

        long stageId = video.getStageId();
        int order = video.getOrder();
        Video video0;
        if (flag == 1) {
            video0 = videoService.getVideoByOrderInStage(stageId, order + 1);
            if (video0 == null) {
                return R.error("修改失败，视频已排在最后");
            }
            video0.setOrder(order);
            video.setOrder(order + 1);
        } else {
            video0 = videoService.getVideoByOrderInStage(stageId, order - 1);
            if (video0 == null) {
                return R.error("修改失败，视频已排在开头");
            }
            video0.setOrder(order);
            video.setOrder(order - 1);
        }
        update(video0);
        return update(video);
    }
}
