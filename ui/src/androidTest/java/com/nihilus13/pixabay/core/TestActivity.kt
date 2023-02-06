package com.nihilus13.pixabay.core

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import dagger.internal.Preconditions

internal class TestActivity : AppCompatActivity()

internal inline fun <reified FRAGMENT : Fragment> launchFragmentInTestActivity(
    fragmentArgs: Bundle? = null,
    crossinline onFragment: Fragment.() -> Unit = {}
): Pair<ActivityScenario<TestActivity>, Fragment> {
    val startActivityIntent = Intent.makeMainActivity(
        ComponentName(
            ApplicationProvider.getApplicationContext(),
            TestActivity::class.java
        )
    )
    lateinit var fragment: Fragment
    val scenario = ActivityScenario.launch<TestActivity>(startActivityIntent)
    scenario.onActivity { activity ->
        fragment = activity.supportFragmentManager.fragmentFactory.instantiate(
            Preconditions.checkNotNull(FRAGMENT::class.java.classLoader!!),
            FRAGMENT::class.java.name
        )
        fragment.arguments = fragmentArgs
        fragment.viewLifecycleOwnerLiveData.observeForever {
            if (it != null) {
                Navigation.setViewNavController(fragment.requireView(), NavController(activity))
            }
        }
        activity.supportFragmentManager
            .beginTransaction()
            .add(android.R.id.content, fragment, "")
            .commitNow()
        fragment.onFragment()
    }
    return scenario to fragment
}