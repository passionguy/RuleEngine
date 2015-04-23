package com.passionguy.demo.checkrule;

public interface Handler<T> {
	public void onEvent(T t);
}
