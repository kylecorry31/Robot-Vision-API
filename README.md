# FRC Enhanced IO

## Vision
The FRC Enhanced IO library supports detecting multiple targets asynchronously. The vision library uses the builder pattern to create detectors and camera sources.

### CameraSource
The CameraSource class is used to grab images from a camera and pass them to a detector if supplied. Once the start method is called, the CameraSource will asynchronously pass the detector an image to process until the stop method is called. 

### TargetDetector
The TargetDetector class specializes in finding vision targets in an image given a target specification. 

### Example
```Java
TargetDetector pegDetector = new TargetDetector.Builder()
    .addTargetSpecs(new PegVisionTargetSpecs())
    .setProcessor((targets)->{
        // do something
    })
    .build();
    
CameraSource camera = new CameraSource.Builder(pegDetector)
    .setType(CameraSource.USB_CAMERA)
    .build();
```
    
    
