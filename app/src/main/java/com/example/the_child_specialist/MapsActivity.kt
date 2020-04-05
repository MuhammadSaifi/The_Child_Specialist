package com.example.the_child_specialist

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import android.os.Build
import android.widget.Toast
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import maes.tech.intentanim.CustomIntent


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener, LocationListener {
    val REQUEST_LOCATION_CODE = 99
    private lateinit var mMap: GoogleMap
    internal var arifChildren = LatLng(31.577773, 74.381589)
    internal var shadmanGernal = LatLng(31.535869, 74.328717)
    internal var fatimaMemorial = LatLng(31.538795, 74.328202)
    internal var serviceHospital = LatLng(31.543769, 74.332837)
    internal var omsHopital = LatLng(31.539673, 74.338673)
    internal var shalamarHospital = LatLng(31.575404, 74.380012)
    internal var mushtaqHospital = LatLng(31.578156, 74.380559)
    internal var zahidaWalfare = LatLng(31.577370, 74.380559)
    internal var ammarMedical = LatLng(31.538210, 74.343823)
    internal var surgimedHospital = LatLng(31.539819, 74.351204)
    internal var cmhHospital = LatLng(31.543330, 74.385537)
    internal var sheikhZaid = LatLng(31.510846, 74.308804)
    internal var hameedLateef = LatLng(31.515530, 74.329403)
    internal var ayshaHospital = LatLng(31.497821, 74.393948)
    internal var adilHospital = LatLng(31.490209, 74.379700)
    internal var amanatMentary = LatLng(31.606802, 74.410084)
    internal var sirGangaRam = LatLng(31.557813, 74.322537)
    internal var dhaMedical = LatLng(31.477382, 74.371718)
    internal var faujiFoundation = LatLng(31.500181, 74.394978)
    internal var gulabDevi = LatLng(31.483221, 74.343975)
    internal var childrenHospital = LatLng(31.480046, 74.342848)
    internal var mayoHospital = LatLng(31.571821, 74.314308)
    internal var ladyaitchison = LatLng(31.573704, 74.315649)
    internal var arrayList = ArrayList<LatLng>()
    internal var arraytitle = ArrayList<String>()
    private var client: GoogleApiClient? = null
    private var lastLocation: Location? = null
    private var currentLocationMarker: Marker? = null
    internal var latitude: Double = 0.toDouble()
    internal var longitude: Double = 0.toDouble()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission()
        }
        arrayList.add(arifChildren)
        arrayList.add(shadmanGernal)
        arrayList.add(fatimaMemorial)
        arrayList.add(serviceHospital)
        arrayList.add(omsHopital)
        arrayList.add(shalamarHospital)
        arrayList.add(mushtaqHospital)
        arrayList.add(zahidaWalfare)
        arrayList.add(ammarMedical)
        arrayList.add(surgimedHospital)
        arrayList.add(cmhHospital)
        arrayList.add(sheikhZaid)
        arrayList.add(hameedLateef)
        arrayList.add(ayshaHospital)
        arrayList.add(adilHospital)
        arrayList.add(amanatMentary)
        arrayList.add(sirGangaRam)
        arrayList.add(dhaMedical)
        arrayList.add(faujiFoundation)
        arrayList.add(gulabDevi)
        arrayList.add(childrenHospital)
        arrayList.add(mayoHospital)
        arrayList.add(ladyaitchison)


        arraytitle.add("Arif Children Hospital")
        arraytitle.add("Shadman General Hospital")
        arraytitle.add("Fatima Memorial Hospital")
        arraytitle.add("Service Hospital")
        arraytitle.add("OMS Hospital")
        arraytitle.add("Shalamar Hospital")
        arraytitle.add("Mushtaq Hospital")
        arraytitle.add("Zahida Walfare Hospital")
        arraytitle.add("Ammar Medical Complex")
        arraytitle.add("Surgimed Hospital")
        arraytitle.add("CMH Lahore")
        arraytitle.add("Sheikh Zaid Hospital")
        arraytitle.add("Hameed Latif Hospital")
        arraytitle.add("Aysha Hospital")
        arraytitle.add("Adil Hospital")
        arraytitle.add("Amanat Mentary Hospital")
        arraytitle.add("Sir Ganga Ram Hospital")
        arraytitle.add("DHA Medical Center")
        arraytitle.add("Fauji Foundation Hospital")
        arraytitle.add("Gulab Devi Hospital")
        arraytitle.add("Children Hospital")
        arraytitle.add("Mayo Hospital")
        arraytitle.add("Lady Aitchison Hospital")

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    fun checkLocationPermission() {
        // here we check the permission is granted or not


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_CODE)
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_CODE)
            }

        }
    }

    protected fun buildGoogleApiClient() {
        client = GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build()
        client!!.connect()
        // here we connected our client to our google map api
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_LOCATION_CODE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    if (client == null) {
                        buildGoogleApiClient()
                    }
                    mMap.isMyLocationEnabled = true
                }
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient()
                mMap.isMyLocationEnabled = true
            }
        } else {
            buildGoogleApiClient()
            mMap.isMyLocationEnabled = true
        }

        for (i in arrayList.indices) {
            mMap.addMarker(MarkerOptions().position(arrayList[i]).title(arraytitle[i]))
            mMap.animateCamera(CameraUpdateFactory.zoomBy(5f))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList[i]))
        }


    }

    override fun onConnected(p0: Bundle?) {

    }

    override fun onConnectionSuspended(p0: Int) {
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
    }

    override fun onLocationChanged(location: Location?) {
        lastLocation = location

        if (currentLocationMarker != null) {
            currentLocationMarker!!.remove()
        }

        latitude = location!!.latitude
        longitude = location.longitude
        val latLng = LatLng(location.latitude, location.longitude)
        val markerOptions = MarkerOptions()
        markerOptions.position(latLng)
        markerOptions.title("Current Position")
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))

        currentLocationMarker = mMap.addMarker(markerOptions)

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        mMap.animateCamera(CameraUpdateFactory.zoomBy(-50f))  // when camera update then it zoom upto 10x

        if (client != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(client, this)
        }

    }

    override fun finish() {
        super.finish()
        CustomIntent.customType(this,"fade-in-fadeout")
    }


}
