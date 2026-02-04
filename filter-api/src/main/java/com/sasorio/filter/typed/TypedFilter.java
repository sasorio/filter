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
package com.sasorio.filter.typed;

import com.sasorio.filter.Filter;
import com.sasorio.filter.FilterQuery;
import com.sasorio.filter.FilterResponse;
import org.jetbrains.annotations.ApiStatus.NonExtendable;
import org.jetbrains.annotations.ApiStatus.OverrideOnly;
import org.jspecify.annotations.NullMarked;

/**
 * A filter which accepts queries of type {@code Q}.
 *
 * <p>Calls to {@link #query(FilterQuery)} will return {@link FilterResponse#ABSTAIN} when
 * {@link #queryableWith(FilterQuery)} returns {@code false}. When it returns {@code true}, the query is cast to
 * {@code Q} and forwarded to {@link #typedQuery(FilterQuery)}.</p>
 *
 * @param <Q> the query type
 * @since 1.0.0
 */
@NullMarked
public interface TypedFilter<Q extends FilterQuery> extends Filter {
  /**
   * Tests if this filter accepts the query.
   *
   * @param query the query
   * @return {@code true} if this filter accepts the query, {@code false} otherwise
   * @since 1.0.0
   */
  boolean queryableWith(final FilterQuery query);

  @NonExtendable
  @Override
  default FilterResponse query(final FilterQuery query) {
    if (this.queryableWith(query)) {
      @SuppressWarnings("unchecked")
      final Q queryAsQ = (Q) query;
      return this.typedQuery(queryAsQ);
    }
    return FilterResponse.ABSTAIN;
  }

  /**
   * Query this filter for a response.
   *
   * @param query the query
   * @return the response
   * @since 1.0.0
   */
  @OverrideOnly
  FilterResponse typedQuery(final Q query);
}
