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

import com.google.common.testing.EqualsTester;
import com.sasorio.filter.test.TestFilterQuery;
import com.sasorio.filter.test.TestFilters;
import java.util.List;
import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@NullMarked
class OneFilterTest {
  @Test
  void testUnboxOriginalFilters() {
    final Filter f0 = new TestFilters.Equals(0);
    final Filter f1 = new TestFilters.Equals(1);
    final OneFilter filter = Filter.one(f0, f1);
    assertIterableEquals(List.of(f0, f1), filter.filters());
  }

  @Test
  void testQuerySimple() {
    final Filter filter = Filter.one(
      new TestFilters.Equals(10),
      new TestFilters.Equals(10)
    );
    assertFalse(filter.allows(new TestFilterQuery(10)));
    assertFalse(filter.allows(new TestFilterQuery(20)));
  }

  @Test
  void testQueryComplex() {
    final Filter filter = Filter.one(
      new TestFilters.Above(9),
      new TestFilters.Equals(10),
      new TestFilters.Below(11)
    );
    assertTrue(filter.allows(new TestFilterQuery(9)));
    assertFalse(filter.allows(new TestFilterQuery(10)));
    assertFalse(filter.allows(new TestFilterQuery(11)));
  }

  @Test
  void testEquality() {
    final TestFilters.Equals f0 = new TestFilters.Equals(0);
    final TestFilters.Equals f1 = new TestFilters.Equals(1);
    final TestFilters.Equals f2 = new TestFilters.Equals(2);
    new EqualsTester()
      .addEqualityGroup(
        Filter.one(f0, f1),
        Filter.one(List.of(f0, f1))
      )
      .addEqualityGroup(
        Filter.one(f1, f2),
        Filter.one(List.of(f1, f2))
      )
      .testEquals();
  }
}
