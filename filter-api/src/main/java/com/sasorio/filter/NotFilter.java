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
 * A filter that returns the inverse response of the {@link #filter() original filter}, as shown in the below table.
 *
 * <table>
 *   <caption>Inverse response mappings</caption>
 *   <thead>
 *     <tr>
 *       <th>Original Response</th>
 *       <th>Inverse Response</th>
 *     </tr>
 *   </thead>
 *   <tbody>
 *     <tr>
 *       <td>ALLOW</td>
 *       <td>DENY</td>
 *     </tr>
 *     <tr>
 *       <td>ABSTAIN</td>
 *       <td>ABSTAIN</td>
 *     </tr>
 *     <tr>
 *       <td>DENY</td>
 *       <td>ALLOW</td>
 *     </tr>
 *   </tbody>
 * </table>
 *
 * @see Filter#not(Filter)
 * @since 1.0.0
 */
@NullMarked
public sealed interface NotFilter extends Filter permits NotFilterImpl {
  /**
   * Gets the child filter.
   *
   * <p>The child filter should not be queried manually.</p>
   *
   * @return the child filter
   * @since 1.0.0
   */
  @Contract(pure = true)
  Filter filter();

  @Override
  default FilterType<NotFilter> type() {
    return FilterType.NOT;
  }
}
