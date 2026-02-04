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

@NullMarked
record ConstantFilterImpl(FilterResponse response) implements ConstantFilter {
  static final ConstantFilterImpl ALLOW = new ConstantFilterImpl(FilterResponse.ALLOW);
  static final ConstantFilterImpl ABSTAIN = new ConstantFilterImpl(FilterResponse.ABSTAIN);
  static final ConstantFilterImpl DENY = new ConstantFilterImpl(FilterResponse.DENY);

  @Override
  public FilterResponse query(final FilterQuery query) {
    return this.response;
  }
}
