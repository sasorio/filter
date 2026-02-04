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

import java.util.List;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

/**
 * A filter that combines child responses with "exactly one may allow" semantics.
 *
 * <p>Returns {@link FilterResponse#ALLOW} when exactly one child allows and none deny. Returns
 * {@link FilterResponse#DENY} if any child denies or if more than one child allows. Returns
 * {@link FilterResponse#ABSTAIN} only when every child abstains (including when the child list is empty).</p>
 *
 * @see Filter#one(Filter...)
 * @see Filter#one(List)
 * @since 1.0.0
 */
@NullMarked
public sealed interface OneFilter extends Filter permits OneFilterImpl {
  /**
   * Gets the child filters.
   *
   * <p>The child filters should not be queried manually.</p>
   *
   * @return the child filters
   * @since 1.0.0
   */
  @Contract(pure = true)
  @Unmodifiable
  List<Filter> filters();

  @Override
  default FilterType<OneFilter> type() {
    return FilterType.ONE;
  }
}
