package org.jetbrains.devkt.yaml.psi.impl;

import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.devkt.yaml.YAMLTokenTypes;
import org.jetbrains.devkt.yaml.psi.YAMLValue;

abstract class YAMLValueImpl extends YAMLPsiElementImpl implements YAMLValue {
	public YAMLValueImpl(@NotNull ASTNode node) {
		super(node);
	}

	@Nullable
	@Override
	public PsiElement getTag() {
		final PsiElement firstChild = getFirstChild();
		if (firstChild.getNode().getElementType() == YAMLTokenTypes.TAG) {
			return firstChild;
		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		return "YAML value";
	}
}
