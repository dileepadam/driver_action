package com.damc.driver_action.ui.homeBase.home

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.damc.driver_action.R
import com.damc.driver_action.accelerationHelper.Accelerometer
import com.damc.driver_action.activityTrackingHelper.ActivityTransitionReceiver
import com.damc.driver_action.activityTrackingHelper.ActivityTransitionsUtil
import com.damc.driver_action.app.AssignmentApplication
import com.damc.driver_action.common.Constants
import com.damc.driver_action.common.Constants.REQUEST_CODE_ACTIVITY_TRANSITION
import com.damc.driver_action.databinding.FragmentHomeScreenBinding
import com.damc.driver_action.ui.BaseFragment
import com.damc.driver_action.utils.Utils
import com.damc.driver_action.velocityHelper.CLocation
import com.damc.driver_action.velocityHelper.IBaseGpsListener
import com.google.android.gms.location.ActivityRecognition
import com.google.android.gms.location.ActivityRecognitionClient
import com.google.android.gms.location.ActivityTransitionResult
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.util.Formatter
import java.util.Locale


class HomeScreen : BaseFragment<FragmentHomeScreenBinding, HomeScreenViewModel>(),
    EasyPermissions.PermissionCallbacks, IBaseGpsListener {

    val TAG = HomeScreen::class.java.simpleName

    lateinit var client: ActivityRecognitionClient

    private lateinit var activityTransitionReceiver: ActivityTransitionReceiver

    lateinit var locationManager: LocationManager

    companion object {
        fun newInstance() = HomeScreen()
    }


    override val layoutId: Int
        get() = R.layout.fragment_home_screen

    override val viewModel: HomeScreenViewModel
        get() {
            val viewModel by activityViewModel<HomeScreenViewModel>()
            return viewModel
        }

    override fun onReady(savedInstanceState: Bundle?) {

        viewModel.actionData =
            (requireActivity().application as AssignmentApplication).getActionData()!!

        viewModel.topSpeed = viewModel.actionData.highestSpeed

        client = ActivityRecognition.getClient(requireActivity())

        viewModel.hardStopCount = MutableLiveData(viewModel.actionData.hardStopCount)
        viewModel.fastAccelerartionCount = MutableLiveData(viewModel.actionData.fastAcceleration)

        binding.tvDate.text = Utils.getCurrentDateAsString()

        viewModel.accelerometer.setListener(object : Accelerometer.Listener {
            override fun onTranslation(acceleration: Float) {
                viewModel.acceleration.postValue(acceleration)
                binding.tvAcceleration.text = "${"%.1f".format(acceleration)} m/s*s"
                viewModel.checkFastAccOrHardStop()
            }

        })

        setActivityClient()

        binding.llDriverData.visibility = View.GONE

        activityTransitionReceiver = object : ActivityTransitionReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                if (ActivityTransitionResult.hasResult(intent)) {
                    val result = ActivityTransitionResult.extractResult(intent)
                    result?.let {
                        result.transitionEvents.forEach { event ->

                            binding.tvUserStatus.text =
                                ActivityTransitionsUtil.toActivityString(event.activityType)

                            if (binding.tvUserStatus.text != "IN VEHICLE") {
                                binding.llDriverData.visibility = View.GONE
                            } else {
                                binding.llDriverData.visibility = View.VISIBLE
                            }


                        }
                    }
                }
            }
        }


        locationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            requestPermissions(
                arrayOf<String>(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                Constants.REQUEST_LOCATION_PERMISSION,
            )
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
            this.updateSpeed(null)
        }

        setObservers()
    }


    fun setObservers() {
        viewModel.fastAccelerartionCount.observe(this, Observer {
            binding.tvFastAcceleration.text = viewModel.fastAccelerartionCount.value?.toString()
        })

        viewModel.hardStopCount.observe(this, Observer {
            binding.tvHardStopCount.text = viewModel.hardStopCount.value?.toString()
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.accelerometer.register()
    }

    override fun onPause() {
        super.onPause()
        viewModel.accelerometer.unregister()
    }

    private fun updateSpeed(location: CLocation?) {
        var nCurrentSpeed = 0f
        if (location != null) {
            location.setUseMetricunits(true)
            nCurrentSpeed = location.speed
        }
        val fmt = Formatter(StringBuilder())
        fmt.format(Locale.US, "%.1f", nCurrentSpeed)
        var strCurrentSpeed: String = fmt.toString()
        strCurrentSpeed = strCurrentSpeed.replace(' ', '0')
        var strUnits = "m/s"
        try {
            viewModel.velocity.postValue(location?.speed)
            binding.tvVelocity.text = "$strCurrentSpeed $strUnits"
            if (viewModel.isStartRide) {
                if (viewModel.topSpeed < location?.speed!!) {
                    viewModel.topSpeed = location.speed
                    viewModel.actionData.highestSpeed = viewModel.topSpeed
                    viewModel.updateUserData(viewModel.actionData)
                }
            }
        } catch (e: Exception) {

        }
    }

    fun setActivityClient() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
            // check for permission
            && !ActivityTransitionsUtil.hasActivityTransitionPermissions(requireContext())
        ) {

            // request for permission
            requestActivityTransitionPermission()
        } else {
            // when permission is already allowed
            requestForUpdates()
        }
    }


    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        if (requestCode == REQUEST_CODE_ACTIVITY_TRANSITION) {
            requestForUpdates()
        } else {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
                this.updateSpeed(null)
            }
        }

    }


    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (requestCode == REQUEST_CODE_ACTIVITY_TRANSITION) {
            if (EasyPermissions.somePermissionPermanentlyDenied(requireActivity(), perms)) {
                AppSettingsDialog.Builder(requireActivity()).build().show()
            } else {
                requestActivityTransitionPermission()
            }
        } else {
            EasyPermissions.requestPermissions(
                this,
                "You need to allow Activity Transition Permissions in order to recognize your location",
                Constants.REQUEST_LOCATION_PERMISSION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            EasyPermissions.requestPermissions(
                this,
                "You need to allow Activity Transition Permissions in order to recognize your location",
                Constants.REQUEST_LOCATION_PERMISSION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }


    private fun requestForUpdates() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACTIVITY_RECOGNITION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

        }
        client
            .requestActivityTransitionUpdates(
                ActivityTransitionsUtil.getActivityTransitionRequest(),
                getPendingIntent()
            )
            .addOnSuccessListener {
                showToast("successful registration")
            }
            .addOnFailureListener {
                showToast("Unsuccessful registration")
            }
    }

    private fun deregisterForUpdates() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACTIVITY_RECOGNITION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        client
            .removeActivityTransitionUpdates(getPendingIntent())
            .addOnSuccessListener {
                getPendingIntent().cancel()
                showToast("successful deregistration")
            }
            .addOnFailureListener { e: Exception ->
                showToast("unsuccessful deregistration")
            }
    }


    private fun getPendingIntent(): PendingIntent {
        val intent = Intent(requireActivity(), ActivityTransitionReceiver::class.java)
        return PendingIntent.getBroadcast(
            requireActivity(),
            Constants.REQUEST_CODE_INTENT_ACTIVITY_TRANSITION,
            intent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) PendingIntent.FLAG_IMMUTABLE else PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG)
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
//        deregisterForUpdates()
    }


    private fun requestActivityTransitionPermission() {
        EasyPermissions.requestPermissions(
            this,
            "You need to allow Activity Transition Permissions in order to recognize your activities",
            Constants.REQUEST_CODE_ACTIVITY_TRANSITION,
        )
    }

    override fun onLocationChangedI(location: Location?) {
        if (location != null) {
            val myLocation = CLocation(location, true)
            updateSpeed(myLocation)
        }
    }

    override fun onProviderDisabledI(provider: String?) {

    }

    override fun onLocationChanged(p0: Location) {
        if (p0 != null) {
            val myLocation = CLocation(p0, true)
            updateSpeed(myLocation)
        }
    }


    override fun onProviderEnabledI(provider: String?) {

    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onGpsStatusChanged(event: Int) {

    }

    fun onClickSummery(view: View) {
        if (!viewModel.isStartRide) {
            binding.llDriverData.visibility = View.VISIBLE
            binding.btRider.text = "Stop Ride"
            binding.tvUserStatus.text = "Driving"
        } else {
            binding.llDriverData.visibility = View.GONE
            binding.btRider.text = "Start Ride"
            binding.tvUserStatus.text = "Waiting for google Service"
        }

        viewModel.isStartRide = !viewModel.isStartRide
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.updateUserData(viewModel.actionData)
    }


}