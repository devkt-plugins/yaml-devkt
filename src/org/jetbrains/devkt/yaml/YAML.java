package org.jetbrains.devkt.yaml;

import org.ice1000.devkt.openapi.ColorScheme;
import org.ice1000.devkt.openapi.ExtendedDevKtLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.devkt.yaml.psi.YAMLKeyValue;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
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
	public boolean shouldAddAsCompletion(@NotNull PsiElement element) {
		return element instanceof YAMLKeyValue;
	}

	@Override
	public final @NotNull String getLineCommentStart() {
		return "#";
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
		else if (type == YAMLTokenTypes.SCALAR_STRING) return colorScheme.getString();
		else if (type == YAMLTokenTypes.SCALAR_DSTRING) return colorScheme.getString();
		else if (type == YAMLTokenTypes.SCALAR_TEXT) return colorScheme.getString();
		else if (type == YAMLTokenTypes.LBRACE || type == YAMLTokenTypes.RBRACE) return colorScheme.getBraces();
		else if (type == YAMLTokenTypes.LBRACKET || type == YAMLTokenTypes.RBRACKET) return colorScheme.getBrackets();
		else return super.attributesOf(type, colorScheme);
	}

	@Override
	public @NotNull Icon getIcon() {
		return YAMLIcons.FILE;
	}
}
