package com.zoux.server.controller;


import com.zoux.server.pojo.Joblevel;
import com.zoux.server.pojo.RespBean;
import com.zoux.server.service.IJoblevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zoux
 * @since 2021-01-23
 */
@RestController
@RequestMapping("/system/basic/joblevel")
public class JoblevelController {

    @Autowired
    private IJoblevelService joblevelService;

    @ApiOperation(value = "获取所有职称")
    @GetMapping("/")
    public List<Joblevel> getAllJoblevels() {
        return joblevelService.list();
    }

    //add
    @ApiOperation(value = "添加职称")
    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody Joblevel joblevel) {
        joblevel.setCreateDate(LocalDateTime.now());
        if (joblevelService.save(joblevel)) {
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }


    //update
    @ApiOperation(value = "更新职称")
    @PutMapping("/")
    public RespBean updateJobLevel(@RequestBody Joblevel joblevel) {
        if (joblevelService.updateById(joblevel)) {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    //delete
    @ApiOperation(value = "删除职称")
    @DeleteMapping("/{id}")
    public RespBean deleteJobLevel(@PathVariable Integer id) {
        if (joblevelService.removeById(id)) {
            return RespBean.success("职称删除成功");
        }
        return RespBean.error("职称删除失败");
    }

    //deletes
    @ApiOperation(value = "批量删除职称")
    @DeleteMapping("/")
    public RespBean deleteJobLevel( Integer[] ids) {
        if (joblevelService.removeByIds(Arrays.asList(ids))) {
            return RespBean.success("批量删除职称成功");
        }
        return RespBean.error("批量删除职称失败");

    }
}
