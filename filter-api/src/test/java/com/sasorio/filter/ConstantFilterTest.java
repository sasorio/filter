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
import org.jspecify.annotations.NullMarked;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@NullMarked
class ConstantFilterTest {
  private static final int REPETITIONS = 100;

  @RepeatedTest(REPETITIONS)
  void testQueryAllowsEverything() {
    record Query() implements FilterQuery {
    }
    assertTrue(Filter.allow().allows(new Query()));
  }

  @RepeatedTest(REPETITIONS)
  void testQueryAbstainsEverything() {
    record Query() implements FilterQuery {
    }
    assertTrue(Filter.abstain().abstains(new Query()));
  }

  @RepeatedTest(REPETITIONS)
  void testQueryDeniesEverything() {
    record Query() implements FilterQuery {
    }
    assertTrue(Filter.deny().denies(new Query()));
  }

  @Test
  void testResponse() {
    assertEquals(FilterResponse.ALLOW, Filter.allow().response());
    assertEquals(FilterResponse.ABSTAIN, Filter.abstain().response());
    assertEquals(FilterResponse.DENY, Filter.deny().response());
  }

  @Test
  void testEquality() {
    new EqualsTester()
      .addEqualityGroup(
        Filter.allow(),
        Filter.always(FilterResponse.ALLOW)
      )
      .addEqualityGroup(
        Filter.abstain(),
        Filter.always(FilterResponse.ABSTAIN)
      )
      .addEqualityGroup(
        Filter.deny(),
        Filter.always(FilterResponse.DENY)
      )
      .testEquals();
  }
}
