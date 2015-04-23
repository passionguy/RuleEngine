package com.passionguy.demo.checkrule;

import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuleGroup {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	protected boolean skipOnFirstAppliedRule;
	protected boolean isFired = false;
	public Set<AbstractRule> rules;

	public boolean isFired() {
		return isFired;
	}

	public boolean isSkipOnFirstAppliedRule() {
		return skipOnFirstAppliedRule;
	}

	public void setSkipOnFirstAppliedRule(boolean skipOnFirstAppliedRule) {
		this.skipOnFirstAppliedRule = skipOnFirstAppliedRule;
	}

	public Set<AbstractRule> getRules() {
		return rules;
	}

	public void setRules(Set<AbstractRule> rules) {
		this.rules = rules;
	}

	public void excute() {
		if (skipOnFirstAppliedRule) {
			for (AbstractRule rule : rules) {
				if (rule.evaluateConditions(OrderContextHolder.getContext())) {
					isFired = true;
					logger.info("规则{}被触发.", new Object[] { rule.getName() });
					try {
						rule.performActions();
						logger.info("规则 {} 被成功执行.",
								new Object[] { rule.getName() });
						if (skipOnFirstAppliedRule) {
							logger.info("由于设定规则组中规则首次被触发将不再执行后续规则，规则组后续规则将不被执行");
							break;
						}
					} catch (Exception exception) {
						logger.info("规则 '" + rule.getName() + "' 执行出现异常.",
								exception);
					}
				}
				logger.info("规则组未被触发.");
			}
		} else {
			final CountDownLatch begin = new CountDownLatch(1);
			final CountDownLatch end = new CountDownLatch(rules.size());
			final ExecutorService exec = Executors.newFixedThreadPool(rules
					.size());
			final AbstractRule[] abstractRules = rules
					.toArray(new AbstractRule[0]);
			for (int index = 0; index < abstractRules.length; index++) {
				final AbstractRule rule = abstractRules[index];
				Runnable run = new Runnable() {
					public void run() {
						try {
							begin.await();
							if (rule.evaluateConditions(OrderContextHolder.getContext())) {
								isFired = true;
								logger.info("规则{}被触发.",
										new Object[] { rule.getName() });
								try {
									rule.performActions();
									logger.info("规则 {} 被成功执行.",
											new Object[] { rule.getName() });
								} catch (Exception exception) {
									logger.info("规则 '" + rule.getName()
											+ "' 执行出现异常.", exception);
								}
							}
						} catch (InterruptedException e) {
						} finally {
							end.countDown();
						}
					}
				};
				exec.submit(run);
			}
			logger.info("规则组执行开始");
			begin.countDown();
			try {
				end.await();
			} catch (InterruptedException e) {
				logger.error("规则组执行过程中异常中断", e);
			}
			logger.info("规则组执行结束");
			exec.shutdown();
		}

	}
}
