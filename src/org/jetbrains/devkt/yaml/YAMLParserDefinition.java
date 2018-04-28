package org.jetbrains.devkt.yaml;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.devkt.yaml.lexer.YAMLFlexLexer;
import org.jetbrains.devkt.yaml.parser.YAMLParser;
import org.jetbrains.devkt.yaml.psi.impl.*;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.lang.ParserDefinition;
import org.jetbrains.kotlin.com.intellij.lang.PsiParser;
import org.jetbrains.kotlin.com.intellij.lexer.Lexer;
import org.jetbrains.kotlin.com.intellij.openapi.project.Project;
import org.jetbrains.kotlin.com.intellij.psi.FileViewProvider;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.psi.PsiFile;
import org.jetbrains.kotlin.com.intellij.psi.tree.IElementType;
import org.jetbrains.kotlin.com.intellij.psi.tree.IFileElementType;
import org.jetbrains.kotlin.com.intellij.psi.tree.TokenSet;

/**
 * @author oleg
 */
public class YAMLParserDefinition implements ParserDefinition, YAMLElementTypes {
	private static final TokenSet myCommentTokens = TokenSet.create(YAMLTokenTypes.COMMENT);

	@NotNull
	public Lexer createLexer(final Project project) {
		return new YAMLFlexLexer();
	}

	@Nullable
	public PsiParser createParser(final Project project) {
		return new YAMLParser();
	}

	public IFileElementType getFileNodeType() {
		return FILE;
	}

	@NotNull
	public TokenSet getWhitespaceTokens() {
		return TokenSet.create(YAMLTokenTypes.WHITESPACE);
	}

	@NotNull
	public TokenSet getCommentTokens() {
		return myCommentTokens;
	}

	@NotNull
	public TokenSet getStringLiteralElements() {
		return TokenSet.create(YAMLTokenTypes.SCALAR_STRING, YAMLTokenTypes.SCALAR_DSTRING, YAMLTokenTypes.TEXT);
	}

	@NotNull
	public PsiElement createElement(final ASTNode node) {
		final IElementType type = node.getElementType();
		if (type == DOCUMENT) {
			return new YAMLDocumentImpl(node);
		}
		if (type == KEY_VALUE_PAIR) {
			return new YAMLKeyValueImpl(node);
		}
		if (type == COMPOUND_VALUE) {
			return new YAMLCompoundValueImpl(node);
		}
		if (type == SEQUENCE) {
			return new YAMLBlockSequenceImpl(node);
		}
		if (type == MAPPING) {
			return new YAMLBlockMappingImpl(node);
		}
		if (type == SEQUENCE_ITEM) {
			return new YAMLSequenceItemImpl(node);
		}
		if (type == HASH) {
			return new YAMLHashImpl(node);
		}
		if (type == ARRAY) {
			return new YAMLArrayImpl(node);
		}
		if (type == SCALAR_LIST_VALUE) {
			return new YAMLScalarListImpl(node);
		}
		if (type == SCALAR_TEXT_VALUE) {
			return new YAMLScalarTextImpl(node);
		}
		if (type == SCALAR_PLAIN_VALUE) {
			return new YAMLPlainTextImpl(node);
		}
		if (type == SCALAR_QUOTED_STRING) {
			return new YAMLQuotedTextImpl(node);
		}
		return new YAMLPsiElementImpl(node);
	}

	public PsiFile createFile(final FileViewProvider viewProvider) {
		return new YAMLFileImpl(viewProvider);
	}

	public SpaceRequirements spaceExistanceTypeBetweenTokens(final ASTNode left, final ASTNode right) {
		return SpaceRequirements.MAY;
	}
}
