package org.jetbrains.devkt.yaml.psi.impl;

import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.openapi.util.Pair;
import org.jetbrains.kotlin.com.intellij.openapi.util.TextRange;
import org.jetbrains.kotlin.com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.kotlin.com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.devkt.yaml.YAMLTokenTypes;
import org.jetbrains.devkt.yaml.YAMLUtil;
import org.jetbrains.devkt.yaml.psi.YAMLScalarList;

import java.util.ArrayList;
import java.util.List;

/**
 * @author oleg
 * @see <http://www.yaml.org/spec/1.2/spec.html#id2795688>
 */
public class YAMLScalarListImpl extends YAMLBlockScalarImpl implements YAMLScalarList {
  public YAMLScalarListImpl(@NotNull final ASTNode node) {
    super(node);
  }

  @NotNull
  @Override
  protected IElementType getContentType() {
    return YAMLTokenTypes.SCALAR_LIST;
  }

  @NotNull
  @Override
  public String getTextValue() {
    return super.getTextValue() + "\n";
  }

  @NotNull
  @Override
  protected String getRangesJoiner(@NotNull CharSequence text, @NotNull List<TextRange> contentRanges, int indexBefore) {
    return "\n";
  }

  @Override
  protected List<Pair<TextRange, String>> getEncodeReplacements(@NotNull CharSequence input) throws IllegalArgumentException {
    if (!StringUtil.endsWithChar(input, '\n')) {
      throw new IllegalArgumentException("Should end with a line break");
    }

    int indent = locateIndent();
    if (indent == 0) {
      indent = YAMLUtil.getIndentToThisElement(this) + DEFAULT_CONTENT_INDENT;
    }
    final String indentString = StringUtil.repeatSymbol(' ', indent);

    final List<Pair<TextRange, String>> result = new ArrayList<>();
    for (int i = 0; i < input.length(); ++i) {
      if (input.charAt(i) == '\n') {
        result.add(Pair.create(TextRange.from(i, 1), "\n" + indentString));
      }
    }

    return result;
  }

  @Override
  public String toString() {
    return "YAML scalar list";
  }
}