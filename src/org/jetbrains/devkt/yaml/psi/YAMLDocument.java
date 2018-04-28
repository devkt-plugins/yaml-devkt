package org.jetbrains.devkt.yaml.psi;

import org.jetbrains.annotations.Nullable;

/**
 * @author oleg
 */
public interface YAMLDocument extends YAMLPsiElement {
  @Nullable
  YAMLValue getTopLevelValue();
}
