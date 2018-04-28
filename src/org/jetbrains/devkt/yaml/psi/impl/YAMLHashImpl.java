package org.jetbrains.devkt.yaml.psi.impl;

import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.devkt.yaml.YAMLElementGenerator;
import org.jetbrains.devkt.yaml.YAMLTokenTypes;
import org.jetbrains.devkt.yaml.psi.YAMLFile;
import org.jetbrains.devkt.yaml.psi.YAMLKeyValue;
import org.jetbrains.devkt.yaml.psi.YAMLMapping;

/**
 * @author oleg
 */
public class YAMLHashImpl extends YAMLMappingImpl implements YAMLMapping {
  public YAMLHashImpl(@NotNull final ASTNode node) {
    super(node);
  }

  @Override
  protected void addNewKey(@NotNull YAMLKeyValue key) {
    PsiElement anchor = null;
    for (PsiElement child = getLastChild(); child != null; child = child.getPrevSibling()) {
      final IElementType type = child.getNode().getElementType();
      if (type == YAMLTokenTypes.COMMA || type == YAMLTokenTypes.LBRACE) {
        anchor = child;
      }
    }
    
    addAfter(key, anchor);

    final YAMLFile dummyFile = YAMLElementGenerator.getInstance(getProject()).createDummyYamlWithText("{,}");
    final PsiElement comma = dummyFile.findElementAt(1);
    assert comma != null && comma.getNode().getElementType() == YAMLTokenTypes.COMMA;
    
    addAfter(comma, key);
  }

  @Override
  public String toString() {
    return "YAML hash";
  }
}