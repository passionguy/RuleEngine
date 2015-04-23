package com.passionguy.demo.checkrule.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import com.google.common.collect.Lists;
import com.passionguy.demo.checkrule.anotation.Rule;
import com.passionguy.demo.checkrule.anotation.RuleHandler;

/**
 * 注解工具类，供前台页面配置用
 * @author tutu
 *
 */
public class RuleHandlerUtils {
	private String rulePackage;
	private String ruleHandlerPackage;

	public String getRulePackage() {
		return rulePackage;
	}

	public void setRulePackage(String rulePackage) {
		this.rulePackage = rulePackage;
	}

	public String getRuleHandlerPackage() {
		return ruleHandlerPackage;
	}

	public void setRuleHandlerPackage(String ruleHandlerPackage) {
		this.ruleHandlerPackage = ruleHandlerPackage;
	}

	public List<Rule> getRules() {
		List<Rule> rules = Lists.newArrayList();
		Reflections reflections = new Reflections(rulePackage);
		Set<Class<?>> allClasses = reflections
				.getTypesAnnotatedWith(Rule.class);
		for (Class<?> glass : allClasses) {
			Rule rule = glass.getAnnotation(Rule.class);
			rules.add(rule);
		}
		return rules;
	}
	
	public List<RuleHandler> getRuleHandlers() {
		List<RuleHandler> ruleHandlers = Lists.newArrayList();
		Reflections reflections = new Reflections(rulePackage);
		Set<Class<?>> allClasses = reflections
				.getTypesAnnotatedWith(RuleHandler.class);
		for (Class<?> glass : allClasses) {
			RuleHandler ruleHandler = glass.getAnnotation(RuleHandler.class);
			ruleHandlers.add(ruleHandler);
		}
		return ruleHandlers;
	}
	public static void main(String[] args) {
		RuleHandlerUtils r = new RuleHandlerUtils();
		r.setRulePackage("com.passionguy.demo.checkrule.rules");
		r.setRulePackage("com.passionguy.demo.checkrule.handlers");
		r.getRules();
		r.getRuleHandlers();
	}

}
