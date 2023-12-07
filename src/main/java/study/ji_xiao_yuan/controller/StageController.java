package study.ji_xiao_yuan.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.ji_xiao_yuan.entity.pojo.Stage;
import study.ji_xiao_yuan.entity.result.R;
import study.ji_xiao_yuan.service.StageService;

/**
 * @author Persolute
 * @version 1.0
 * @description Stage Controller
 * @email 1538520381@qq.com
 * @date 2023/12/7 13:20
 */
@Slf4j
@RestController
@RequestMapping("/stage")
public class StageController {
    @Autowired
    private StageService stageService;

    /*
     * @author Persolute
     * @version 1.0
     * @description 创建阶段
     * @email 1538520381@qq.com
     * @date 2023/12/7 14:07
     */
    @PostMapping
    public R<Stage> save(@RequestBody Stage stage) {
        // 设置阶段顺序为最后
        stage.setOrder(stageService.count());

        stageService.save(stage);

        return R.success();
    }


}
