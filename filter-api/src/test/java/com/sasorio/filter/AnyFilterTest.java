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

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@NullMarked
class AnyFilterTest {
  @Test
  void testUnboxOriginalFilters() {
    final Filter f0 = new TestFilters.Equals(0);
    final Filter f1 = new TestFilters.Equals(1);
    final AnyFilter filter = Filter.any(f0, f1);
    assertIterableEquals(List.of(f0, f1), filter.filters());
  }

  @Test
  void testQuerySimple() {
    final Filter filter = Filter.any(
      new TestFilters.Equals(10),
      new TestFilters.Equals(20)
    );
    assertTrue(filter.allows(new TestFilterQuery(10)));
    assertTrue(filter.denies(new TestFilterQuery(15)));
    assertTrue(filter.allows(new TestFilterQuery(20)));
  }

  @Test
  void testQueryComplex() {
    final Filter filter = Filter.any(
      new TestFilters.Equals(6),
      new TestFilters.Above(9)
    );
    assertTrue(filter.allows(new TestFilterQuery(6)));
    assertTrue(filter.allows(new TestFilterQuery(10)));
  }

  @Test
  void testEquality() {
    final TestFilters.Equals f0 = new TestFilters.Equals(0);
    final TestFilters.Equals f1 = new TestFilters.Equals(1);
    final TestFilters.Equals f2 = new TestFilters.Equals(2);
    new EqualsTester()
      .addEqualityGroup(
        Filter.any(f0, f1),
        Filter.any(List.of(f0, f1))
      )
      .addEqualityGroup(
        Filter.any(f1, f2),
        Filter.any(List.of(f1, f2))
      )
      .testEquals();
  }
}
