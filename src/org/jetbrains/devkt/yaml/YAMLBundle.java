package org.jetbrains.devkt.yaml;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;
import org.jetbrains.kotlin.com.intellij.CommonBundle;

import java.util.ResourceBundle;

/**
 * @author oleg
 */
public class YAMLBundle {
	public static @NotNull String message(@NotNull @PropertyKey(resourceBundle = BUNDLE) String key, @NotNull Object... params) {
		return CommonBundle.message(getBundle(), key, params);
	}

	private static ResourceBundle bundle;
	@NonNls
	private static final String BUNDLE = "org.jetbrains.devkt.yaml.YAMLBundle";

	private YAMLBundle() {
	}

	/*
	 * This method added for jruby access
	 */
	public static String message(@PropertyKey(resourceBundle = BUNDLE) String key) {
		return CommonBundle.message(getBundle(), key);
	}

	private static ResourceBundle getBundle() {
		return bundle == null ? (bundle = ResourceBundle.getBundle(BUNDLE)) : bundle;
	}
}
