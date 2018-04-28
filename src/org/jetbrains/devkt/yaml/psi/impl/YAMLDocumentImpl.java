package org.jetbrains.devkt.yaml.psi.impl;

import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.devkt.yaml.psi.YAMLDocument;
import org.jetbrains.devkt.yaml.psi.YAMLValue;

/**
 * @author oleg
 */
public class YAMLDocumentImpl extends YAMLPsiElementImpl implements YAMLDocument {
  public YAMLDocumentImpl(@NotNull final ASTNode node) {
    super(node);
  }

  @Override
  public String toString() {
    return "YAML document";
  }

  @Nullable
  @Override
  public YAMLValue getTopLevelValue() {
    return PsiTreeUtil.findChildOfType(this, YAMLValue.class);
  }
}
