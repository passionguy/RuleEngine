package com.passionguy.demo.checkrule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.passionguy.demo.checkrule.handlers.CancelOrderHandler;
import com.passionguy.demo.checkrule.handlers.CheckPassHandler;
import com.passionguy.demo.checkrule.rules.BlacklistCheckRule;
import com.passionguy.demo.checkrule.rules.OrderTypeCheckRule;

public class Main {
	@Test
	public void BasicTest() {
		List<RuleGroup> ruleGroups = Lists.newArrayList();
		// 选择规则并设置处理句柄
		AbstractRule rule1 = new OrderTypeCheckRule();
		rule1.setName("订单类型检查规则");
		Set<AbstractRule> rules = new HashSet<AbstractRule>();
		Handler<Order> handler=new CheckPassHandler();
		List<Handler<Order>> handlers=Lists.newArrayList();
		handlers.add(handler);
		rule1.setHandlers(handlers);
		rules.add(rule1);
		// 规则分组
		RuleGroup ruleGroup = new RuleGroup();
		ruleGroup.setSkipOnFirstAppliedRule(false);
		ruleGroup.setRules(rules);
		// 选择规则并设置处理句柄
		AbstractRule rule2 = new BlacklistCheckRule();
		rule2.setName("黑名单检查规则");
		Set<AbstractRule> rules2 = new HashSet<AbstractRule>();
		Handler<Order> handler2=new CancelOrderHandler();
		List<Handler<Order>> handlers2=Lists.newArrayList();
		handlers2.add(handler2);
		rule2.setHandlers(handlers2);
		rules2.add(rule2);
		// 规则分组
		RuleGroup ruleGroup2 = new RuleGroup();
		ruleGroup2.setSkipOnFirstAppliedRule(false);
		ruleGroup2.setRules(rules2);
		
		
		ruleGroups.add(ruleGroup);
		ruleGroups.add(ruleGroup2);
		RuleEngine.run(ruleGroups);
	}
	@Test
	public void MultiThreadTest(){
		List<RuleGroup> ruleGroups = Lists.newArrayList();
		// 选择规则并设置处理句柄
		AbstractRule rule1 = new OrderTypeCheckRule();
		rule1.setName("订单类型检查规则");
		AbstractRule rule2 = new BlacklistCheckRule();
		rule2.setName("黑名单检查规则");
		Handler<Order> handler2=new CancelOrderHandler();
		List<Handler<Order>> handlers2=Lists.newArrayList();
		handlers2.add(handler2);
		Set<AbstractRule> rules = new HashSet<AbstractRule>();
		Handler<Order> handler=new CheckPassHandler();
		List<Handler<Order>> handlers=Lists.newArrayList();
		handlers.add(handler);
		rule1.setHandlers(handlers);
		rule2.setHandlers(handlers2);
		rules.add(rule1);
		rules.add(rule2);
		// 规则分组
		RuleGroup ruleGroup = new RuleGroup();
		ruleGroup.setSkipOnFirstAppliedRule(false);
		ruleGroup.setRules(rules);
		ruleGroups.add(ruleGroup);
		RuleEngine.run(ruleGroups);
	}
}
