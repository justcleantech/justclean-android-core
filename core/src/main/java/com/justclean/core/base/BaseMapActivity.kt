package com.justclean.core.base

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.justclean.core.R
import com.justclean.core.heplers.isGpsOpen
import com.justclean.core.heplers.openPermissionSettings
import com.justclean.core.heplers.visible
import kotlinx.android.synthetic.main.activity_base_map.*


open class BaseMapActivity : BaseActivity(), OnMapReadyCallback, GoogleMap.OnCameraIdleListener,
    GoogleMap.OnCameraMoveListener, GoogleMap.OnCameraMoveStartedListener,
    GoogleMap.OnMyLocationClickListener {

    private var locationRequest: LocationRequest? = null
    private lateinit var mapView: View
    private lateinit var mapFragment: SupportMapFragment
    lateinit var googleMap: GoogleMap
    lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var locationCallback: LocationCallback
    var gpsPermissionAction = MutableLiveData<Boolean>()
    var mapReady = MutableLiveData<Boolean>()
    var lastSelectedLocation = MutableLiveData<LatLng>()
    var defaultLocation = LatLng(29.3790539, 47.9910992)
    var selectedLatLng: LatLng? = null
    var userLocation: LatLng? = null
    var zoomLevel = 15f
    var bearingLever = 90f
    var tilt = 40f
    var dialogTitle = "Location"
    var dialogBody = "accept location permission"
    var dialogPositiveBtn = "yes"
    var dialogNegativeBtn = "no"
    lateinit var permissionDialog: AlertDialog

    override fun onActivityReady(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_base_map)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        getLocation()
        getLocationCallBack()


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        getLocation()
        resetLocationCallBack()
        startLocationUpdates()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        hideDialog()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.setOnCameraIdleListener(this)
        googleMap.setOnCameraMoveListener(this)
        googleMap.setOnCameraMoveStartedListener(this)
        this.googleMap = googleMap

        googleMap.setOnCameraIdleListener {
            selectedLatLng = googleMap.cameraPosition.target
            lastSelectedLocation.value = googleMap.cameraPosition.target
            Log.e("MAP_TAG", "$selectedLatLng  -------------")
        }

        googleMap.uiSettings.isCompassEnabled = false
        try {
            googleMap.isMyLocationEnabled = true
            googleMap.uiSettings.isMyLocationButtonEnabled = true

        } catch (e: SecurityException) {
            e.printStackTrace()
        }

        getGpsButton().setOnClickListener {
            getLocation()
            startLocationUpdates()
        }
        moveMapCamera(defaultLocation)

        viewMapWrapper.onTouch = {
            if (MotionEvent.ACTION_DOWN == it.action) {
                //  googleMap.setMinZoomPreference(0f)
            }
        }
        if (isGpsOpen() && isPermissionGranted()) startLocationUpdates()
    }

    fun getLocation() {
        if (checkGps()) return
        if (checkAppLocationPermissions()) return
        startLocationUpdates()
    }

    private fun getGpsButton(): View {
        mapView = mapFragment.requireView()
        if (mapView != null && mapView.findViewById<View?>("1".toInt()) != null) {
            val locationButton =
                (mapView.findViewById<View>("1".toInt()).parent as View).findViewById<View>("2".toInt())
            val rlp: RelativeLayout.LayoutParams =
                locationButton.layoutParams as RelativeLayout.LayoutParams
            locationButton.visible()
            locationButton.setOnClickListener {
                getLocation()
                startLocationUpdates()
            }
            return locationButton
        }
        return View(this)
    }

    private fun checkGps(): Boolean {
        if (!isGpsOpen()) {
            val builder = AlertDialog.Builder(this, R.style.AlertDialogTheme)
            builder.setTitle(dialogTitle)
                .setMessage(dialogBody)
                .setCancelable(false)
                .setPositiveButton(dialogPositiveBtn) { dialog, _ ->
                    startActivityForResult(
                        Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS),
                        GPS_REQUEST
                    )
                    dialog.dismiss()
                    gpsPermissionAction.value = true
                    hideDialog()
                }
                .setNegativeButton(dialogNegativeBtn) { dialog, _ ->
                    gpsPermissionAction.value = false
                    moveMapCamera(defaultLocation)
                    dialog.dismiss()
                    hideDialog()
                }
            permissionDialog = builder.show()
            permissionDialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE).background =
                getDrawable(R.drawable.jc_button_background)
            permissionDialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE)
                .setTextColor(ContextCompat.getColor(this, R.color.white))
            permissionDialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(ContextCompat.getColor(this, R.color.body_dark))
            return true
        }
        return false
    }

    private fun checkAppLocationPermissions(): Boolean {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationPermissions()
            hideDialog()
            return true
        }
        hideDialog()
        return false
    }

    fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            LOCATION_REQUEST
        )
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        hideDialog()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        when (requestCode) {
            LOCATION_REQUEST -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    gpsPermissionAction.value = true
                    getLocation()
                    startLocationUpdates()
                } else {
                    openPermissionSettings()
                }
                return
            }
        }
    }

    fun startLocationUpdates() {
        locationRequest = LocationRequest()
        locationRequest!!.interval = 1000000
        locationRequest!!.fastestInterval = 1000000
        locationRequest!!.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //   getLocation()
            return
        }
        if (::locationCallback.isInitialized)
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
    }

    fun getLocationCallBack() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                if (locationResult == null) {
                    moveMapCamera(LatLng(defaultLocation.latitude, defaultLocation.longitude))
                    return
                }
                for (location in locationResult.locations) {
                    if (location != null) {
                        moveMapCamera(LatLng(location.latitude, location.longitude))
                        userLocation = LatLng(location.latitude, location.longitude)
                        mapReady.postValue(true)
                        return
                    } else {
                        //moveMapCamera(defaultLocation)
                    }
                }
            }
        }
    }

    fun moveMapCamera(latLang: LatLng) {
        if (::googleMap.isInitialized) {

            googleMap.moveCamera(
                CameraUpdateFactory.newCameraPosition(
                    CameraPosition.Builder()
                        .target(latLang)
                        .zoom(zoomLevel)
                        .bearing(bearingLever)
                        .tilt(tilt)
                        .build()
                )
            )
        }
    }

    private fun resetLocationCallBack() {
        if (::fusedLocationClient.isInitialized) {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }

    }

    override fun onPause() {
        super.onPause()
        resetLocationCallBack()
    }

    fun setTooltipText(txt: String = "", res: Int = 0, colorTxt: Int = 0) {
        if (!txt.isNullOrEmpty()) txtTooltip.text = txt
        else if (res > 0) txtTooltip.text = getString(res)

        if (colorTxt > 0) txtTooltip.setTextColor(colorTxt)
    }

    fun setTooltipVisibility(visibility: Boolean = false) {
        txtTooltip.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    fun setPinVisibility(visibility: Boolean = false) {
        pin.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    fun setTooltipBackground(color: Int = 0, drawable: Int = 0) {
        if (color > 0) txtTooltip.setBackgroundColor(color)
        else if (drawable > 0) txtTooltip.setBackgroundResource(drawable)
    }

    fun setGpsDialogPermissionMessages(
        title: String,
        body: String,
        positiveBtn: String,
        negativeBtn: String
    ) {
        this.dialogTitle = title
        this.dialogBody = body
        this.dialogPositiveBtn = positiveBtn
        this.dialogNegativeBtn = negativeBtn
    }

    fun hideDialog() {
        if (::permissionDialog.isInitialized) permissionDialog.dismiss()
    }

    fun setPinMarker(res: Int = 0, bitmap: Bitmap? = null) {
        if (res > 0) pin.setImageDrawable(ContextCompat.getDrawable(this, res))
        if (bitmap != null) pin.setImageBitmap(bitmap)
    }


    fun addMarkers(vararg latLang: LatLng) {
        if (::googleMap.isInitialized) {
            if (latLang.isNotEmpty()) {
                val markersList = ArrayList<Marker>()
                setPinVisibility(visibility = false)
                setTooltipVisibility(visibility = false)
                for (i in latLang) {
                    markersList.add(
                        googleMap.addMarker(
                            MarkerOptions().position(i)
                        )
                    )
                }
                val builder: LatLngBounds.Builder = LatLngBounds.Builder()
                for (marker in markersList) {
                    builder.include(marker.position)
                }
                val bounds: LatLngBounds = builder.build()
                val padding = 0 // offset from edges of the map in pixels
                val cu = CameraUpdateFactory.newLatLngBounds(bounds, padding)
                //googleMap.setMaxZoomPreference(10f)
                googleMap.animateCamera(cu)


            }
        }

    }

    fun clearMarkers(vararg latLang: LatLng) {
        val list = getMarkerList(*latLang)
        if (latLang.isNotEmpty()) {
            for (i in 0..latLang.size - 1) {
                var marker: Marker? = list[i]
                marker?.remove()
                marker = null
            }
        } else googleMap.clear()

    }

    private fun getMarkerList(vararg latLang: LatLng): List<Marker> {
        val markersList = ArrayList<Marker>()
        for (i in latLang) {
            markersList.add(
                googleMap.addMarker(
                    MarkerOptions().position(i)
                )
            )
        }
        return markersList
    }


    override fun onCameraIdle() {
        //showToast("Move One")
    }

    override fun onCameraMove() {
        //  showToast("Move onCameraMove")
    }

    override fun onCameraMoveStarted(p0: Int) {
        //   showToast("Move onCameraMoveStarted")
    }

    override fun onMyLocationClick(p0: Location) {
        //  showToast("Location Clicked")
    }

    companion object {
        const val LOCATION_REQUEST = 2100
        const val GPS_REQUEST = 2200
    }

}