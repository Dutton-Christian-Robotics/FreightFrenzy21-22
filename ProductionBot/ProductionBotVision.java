package org.firstinspires.ftc.teamcode.dcs15815;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;



public class ProductionBotVision extends DefenderBotSystem {
	OpenCvWebcam webcam;
	TealContourPipeline pipeline;
	private int _barcodePosition = -1;
	
	
	ProductionBotVision(HardwareMap hm, DefenderBotConfiguration config, DefenderBot b) {
		super(hm, config, b);
		int cameraMonitorViewId = hm.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hm.appContext.getPackageName());
		webcam = OpenCvCameraFactory.getInstance().createWebcam(hm.get(WebcamName.class, configString("CAMERA_NAME")), cameraMonitorViewId);
		pipeline = new TealContourPipeline();
		webcam.setPipeline(pipeline);
		webcam.setMillisecondsPermissionTimeout(2500); // Timeout for obtaining permission is configurable. Set before opening.
		webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
			@Override
			public void onOpened() {
				webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
			}
	
			@Override
			public void onError(int errorCode) {
//				b.telemetry.addData("Error opening webcam");
//				b.telemetry.update();
			}
	
		});
	}

	public boolean knowsBarcodePosition() {
	    return barcodePosition() > 0;
	}
	public int barcodePosition() {
	    _barcodePosition = pipeline.barcodePosition;
	    return _barcodePosition;
	}
//    public int barcodeConfidence() {
//	   return pipeline.barcodeConfidence;
//    }
//	public double detectionZ() { return pipeline.z; }
	

}