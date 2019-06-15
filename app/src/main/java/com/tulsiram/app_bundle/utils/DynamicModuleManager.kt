/*
 * Copyright (c) 2019, Tulsiram Rathod.
 */

package com.tulsiram.app_bundle.utils

import android.content.Context
import android.util.Log
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.google.android.play.core.tasks.OnFailureListener

/**
 * dynamic module manager is used to download and install feature module
 */
class DynamicModuleManager(context: Context) {

    private var sessionId: Int = 0
    private var installStateListener: SplitInstallStateUpdatedListener? = null
    private var splitInstallManager: SplitInstallManager = SplitInstallManagerFactory.create(context)

    enum class InstallStateUpdate {
        INSTALLED,
        FAILED,
        DOWNLOADING,
        INSTALLING
    }

    companion object {

        @Volatile
        private var INSTANCE: DynamicModuleManager? = null

        fun getInstance(context: Context): DynamicModuleManager =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: DynamicModuleManager(context).also { INSTANCE = it }
            }
    }

    /**
     * @return check if module is installed
     */
    fun isModuleInstalled(vararg moduleNames: String): Boolean {
        for (moduleName in moduleNames) {
            if (!splitInstallManager.installedModules.contains(moduleName)) {
                return false
            }
        }
        return true
    }

    /**
     * request for specific module install
     *
     * @param moduleNames as number of args
     */
    fun requestModuleInstall(vararg moduleNames: String, listener: (Exception) -> Unit) {
        val builder = SplitInstallRequest.newBuilder()
        for (moduleName in moduleNames) {
            builder.addModule(moduleName)
        }
        val request = builder.build()

        splitInstallManager.startInstall(request)
            .addOnFailureListener(object : OnFailureListener {
                override fun onFailure(e: Exception) {
                    Log.e(TAG, "Exception: $e")
                    listener(e)
                }
            })
            .addOnSuccessListener { sessionId ->
                this.sessionId = sessionId
            }
    }

    /**
     * register for state installStateListener
     *
     * @param listener state listener used to show progress or failure
     */
    fun registerInstallStateListener(listener: (InstallStateUpdate, Int, Int) -> Unit) {
        installStateListener = object : SplitInstallStateUpdatedListener {
            override fun onStateUpdate(splitInstallSessionState: SplitInstallSessionState) {
                if (splitInstallSessionState.sessionId() == sessionId) {
                    when (splitInstallSessionState.status()) {
                        SplitInstallSessionStatus.INSTALLED -> {
                            Log.d(TAG, "Dynamic Module downloaded")
                            listener(
                                InstallStateUpdate.INSTALLED, splitInstallSessionState.totalBytesToDownload().toInt(),
                                splitInstallSessionState.bytesDownloaded().toInt())
                        }
                        SplitInstallSessionStatus.FAILED -> {
                            Log.e(TAG, "Dynamic Module download failed")
                            listener(
                                InstallStateUpdate.FAILED, splitInstallSessionState.totalBytesToDownload().toInt(),
                                splitInstallSessionState.bytesDownloaded().toInt())
                        }
                        SplitInstallSessionStatus.INSTALLING -> {
                            listener(
                                InstallStateUpdate.INSTALLING, splitInstallSessionState.totalBytesToDownload().toInt(),
                                splitInstallSessionState.bytesDownloaded().toInt())
                        }
                        SplitInstallSessionStatus.DOWNLOADING -> {
                            listener(
                                InstallStateUpdate.DOWNLOADING, splitInstallSessionState.totalBytesToDownload().toInt(),
                                splitInstallSessionState.bytesDownloaded().toInt())
                        }
                    }
                }
            }
        }

        splitInstallManager.registerListener(installStateListener)

    }

    /**
     * unregister for state listener
     */
    fun unregisterInstallStateListener() {
        if (installStateListener != null) {
            splitInstallManager.unregisterListener(installStateListener)
        }
    }
}