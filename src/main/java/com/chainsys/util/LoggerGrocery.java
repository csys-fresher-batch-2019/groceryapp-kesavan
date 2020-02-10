package com.chainsys.util;

public class LoggerGrocery {
	public static LoggerGrocery getInstance() {
		LoggerGrocery Logger = new LoggerGrocery();
		return Logger;
	}

	public void getInput(Object message) {
		System.out.println(message);
	}

	public void debug(Object message) {
		System.out.println(message);
	}

	public void info(Object message) {
		System.out.println(message);
	}

	public void error(Object message) {
		System.err.println(message);
	}

}
