package com.example.broadcastreceiver

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager

class MainActivity : AppCompatActivity() {
    var broadcast = InternetConnection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = IntentFilter()
//        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
//        filter.addAction(ConnectivityManager.EXTRA_NETWORK_REQUEST)
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
        filter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED)
        registerReceiver(broadcast, filter)


//        fun onDestroy(){
//            super.onDestroy()
//            unregisterReceiver(broadcast)
//        }
    }
}


/* Phân biệt 2 loại Broadcast Receiver
- Dynamic Broadcast Receiver:
    + Đăng ký tại runtime: được đăng ký và hủy đăng ký tại runtime bằng cách sử dụng phương thức registerReceiver() và unregisterReceiver() trong mã Java/Kotlin.
    + Chỉ hoạt động khi nếu ứng dụng đang hoạt động hoặc được thu nhỏ
    + Ưu điểm: linh hoạt, có thể thêm và xóa Broadcast Receiver tại bất kỳ thời điểm nào trong quá trình chạy ứng dụng.
    + Nhược điểm: tốn tài nguyên hệ thống, cần quản lý vòng đời của Receiver để tránh lãng phí tài nguyên và tiêu tốn pin thiết bị.

- Static Broadcast Receiver:
    + Đăng ký trong AndroidManifest.xml: Static Broadcast Receiver được đăng ký trong tệp AndroidManifest.xml của ứng dụng và không cần đăng ký hoặc hủy đăng ký tại runtime.
    + Hoạt động ngay cả khi ứng dụng bị đóng
    + Không linh hoạt: Không thể thêm hoặc xóa Receiver tại runtime, và nó hoạt động liên tục khi ứng dụng được cài đặt.
    + Hiệu suất tốt hơn: Static Receiver được kích hoạt bởi hệ thống khi sự kiện xảy ra, nên không cần tiêu tốn tài nguyên khi không hoạt động.
 */