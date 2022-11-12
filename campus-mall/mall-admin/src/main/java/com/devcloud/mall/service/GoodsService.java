package com.devcloud.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.devcloud.mall.domain.Goods;
import com.devcloud.mall.domain.vo.GoodsQuery;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tminto
 * @since 2022-10-30
 */
public interface GoodsService extends IService<Goods> {

    Map<String, Object> getGoodsList(Integer page, Integer limit, GoodsQuery goodsQuery);

    Goods getGoodsInfo(String id);

    void checkGoods(Goods goods);
}
