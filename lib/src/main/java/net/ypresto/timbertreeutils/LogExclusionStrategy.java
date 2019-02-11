/*
 * Copyright (C) 2015 Yuya Tanaka
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
package net.ypresto.timbertreeutils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface LogExclusionStrategy {
    /**
     * @param priority Log priority.
     * @param tag      Tag for log.
     * @param message  Formatted log message.
     * @param t        Accompanying exceptions.
     * @return {@code true} if the log should be skipped, otherwise {@code false}.
     * @see timber.log.Timber.Tree#log(int, String, String, Throwable)
     */
    boolean shouldSkipLog(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable t);
}
