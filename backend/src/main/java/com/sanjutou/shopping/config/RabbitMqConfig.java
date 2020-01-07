package com.sanjutou.shopping.config;

import com.google.gson.Gson;
import com.sanjutou.shopping.entity.OderInfo;
import com.sanjutou.shopping.util.SkuStockUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitMq配置相关
 *
 * @author admin
 */
@Configuration
public class RabbitMqConfig {

    /**
     * redis的工具类
     */
    private final SkuStockUtil skuStockUtil;

    /**
     * LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqConfig.class);

    @Autowired
    public RabbitMqConfig(SkuStockUtil skuStockUtil) {
        this.skuStockUtil = skuStockUtil;
    }

    /**
     * 声明队列并持久化
     *
     * @return 队列
     */
    @Bean
    public Queue skuQueue() {
        return new Queue("skuQueue", true);
    }

    /**
     * 声明交换机
     *
     * @return 交换机
     */
    @Bean
    public DirectExchange skuDirectExchange() {
        return new DirectExchange("skuDirectExchange");
    }

    /**
     * 交换机和队列绑定。
     *
     * @return 设置路由键
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(skuQueue()).to(skuDirectExchange()).with("FlashSaleOder");
    }

    /**
     * 配置rabbitTemplate
     *
     * @param connectionFactory 工厂
     * @return 对象
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        // 开启confirm
        rabbitTemplate.setMandatory(true);
        // 设置序列化
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        // 消息发送的回调
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                LOGGER.info("消息发送成功：{}", new Gson().toJson(correlationData));
                // 删除对象消息
                if (correlationData != null && correlationData.getId() != null) {
                    skuStockUtil.evictOderInfo(Long.parseLong(correlationData.getId()));
                }
            } else {
                LOGGER.error("消息发送失败：数据：{}；cause：{}", new Gson().toJson(correlationData), cause);
                // 重试
                if (correlationData != null && correlationData.getId() != null) {
                    final OderInfo oderInfo = skuStockUtil.queryOderInfo(Long.parseLong(correlationData.getId()));
                }
            }
        });
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> LOGGER.error("消息发送异常：{}；交换机：{}；路由键：{}", message, exchange, routingKey));
        return rabbitTemplate;
    }

    /**
     * rabbitMq消息接收的配置
     *
     * @param connectionFactory 配置工厂
     * @return 监听模板
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        // 序列化设置
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        // 设置之后配置文件失效
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }
}
