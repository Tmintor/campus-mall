package com.devcloud.mall.controller;

import com.devcloud.mall.common.R;
import com.devcloud.mall.domain.Goods;
import com.devcloud.mall.domain.vo.GoodsQuery;
import com.devcloud.mall.service.GoodsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 吴员外
 * @date 2022/11/4 8:52
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation("获取商品列表")
    @PostMapping("/list/{page}/{limit}")
    public R getGoodsList(@PathVariable Integer limit,
                          @PathVariable Integer page,
                          @RequestBody(required = false) GoodsQuery goodsQuery) {
        Map<String, Object> map = goodsService.getGoodsList(page, limit, goodsQuery);
        return R.ok().data(map);
    }

    @ApiOperation("商品详细")
    @GetMapping("/{id}")
    public R getGoodsInfo(@PathVariable String id) {
        Goods goods = goodsService.getGoodsInfo(id);
        return R.ok().data("goodsInfo", goods);
    }

    @ApiOperation("商品审核")
    @PostMapping("/check")
    public R checkGoods(@RequestBody Goods goods) {
        goodsService.checkGoods(goods);
        return R.ok();
    }

}
