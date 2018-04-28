package org.jetbrains.devkt.yaml.psi.impl;

import org.jetbrains.kotlin.com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.devkt.yaml.psi.YAMLPsiElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author oleg
 */
public class YAMLPsiElementImpl extends ASTWrapperPsiElement implements YAMLPsiElement {
  public YAMLPsiElementImpl(@NotNull final ASTNode node) {
    super(node);
  }

  @Override
  public String toString() {
    return "YAML element";
  }

  public List<YAMLPsiElement> getYAMLElements() {
    final ArrayList<YAMLPsiElement> result = new ArrayList<>();
    for (ASTNode node : getNode().getChildren(null)) {
      final PsiElement psi = node.getPsi();
      if (psi instanceof YAMLPsiElement){
        result.add((YAMLPsiElement) psi);
      }
    }
    return result;
  }
}
