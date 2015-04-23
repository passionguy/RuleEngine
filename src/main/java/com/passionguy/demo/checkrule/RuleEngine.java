package com.passionguy.demo.checkrule;

import java.util.List;

/**
 * 顺序执行规则组列表
 * 如果规则组中skipOnFirstAppliedRule为false，将以多线程方式执行规则
 * 如果规则组中skipOnFirstAppliedRule为true，规则将串行执行，规则在第一个被触发后终止
 * @author tutu
 *
 */
public class RuleEngine {

	public static void run(List<RuleGroup> ruleGroups) {
		for (RuleGroup rulegroup : ruleGroups) {
			rulegroup.excute();
			if (rulegroup.isFired)
				break;
		}
	}
}
