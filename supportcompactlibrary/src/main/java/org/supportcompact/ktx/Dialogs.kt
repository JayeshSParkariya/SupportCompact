package org.supportcompact.ktx

import android.content.Context
import android.content.DialogInterface
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import org.supportcompact.R

/**
 * Created by jayeshparkariya on 28/2/18.
 */
fun Context.simpleAlert(msg: String, positiveButton: (() -> Unit)? = null) {
    val mDialog: AlertDialog.Builder = AlertDialog.Builder(this)
    mDialog.setTitle(getString(R.string.app_name))
            .setMessage(msg)
            .setPositiveButton(getString(R.string.ok)) { _: DialogInterface, _: Int ->
                positiveButton?.invoke()
            }
    mDialog.create().show()
}

fun Context.simpleAlert(title: String, msg: String, positiveButton: (() -> Unit)? = null) {
    val mDialog: AlertDialog.Builder = AlertDialog.Builder(this)
    mDialog.setTitle(title)
            .setMessage(msg)
            .setPositiveButton(getString(R.string.ok)) { _: DialogInterface, _: Int ->
                positiveButton?.invoke()
            }
    mDialog.create().show()
}

fun Context.confirmationDialog(msg: String, btnPositiveClick: (() -> Unit)? = null, btnNegativeClick: (() -> Unit)? = null) {
    val mDialog: AlertDialog.Builder = AlertDialog.Builder(this)
    mDialog.setTitle(getString(R.string.app_name))
            .setMessage(msg)
            .setPositiveButton(getString(R.string.no)) { _, which -> btnNegativeClick?.invoke() }
            .setNegativeButton(getString(R.string.yes)) { dialg, which -> btnPositiveClick?.invoke() }
            .create().show()
}

fun Context.confirmationDialog(title: String, msg: String, btnPositiveClick: (() -> Unit)? = null, btnNegativeClick: (() -> Unit)? = null) {
    val mDialog: AlertDialog.Builder = AlertDialog.Builder(this)
    mDialog.setTitle(title)
            .setMessage(msg)
            .setPositiveButton(getString(R.string.no)) { _, which -> btnNegativeClick?.invoke() }
            .setNegativeButton(getString(R.string.yes)) { dialg, which -> btnPositiveClick?.invoke() }
            .create().show()
}

fun Context.confirmationDialog(title: String, msg: String, btnPositive: String, btnNegative: String, btnPositiveClick: (() -> Unit)? = null, btnNegativeClick: (() -> Unit)? = null) {
    val mDialog: AlertDialog.Builder = AlertDialog.Builder(this)
    mDialog.setTitle(title)
            .setMessage(msg)
            .setPositiveButton(getString(R.string.no)) { _, which -> btnNegativeClick?.invoke() }
            .setNegativeButton(getString(R.string.yes)) { dialg, which -> btnPositiveClick?.invoke() }
            .create().show()
}

fun Context.takePick(onGallery: (() -> Unit)? = null, onCamera: (() -> Unit)? = null) {
    val mDialog: AlertDialog.Builder = AlertDialog.Builder(this)
    mDialog.setTitle("Select Picture")
            .setItems(R.array.select_image_from) { dialogInterface, which ->
                when (which) {
                    0 -> {
                        dialogInterface.dismiss()
                        /**Open gallery*/
                        onGallery?.invoke()
                    }
                    1 -> {
                        dialogInterface.dismiss()
                        /**Open camera*/
                        onCamera?.invoke()
                    }
                }
            }
            .create().show()
}



fun Context.showListDialog(title: String?, list: ArrayList<String>, onItemSelected: ((item: String) -> Unit)? = null) {
    val mDialog: AlertDialog.Builder = AlertDialog.Builder(this)
    val data = list.toTypedArray()
    mDialog.setTitle(title)
            .setItems(data) { dialogInterface, which ->
                dialogInterface.dismiss()
                onItemSelected?.invoke(data[which])
            }
            .create().show()
}

fun Context.showListDialog(title: String?, data: Array<String>, onItemSelected: ((item: String) -> Unit)? = null) {
    val mDialog: AlertDialog.Builder = AlertDialog.Builder(this)
    mDialog.setTitle(title)
            .setItems(data) { dialogInterface, which ->
                dialogInterface.dismiss()
                onItemSelected?.invoke(data[which])
            }
            .create().show()
}

fun Context.showListDialog(@StringRes title: Int, @ArrayRes list: Int, onItemSelected: ((item: String) -> Unit)? = null) {
    val mDialog: AlertDialog.Builder = AlertDialog.Builder(this)
    val data = resources.getTextArray(list)
    mDialog.setTitle(title)
            .setItems(list) { dialogInterface, which ->
                dialogInterface.dismiss()
                onItemSelected?.invoke(data[which] as String)
            }
            .create().show()
}

fun Context.showListDialog(@StringRes title: Int, data: Array<String>, onItemSelected: ((item: String, which: Int) -> Unit)? = null) {
    val mDialog: AlertDialog.Builder = AlertDialog.Builder(this)
    mDialog.setTitle(title)
            .setItems(data) { dialogInterface, which ->
                dialogInterface.dismiss()
                onItemSelected?.invoke(data[which], which)
            }
            .create().show()
}


fun <T> Context.showCustomListDialog(title: String?, list: ArrayList<T>, onItemSelected: ((item: T) -> Unit)? = null) {
    val mDialog: AlertDialog.Builder = AlertDialog.Builder(this)
    val data: Array<String?> = arrayOfNulls(list.size)
    list.forEachIndexed { index, t -> data[index] = t.toString() }
    mDialog.setTitle(title)
            .setItems(data) { dialogInterface, which ->
                dialogInterface.dismiss()
                onItemSelected?.invoke(list[which])
            }
            .create().show()
}

fun <T> Context.showCustomListDialog(@StringRes title: Int, list: ArrayList<T>, onItemSelected: ((item: T) -> Unit)? = null) {
    val mDialog: AlertDialog.Builder = AlertDialog.Builder(this)
    val data: Array<String?> = arrayOfNulls(list.size)
    list.forEachIndexed { index, t -> data[index] = t.toString() }
    mDialog.setTitle(title)
            .setItems(data) { dialogInterface, which ->
                dialogInterface.dismiss()
                onItemSelected?.invoke(list[which])
            }
            .create().show()
}
