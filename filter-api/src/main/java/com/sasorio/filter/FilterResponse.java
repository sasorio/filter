/*
 * Copyright 2018 Sasorio
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sasorio.filter;

import java.util.function.BooleanSupplier;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * A response from querying a {@link Filter}.
 *
 * @since 1.0.0
 */
@NullMarked
public enum FilterResponse {
  /**
   * The filter allows the query.
   *
   * @since 1.0.0
   */
  ALLOW,
  /**
   * The filter abstains from responding to the query.
   *
   * @since 1.0.0
   */
  ABSTAIN,
  /**
   * The filter denies the query.
   *
   * @since 1.0.0
   */
  DENY;

  /**
   * Converts a {@code boolean} into a response.
   *
   * @param value the boolean
   * @return the response
   * @since 1.0.0
   */
  @Contract(pure = true)
  public static FilterResponse fromBoolean(final boolean value) {
    return value ? ALLOW : DENY;
  }

  /**
   * Converts a {@link Boolean} into a response.
   *
   * @param value the boolean
   * @return the response
   * @since 1.0.0
   */
  @Contract(pure = true)
  public static FilterResponse fromBoolean(final @Nullable Boolean value) {
    return value == null ? ABSTAIN : fromBoolean(value.booleanValue());
  }

  /**
   * Converts to a {@code boolean}, supplying from {@code abstain} when the response is {@link #ABSTAIN}.
   *
   * @param abstain the response supplier when the response is {@link #ABSTAIN}
   * @return a {@code boolean}
   * @since 1.0.0
   */
  public boolean toBoolean(final BooleanSupplier abstain) {
    return switch (this) {
      case ALLOW -> true;
      case ABSTAIN -> abstain.getAsBoolean();
      case DENY -> false;
    };
  }
}
