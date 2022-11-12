package com.devcloud.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.devcloud.mall.domain.Goods;
import com.devcloud.mall.domain.dto.GoodsDetailDto;
import com.devcloud.mall.mapper.GoodsMapper;
import com.devcloud.mall.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devcloud.mall.utils.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public void publishGoods(Goods goods) {
        //未发布
        goods.setStatus(0);
        goods.setViewCount(0);
        baseMapper.insert(goods);
    }

    @Override
    public List<Goods> getPublishGoods(String userId) {
        LambdaQueryWrapper<Goods> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Goods::getUserId, userId)
                .orderByDesc(Goods::getCreateTime);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public Map<String, Object> getGoodsList(Integer current, Integer limit) {
        Page<Goods> page = new Page<>(current, limit);
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Goods::getStatus, 1);
        baseMapper.selectPage(page, lambdaQueryWrapper);
        Map<String, Object> map = BeanUtils.beanToPageMap(page);
        return map;
    }

    @Override
    public GoodsDetailDto getGoodsInfo(String id) {
        return baseMapper.selectGoodsDetailById(id);
    }

    @Override
    public void cancelPublish(String goodsId) {
        Goods goods = new Goods();
        goods.setId(goodsId);
        //设置为已下架
        goods.setStatus(2);
        baseMapper.updateById(goods);
    }

    @Override
    public void changeGoodsInfo(Goods goods) {
        baseMapper.updateById(goods);
    }

}
