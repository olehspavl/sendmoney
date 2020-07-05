package com.sendmoney.utils

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun <T : ViewBinding> Fragment.viewBinding(
    viewBindingFactory: (View) -> T
): ReadOnlyProperty<Fragment, T> = object : ReadOnlyProperty<Fragment, T>, LifecycleObserver {

    private var binding: T? = null
    private var viewLifecycleOwner: LifecycleOwner? = null

    init {
        this@viewBinding
            .viewLifecycleOwnerLiveData
            .observe(this@viewBinding, Observer { newOwner ->
                viewLifecycleOwner?.lifecycle?.removeObserver(this)
                viewLifecycleOwner = newOwner.also { it.lifecycle.addObserver(this) }
            })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        binding = null
    }

    override fun getValue(
        thisRef: Fragment,
        property: KProperty<*>
    ): T = binding.let { value ->
        when {
            value != null -> value
            lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED) ->
                viewBindingFactory(thisRef.requireView()).apply { binding = this }
            else -> throw IllegalStateException("Shouldn't attempt to get bindings at this point")
        }
    }
}