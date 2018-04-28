package org.jetbrains.devkt.yaml.psi;

import org.jetbrains.annotations.NotNull;

public interface YAMLScalar extends YAMLValue {
	@NotNull
	String getTextValue();

	boolean isMultiline();
}
