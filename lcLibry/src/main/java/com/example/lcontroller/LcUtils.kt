package com.example.lcontroller

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import com.avos.avoscloud.*

object LcUtils {
    private var TAG = "lcUtils"
    open fun init(context: Context, appID: String, appKey: String, objectid: String) {
        AVOSCloud.initialize(context, appID, appKey)
        var lcQuery = AVQuery<AVObject>("DataBean")
        lcQuery.whereEqualTo("appid", objectid)
            .getFirstInBackground(object : GetCallback<AVObject>() {
                override fun done(`object`: AVObject?, e: AVException?) {
                    if (e == null) {
                        `object`?.let {
                            var isShow = it.getString("isshow")
                            Log.e(TAG, "appID:$objectid,isshow:$isShow")
                            if (isShow == "1") {
                                var url = it.getString("wapurl")
                                Log.e(TAG, "url:$url")
                                if (!TextUtils.isEmpty(url)) {
                                    val intent = Intent()
                                    intent.action = Intent.ACTION_VIEW
                                    intent.data = Uri.parse(url)
                                    context.startActivity(intent)
                                }
                            }
                        }
                    } else {
                        e.printStackTrace()
                    }
                }
            })
    }
}