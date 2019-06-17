/*
 * Copyright (c) 2019, Tulsiram Rathod.
 */

package com.tulsiram.app_bundle.ui.fragments

import android.os.Bundle
import android.view.View
import com.tulsiram.app_bundle.R
import com.tulsiram.app_bundle.ui.activities.BaseActivity
import com.tulsiram.app_bundle.ui.activities.showToast
import com.tulsiram.app_bundle.ui.activities.startActivity
import com.tulsiram.app_bundle.utils.DynamicModuleManager
import com.tulsiram.app_bundle.utils.FEATURE_SIGN_UP_ACTIVITY
import com.tulsiram.app_bundle.utils.FEATURE_SIGN_UP_MODULE
import kotlinx.android.synthetic.main.fragment_login.*


/**
 * login fragment
 */
class LoginFragment : BaseFragment() {

    private var dynamicModuleManager: DynamicModuleManager? = null

    override fun getLayoutResId() = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogin.setOnClickListener { context?.showToast("Login Click.") }
        tvSignUp.setOnClickListener { performSignUp() }
    }

    /**
     * check for installed module if not exist then download and install
     */
    private fun performSignUp() {
        dynamicModuleManager = DynamicModuleManager.getInstance(context!!)

        if (dynamicModuleManager!!.isModuleInstalled(FEATURE_SIGN_UP_MODULE)) {
            (context as BaseActivity).startActivity(clazzName = FEATURE_SIGN_UP_ACTIVITY)
        } else {
            dynamicModuleManager!!.registerInstallStateListener { status, currentProgress, maxProgress ->
                when (status) {
                    DynamicModuleManager.InstallStateUpdate.DOWNLOADING ->
                        showProgress(getString(R.string.downloading), currentProgress, maxProgress)
                    DynamicModuleManager.InstallStateUpdate.INSTALLING ->
                        showProgress(getString(R.string.installing), currentProgress, maxProgress)
                    DynamicModuleManager.InstallStateUpdate.INSTALLED -> {
                        hideProgress()
                        context?.showToast("Feature install success.")
                        (context as BaseActivity).startActivity(clazzName = FEATURE_SIGN_UP_ACTIVITY)
                    }
                    DynamicModuleManager.InstallStateUpdate.FAILED -> {
                        hideProgress()
                        context?.showToast("Feature install failed.")
                    }
                }
            }

            dynamicModuleManager!!.requestModuleInstall(FEATURE_SIGN_UP_MODULE) {
                context?.showToast("Feature install failed!!")
                hideProgress()
            }
        }
    }

    /**
     * show download and install progress
     *
     * @param msg to be shown
     * @param currentProgress current progress
     * @param maxProgress maximum progress
     */
    private fun showProgress(msg: String, currentProgress: Int, maxProgress: Int) {
        tvSignUp.visibility = View.INVISIBLE
        groupProgress.visibility = View.VISIBLE

        tvProgress.text = msg
        progressBar.max = maxProgress
        progressBar.progress = currentProgress
    }

    /**
     * hide progress
     */
    private fun hideProgress() {
        tvSignUp.visibility = View.VISIBLE
        groupProgress.visibility = View.INVISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        dynamicModuleManager?.unregisterInstallStateListener()
    }
}