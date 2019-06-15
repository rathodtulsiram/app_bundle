/*
 * Copyright (c) 2019, Tulsiram Rathod.
 */

package com.tulsiram.app_bundle.utils

/**
 * @return tag as a class name of current class
 */
val Any.TAG: String get() = javaClass.simpleName

/**
 * @return sign up feature module name
 */
const val FEATURE_SIGN_UP_MODULE = "feature_sign_up"

/**
 * @return sign up screen path
 */
const val FEATURE_SIGN_UP_ACTIVITY = "com.tulsiram.app_bundle.feature_sign_up.ui.activities.SignUpActivity"

