package study.ji_xiao_yuan.controller;

import com.alibaba.druid.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import study.ji_xiao_yuan.common.NonStaticResourceHttpRequestHandler;
import study.ji_xiao_yuan.entity.pojo.Video;
import study.ji_xiao_yuan.entity.result.R;
import study.ji_xiao_yuan.service.VideoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

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
@RequiredArgsConstructor
public class VideoController {
    @Value("${ji_xiao_yuan.video_path}")
    private String basePath;
    @Autowired
    private VideoService videoService;

    private final NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;

    /*
     * @author Persolute
     * @version 1.0
     * @description 视频上传
     * @email 1538520381@qq.com
     * @date 2023/12/11 12:02
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID() + suffix;
        File dir = new File(basePath);

        if (!dir.exists()) {
            dir.mkdir();
        }

        try {
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            return R.error("上传失败");
        }
        return R.success(fileName);
    }

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

    /*
     * @author Persolute
     * @version 1.0
     * @description 播放视频
     * @email 1538520381@qq.com
     * @date 2023/12/12 14:42
     */
    @GetMapping("/play/{videoId}")
    public R<String> play(HttpServletRequest request, HttpServletResponse response, @PathVariable Long videoId) throws IOException, ServletException {
        Video video = videoService.getById(videoId);
        if (video == null) {
            return R.error("获取视频失败");
        }
        Path path = Paths.get(basePath + video.getPath());
        if (Files.exists(path)) {
            String mimeType = Files.probeContentType(path);
            if (!StringUtils.isEmpty(mimeType)) {
                response.setContentType(mimeType);
            }
            request.setAttribute(NonStaticResourceHttpRequestHandler.ATTR_FILE, path);
            nonStaticResourceHttpRequestHandler.handleRequest(request, response);
            return R.success("获取视频成功");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            return R.error("获取视频失败");
        }
    }
}
