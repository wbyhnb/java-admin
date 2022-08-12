package com.admin.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import java.util.List;
import com.admin.utils.Result;
import com.admin.service.IMenuService;
import com.admin.entity.Menu;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 菜单权限表
 * @author BYW
 * @date 2022-08-08
 */
@RestController
@RequestMapping("/menu")
    public class MenuController {
    @Resource
    private IMenuService menuService;

    /**
    *更新数据 菜单权限表
    */
    @ApiOperation(value = "新增修改数据")
    @PostMapping("/save")
    public Result save(@RequestBody Menu menu){
        return Result.success(menuService.saveOrUpdate(menu));
    }

    /**
    *条件查询 菜单权限表
    */
    @ApiOperation(value = "根据id查询数据")
    @GetMapping({"/detail/{id}"})
    public Result getById(@PathVariable("id") int id) {
        return Result.success(menuService.getById(id));
    }

    /**
    *删除数据 菜单权限表
    */
    @ApiOperation(value = "删除数据")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success("删除成功",menuService.removeById(id));
    }

    /**
    * 全部数据 菜单权限表
    */
    @ApiOperation(value = "查询全部数据")
    @GetMapping("/list")
    public Result findAll(){
        return Result.success("获取成功",menuService.list());
    }

    /**
    * 分页 菜单权限表
    */
    @ApiOperation(value = "查询全部数据(分页)")
    @GetMapping("/page")
    public Page<Menu> findPage(@RequestParam(name = "pageNum",defaultValue = "1") Integer pageNum,
    @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize){
        return menuService.page(new Page<>(pageNum,pageSize));
    }

}

