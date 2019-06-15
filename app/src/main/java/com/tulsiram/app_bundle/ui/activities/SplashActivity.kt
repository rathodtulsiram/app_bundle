/*
 * Copyright (c) 2019, Tulsiram Rathod.
 */

package com.tulsiram.app_bundle.ui.activities

import android.os.Bundle

/**
 * show splash screen
 */
class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(clazz =  LoginActivity::class.java, finishSelf = true)
    }
}
