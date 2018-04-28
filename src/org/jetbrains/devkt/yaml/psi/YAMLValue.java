package org.jetbrains.devkt.yaml.psi;

import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;

public interface YAMLValue extends YAMLPsiElement {
  @Nullable
  PsiElement getTag();
}
