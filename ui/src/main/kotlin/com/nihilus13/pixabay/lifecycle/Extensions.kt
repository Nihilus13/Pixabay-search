package com.nihilus13.pixabay.lifecycle

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T> Fragment.viewBinding(): ReadOnlyProperty<Fragment, T> =
    object : ReadOnlyProperty<Fragment, T>, DefaultLifecycleObserver {

        private var binding: T? = null

        private var viewLifeCycleOwner: LifecycleOwner? = null

        init {
            this@viewBinding
                .viewLifecycleOwnerLiveData
                .observe(this@viewBinding, ::registerLifecycleObserver)

        }

        private fun registerLifecycleObserver(newViewLifeCycleOwner: LifecycleOwner) {
            viewLifeCycleOwner?.lifecycle?.removeObserver(this)
            viewLifeCycleOwner = newViewLifeCycleOwner.also { it.lifecycle.addObserver(this) }
        }

        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            binding = null
        }

        override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
            if (binding == null) createBinding()
            return binding!!
        }

        private fun createBinding() {
            val bind = T::class.java.getMethod("bind", View::class.java)
            binding = bind.invoke(null, this@viewBinding.requireView()) as T
        }
    }

inline fun <reified T : ViewBinding> AppCompatActivity.viewBinding() =
    object : ReadOnlyProperty<AppCompatActivity, T> {

        private var binding: T? = null

        private fun createBinding() {
            val bind = T::class.java.getMethod("bind", View::class.java)
            binding =
                bind.invoke(null, findViewById<ViewGroup>(android.R.id.content).getChildAt(0)) as T
        }

        override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
            if (binding == null) {
                createBinding()
            }
            return binding!!
        }
    }

inline fun <reified T> SavedStateHandle.getSavedStateLiveData(initialValue: T): MutableLiveData<T> =
    getLiveData(T::class.java.name, initialValue)
