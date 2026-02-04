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
import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@NullMarked
class NotFilterTest {
  @Test
  void testUnboxOriginalFilter() {
    final Filter original = new TestFilters.Equals(20);
    final NotFilter filter = Filter.not(original);
    assertSame(original, filter.filter());
  }

  @Test
  void testQuery() {
    final Filter filter = Filter.not(
      new TestFilters.Equals(20)
    );
    assertTrue(filter.allows(new TestFilterQuery(10)));
    assertTrue(filter.allows(new TestFilterQuery(15)));
    assertTrue(filter.denies(new TestFilterQuery(20)));
  }

  @Test
  void testEquality() {
    final TestFilters.Equals f0 = new TestFilters.Equals(0);
    final TestFilters.Equals f1 = new TestFilters.Equals(1);
    new EqualsTester()
      .addEqualityGroup(
        Filter.not(f0)
      )
      .addEqualityGroup(
        Filter.not(f1)
      )
      .testEquals();
  }
}
