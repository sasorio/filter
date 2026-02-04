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
package com.sasorio.filter.test;

import com.sasorio.filter.FilterQuery;
import com.sasorio.filter.FilterType;
import com.sasorio.filter.typed.StronglyTypedFilter;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class TestFilters {
  @NullMarked
  interface Abstract extends StronglyTypedFilter<TestFilterQuery> {
    @Override
    default boolean queryableWith(final FilterQuery query) {
      return query instanceof TestFilterQuery;
    }
  }

  @NullMarked
  public record Equals(int value) implements Abstract {
    private static final FilterType<Equals> TYPE = new FilterType<>("equals");

    @Override
    public boolean queryResponse(final TestFilterQuery query) {
      return query.value() == this.value();
    }

    @Override
    public FilterType<Equals> type() {
      return TYPE;
    }
  }

  @NullMarked
  public record Below(int value) implements Abstract {
    private static final FilterType<Below> TYPE = new FilterType<>("below");

    @Override
    public boolean queryResponse(final TestFilterQuery query) {
      return query.value() < this.value();
    }

    @Override
    public FilterType<Below> type() {
      return TYPE;
    }
  }

  @NullMarked
  public record Above(int value) implements Abstract {
    private static final FilterType<Above> TYPE = new FilterType<>("above");

    @Override
    public boolean queryResponse(final TestFilterQuery query) {
      return query.value() > this.value();
    }

    @Override
    public FilterType<Above> type() {
      return TYPE;
    }
  }
}
