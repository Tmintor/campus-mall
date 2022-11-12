package com.devcloud.mall.controller;


import com.devcloud.mall.common.R;
import com.devcloud.mall.domain.Goods;
import com.devcloud.mall.domain.dto.GoodsDetailDto;
import com.devcloud.mall.service.GoodsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tminto
 * @since 2022-10-30
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation("发布商品")
    @PostMapping("/publish")
    public R addGoods(@RequestBody Goods goods) {
        goodsService.publishGoods(goods);
        return R.ok();
    }

    @ApiOperation("查看已发布商品")
    @GetMapping("/publish/{userId}")
    public R getPublishGoods(@PathVariable String userId) {
        List<Goods> goodsList = goodsService.getPublishGoods(userId);
        return R.ok().data("publishList", goodsList);
    }

    @ApiOperation("商品列表")
    @GetMapping("/list/{page}/{limit}")
    public R getGoodsList(@PathVariable Integer limit, @PathVariable Integer page) {
        Map<String, Object> map = goodsService.getGoodsList(page, limit);
        return R.ok().data(map);
    }

    @ApiOperation("商品详细")
    @GetMapping("/{id}")
    public R getGoodsInfo(@PathVariable String id) {
        GoodsDetailDto goods = goodsService.getGoodsInfo(id);
        return R.ok().data("goodsInfo", goods);
    }

    @ApiOperation("下架商品")
    @DeleteMapping("/publish/{goodsId}")
    public R cancelPublish(@PathVariable String goodsId) {
        goodsService.cancelPublish(goodsId);
        return R.ok();
    }

    @ApiOperation("修改发布信息")
    @PutMapping("/publish")
    public R changeGoodsInfo(@RequestBody Goods goods) {
        goodsService.changeGoodsInfo(goods);
        return R.ok();
    }
}

