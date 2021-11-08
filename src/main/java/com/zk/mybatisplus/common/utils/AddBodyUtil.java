package com.zk.mybatisplus.common.utils;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

public class AddBodyUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddBodyUtil.class);

    /**
     * 返回消息拼装
     *
     * @param pileCode 命令字
     * @param body     帧信息单元
     * @return
     */
    public static ReturnResponse messageSend(String pileCode, byte[] info, byte[] cmd, byte[] body) {
        ReturnResponse response = new ReturnResponse();
        Jedis jedis = null;
        jedis = RedisPool.getJedis();

        byte[] origin = new byte[2];
        origin[0] = (byte) 0xAA;
        origin[1] = (byte) 0xF5;

        String returnMes;
        byte[] responseByte;
        Integer len = body.length + 9;
        responseByte = ConversionUtil.byteMergerAll(
                origin, //起始域
                ConversionUtil.byteExchange(ConversionUtil.strHexByte(MessageBodyUtil.sixteenStr(len.toString(), 4))), //长度域
                info, //信息域   序列号域
                cmd, //命令字
                body //数据域
        );

        Integer hexString = ConversionUtil.hexString(ConversionUtil.byteMergerAll(cmd, body)); //校验和
        int hilo = ConversionUtil.hilo(hexString, 1); //校验和低八位
        responseByte = ConversionUtil.byteMergerAll(responseByte, ConversionUtil.byteExchange(ConversionUtil.strHexByte(ConversionUtil.strFormat(ConversionUtil.intToHex(hilo), 2))));

        try {
            String message = jedis.get(pileCode);
            if (message != null) {
                String clientId = JSONObject.parseObject(message).get("clientId").toString();
                if (clientId == null || StringUtils.pathEquals(clientId, "")) {
                    LOGGER.info("================18.1 服务器主动请求桩端========================");
                    LOGGER.info("================18.2 无该编号桩的活跃连接========================");
                    returnMes = "无该编号桩的活跃连接";
                    response.setSuccess(false);
                } else {
                    LOGGER.info("================18. 服务器主动请求桩端========================");
                    Channel channel = NettyUtil.channelAll().get(clientId);
                    LOGGER.info("================18.3 发送给桩的源码：" + ConversionUtil.bytesToHexString(responseByte) + "========================\r\n");
                    send(channel, responseByte);
                    returnMes = "操作成功";
                }
            } else {
                LOGGER.info("================18.1 服务器主动请求桩端========================");
                LOGGER.info("================18.2 无该编号桩的活跃连接========================");
                returnMes = "无该编号桩的活跃连接";
                response.setSuccess(false);
            }
        } catch (Exception e) {
            response.setSuccess(false);
            returnMes = "请求错误";
            LOGGER.info("请求错误：" + e.getMessage());
        } finally {
            RedisPool.closeJedisPool(jedis);
        }
        response.setMessage(returnMes);
        return response;
    }

    public static void send(Channel channel, byte[] data) {
        if (data == null) {
            LOGGER.error("通道：" + channel.id().asLongText() + " 发送数据失败，数据为空");
        }
        if (!channel.isActive()) {
            channel.close();
            LOGGER.error("通道：" + channel.id().asLongText() + ":发送数据失败，通道已断开");
            return;
        }
        //write的时候用池化 buf.retain 和 buf.release()要成对出现，避免内存无限增长出现泄漏
        ByteBuf buf = PooledByteBufAllocator.DEFAULT.directBuffer(data.length).writeBytes(data).retain(1);
        channel.eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                channel.writeAndFlush(buf).addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future) {
                        if (!future.isSuccess()) {
                            LOGGER.error(Thread.currentThread().getName() + ":发送数据失败");
                        }
                        buf.release();
                    }
                });
            }
        });
    }
}