package com.zk.mybatisplus.common.utils;

import com.config.SpringContext;
import com.service.IMessageService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import lombok.extern.slf4j.Slf4j;
import org.jboss.netty.util.internal.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class NettyUtil {

    /**
     * 活跃通道列表  channelId:channel
     */
    public static ConcurrentHashMap<String, Channel> sessionChannelMap = new ConcurrentHashMap<String, Channel>();
    /**
     * 设备编号和通道id映射  SN:channelId
     */
    public static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
    /**
     * 保存连接时间   channelId:time
     */
    public static ConcurrentHashMap<String, String> mapTime = new ConcurrentHashMap<String, String>();

    /**
     * 获取所有连接
     */
    public static Map<String, Channel> channelAll() {
        return sessionChannelMap;
    }

    /**
     * 获取所有连接SN
     */
    public static Map<String, String> channelSNAll() {
        return map;
    }

    /**
     * 获取所有连接的时间
     */
    public static Map<String, String> channelConnectTimeAll() {
        return mapTime;
    }

    /**
     * 获取连接时间
     */
    public static String getTime(String channelId) {
        return mapTime.get(channelId);
    }

    /**
     * 获取连接对应的桩编号
     */
    public static String getSN(String channelId) {
        return map.get(channelId);
    }

    /**
     * 获取连接id
     */
    public static String getChannelId(String SN) {
        return map.get(SN);
    }

    /**
     * 获取链接channel
     */
    public static Channel getChannelBySN(String SN) {
        return getChannel((getChannelId(SN)));
    }

    /**
     * 获取链接channel
     */
    public static Channel getChannel(String channelId) {
        return sessionChannelMap.get(channelId);
    }

    /**
     * 获取连接数
     */
    public static Integer getConnectNumber() {
        return sessionChannelMap.size();
    }

    /**
     * 添加连接
     */
    public static boolean addConnect(Channel channel) {
        String channelId = channel.id().asLongText();
        //作为主动请求的依据,若是重连会覆盖
        channelAll().put(channelId, channel);
        channelConnectTimeAll().put(channelId, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return true;
    }

    /**
     * 移除连接
     */
    public static boolean deleteConnect(Channel channel) {
        String channelId = channel.id().asLongText();
        // 删除通道
        channelAll().remove(channelId);
        // 删除连接时间
        channelConnectTimeAll().remove(channelId);
        if (channelSNAll().containsKey(channelId)) {
            // 删除通道id和SN映射关系
            channelSNAll().remove(getSN(channelId));
            channelSNAll().remove(channelId);
        }
        return true;
    }

    /**
     * 通过SN移除过期连接
     */
    public static boolean deleteConnectBySN(String SN) {
        if (channelSNAll().containsKey(SN)) {
            String channelId = getChannelId(SN);
            // 删除通道
            channelAll().remove(channelId);
            // 删除连接时间
            channelConnectTimeAll().remove(channelId);
            // 删除通道id和SN映射关系
            channelSNAll().remove(SN);
            channelSNAll().remove(channelId);
        }
        return true;
    }

    /**
     * @return java.util.List<java.lang.String>
     * @Author xiongchuan
     * @Description 根据value获取值
     * @Date 2020/5/7 23:44
     * @Param [value]
     **/
    public static List<String> getByValue(String value) {
        List<String> list = new ArrayList<>();
        for (String key : map.keySet()) {
            if (map.get(key).equals(value)) {
                list.add(key);
            }
        }
        return list;
    }

    /**
     * 通过 SN 发送信息给指定设备
     */
    public static String sendBySN(HttpServletRequest request, String SN, String code) {
        /*Jedis jedis = null;
        jedis = RedisPool.getJedis();
        String response = "";
        try {

        } catch (Exception e) {
            log.info("请求错误：{}", e.getMessage());
        } finally {
            RedisPool.closeJedisPool(jedis);
        }*/

        String response;
        try {
            // 获取通道
            Channel channel = getChannelBySN(SN);
            if (null != channel) {
                IMessageService<String> service = SpringContext.getHandle(code);
                if (service != null) {
                    service.handle(request, code, null, getChannelId(SN));
                    response = "设备<" + SN + ">指令<{" + code + "}>下发成功";
                } else {
                    response = "处理类为空，设备<" + SN + ">指令<{" + code + "}>下发终止";
                }
            } else {
                response = "设备<" + SN + ">未注册，指令<{" + code + "}>下发终止";
            }
        } catch (Exception e) {
            response = "出现异常，设备<" + SN + ">指令<{" + code + "}>下发终止 异常<" + e.getMessage() + ">";
            e.printStackTrace();
        }
        log.info(response);
        return response;
    }

    /**
     * 根据 通道id 发送数据
     * <p>
     * 数据下发专用方法
     */
    public static void sendDown(String channelId, byte[] data) {
        Channel channel = getChannel(channelId);
        if (null != channel) {
            // crc校验
            byte[] crcByte = ConversionUtil.CRC2ToByte(data);
            // 组装数据
            byte[] bytes = ConversionUtil.mergeBytes(data, crcByte);
            send(channel, bytes);
        }
    }

    // 根据 通道id 发送数据
    public static void send(String channelId, byte[] data) {
        Channel channel = getChannel(channelId);
        if (null != channel) {
            send(channel, data);
        }
    }

    // 根据 通道 发送数据
    public static void send(Channel channel, byte[] data) {
        if (data == null) {
            log.error("通道：{} 发送数据失败，数据为空", channel.id().asLongText());
        }
        if (!channel.isActive()) {
            channel.close();
            log.error("通道：{} 发送数据失败，通道已断开", channel.id().asLongText());
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
                            log.error("{}:发送数据失败", Thread.currentThread().getName());
                        }
                        buf.release();
                    }
                });
            }
        });
    }

}
