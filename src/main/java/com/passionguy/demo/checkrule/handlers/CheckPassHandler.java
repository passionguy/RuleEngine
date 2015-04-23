package com.passionguy.demo.checkrule.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.passionguy.demo.checkrule.Handler;
import com.passionguy.demo.checkrule.Order;
import com.passionguy.demo.checkrule.anotation.RuleHandler;
@RuleHandler(name="审核通过处理句柄",description="审核通过处理句柄")
public class CheckPassHandler implements Handler<Order>{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public void onEvent(Order order) {
		logger.info("订单审核通过.......");
	}

}
