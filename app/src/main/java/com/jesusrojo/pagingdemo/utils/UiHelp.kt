package com.jesusrojo.pagingdemo.utils

import java.text.NumberFormat


fun formatNumber(number: Int?): String{
    var result = ""
    number?.let{
        val numberFormat = NumberFormat.getInstance()
        result = numberFormat.format(number)
    }
    return result
}
//fun convertTitle(title: String?) =
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            Html.fromHtml(title, Html.FROM_HTML_MODE_LEGACY).toString()
//        } else {
//            Html.fromHtml(title).toString()
//        }
//
//
//fun convertDate(timeStamp: Long?): String{
//    var time = ""
//    timeStamp?.let{
//        val cal = Calendar.getInstance()
//        cal.timeInMillis= timeStamp * 1000
//        time = DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString()
//    }
//    return time
//}