package com.example.chaljabhai

import android.app.Activity
import android.app.AlertDialog

class loadingDialog (val mAcitvity:Activity){

    private  lateinit var isdialog:AlertDialog
    fun startLoadin(){
        val inflater = mAcitvity.layoutInflater
        val dialogView= inflater.inflate(R.layout.loading_bar,null)
        val builder= AlertDialog.Builder(mAcitvity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        isdialog= builder.create()
        isdialog.show()
    }
    fun isDismiss()
    {
        isdialog.dismiss()
    }

}