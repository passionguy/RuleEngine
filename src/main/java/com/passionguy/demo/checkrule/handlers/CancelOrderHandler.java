package com.passionguy.demo.checkrule.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.passionguy.demo.checkrule.Handler;
import com.passionguy.demo.checkrule.Order;
import com.passionguy.demo.checkrule.anotation.RuleHandler;
@RuleHandler(name="订单取消处理句柄",description="订单取消处理句柄")
public class CancelOrderHandler implements Handler<Order>{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public void onEvent(Order t) {
		logger.info("正在做订单取消.........");
		
	}

}
