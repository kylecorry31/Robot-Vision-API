# FRC Robot Vision API

[![Build Status](https://travis-ci.org/kylecorry31/Robot-Vision-API.svg?branch=master)](https://travis-ci.org/kylecorry31/Robot-Vision-API)

The Robot Vision API supports detecting multiple targets asynchronously. The vision library uses the builder pattern to create detectors and camera sources. Through this API, the robot is able to detect targets, groups of targets, or even multiple kinds of targets asynchronously. 

### CameraSource
The CameraSource class is used to grab images from a camera and pass them to a detector if supplied. Once the start method is called, the CameraSource will asynchronously pass the detector an image to process until the stop method is called. 

### TargetDetector
The TargetDetector class specializes in finding vision targets in an image given a target specification. 

### Getting Started
Download the jar file from the releases page (https://github.com/kylecorry31/Robot-Vision-API/releases), along with it's dependency jar from the Geometry API (https://github.com/kylecorry31/Geometry/releases/) and add them to your project in a libs folder. Add the libs folder to your build.properties file like so, at the end of the file: 

    userLibs = libs
    
Then add both files to your Eclipse buildpath.


### Displaying the most probable target example
```Java
final CvSource outputStream = CameraServer.getInstance().putVideo("Target", 640, 480);
final CameraSource camera = new CameraSource(new UsbCamera("cam", 0));
camera.setExposure(0);
camera.setBrightness(0);
camera.setResolution(640, 480);

camera.start();

final int MIN_PIXEL_AREA = 200;

PegDetector pegDetector = new PegDetector(new IndividualPegDetector(new BrightnessFilter(200, 255), MIN_PIXEL_AREA));

Mat image = camera.getPicture();

List<Targets> targets = pegDetector.detect(image);

for(Target target: targets){
    Rect boundary = new Rect((int) Math.round(target.getPosition().x),
        (int) Math.round(target.getPosition().y),
        (int) Math.round(target.getWidth()),
        (int) Math.round(target.getHeight()));
    Imgproc.rectangle(image, boundary.tl(), boundary.br(), new Scalar(0, 255, 0));
    outputStream.putFrame(image);
}
```
