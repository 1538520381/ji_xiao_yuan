package study.ji_xiao_yuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import study.ji_xiao_yuan.entity.pojo.Stage;

/**
 * @author Persolute
 * @version 1.0
 * @description Stage Service
 * @email 1538520381@qq.com
 * @date 2023/12/7 13:18
 */
public interface StageService extends IService<Stage> {
    Stage getByOrder(Integer order);
}
