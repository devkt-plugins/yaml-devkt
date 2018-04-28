package org.jetbrains.devkt.yaml.psi.impl;

import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.devkt.yaml.YAMLElementGenerator;
import org.jetbrains.devkt.yaml.YAMLUtil;
import org.jetbrains.devkt.yaml.psi.YAMLKeyValue;

public class YAMLBlockMappingImpl extends YAMLMappingImpl {
  public YAMLBlockMappingImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  protected void addNewKey(@NotNull YAMLKeyValue key) {
    final int indent = YAMLUtil.getIndentToThisElement(this);

    final YAMLElementGenerator generator = YAMLElementGenerator.getInstance(getProject());
    add(generator.createEol());
    if (indent > 0) {
      add(generator.createIndent(indent));
    }
    add(key);
  }
}
