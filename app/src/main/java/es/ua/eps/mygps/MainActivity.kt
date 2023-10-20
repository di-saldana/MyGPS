package es.ua.eps.mygps

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.telephony.TelephonyManager
import android.telephony.gsm.GsmCellLocation

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //  Telephony Manager & GmsCellLocation
        val iccidText = findViewById<TextView>(R.id.iccidText)
        val imsiText = findViewById<TextView>(R.id.imsiText)
        val operatorNameText = findViewById<TextView>(R.id.operatorNameText)
        val networkTypeText = findViewById<TextView>(R.id.networkTypeText)
        val imeiText = findViewById<TextView>(R.id.imeiText)
        val cellIdText = findViewById<TextView>(R.id.cellIdText)
        val lacText = findViewById<TextView>(R.id.lacText)

        val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val cellLocation = telephonyManager.cellLocation as GsmCellLocation

        val networkType = telephonyManager.networkType
        networkTypeText.text = "Network Type: $networkType"

        val imei = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            telephonyManager.imei
        } else {
            imeiText.text = "IMEI (Device ID): $--"
        }
        imeiText.text = "IMEI (Device ID): $imei"

        val cellId = cellLocation.cid
        cellIdText.text = "Cell ID: $cellId"

        val lac = cellLocation.lac
        lacText.text = "LAC (Location Area Code): $lac"

        val simSerialNumber = telephonyManager.simSerialNumber
        iccidText.text = "ICCID (Sim Serial Number): $simSerialNumber"

        val subscriberId = telephonyManager.subscriberId
        imsiText.text = "IMSI (Subscriber ID): $subscriberId"

        val operatorName = telephonyManager.networkOperatorName
        operatorNameText.text = "Operator Name: $operatorName"
    }
}