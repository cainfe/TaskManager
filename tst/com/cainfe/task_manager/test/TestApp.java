package com.cainfe.task_manager.test;

import com.cainfe.task_manager.App;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestApp {

	@Test
	void test() {
		assertEquals("Hello World!", App.greetings());
	}

}
