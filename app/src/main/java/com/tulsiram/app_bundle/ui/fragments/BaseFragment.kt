/*
 * Copyright (c) 2019, Tulsiram Rathod.
 */

package com.tulsiram.app_bundle.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * base fragment for application
 */
abstract class BaseFragment : Fragment() {
    /**
     * @return resource id of fragment layout
     */
    abstract fun getLayoutResId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResId(), container, false)
    }
}