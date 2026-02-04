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

import org.jspecify.annotations.NullMarked;

/**
 * Builtin filter types.
 *
 * @param name the stable identifier for this filter type
 * @param <F> the filter associated with this type
 */
@NullMarked
@SuppressWarnings("unused")
public record FilterType<F extends Filter>(String name) {
  /**
   * Filter type for {@link AllFilter}.
   *
   * @see AllFilter
   */
  public static final FilterType<AllFilter> ALL = new FilterType<>("all");
  /**
   * Filter type for {@link AnyFilter}.
   *
   * @see AnyFilter
   */
  public static final FilterType<AnyFilter> ANY = new FilterType<>("any");
  /**
   * Filter type for {@link ConstantFilter}.
   *
   * @see ConstantFilter
   */
  public static final FilterType<ConstantFilter> CONSTANT = new FilterType<>("constant");
  /**
   * Filter type for {@link NotFilter}.
   *
   * @see NotFilter
   */
  public static final FilterType<NotFilter> NOT = new FilterType<>("not");
  /**
   * Filter type for {@link OneFilter}.
   *
   * @see OneFilter
   */
  public static final FilterType<OneFilter> ONE = new FilterType<>("one");
}
