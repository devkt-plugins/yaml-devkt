package org.jetbrains.devkt.yaml.psi.impl;

import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class YAMLBlockSequenceImpl extends YAMLSequenceImpl {
  public YAMLBlockSequenceImpl(@NotNull ASTNode node) {
    super(node);
  }
}
