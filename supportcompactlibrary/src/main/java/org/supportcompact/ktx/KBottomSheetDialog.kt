package org.supportcompact.ktx

import android.content.Context
import com.google.android.material.bottomsheet.BottomSheetDialog
import androidx.fragment.app.Fragment
import android.view.View

fun Fragment.bottomSheetDialog(init: BottomSheetDialogBuilder.() -> Unit): BottomSheetDialogBuilder? = activity?.bottomSheetDialog(init)

fun Context.bottomSheetDialog(init: BottomSheetDialogBuilder.() -> Unit) = BottomSheetDialogBuilder(this).apply(init)

class BottomSheetDialogBuilder(context: Context) {

    var dialog: BottomSheetDialog? = null

    init {
        dialog = BottomSheetDialog(context)
    }

    fun dismiss() = dialog?.dismiss()

    fun show(): BottomSheetDialogBuilder {
        dialog!!.show()
        return this
    }

    fun customView(view: View) {
        dialog?.setContentView(view)
    }

    fun cancelable(value: Boolean = true) {
        dialog?.setCancelable(value)
    }

    fun cancelableTouchOutside(value: Boolean = true) {
        dialog?.setCanceledOnTouchOutside(value)
    }

}