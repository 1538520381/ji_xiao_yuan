package study.ji_xiao_yuan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.junit.jupiter.api.Order;
import org.springframework.stereotype.Service;
import study.ji_xiao_yuan.dao.StageDao;
import study.ji_xiao_yuan.entity.pojo.Stage;
import study.ji_xiao_yuan.service.StageService;

/**
 * @author Persolute
 * @version 1.0
 * @description Stage ServiceImpl
 * @email 1538520381@qq.com
 * @date 2023/12/7 13:19
 */
@Service
public class StageServiceImpl extends ServiceImpl<StageDao, Stage> implements StageService {
    /*
     * @author Persolute
     * @version 1.0
     * @description 根据顺序查找阶段
     * @email 1538520381@qq.com
     * @date 2023/12/7 21:43
     */
    @Override
    public Stage getByOrder(Integer order) {
        LambdaQueryWrapper<Stage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Stage::getOrder, order);
        return this.getOne(lambdaQueryWrapper);
    }
}
