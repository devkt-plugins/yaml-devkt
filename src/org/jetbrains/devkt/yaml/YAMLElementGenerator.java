package org.jetbrains.devkt.yaml;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.devkt.yaml.psi.YAMLFile;
import org.jetbrains.devkt.yaml.psi.YAMLKeyValue;
import org.jetbrains.devkt.yaml.psi.impl.YAMLQuotedTextImpl;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.openapi.components.ServiceManager;
import org.jetbrains.kotlin.com.intellij.openapi.project.Project;
import org.jetbrains.kotlin.com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.psi.PsiFile;
import org.jetbrains.kotlin.com.intellij.psi.PsiFileFactory;
import org.jetbrains.kotlin.com.intellij.psi.TokenType;
import org.jetbrains.kotlin.com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.kotlin.com.intellij.util.LocalTimeCounter;

import java.util.List;

/**
 * @author traff
 */
public class YAMLElementGenerator {
	private final Project myProject;

	public YAMLElementGenerator(Project project) {
		myProject = project;
	}

	public static YAMLElementGenerator getInstance(Project project) {
		return ServiceManager.getService(project, YAMLElementGenerator.class);
	}

	@NotNull
	public static String createChainedKey(@NotNull List<String> keyComponents, int indentAddition) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < keyComponents.size(); ++i) {
			if (i > 0) {
				sb.append(StringUtil.repeatSymbol(' ', indentAddition + 2 * i));
			}
			sb.append(keyComponents.get(i)).append(":");
			if (i + 1 < keyComponents.size()) {
				sb.append('\n');
			}
		}
		return sb.toString();
	}

	@NotNull
	public YAMLKeyValue createYamlKeyValue(@NotNull String keyName, @NotNull String valueText) {
		final PsiFile tempFile = createDummyYamlWithText(keyName + ": " + valueText);
		return PsiTreeUtil.collectElementsOfType(tempFile, YAMLKeyValue.class).iterator().next();
	}

	@NotNull
	public YAMLQuotedTextImpl createYamlDoubleQuotedString() {
		final YAMLFile tempFile = createDummyYamlWithText("\"foo\"");
		return PsiTreeUtil.collectElementsOfType(tempFile, YAMLQuotedTextImpl.class).iterator().next();
	}

	@NotNull
	public YAMLFile createDummyYamlWithText(@NotNull String text) {
		return (YAMLFile) PsiFileFactory.getInstance(myProject)
				.createFileFromText("temp." + YAMLFileType.YML.getDefaultExtension(), YAMLFileType.YML, text, LocalTimeCounter.currentTime(), true);
	}

	@NotNull
	public PsiElement createEol() {
		final YAMLFile file = createDummyYamlWithText("\n");
		return PsiTreeUtil.getDeepestFirst(file);
	}

	@NotNull
	public PsiElement createSpace() {
		final YAMLKeyValue keyValue = createYamlKeyValue("foo", "bar");
		final ASTNode whitespaceNode = keyValue.getNode().findChildByType(TokenType.WHITE_SPACE);
		assert whitespaceNode != null;
		return whitespaceNode.getPsi();
	}

	@NotNull
	public PsiElement createIndent(int size) {
		final YAMLFile file = createDummyYamlWithText(StringUtil.repeatSymbol(' ', size));
		return PsiTreeUtil.getDeepestFirst(file);
	}

	@NotNull
	public PsiElement createColon() {
		final YAMLFile file = createDummyYamlWithText("? foo : bar");
		final PsiElement at = file.findElementAt("? foo ".length());
		assert at != null && at.getNode().getElementType() == YAMLTokenTypes.COLON;
		return at;
	}

}
