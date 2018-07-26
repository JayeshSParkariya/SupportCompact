package org.supportcompact.ktx

import android.arch.lifecycle.ViewModel
import org.greenrobot.eventbus.EventBus

const val SHOW_PROGRESS: Short = 1
const val DISMISS_PROGRESS: Short = 0

fun ViewModel.showProgress() = EventBus.getDefault().postSticky(SHOW_PROGRESS)

fun ViewModel.dismissProgress() = EventBus.getDefault().postSticky(DISMISS_PROGRESS)