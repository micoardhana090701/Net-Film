package com.bluxtech.netfilm.core.util

import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

class AppExecutor @VisibleForTesting constructor(
    private val diskIO: Executor
) {
    companion object;

    @Inject
    constructor() : this(
        Executors.newSingleThreadExecutor()
    )

    fun diskIO(): Executor = diskIO

}