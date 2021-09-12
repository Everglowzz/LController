package com.example.lcontroller
import android.content.Context
import android.content.Intent
import com.avos.avoscloud.*

object  LcUtils {
  open  fun init(context: Context,appID:String,appKey:String,objectid:String){
      AVOSCloud.initialize(context,appID,appKey)
        var lcQuery =  AVQuery<AVObject>("DataBean")
      lcQuery.whereEqualTo("appid",objectid).getFirstInBackground(object :GetCallback<AVObject>(){
          override fun done(`object`: AVObject?, e: AVException?) {
              if (e==null) {
                  `object`?.let {
                      var isShow =   it.getString("isshow")
                      if (isShow == "1"){
                          var url = it.getString("wapurl")
                          val intent = Intent(context, LcActivity::class.java)
                          intent.putExtra("url", url)
                          context.startActivity(intent)
                      }
                  }
              }
          }
      })
    }
}