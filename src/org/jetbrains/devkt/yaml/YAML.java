package org.jetbrains.devkt.yaml;

import org.ice1000.devkt.openapi.ExtendedDevKtLanguage;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author ice1000
 */
public class YAML<T> extends ExtendedDevKtLanguage<T> {
	public YAML() {
		super(YAMLLanguage.INSTANCE, new YAMLParserDefinition());
	}

	@NotNull
	@Override
	public Icon getIcon() {
		return YAMLIcons.FILE;
	}
}
