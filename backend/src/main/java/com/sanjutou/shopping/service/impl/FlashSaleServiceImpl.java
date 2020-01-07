package com.sanjutou.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbitmq.client.Channel;
import com.sanjutou.shopping.entity.FlashSale;
import com.sanjutou.shopping.entity.OderInfo;
import com.sanjutou.shopping.mapper.FlashSaleMapper;
import com.sanjutou.shopping.service.FlashSaleService;
import com.sanjutou.shopping.service.OderInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 秒杀服务实类。
 *
 * @author liubin
 */
@Service
public class FlashSaleServiceImpl extends ServiceImpl<FlashSaleMapper, FlashSale> implements FlashSaleService {

    /**
     * 秒杀mapper。
     */
    private final FlashSaleMapper flashSaleMapper;

    /**
     * 订单服务。
     */
    private final OderInfoService oderInfoService;

    private static final Logger LOGGER = LoggerFactory.getLogger(FlashSaleServiceImpl.class);

    @Autowired
    public FlashSaleServiceImpl(FlashSaleMapper flashSaleMapper, OderInfoService oderInfoService) {
        this.flashSaleMapper = flashSaleMapper;
        this.oderInfoService = oderInfoService;
    }


    @Override
    public FlashSale queryFlashSale() {
        final QueryWrapper<FlashSale> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("start_time");
        wrapper.eq("status", 1);
        // 开始时间大于现在
        wrapper.gt("start_time", LocalDateTime.now());
        wrapper.last("limit 1");
        return flashSaleMapper.selectOne(wrapper);
    }


    /**
     * 处理mq队列中的订单消息
     *
     * @param oderInfo 订单信息
     * @param message  消息
     * @param channel  channel
     */
    @RabbitListener(queues = "skuQueue")
    public void dealFlashOder(@Payload OderInfo oderInfo, Message message, Channel channel) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            LOGGER.info("秒杀订单：{}：{}：{}", oderInfo.getId(), deliveryTag, oderInfo);
            final boolean b = oderInfoService.newFlashSaleOder(oderInfo);
            if (b) {
                //成功消费
                channel.basicAck(deliveryTag, true);
            } else {
                //消费失败，销毁消息
                channel.basicReject(deliveryTag, false);
            }
        } catch (Exception e) {
            LOGGER.error("秒杀订单异常：{}：{}\n", oderInfo.getId(), deliveryTag, e);
        }
    }


}
