package org.jetbrains.devkt.yaml.psi;

import org.jetbrains.kotlin.com.intellij.psi.PsiFile;

import java.util.List;

/**
 * @author oleg
 */
public interface YAMLFile extends PsiFile, YAMLPsiElement {
  List<YAMLDocument> getDocuments();
}
