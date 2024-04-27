package id.kasirvippro.android.feature.scan

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.zxing.BarcodeFormat
import com.google.zxing.Result
import id.kasirvippro.android.R
import id.kasirvippro.android.base.BaseActivity
import id.kasirvippro.android.ui.ext.toGone
import id.kasirvippro.android.ui.ext.toVisible
import id.kasirvippro.android.utils.AppConstant
import id.kasirvippro.android.utils.Helper
import kotlinx.android.synthetic.main.activity_scan_code.*
import me.dm7.barcodescanner.zxing.ZXingScannerView
import java.util.ArrayList

class ScanCodeActivity : BaseActivity<ScanCodePresenter, ScanCodeContract.View>(),
    ScanCodeContract.View,
    ZXingScannerView.ResultHandler {

    private lateinit var mScannerView: ZXingScannerView
    private var mFlash: Boolean = false
    private var mAutoFocus: Boolean = true
    private var mSelectedIndices = arrayListOf<Int>()
    private var mCameraId = -1

    override fun createPresenter(): ScanCodePresenter {
        return ScanCodePresenter(this, this)
    }

    override fun createLayout(): Int = R.layout.activity_scan_code

    override fun startingUpActivity(savedInstanceState: Bundle?) {
        renderView()
        getPresenter()?.onViewCreated(intent)
        displayScanner()
        checkCameraPermission()
    }

    override fun renderView() {
        setupToolbar()
        permission_btn.setOnClickListener {
            openCameraPermission()
        }
    }

    override fun onResume() {
        super.onResume()
        if (getCameraPermission()) {
            mScannerView.setResultHandler(this)
            mScannerView.startCamera(mCameraId)
            mScannerView.flash = mFlash
            mScannerView.setAutoFocus(mAutoFocus)
        }
        setupToolbar()
    }

    override fun onPause() {
        super.onPause()
        mScannerView.stopCamera()
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter()?.onDestroy()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            AppConstant.REQUEST_CAMERA_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permission_layout.toGone()
                    content_frame.toVisible()
                } else {
                    permission_layout.toVisible()
                    content_frame.toGone()
                }
                return
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_flashlight, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == android.R.id.home){
            onBackPressed()
        }
        else if(id == R.id.action_flash){
            if(content_frame.isVisible){
                mFlash = !mFlash
                mScannerView.flash = mFlash
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun handleResult(rawResult: Result?) {
        val resultText = rawResult?.text
        if (rawResult == null || resultText.isNullOrEmpty()) {
            mScannerView.resumeCameraPreview(this)
            return
        }
        Log.d("scan",resultText)

        val newintent:Intent = intent
        newintent.putExtra(AppConstant.DATA,resultText)
        setResult(Activity.RESULT_OK,newintent)
        //EventBus.getDefault().post(onCode(resultText))
        finish()

    }

    override fun resumeCamera() {
        mScannerView.resumeCameraPreview(this)
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permission_layout.toVisible()
            content_frame.toGone()
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CAMERA)) {
                //                Util.openAppSettings(this);
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CAMERA), AppConstant.REQUEST_CAMERA_PERMISSION
                )
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CAMERA), AppConstant.REQUEST_CAMERA_PERMISSION
                )
            }
            return
        }
        permission_layout.toGone()
        content_frame.toVisible()
    }

    private fun openCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permission_layout.visibility = View.VISIBLE
            content_frame.visibility = View.GONE
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CAMERA), AppConstant.REQUEST_CAMERA_PERMISSION
                )
            } else {
                Helper.openAppSettings(this)
                //                ActivityCompat.requestPermissions(this,
                //                        new String[]{android.Manifest.permission.CAMERA}, AppConstant.REQUEST_ZXING_CAMERA_PERMISSION);
            }
            return
        }
        permission_layout.toGone()
        content_frame.toVisible()
    }

    private fun displayScanner() {
        mScannerView = ZXingScannerView(this)
        setupFormats()
        content_frame.addView(mScannerView)
    }

    private fun setupFormats() {
        val formats = ArrayList<BarcodeFormat>()
        if (mSelectedIndices.isEmpty()) {
            mSelectedIndices = ArrayList()
            for (i in ZXingScannerView.ALL_FORMATS.indices) {
                mSelectedIndices.add(i)
            }
        }

        for (index in mSelectedIndices) {
            formats.add(ZXingScannerView.ALL_FORMATS[index])
        }
        mScannerView.setFormats(formats)
    }

    private fun getCameraPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CAMERA)) {
                permission_layout.toVisible()
                content_frame.toGone()
            } else {
                permission_layout.toVisible()
                content_frame.toGone()
            }
//            Timber.d("camera permission is NOT granted")
            return false
        }
//        Timber.d("camera permission is granted")
        permission_layout.toGone()
        content_frame.toVisible()
        return true
    }
    private fun setupToolbar() {
        supportActionBar?.apply {
            title = "Scan"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            elevation = 0f
        }

    }

    override fun onBackPressed() {
        mScannerView.stopCamera()
        finish()
    }

}
