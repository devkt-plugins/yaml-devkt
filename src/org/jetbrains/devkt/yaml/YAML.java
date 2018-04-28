package org.jetbrains.devkt.yaml;

import org.ice1000.devkt.openapi.ColorScheme;
import org.ice1000.devkt.openapi.ExtendedDevKtLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.com.intellij.psi.tree.IElementType;

import javax.swing.*;

/**
 * @author ice1000
 */
public class YAML<T> extends ExtendedDevKtLanguage<T> {
	public YAML() {
		super(YAMLLanguage.INSTANCE, new YAMLParserDefinition());
	}

	@Override
	public boolean satisfies(@NotNull String fileName) {
		return fileName.endsWith(".yml") || fileName.endsWith(".yaml");
	}

	@Nullable
	@Override
	public T attributesOf(@NotNull IElementType type, @NotNull ColorScheme<? extends T> colorScheme) {
		if (type == YAMLTokenTypes.COMMA) return colorScheme.getComma();
		else if (type == YAMLTokenTypes.COLON) return colorScheme.getColon();
		else if (type == YAMLTokenTypes.COMMENT) return colorScheme.getLineComments();
		else if (type == YAMLTokenTypes.SCALAR_KEY) return colorScheme.getKeywords();
		else return super.attributesOf(type, colorScheme);
	}

	@Override
	public @NotNull Icon getIcon() {
		return YAMLIcons.FILE;
	}
}
