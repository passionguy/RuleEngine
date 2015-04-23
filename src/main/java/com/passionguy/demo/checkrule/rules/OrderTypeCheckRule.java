package com.passionguy.demo.checkrule.rules;

import com.passionguy.demo.checkrule.AbstractRule;
import com.passionguy.demo.checkrule.anotation.Rule;
@Rule(name="订单类型检查规则",description="订单类型检查规则")
public class OrderTypeCheckRule<Order> extends AbstractRule<Order>{

	@Override
	public boolean evaluateConditions(Order order) {
		return true;
	}
}
