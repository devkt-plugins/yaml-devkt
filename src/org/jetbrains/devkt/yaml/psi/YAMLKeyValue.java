package org.jetbrains.devkt.yaml.psi;

import org.jetbrains.kotlin.com.intellij.pom.PomTarget;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author oleg
 */
public interface YAMLKeyValue extends YAMLPsiElement, PsiNamedElement, PomTarget {
  @Contract(pure = true)
  @Nullable
  PsiElement getKey();

  @Contract(pure = true)
  @NotNull
  String getKeyText();

  @Contract(pure = true)
  @Nullable
  YAMLValue getValue();

  @Contract(pure = true)
  @NotNull
  String getValueText();
  
  YAMLMapping getParentMapping();

  void setValue(@NotNull YAMLValue value);
}