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
import org.jetbrains.annotations.ApiStatus.NonExtendable;
import org.jetbrains.annotations.CheckReturnValue;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * A filter is something that can respond with either "yes", "no", or "don't care" to a question.
 *
 * <p>The response is represented by {@link FilterResponse} and is queried using
 * {@link #query(FilterQuery)} or the convenience methods {@link #allows(FilterQueryLike)},
 * {@link #abstains(FilterQueryLike)}, and {@link #denies(FilterQueryLike)}.</p>
 *
 * @see AllFilter
 * @see AnyFilter
 * @see ConstantFilter
 * @see NotFilter
 * @see OneFilter
 * @since 1.0.0
 */
// @FunctionalInterface
@NullMarked
public interface Filter {
  /**
   * Creates filter that responds with {@link FilterResponse#ALLOW} if all of its children also respond with {@link FilterResponse#ALLOW}.
   *
   * @param filter1 the 1st filter
   * @param filter2 the 2nd filter
   * @return an {@link AllFilter}
   * @since 1.0.0
   */
  @Contract(pure = true)
  static AllFilter all(final Filter filter1, final Filter filter2) {
    return all(List.of(filter1, filter2));
  }

  /**
   * Creates filter that responds with {@link FilterResponse#ALLOW} if all of its children also respond with {@link FilterResponse#ALLOW}.
   *
   * @param filter1 the 1st filter
   * @param filter2 the 2nd filter
   * @param filter3 the 3rd filter
   * @return an {@link AllFilter}
   * @since 1.0.0
   */
  @Contract(pure = true)
  static AllFilter all(final Filter filter1, final Filter filter2, final Filter filter3) {
    return all(List.of(filter1, filter2, filter3));
  }

  /**
   * Creates filter that responds with {@link FilterResponse#ALLOW} if all of its children also respond with {@link FilterResponse#ALLOW}.
   *
   * @param filters the filters
   * @return an {@link AllFilter}
   * @since 1.0.0
   */
  @Contract(pure = true)
  static AllFilter all(final Filter... filters) {
    return all(List.of(filters));
  }

  /**
   * Creates a filter that responds with {@link FilterResponse#ALLOW} if all of its children also respond with {@link FilterResponse#ALLOW}.
   *
   * @param filters the filters
   * @return an {@link AllFilter}
   * @since 1.0.0
   */
  @Contract(pure = true)
  static AllFilter all(final List<Filter> filters) {
    return new AllFilterImpl(filters);
  }

  /**
   * Creates a filter that responds with {@link FilterResponse#ALLOW} if any of its children respond with {@link FilterResponse#ALLOW}.
   *
   * @param filter1 the 1st filter
   * @param filter2 the 2nd filter
   * @return an {@link AnyFilter}
   * @since 1.0.0
   */
  @Contract(pure = true)
  static AnyFilter any(final Filter filter1, final Filter filter2) {
    return any(List.of(filter1, filter2));
  }

  /**
   * Creates a filter that responds with {@link FilterResponse#ALLOW} if any of its children respond with {@link FilterResponse#ALLOW}.
   *
   * @param filter1 the 1st filter
   * @param filter2 the 2nd filter
   * @param filter3 the 3rd filter
   * @return an {@link AnyFilter}
   * @since 1.0.0
   */
  @Contract(pure = true)
  static AnyFilter any(final Filter filter1, final Filter filter2, final Filter filter3) {
    return any(List.of(filter1, filter2, filter3));
  }

  /**
   * Creates a filter that responds with {@link FilterResponse#ALLOW} if any of its children respond with {@link FilterResponse#ALLOW}.
   *
   * @param filters the filters
   * @return an {@link AnyFilter}
   * @since 1.0.0
   */
  @Contract(pure = true)
  static AnyFilter any(final Filter... filters) {
    return any(List.of(filters));
  }

  /**
   * Creates a filter that responds with {@link FilterResponse#ALLOW} if any of its children respond with {@link FilterResponse#ALLOW}.
   *
   * @param filters the filters
   * @return an {@link AnyFilter}
   * @since 1.0.0
   */
  @Contract(pure = true)
  static AnyFilter any(final List<Filter> filters) {
    return new AnyFilterImpl(filters);
  }

  /**
   * Creates a filter that returns the inverse response.
   *
   * @param filter a filter
   * @return a {@link NotFilter}
   * @since 1.0.0
   */
  @Contract(pure = true)
  static NotFilter not(final Filter filter) {
    return new NotFilterImpl(filter);
  }

  /**
   * Creates a filter that responds with {@link FilterResponse#ALLOW} if only one of its children respond with {@link FilterResponse#ALLOW}.
   *
   * @param filter1 the 1st filter
   * @param filter2 the 2nd filter
   * @return a {@link OneFilter}
   * @since 1.0.0
   */
  @Contract(pure = true)
  static OneFilter one(final Filter filter1, final Filter filter2) {
    return one(List.of(filter1, filter2));
  }

  /**
   * Creates a filter that responds with {@link FilterResponse#ALLOW} if only one of its children respond with {@link FilterResponse#ALLOW}.
   *
   * @param filter1 the 1st filter
   * @param filter2 the 2nd filter
   * @param filter3 the 3rd filter
   * @return a {@link OneFilter}
   * @since 1.0.0
   */
  @Contract(pure = true)
  static OneFilter one(final Filter filter1, final Filter filter2, final Filter filter3) {
    return one(List.of(filter1, filter2, filter3));
  }

  /**
   * Creates a filter that responds with {@link FilterResponse#ALLOW} if only one of its children respond with {@link FilterResponse#ALLOW}.
   *
   * @param filters the filters
   * @return a {@link OneFilter}
   * @since 1.0.0
   */
  @Contract(pure = true)
  static OneFilter one(final Filter... filters) {
    return one(List.of(filters));
  }

  /**
   * Creates a filter that responds with {@link FilterResponse#ALLOW} if only one of its children respond with {@link FilterResponse#ALLOW}.
   *
   * @param filters the filters
   * @return a {@link OneFilter}
   * @since 1.0.0
   */
  @Contract(pure = true)
  static OneFilter one(final List<Filter> filters) {
    return new OneFilterImpl(filters);
  }

  /**
   * Gets a filter that always responds with {@code response}.
   *
   * @param response the response to always respond with
   * @return a {@link ConstantFilter}
   * @since 1.0.0
   */
  @Contract(pure = true)
  static ConstantFilter always(final FilterResponse response) {
    return switch (response) {
      case ALLOW -> allow();
      case ABSTAIN -> abstain();
      case DENY -> deny();
    };
  }

  /**
   * Gets a filter that always responds with {@link FilterResponse#ALLOW}.
   *
   * @return a {@link ConstantFilter}
   * @since 1.0.0
   */
  @Contract(pure = true)
  static ConstantFilter allow() {
    return ConstantFilterImpl.ALLOW;
  }

  /**
   * Gets a filter that always responds with {@link FilterResponse#ABSTAIN}.
   *
   * @return a {@link ConstantFilter}
   * @since 1.0.0
   */
  @Contract(pure = true)
  static ConstantFilter abstain() {
    return ConstantFilterImpl.ABSTAIN;
  }

  /**
   * Gets a filter that always responds with {@link FilterResponse#DENY}.
   *
   * @return a {@link ConstantFilter}
   * @since 1.0.0
   */
  @Contract(pure = true)
  static ConstantFilter deny() {
    return ConstantFilterImpl.DENY;
  }

  /**
   * Query this filter for a response.
   *
   * @param query the query
   * @return the response
   * @since 1.0.0
   */
  @CheckReturnValue
  FilterResponse query(final FilterQuery query);

  /**
   * Query this filter for a response.
   *
   * @param query the query
   * @return the response
   * @since 1.0.0
   */
  @CheckReturnValue
  @NonExtendable
  default FilterResponse query(final FilterQueryLike query) {
    return this.query(query.asFilterQuery());
  }

  /**
   * Query this filter and return {@code true} if the response is {@link FilterResponse#ALLOW}, and {@code false} otherwise.
   *
   * @param query the query
   * @return {@code true} if allowed, {@code false} otherwise
   * @since 1.0.0
   */
  @CheckReturnValue
  @NonExtendable
  default boolean allows(final FilterQueryLike query) {
    return this.query(query) == FilterResponse.ALLOW;
  }

  /**
   * Query this filter and return {@code true} if the response is {@link FilterResponse#ABSTAIN}, and {@code false} otherwise.
   *
   * @param query the query
   * @return {@code true} if abstained, {@code false} otherwise
   * @since 1.0.0
   */
  @CheckReturnValue
  @NonExtendable
  default boolean abstains(final FilterQueryLike query) {
    return this.query(query) == FilterResponse.ABSTAIN;
  }

  /**
   * Query this filter and return {@code true} if the response is {@link FilterResponse#DENY}, and {@code false} otherwise.
   *
   * @param query the query
   * @return {@code true} if denied, {@code false} otherwise
   * @since 1.0.0
   */
  @CheckReturnValue
  @NonExtendable
  default boolean denies(final FilterQueryLike query) {
    return this.query(query) == FilterResponse.DENY;
  }

  /**
   * Gets the type identifier for this filter.
   *
   * @return the filter type
   * @since 1.0.0
   */
  @Contract(pure = true)
  FilterType<? extends Filter> type();
}
