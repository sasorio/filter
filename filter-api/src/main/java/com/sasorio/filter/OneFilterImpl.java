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
import org.jspecify.annotations.NullMarked;

@NullMarked
record OneFilterImpl(List<Filter> filters) implements OneFilter {
  OneFilterImpl {
    filters = List.copyOf(filters);
  }

  @Override
  @SuppressWarnings("ForLoopReplaceableByForEach")
  public FilterResponse query(final FilterQuery query) {
    FilterResponse result = FilterResponse.ABSTAIN;
    final List<Filter> filters = this.filters;
    for (int i = 0, size = filters.size(); i < size; i++) {
      final FilterResponse response = filters.get(i).query(query);
      if (response == FilterResponse.ALLOW) {
        if (result == FilterResponse.ALLOW) {
          return FilterResponse.DENY;
        } else {
          result = response;
        }
      } else if (response == FilterResponse.DENY) {
        result = response;
      }
    }
    return result;
  }
}
