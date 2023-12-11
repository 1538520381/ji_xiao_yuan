package study.ji_xiao_yuan.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.ji_xiao_yuan.entity.pojo.Video;
import study.ji_xiao_yuan.entity.result.R;
import study.ji_xiao_yuan.service.VideoService;

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
        video.setOrder(videoService.count());
        videoService.save(video);
        return R.success("新增视频成功");
    }
}
