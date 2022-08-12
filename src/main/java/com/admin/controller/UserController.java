package com.admin.controller;


import com.admin.config.ResultStatus;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import java.util.List;
import com.admin.utils.Result;
import com.admin.service.IUserService;
import com.admin.entity.User;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description 用户信息表
 * @author BYW
 * @date 2022-08-04
 */
@RestController
@RequestMapping("/user")
    public class UserController {
    @Resource
    private IUserService userService;

    /**
    *更新数据 用户信息表
    */
    @ApiOperation(value = "新增修改数据")
    @PostMapping("/save")
    public Result save(@RequestBody User user){
        return Result.success(userService.saveOrUpdate(user));
    }

    /**
    *条件查询 用户信息表
    */
    @ApiOperation(value = "根据id查询数据")
    @GetMapping({"/detail/{id}"})
    public Result getById(@PathVariable("id") int id) {
        return Result.success(userService.getById(id));
    }

    /**
    *删除数据 用户信息表
    */
    @ApiOperation(value = "删除数据")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(userService.removeById(id));
    }

    /**
    * 全部数据 用户信息表
    */
    @ApiOperation(value = "查询全部数据")
    @GetMapping("/list")
    public Result findAll(){return Result.success("获取成功",userService.list());
    }

    /**
    * 分页 用户信息表
    */
    @ApiOperation(value = "查询全部数据(分页)")
    @GetMapping("/page")
    public Page<User> findPage(@RequestParam(name = "pageNum",defaultValue = "1") Integer pageNum,
    @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize){
        return userService.page(new Page<>(pageNum,pageSize));
    }

}

