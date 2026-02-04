/*
 * Copyright 2024 Sasorio
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
/**
 * Typed filter helpers.
 *
 * <p>{@link com.sasorio.filter.typed.TypedFilter TypedFilter} and
 * {@link com.sasorio.filter.typed.StronglyTypedFilter StronglyTypedFilter} provide
 * typed query handling on top of the base {@link com.sasorio.filter.Filter Filter}
 * API, returning {@link com.sasorio.filter.FilterResponse#ABSTAIN ABSTAIN} when a
 * query is not supported.</p>
 */
package com.sasorio.filter.typed;
