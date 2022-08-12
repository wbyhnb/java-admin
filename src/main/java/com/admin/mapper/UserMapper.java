package com.admin.mapper;

import com.admin.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author BYW
 * @since 2022-08-04
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
