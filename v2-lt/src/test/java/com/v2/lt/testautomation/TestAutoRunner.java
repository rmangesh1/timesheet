package com.v2.lt.testautomation;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.v2.lt.test.AllTests;

public class TestAutoRunner {

	 public static void main(String[] args) {
		    Result result = JUnitCore.runClasses(AllTests.class);
		    for (Failure failure : result.getFailures()) {
		      System.out.println(failure.toString());
		    }
		  }
}
