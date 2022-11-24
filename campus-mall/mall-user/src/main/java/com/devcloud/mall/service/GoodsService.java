package com.devcloud.mall.service;

import com.devcloud.mall.domain.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.devcloud.mall.domain.dto.GoodsDetailDto;

import java.util.List;
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

    void publishGoods(Goods goods);

    List<Goods> getPublishGoods(String userId);

    Map<String, Object> getGoodsList(Integer page, Integer limit);

    GoodsDetailDto getGoodsInfo(String id);

    void cancelPublish(String goodsId);

    void changeGoodsInfo(Goods goods);

    Map<String, Object> getCategoryGoods(String cid, Integer page, Integer limit);
}
