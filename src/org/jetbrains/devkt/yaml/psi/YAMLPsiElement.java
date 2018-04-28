package org.jetbrains.devkt.yaml.psi;

import org.jetbrains.kotlin.com.intellij.psi.NavigatablePsiElement;

import java.util.List;

/**
 * @author oleg
 */
public interface YAMLPsiElement extends NavigatablePsiElement {
  List<YAMLPsiElement> getYAMLElements();
}
