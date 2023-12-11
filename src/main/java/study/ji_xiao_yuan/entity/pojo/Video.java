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
 * @description Video类 视频
 * @email 1538520381@qq.com
 * @date 2023/12/7 13:23
 */
@Data
public class Video implements Serializable {
    private static final long serialVersionUID = 1L;
    // 主键
    @TableId
    private Long id;
    // 视频名称
    private String name;
    // 视频路径
    private String path;
    // 阶段编号
    private Long stageId;
    // 顺序
    @TableField("`order`")
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
