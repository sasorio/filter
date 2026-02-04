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

import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * A filter that always returns the same response for every query.
 *
 * @see Filter#always(FilterResponse)
 * @see Filter#allow()
 * @see Filter#deny()
 * @see Filter#abstain()
 * @since 1.0.0
 */
@NullMarked
public sealed interface ConstantFilter extends Filter permits ConstantFilterImpl {
  /**
   * Gets the response this filter returns for every query.
   *
   * @return the response
   * @since 1.0.0
   */
  @Contract(pure = true)
  FilterResponse response();

  @Override
  default FilterType<ConstantFilter> type() {
    return FilterType.CONSTANT;
  }
}
