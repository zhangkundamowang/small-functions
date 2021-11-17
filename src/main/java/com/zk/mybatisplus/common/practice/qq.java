//package com.zk.mybatisplus.common.practice;
//
//import cn.hutool.core.date.DateUtil;
//import org.apache.commons.lang3.StringUtils;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//public class qq {
//
//    /**
//     * 查询本次付费价格
//     */
//    public static Map<String,BigDecimal> getChargePrices(String deviceSn, BigDecimal beforeChargeQuantity, BigDecimal afterChargeQuantity) {
//        Map<String, String> map = RedisPool.getMap(deviceSn + "_change");
//        //获取电费标准
//        String electricityFee =  map.get("electricityFee");
//        BigDecimal eleFee = new BigDecimal("0");
//        if (StringUtils.isNotBlank(electricityFee)) {
//            eleFee = new BigDecimal(electricityFee);
//        }
//        //获取服务费标准
//        String serviceFee =  map.get("serviceFee");
//        BigDecimal serFee = new BigDecimal("0");
//        if (StringUtils.isNotBlank(serviceFee)) {
//            serFee = new BigDecimal(serviceFee);
//        }
//        //获取两电池 电量百分比差值
//        BigDecimal sub = afterChargeQuantity.subtract(beforeChargeQuantity);
//        //设置收费标准 每百分之五收费
//        BigDecimal standard = new BigDecimal("5.00");
//        //算出商 results[0] 和余数 results[1]
//        BigDecimal[] results = sub.divideAndRemainder(standard);
//        //非整除情况 商加1
//        if(results[1].compareTo(BigDecimal.ZERO)==1){
//            results[0]= results[0].add(new BigDecimal("1"));
//        }
//        //本次换电电费 保留两位小数
//        BigDecimal totalEleFee = results[0].multiply(eleFee).setScale(2,BigDecimal.ROUND_HALF_UP);
//        //本次换电服务费
//        BigDecimal totalSerFee = results[0].multiply(serFee).setScale(2,BigDecimal.ROUND_HALF_UP);
//        //总费用
//        BigDecimal totalMoney = totalEleFee.add(totalSerFee).setScale(2,BigDecimal.ROUND_HALF_UP);
//
//        Map<String,BigDecimal> mapFee=new HashMap<>();
//        mapFee.put("totalEleFee",totalEleFee);
//        mapFee.put("totalSerFee",totalSerFee);
//        mapFee.put("totalMoney",totalMoney);
//        return mapFee;
//    }
//
//
//    // 结束订单，计算金额，保存消费消息
//    public static void orderStop(String recordCode, String deviceSN) {
//        log.info("---------------------------------------------");
//        log.info("------------------------------------开始结算");
//        java.util.Date date = new java.util.Date();
//        try {
//            // 查出订单和记录
//            TChargeChangeRecord record = ConstantFactory.me().queryByRecordCode(recordCode);
//            TChargeOrder order = ConstantFactory.me().queryOrderByRecordCode(recordCode);
//            TChargeCustomer customer = ConstantFactory.me().getUserByUserId(order.getUserId());
//            TChargeBatInfo inBat = ConstantFactory.me().queryBatInfoById(record.getInBatInfoId());
////          TChargeBatInfo outBat = ConstantFactory.me().queryBatInfoById(record.getOutBatInfoId());
//
//            // 计算功率
////            BigDecimal power = outBat.getBatVoltage().multiply(new BigDecimal(outBat.getBatFullCap())).divide(new BigDecimal("1000"));
//            // 电价
////            BigDecimal e = DictUtil.getChargePrice(deviceSN);
//
//            // 按照百分之五 电量差 计算电费
//            Map<String,BigDecimal> mapFee = DictUtil.getChargePrices(deviceSN,inBat.getBatRsoc(),order.getAfterChargeQuantity());
//            log.info("------------------------------------本次付费电费<{}>", mapFee.get("totalEleFee"));
//            log.info("------------------------------------本次付费服务费<{}>", mapFee.get("totalSerFee"));
//            log.info("------------------------------------本次付费总费用<{}>", mapFee.get("totalMoney"));
//
//            BigDecimal money=mapFee.get("totalMoney");
//            // 订单
//            if (null != inBat) {
//                order.setBeforeChargeQuantity(inBat.getBatRsoc());
//            }
//            order.setUpdateTime(date);
//            order.setPayTime(date);
//            order.setEndTime(date);
//            order.setStatus("4");
//            order.setPayStatus("01");
//            order.setEndReason("01");
//            order.setRemark("结算正常");
//            order.setTotalElectricityFee(mapFee.get("totalEleFee"));
//            order.setTotalServiceFee(mapFee.get("totalSerFee"));
//            order.setTotalAmount(money);
//
//            // 计算充电度数
////            BigDecimal d = order.getAfterChargeQuantity().subtract(order.getBeforeChargeQuantity()).multiply(power).divide(new BigDecimal("100"));
////            order.setTotalCharge(d);
//
//            // 金额
////            BigDecimal moneyValue = d.multiply(e).divide(new BigDecimal("100"));
////            order.setTotalAmount(moneyValue);
//
//            // 用户
//            customer.setTotal(customer.getTotal().add(money));
//            customer.setAccountBalance(customer.getAccountBalance().subtract(money));
//
//            // 消费记录
//            TChargeMessage message = new TChargeMessage();
//            message.setRecordId(order.getId());
//            message.setUserId(customer.getUserId());
//            message.setUserName(customer.getUserName());
//            message.setChargeAmount(money);
//            message.setAccountBalance(customer.getAccountBalance());
//            message.setTitle("消费");
//            message.setContent("换电消费" + money + "元");
//            message.setUpdateTime(date);
//            message.setMonthDate(DateUtil.format(new Date(), "yyyy-MM"));
//            message.setClientMessageType("expenditure");
//            message.setServerMessageType("income");
//            message.setChannel("user");
//            message.setCompanyName(order.getCompanyName());
//            message.setAccounts(order.getAccounts());
//
//            ConstantFactory.me().updateOrder(order);
//            ConstantFactory.me().saveChargeMessage(message);
//            ConstantFactory.me().getTChargeCustomerMapperBean().updateByPrimaryKeySelective(customer);
//            log.info("------------------------------------结算成功");
//            log.info("---------------------------------------------");
//        } catch (Exception e) {
//
//            TChargeOrder order = ConstantFactory.me().queryOrderByRecordCode(recordCode);
//            if (null != order) {
//                order.setUpdateTime(date);
//                order.setRemark("结算异常：" + (StringUtils.isNotBlank(e.getMessage()) == true ? e.getMessage() : "数据为空"));
//                ConstantFactory.me().updateOrder(order);
//            }
//
//            e.printStackTrace();
//            log.info("-----------------------------------------------------------结算异常：{}", e.getMessage());
//        }
//    }
//
//
//
//
//
//
//
//
//}
