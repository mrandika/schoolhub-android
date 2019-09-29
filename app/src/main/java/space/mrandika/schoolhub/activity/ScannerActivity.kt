package space.mrandika.schoolhub.activity

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.zxing.BarcodeFormat
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import space.mrandika.schoolhub.fragment.PaymentBottomSheet
import space.mrandika.schoolhub.fragment.PresenceBottomSheet
import space.mrandika.schoolhub.fragment.SarprasBorrowBottomSheet
import space.mrandika.schoolhub.logic.Presence.PresencePresenter
import space.mrandika.schoolhub.logic.Presence.PresenceView
import space.mrandika.schoolhub.network.Api

class ScannerActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {
    private lateinit var mScannerView: ZXingScannerView
    private val camAccessCode = 100

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
            val bundle = Bundle()
            bundle.putString("code", resultText)
            val bottomNavDrawerFragment = PresenceBottomSheet()
            bottomNavDrawerFragment.show(supportFragmentManager, bottomNavDrawerFragment.tag)
            bottomNavDrawerFragment.arguments = bundle
        } else if (resultText.contains("SRP=")) {
            val bundle = Bundle()
            bundle.putString("code", resultText)
            val bottomNavDrawerFragment = SarprasBorrowBottomSheet()
            bottomNavDrawerFragment.show(supportFragmentManager, bottomNavDrawerFragment.tag)
            bottomNavDrawerFragment.arguments = bundle
        } else if (resultText.contains("KTN=")) {
            val bundle = Bundle()
            bundle.putString("code", resultText)
            val bottomNavDrawerFragment = PaymentBottomSheet()
            bottomNavDrawerFragment.show(supportFragmentManager, bottomNavDrawerFragment.tag)
            bottomNavDrawerFragment.arguments = bundle
        }
    }
}
