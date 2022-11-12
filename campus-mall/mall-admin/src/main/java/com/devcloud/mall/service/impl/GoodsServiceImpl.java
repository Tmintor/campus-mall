package com.devcloud.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devcloud.mall.domain.Goods;
import com.devcloud.mall.domain.vo.GoodsQuery;
import com.devcloud.mall.mapper.GoodsMapper;
import com.devcloud.mall.service.GoodsService;
import com.devcloud.mall.utils.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tminto
 * @since 2022-10-30
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Override
    public Map<String, Object> getGoodsList(Integer current, Integer limit, GoodsQuery goodsQuery) {
        Page<Goods> page = new Page<>(current,limit);
        baseMapper.selectPageByQuery(page, goodsQuery);
        return BeanUtils.beanToPageMap(page);
    }

    @Override
    public Goods getGoodsInfo(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void checkGoods(Goods goods) {
        baseMapper.updateById(goods);
    }

}
