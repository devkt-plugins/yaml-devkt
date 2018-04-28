package org.jetbrains.devkt.yaml.psi.impl;

import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.devkt.yaml.psi.YAMLCompoundValue;
import org.jetbrains.devkt.yaml.psi.YAMLScalar;

/**
 * @author oleg
 */
public class YAMLCompoundValueImpl extends YAMLValueImpl implements YAMLCompoundValue {
  public YAMLCompoundValueImpl(@NotNull final ASTNode node) {
    super(node);
  }

  @Override
  public String toString() {
    return "YAML compound value";
  }

  @NotNull
  @Override
  public String getTextValue() {
    PsiElement element = getTag() != null ? getTag().getNextSibling() : getFirstChild();

    while (element != null && !(element instanceof YAMLScalar)) {
      element = element.getNextSibling();
    }

    if (element != null) {
      return ((YAMLScalar)element).getTextValue();
    }
    else {
      return "<compoundValue:" + Integer.toHexString(getText().hashCode()) + ">";
    }
  }
}