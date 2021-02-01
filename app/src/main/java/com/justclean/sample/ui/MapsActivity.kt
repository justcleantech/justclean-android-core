package com.justclean.sample.ui


import android.os.Bundle
import android.os.CountDownTimer
import android.telephony.mbms.MbmsErrors
import android.util.Log
import androidx.lifecycle.Observer
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.justclean.core.R
import com.justclean.core.base.BaseMapActivity
import com.justclean.core.base.LayoutRes


@LayoutRes(layout = R.layout.activity_maps)
class MapsActivity : BaseMapActivity(), GoogleMap.OnCameraIdleListener {

    lateinit var latLng: LatLng
    override fun onActivityReady(savedInstanceState: Bundle?) {
        super.onActivityReady(savedInstanceState)
        setTooltipBackground(color = R.color.colorPrimaryLight)
        setTooltipText(txt = "Test tooltip text")
        setGpsDialogPermissionMessages(
            "Location",
            "please Allow to get well data",
            "Allow",
            "Later"
        )
        gpsPermissionAction.observe(this, Observer {
            if (it) showToast("Accepted")
            else showToast("False")
        })


        lastSelectedLocation.observe(this, Observer { location ->
            if (location != null) {
                Log.e("LastLocation", "onActivityReady: $location")
                this.latLng = location
            }

        })

        mapReady.observe(this, Observer { ready ->
            if (ready) {
                addMapMarkers()
            }

        })

        //clearMarkers()


    }


    private fun addMapMarkers() {
        addMarkers(
            LatLng(29.3790539, 47.9910992), LatLng(29.4790539, 47.9510992),
            LatLng(29.4490539, 47.8810992)
        )
    }
}