package org.supportcompact

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.view.View


open class ActivityViewModel : ViewModel() {

    val progressBar = ObservableField(View.GONE)

}