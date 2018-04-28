package org.jetbrains.devkt.yaml.psi;

import org.jetbrains.kotlin.com.intellij.psi.PsiDirectory;
import org.jetbrains.kotlin.com.intellij.psi.PsiElement;
import org.jetbrains.kotlin.com.intellij.psi.PsiFile;
import org.jetbrains.kotlin.com.intellij.psi.PsiManager;
import org.jetbrains.kotlin.com.intellij.psi.impl.PsiTreeChangeEventImpl;
import org.jetbrains.kotlin.com.intellij.psi.impl.PsiTreeChangePreprocessorBase;
import org.jetbrains.annotations.NotNull;

/**
 * @author oleg
 */
final class YAMLPsiManager extends PsiTreeChangePreprocessorBase {
  public YAMLPsiManager(@NotNull PsiManager psiManager) {
    super(psiManager);
  }

  @Override
  protected boolean acceptsEvent(@NotNull PsiTreeChangeEventImpl event) {
    return event.getFile() instanceof YAMLFile;
  }

  @Override
  protected boolean isOutOfCodeBlock(@NotNull PsiElement element) {
    while (true) {
      if (element instanceof YAMLFile) {
        return true;
      }
      if (element instanceof PsiFile || element instanceof PsiDirectory) {
        return false;
      }
      PsiElement parent = element.getParent();
      if (!(parent instanceof YAMLFile ||
            parent instanceof YAMLKeyValue ||
            parent instanceof YAMLCompoundValue ||
            parent instanceof YAMLDocument)) {
        return false;
      }
      element = parent;
    }
  }

}
