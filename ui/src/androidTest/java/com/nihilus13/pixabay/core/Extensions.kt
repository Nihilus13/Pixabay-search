package com.nihilus13.pixabay.core

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.karumi.shot.ScreenshotTest
import com.nihilus13.pixabay.common.StateObserver

internal inline fun <reified FRAGMENT : Fragment> ScreenshotTest.compareStateScreenshot(
    state: Any = Unit,
    forcedHeight: Int? = null,
    calcHeightWithIds: List<Int>? = null,
    fragmentArgs: Bundle? = null,
    testName: String? = null
) {
    StateObserver.testState = state
    val (scenario, fragment) = launchFragmentInTestActivity<FRAGMENT>(fragmentArgs)

    if (state != Unit && fragment !is StateObserver<*>) {
        Log.e(
            "ScreenshotTest",
            "${fragment::class.simpleName} doesn't implement ${StateObserver::class.java.simpleName}"
        )
    }
    val activity = fragment.requireActivity()
    scenario.moveToState(androidx.lifecycle.Lifecycle.State.RESUMED)

    activity.runOnUiThread { fragment.requireActivity().currentFocus?.clearFocus() }

    val height = forcedHeight ?: calcHeightWithIds?.fold(0) { sum, viewId ->
        sum + activity.findViewById<View>(viewId).measuredHeight
    }
    compareScreenshot(activity = activity, heightInPx = height, name = testName)
    StateObserver.testState = null
}