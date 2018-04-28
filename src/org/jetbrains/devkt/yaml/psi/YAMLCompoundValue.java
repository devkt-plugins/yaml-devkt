package org.jetbrains.devkt.yaml.psi;

import org.jetbrains.annotations.NotNull;

/**
 * @author oleg
 */
public interface YAMLCompoundValue extends YAMLValue {
  @NotNull
  String getTextValue();
}