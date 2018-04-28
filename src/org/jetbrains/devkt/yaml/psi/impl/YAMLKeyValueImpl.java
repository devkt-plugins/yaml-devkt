package org.jetbrains.devkt.yaml.psi.impl;

import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.navigation.ItemPresentation;
import org.jetbrains.kotlin.com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.psi.PsiReference;
import org.jetbrains.kotlin.com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import org.jetbrains.kotlin.com.intellij.util.IncorrectOperationException;
import org.jetbrains.kotlin.com.intellij.util.ObjectUtils;
import org.jetbrains.kotlin.com.intellij.util.PlatformIcons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.devkt.yaml.YAMLElementGenerator;
import org.jetbrains.devkt.yaml.YAMLTokenTypes;
import org.jetbrains.devkt.yaml.YAMLUtil;
import org.jetbrains.devkt.yaml.psi.*;

import javax.swing.*;

/**
 * @author oleg
 */
public class YAMLKeyValueImpl extends YAMLPsiElementImpl implements YAMLKeyValue {
	public YAMLKeyValueImpl(@NotNull final ASTNode node) {
		super(node);
	}

	@Override
	public String toString() {
		return "YAML key value";
	}

	@Nullable
	public PsiElement getKey() {
		final PsiElement result = findChildByType(YAMLTokenTypes.SCALAR_KEY);
		if (result != null) return result;
		if (isExplicit()) return findChildByClass(YAMLCompoundValue.class);
		return null;
	}

	@Nullable
	@Override
	public YAMLMapping getParentMapping() {
		return ObjectUtils.tryCast(super.getParent(), YAMLMapping.class);
	}

	@Nullable
	@Override
	public String getName() {
		return getKeyText();
	}

	@NotNull
	public String getKeyText() {
		final PsiElement keyElement = getKey();
		if (keyElement == null) {
			return "";
		}

		if (keyElement instanceof YAMLCompoundValue) {
			return ((YAMLCompoundValue) keyElement).getTextValue();
		}

		final String text = keyElement.getText();
		return StringUtil.unquoteString(text.substring(0, text.length() - 1));
	}

	@Nullable
	public YAMLValue getValue() {
		for (PsiElement child = getLastChild(); child != null; child = child.getPrevSibling()) {
			if (child instanceof YAMLValue) {
				return ((YAMLValue) child);
			}
		}
		return null;
	}

	@NotNull
	public String getValueText() {
		final YAMLValue value = getValue();
		if (value instanceof YAMLScalar) {
			return ((YAMLScalar) value).getTextValue();
		} else if (value instanceof YAMLCompoundValue) {
			return ((YAMLCompoundValue) value).getTextValue();
		}
		return "";
	}


	@Override
	public void setValue(@NotNull YAMLValue value) {
		adjustWhitespaceToContentType(value instanceof YAMLScalar);

		if (getValue() != null) {
			getValue().replace(value);
			return;
		}

		final YAMLElementGenerator generator = YAMLElementGenerator.getInstance(getProject());
		if (isExplicit()) {
			if (findChildByType(YAMLTokenTypes.COLON) == null) {
				add(generator.createColon());
				add(generator.createSpace());
				add(value);
			}
		} else {
			add(value);
		}
	}

	private void adjustWhitespaceToContentType(boolean isScalar) {
		assert getKey() != null;
		PsiElement key = getKey();

		if (key.getNextSibling() != null && key.getNextSibling().getNode().getElementType() == YAMLTokenTypes.COLON) {
			key = key.getNextSibling();
		}

		while (key.getNextSibling() != null && !(key.getNextSibling() instanceof YAMLValue)) {
			key.getNextSibling().delete();
		}
		final YAMLElementGenerator generator = YAMLElementGenerator.getInstance(getProject());
		if (isScalar) {
			addAfter(generator.createSpace(), key);
		} else {
			final int indent = YAMLUtil.getIndentToThisElement(this);
			addAfter(generator.createIndent(indent + 2), key);
			addAfter(generator.createEol(), key);
		}
	}

	@NotNull
	@Override
	protected Icon getElementIcon(int flags) {
		return PlatformIcons.PROPERTY_ICON;
	}

	public PsiElement setName(@NonNls @NotNull String newName) throws IncorrectOperationException {
		return YAMLUtil.rename(this, newName);
	}

	private boolean isExplicit() {
		final ASTNode child = getNode().getFirstChildNode();
		return child != null && child.getElementType() == YAMLTokenTypes.QUESTION;
	}
}
