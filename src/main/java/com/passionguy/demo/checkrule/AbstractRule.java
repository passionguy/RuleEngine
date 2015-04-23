package com.passionguy.demo.checkrule;

import java.util.List;

public abstract class AbstractRule<T> {
	private String id;
	private String name;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	protected List<Handler<Order>> handlers;

	public List<Handler<Order>> getHandlers() {
		return handlers;
	}

	public abstract boolean evaluateConditions(T t);

	public void setHandlers(List<Handler<Order>> handlers) {
		this.handlers = handlers;
	};

	public void performActions() throws Exception {
		for (Handler<Order> handler : handlers) {
			handler.onEvent(OrderContextHolder.getContext());
		}
	}
}
