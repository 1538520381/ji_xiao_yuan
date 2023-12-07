package study.ji_xiao_yuan.entity.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Persolute
 * @version 1.0
 * @description User类 管理员
 * @email 1538520381@qq.com
 * @date 2023/12/7 12:57
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    // 主键
    @TableId
    private Long id;
    // 账号
    private String account;
    // 密码
    private String password;
    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    // 更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
