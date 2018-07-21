package io.pjhjohn.calculator

import org.junit.Rule
import org.junit.rules.TestWatcher
import org.junit.runner.Description

open class UnitTestWatcher {

    @Rule
    @JvmField
    val watcher = object : TestWatcher() {
        override fun starting(description: Description) {
            println("- Running ${description.methodName} ...")
        }

        override fun succeeded(description: Description) {
            println("> Result : Success\n")
        }
    }
}
