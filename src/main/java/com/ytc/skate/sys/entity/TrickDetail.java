package com.ytc.skate.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2023-08-06
 */
@TableName("x_Trick_Detail")
public class TrickDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userId;

    private String name;

    private Integer trickId;

    private Long count;

    /**
     * 创建时间
     */
    private LocalDateTime addTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private String trickName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getTrickId() {
        return trickId;
    }

    public void setTrickId(Integer trickId) {
        this.trickId = trickId;
    }
    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    public String getTrickName() {
        return trickName;
    }

    public void setTrickName(String trickName) {
        this.trickName = trickName;
    }

    @Override
    public String toString() {
        return "TrickDetail{" +
            "id=" + id +
            ", userId=" + userId +
            ", name=" + name +
            ", trickId=" + trickId +
            ", count=" + count +
            ", addTime=" + addTime +
            ", updateTime=" + updateTime +
            ", trickName=" + trickName +
        "}";
    }
}
