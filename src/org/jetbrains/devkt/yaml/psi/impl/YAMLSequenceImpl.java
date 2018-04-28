package org.jetbrains.devkt.yaml.psi.impl;

import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.devkt.yaml.psi.YAMLSequence;
import org.jetbrains.devkt.yaml.psi.YAMLSequenceItem;

import java.util.List;

public abstract class YAMLSequenceImpl extends YAMLCompoundValueImpl implements YAMLSequence {
  public YAMLSequenceImpl(@NotNull ASTNode node) {
    super(node);
  }

  @NotNull
  @Override
  public List<YAMLSequenceItem> getItems() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, YAMLSequenceItem.class);
  }

  @NotNull
  @Override
  public String getTextValue() {
    return "<sequence:" + Integer.toHexString(getText().hashCode()) + ">";
  }

  @Override
  public String toString() {
    return "YAML sequence";
  }
}
