package com.skbr;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedType;

import com.sun.org.apache.xalan.internal.xsltc.cmdline.Compile;

//Only public and package access specifier allowed
//Target only applicable to type (class, interface, annotation type, enum)
@Target(ElementType.TYPE)
// Retention within class files and visible at runtime
@Retention(RetentionPolicy.RUNTIME)
@interface Version {

	int major();

	int minor();
}

// Elements can have default value specified
@interface VersionWithDefault {

	int major() default 1;

	int minor() default 0;
}

public class AnnotationsTest {

	public static void main(String[] args) {


		print(Car.class);
		print(Engine.class);
		print(AudioSystem.class);
		print(BrakeSystem.class);

	}
	private static void print(Class clazz) {
		
		System.out.println();
		System.out.println();
		System.out.println("Printing for " + clazz);

		for (AnnotatedType at : clazz.getAnnotatedInterfaces()) {
			System.out.println(at);
		}
		System.out.println(clazz.getAnnotatedSuperclass());

		for (Annotation a : clazz.getAnnotations()) {
			System.out.println(a);
		}
		for (Annotation a : clazz.getDeclaredAnnotations()) {
			System.out.println(a);
		}

	}

	// All elements are specified (by convention in the same order as
	// declaration)
	@Version(major = 1, minor = 0)
	private static class Car {
	}

	// Order of the elements is irrelevant
	@Version(minor = 0, major = 1)
	private static class BrakeSystem {
	}

	// This won't compile since all elements have to be specified
	// @Version(major=1)
	@Version(major = 1, minor = 0)
	private static class Engine {
	}

	// This compiles since each element has a default value
	@VersionWithDefault()
	private static class AudioSystem {
	}

}
