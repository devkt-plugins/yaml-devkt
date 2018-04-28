package org.jetbrains.devkt.yaml;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.kotlin.com.intellij.psi.tree.IElementType;

/**
 * @author oleg
 */
public class YAMLElementType extends IElementType {
	public YAMLElementType(@NonNls String debugName) {
		super(debugName, YAMLLanguage.INSTANCE);
	}
}
