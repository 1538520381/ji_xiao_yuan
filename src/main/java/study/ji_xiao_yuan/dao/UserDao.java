package study.ji_xiao_yuan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import study.ji_xiao_yuan.entity.pojo.User;

/**
 * @author Persolute
 * @version 1.0
 * @description User Dao
 * @email 1538520381@qq.com
 * @date 2023/12/7 13:05
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
}
