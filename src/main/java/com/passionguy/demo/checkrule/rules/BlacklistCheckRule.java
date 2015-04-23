package com.passionguy.demo.checkrule.rules;

import com.passionguy.demo.checkrule.AbstractRule;
import com.passionguy.demo.checkrule.anotation.Rule;

@Rule(name="黑名单检查",description="黑名单检查")
public class BlacklistCheckRule<Order> extends AbstractRule<Order>{

	@Override
	public boolean evaluateConditions(Order order) {
		// TODO Auto-generated method stub
		return true;
	}

}
