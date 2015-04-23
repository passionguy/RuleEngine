package com.passionguy.demo.checkrule;

public class OrderContextHolder {
	private static ThreadLocal<Order> contextThreadLocal = new ThreadLocal<Order>();
	public static void setContext(Order order){
		contextThreadLocal.set(order);
	}
	
	public static Order getContext(){
		return contextThreadLocal.get();
	}
}
