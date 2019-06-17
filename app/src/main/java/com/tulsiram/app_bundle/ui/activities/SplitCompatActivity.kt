/*
 * Copyright (c) 2019, Tulsiram Rathod.
 */

package com.tulsiram.app_bundle.ui.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.splitcompat.SplitCompat

/**
 * split activity for Dynamic Delivery Module
 */
abstract class SplitCompatActivity : AppCompatActivity() {
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        SplitCompat.install(this)
    }
}