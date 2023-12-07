package study.ji_xiao_yuan.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
