package com.intercab.service.integrator.core.service.common;

public interface ICommonService<T, TLog> {
	void processData(String message);
	void sendToService(T entityEventHub, TLog log);
}