package study.ji_xiao_yuan.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import study.ji_xiao_yuan.entity.pojo.Stage;
import study.ji_xiao_yuan.entity.pojo.Video;
import study.ji_xiao_yuan.entity.result.R;
import study.ji_xiao_yuan.service.StageService;
import study.ji_xiao_yuan.service.VideoService;

import java.util.List;

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

    @Autowired
    private VideoService videoService;

    /*
     * @author Persolute
     * @version 1.0
     * @description 创建阶段
     * @email 1538520381@qq.com
     * @date 2023/12/7 14:07
     */
    @PostMapping
    public R<String> save(@RequestBody Stage stage) {
        // 设置阶段顺序为最后
        stage.setOrder(stageService.count());

        if (stageService.save(stage)) {
            return R.success("新增阶段成功");
        } else {
            return R.error("新增阶段失败");
        }
    }

    /*
     * @author Persolute
     * @version 1.0
     * @description 删除阶段
     * @email 1538520381@qq.com
     * @date 2023/12/7 19:38
     */
    @DeleteMapping("/{stageId}")
    public R<String> deleteById(@PathVariable Long stageId) {
        List<Video> list = videoService.getByStageId(stageId);
        if (!list.isEmpty()) {
            return R.error("请先删除该阶段下的所有视频");
        } else if (stageService.removeById(stageId)) {
            return R.success("删除成功");
        } else {
            return R.error("删除失败");
        }
    }

    /*
     * @author Persolute
     * @version 1.0
     * @description 修改阶段属性
     * @email 1538520381@qq.com
     * @date 2023/12/7 21:01
     */
    @PutMapping
    public R<String> update(@RequestBody Stage stage) {
        if (stageService.updateById(stage)) {
            return R.success("修改成功");
        } else {
            return R.error("修改失败");
        }
    }

    /*
     * @author Persolute
     * @version 1.0
     * @description 修改阶段顺序
     * @email 1538520381@qq.com
     * @date 2023/12/7 21:36
     */
    @PutMapping("/{stageId}/{flag}")
    public R<String> updateStageOrder(@PathVariable Long stageId, @PathVariable Integer flag) {
        Stage stage = stageService.getById(stageId);
        if (stage == null) {
            return R.error("修改失败,该阶段不存在");
        }

        int order = stage.getOrder();
        Stage stage0;
        if (flag == 1) {
            stage0 = stageService.getByOrder(order + 1);
            if (stage0 == null) {
                return R.error("修改失败，该阶段已排在最后");
            }
            stage0.setOrder(order);
            stage.setOrder(order + 1);
        } else {
            stage0 = stageService.getByOrder(order - 1);
            if (stage0 == null) {
                return R.error("修改失败，该阶段已排在开头");
            }
            stage0.setOrder(order);
            stage.setOrder(order - 1);
        }
        update(stage0);
        return update(stage);
    }

    /*
     * @author Persolute
     * @version 1.0
     * @description 根据顺序返回阶段列表
     * @email 1538520381@qq.com
     * @date 2023/12/7 22:20
     */
    @GetMapping
    public R<List<Stage>> getStageList() {
        return R.success("阶段列表获取成功", stageService.getStageList());
    }
}
