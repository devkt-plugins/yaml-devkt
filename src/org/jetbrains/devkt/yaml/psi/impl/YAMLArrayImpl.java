package org.jetbrains.devkt.yaml.psi.impl;

import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.devkt.yaml.psi.YAMLSequence;

/**
 * @author oleg
 */
public class YAMLArrayImpl extends YAMLSequenceImpl implements YAMLSequence {
  public YAMLArrayImpl(@NotNull final ASTNode node) {
    super(node);
  }

  @Override
  public String toString() {
    return "YAML array";
  }
}