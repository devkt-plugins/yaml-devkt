package org.jetbrains.devkt.yaml.psi.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.devkt.yaml.YAMLElementTypes;
import org.jetbrains.devkt.yaml.YAMLTokenTypes;
import org.jetbrains.devkt.yaml.lexer.YAMLGrammarCharUtil;
import org.jetbrains.devkt.yaml.psi.YAMLScalar;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.openapi.util.Pair;
import org.jetbrains.kotlin.com.intellij.openapi.util.TextRange;

import java.util.Collections;
import java.util.List;

public abstract class YAMLScalarImpl extends YAMLValueImpl implements YAMLScalar {
	protected static final int MAX_SCALAR_LENGTH_PREDEFINED = 60;

	public YAMLScalarImpl(@NotNull ASTNode node) {
		super(node);
	}

	@NotNull
	public abstract List<TextRange> getContentRanges();

	@NotNull
	protected abstract String getRangesJoiner(@NotNull CharSequence text, @NotNull List<TextRange> contentRanges, int indexBefore);

	protected List<Pair<TextRange, String>> getDecodeReplacements(@NotNull CharSequence input) {
		return Collections.emptyList();
	}

	protected List<Pair<TextRange, String>> getEncodeReplacements(@NotNull CharSequence input) throws IllegalArgumentException {
		throw new IllegalArgumentException("Not implemented");
	}

	@NotNull
	@Override
	public String getTextValue() {
		final String text = getText();
		final List<TextRange> contentRanges = getContentRanges();

		final StringBuilder builder = new StringBuilder();

		for (int i = 0; i < contentRanges.size(); i++) {
			final TextRange range = contentRanges.get(i);

			final CharSequence curString = range.subSequence(text);
			builder.append(curString);

			if (i + 1 != contentRanges.size()) {
				builder.append(getRangesJoiner(text, contentRanges, i));
			}
		}
		return processReplacements(builder, getDecodeReplacements(builder));
	}

	static
	@NotNull String processReplacements(@NotNull CharSequence input,
	                                    @NotNull List<Pair<TextRange, String>> replacements) throws IndexOutOfBoundsException {
		StringBuilder result = new StringBuilder();
		int currentOffset = 0;
		for (Pair<TextRange, String> replacement : replacements) {
			result.append(input.subSequence(currentOffset, replacement.getFirst().getStartOffset()));
			result.append(replacement.getSecond());
			currentOffset = replacement.getFirst().getEndOffset();
		}
		result.append(input.subSequence(currentOffset, input.length()));
		return result.toString();
	}

	protected static boolean isSurroundedByNoSpace(CharSequence text, int pos) {
		return (pos - 1 < 0 || !YAMLGrammarCharUtil.isSpaceLike(text.charAt(pos - 1)))
				&& (pos + 1 >= text.length() || !YAMLGrammarCharUtil.isSpaceLike(text.charAt(pos + 1)));
	}

	@Nullable
	protected final ASTNode getFirstContentNode() {
		ASTNode node = getNode().getFirstChildNode();
		while (node != null && (
				node.getElementType() == YAMLTokenTypes.TAG || YAMLElementTypes.BLANK_ELEMENTS.contains(node.getElementType()))) {
			node = node.getTreeNext();
		}
		return node;
	}
}
