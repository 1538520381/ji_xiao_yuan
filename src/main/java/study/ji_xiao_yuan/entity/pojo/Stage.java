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
 * @description Stage类 阶段
 * @email 1538520381@qq.com
 * @date 2023/12/7 13:13
 */
@Data
public class Stage implements Serializable {
    public static final long serialVersionUID = 1L;
    // 主键
    @TableId
    private Long id;
    // 阶段名称
    private String name;
    // 简介
    private String introduction;
    // 顺序
    private Integer order;
    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    // 修改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    // 创建用户
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;
    // 修改用户
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
}
