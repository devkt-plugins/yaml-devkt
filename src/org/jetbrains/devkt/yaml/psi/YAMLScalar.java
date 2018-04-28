package org.jetbrains.devkt.yaml.psi;

import org.jetbrains.kotlin.com.intellij.psi.PsiLanguageInjectionHost;
import org.jetbrains.annotations.NotNull;

public interface YAMLScalar extends YAMLValue, PsiLanguageInjectionHost {
  @NotNull
  String getTextValue();

  boolean isMultiline();
}
