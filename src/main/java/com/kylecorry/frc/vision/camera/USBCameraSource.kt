package com.kylecorry.frc.vision.camera

import edu.wpi.cscore.UsbCamera

/**
 * Created by Kylec on 5/16/2017.
 */
class USBCameraSource(port: Int) : CameraSource(UsbCamera("cam" + port, port))
