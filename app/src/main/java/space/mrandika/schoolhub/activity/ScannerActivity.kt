package space.mrandika.schoolhub.activity

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.google.zxing.BarcodeFormat
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import space.mrandika.schoolhub.logic.Presence.PresencePresenter
import space.mrandika.schoolhub.logic.Presence.PresenceView
import space.mrandika.schoolhub.network.Api

class ScannerActivity : Activity(), ZXingScannerView.ResultHandler, PresenceView {
    private lateinit var mScannerView: ZXingScannerView
    private val camAccessCode = 100

    private lateinit var presencePresenter: PresencePresenter

    override fun showLoading() {
        //
    }

    override fun hideLoading() {
        //
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivityIfNeeded(Intent(this, MainActivity::class.java), 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mScannerView = ZXingScannerView(this)
        setContentView(mScannerView)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), camAccessCode)
            }
        }

        mScannerView.setFormats(listOf(BarcodeFormat.QR_CODE))
    }

    override fun onResume() {
        super.onResume()
        mScannerView.setResultHandler(this)
        mScannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        mScannerView.stopCamera()
    }

    override fun handleResult(result: Result?) {
        try {
            val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val r = RingtoneManager.getRingtone(applicationContext, notification)
            r.play()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        mScannerView.stopCamera()

        val resultText = result.toString()

        if (resultText.contains("PRS=")) {
            val code = resultText.replace("PRS=", "")
            val sharedPreference = this.getSharedPreferences("token", Context.MODE_PRIVATE)
            val token = sharedPreference.getString("token", null)
            presencePresenter = PresencePresenter(this, this, Api)
            presencePresenter.postPresence(token, code)
        }
    }
}
