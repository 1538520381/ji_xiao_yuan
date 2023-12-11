package study.ji_xiao_yuan.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import study.ji_xiao_yuan.entity.result.R;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Persolute
 * @version 1.0
 * @description 通用 Controller
 * @email 1538520381@qq.com
 * @date 2023/12/11 11:50
 */
@RestController
@RequestMapping("/common")
public class CommonController {
    @Value("${ji_xiao_yuan.video_path}")
    private String path;

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
        File dir = new File(path);

        if (!dir.exists()) {
            dir.mkdir();
        }

        try {
            file.transferTo(new File(path + fileName));
        } catch (IOException e) {
            return R.error("上传失败");
        }
        return R.success(fileName);
    }
}
