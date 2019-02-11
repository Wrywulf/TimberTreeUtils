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

import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import timber.log.Timber;

/**
 * An implementation of {@link Timber.Tree} which throws {@link java.lang.Error} when priority of
 * log is exceeded the limit. Useful for development or test environment.
 *
 * @author ypresto
 */
public class ThrowErrorTree extends Timber.Tree {
    private final int mLogPriority;
    private final LogExclusionStrategy mLogExclusionStrategy;

    /**
     * Create instance with default log priority of ERROR.
     */
    public ThrowErrorTree() {
        this(Log.ERROR);
    }

    /**
     * @param logPriority Minimum log priority to throw error. Expects one of constants defined in {@link Log}.
     */
    public ThrowErrorTree(int logPriority) {
        this(logPriority, null);
    }

    /**
     * @param logPriority          Minimum log priority to throw error. Expects one of constants defined in {@link Log}.
     * @param logExclusionStrategy Strategy used to skip throwing error.
     */
    public ThrowErrorTree(int logPriority, @Nullable LogExclusionStrategy logExclusionStrategy) {
        mLogPriority = logPriority;
        mLogExclusionStrategy = logExclusionStrategy != null ? logExclusionStrategy : NullLogExclusionStrategy.INSTANCE;
    }

    @Override
    protected boolean isLoggable(int priority) {
        return priority >= mLogPriority;
    }

    @Override
    protected void log(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable t) {
        if (mLogExclusionStrategy.shouldSkipLog(priority, tag, message, t)) {
            return;
        }

        if (t != null) {
            throw new LogPriorityExceededError(priority, mLogPriority, t);
        } else {
            throw new LogPriorityExceededError(priority, mLogPriority);
        }
    }

}
