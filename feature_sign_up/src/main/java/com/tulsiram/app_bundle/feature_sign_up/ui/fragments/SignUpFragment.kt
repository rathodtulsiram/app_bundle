/*
 * Copyright (c) 2019, Tulsiram Rathod.
 */

package com.tulsiram.app_bundle.feature_sign_up.ui.fragments

import android.os.Bundle
import android.view.View
import com.tulsiram.app_bundle.feature_sign_up.R
import com.tulsiram.app_bundle.ui.activities.showToast
import com.tulsiram.app_bundle.ui.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_sign_up.*

/**
 * sign up fragment to handle sign up flow
 */
class SignUpFragment : BaseFragment() {

    override fun getLayoutResId(): Int  = R.layout.fragment_sign_up

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSignUp.setOnClickListener { context?.showToast("Perform Validation & Sign up from here.") }
    }
}