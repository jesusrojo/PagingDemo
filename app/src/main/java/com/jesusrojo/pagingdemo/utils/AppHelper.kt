package com.jesusrojo.pagingdemo.utils

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import android.content.DialogInterface

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.jesusrojo.pagingdemo.R


object AppHelper {

    fun showErrorSnackBar(view: View, context: Context, msg: String) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).apply {
            setBackgroundTint(ContextCompat.getColor(context, R.color.purple_700))
            setTextColor(ContextCompat.getColor(context, R.color.white))
            setActionTextColor(ContextCompat.getColor(context, R.color.white))
//            setAction(R.string.close) {
//                dismiss()
//            }
            anchorView = view // otherwise might be hiden for bottom menu
        }.show()
    }

    fun showAlertDetails(activity: AppCompatActivity, message: String) {
        val alertDialog: AlertDialog.Builder =  AlertDialog.Builder(activity)
        alertDialog.setTitle(R.string.details)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton(R.string.close,
            DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
        alertDialog.show()
    }
}