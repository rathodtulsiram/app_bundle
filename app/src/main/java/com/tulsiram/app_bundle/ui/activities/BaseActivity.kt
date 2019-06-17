/*
 * Copyright (c) 2019, Tulsiram Rathod.
 */

package com.tulsiram.app_bundle.ui.activities

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.tulsiram.app_bundle.BuildConfig

/**
 * abstract activity
 */
@SuppressLint("Registered")
abstract class BaseActivity : SplitCompatActivity()

/**
 * function to start activity
 *
 * @param clazz as end activity
 * @param finishSelf finish self
 */
fun BaseActivity.startActivity(
    clazz: Class<out BaseActivity>? = null,
    clazzName: String? = null,
    finishSelf: Boolean = false
) {
    when {
        clazz != null -> Intent(this, clazz)
        clazzName != null -> Intent().setClassName(BuildConfig.APPLICATION_ID, clazzName)
        else -> throw ActivityNotFoundException("Specify start activity by activity clazz or fully qualified clazzName")
    }.apply {
        startActivity(this)
    }

    if (finishSelf) this.finish()
}

/**
 * extension function to show toast
 *
 * @param msg message
 */
fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}