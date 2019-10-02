package org.supportcompact

import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableField
import android.view.View


open class ActivityViewModel : ViewModel() {

    val progressBar = ObservableField(View.GONE)

}