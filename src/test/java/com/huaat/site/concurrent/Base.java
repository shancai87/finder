package com.huaat.site.concurrent;

public interface Base {
	double calculate(int a);

	default double sqrt(int a) {
		return Math.sqrt(a);
	}
}
