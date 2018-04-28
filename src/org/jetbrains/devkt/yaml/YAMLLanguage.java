package org.jetbrains.devkt.yaml;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.com.intellij.lang.Language;
import org.jetbrains.kotlin.com.intellij.openapi.fileTypes.LanguageFileType;

/**
 * @author oleg
 */
public class YAMLLanguage extends Language {
	public static final @NotNull YAMLLanguage INSTANCE = new YAMLLanguage();

	private YAMLLanguage() {
		super("yaml", "text/" + YAMLFileType.DEFAULT_EXTENSION);
	}

	@Nullable
	@Override
	public LanguageFileType getAssociatedFileType() {
		return YAMLFileType.YML;
	}

	@NotNull
	@Override
	public String getDisplayName() {
		return "YAML";
	}
}
