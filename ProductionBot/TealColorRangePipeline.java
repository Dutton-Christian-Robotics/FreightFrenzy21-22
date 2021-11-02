package org.firstinspires.ftc.teamcode.dcs15815;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;


public class TealColorRangePipeline extends OpenCvPipeline {

    Mat convertedInput = new Mat();
    Mat HSV = new Mat();

    Mat x = new Mat();

    static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(0, 0);
    static final Point REGION2_TOPLEFT_ANCHOR_POINT = new Point(106, 0);
    static final Point REGION3_TOPLEFT_ANCHOR_POINT = new Point(212, 0);

    static final int REGION_WIDTH = 107;
    static final int REGION_HEIGHT = 120;

    Point region1_pointA = new Point(
		  REGION1_TOPLEFT_ANCHOR_POINT.x,
		  REGION1_TOPLEFT_ANCHOR_POINT.y);
    Point region1_pointB = new Point(
		  REGION1_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
		  REGION1_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
    Point region2_pointA = new Point(
		  REGION2_TOPLEFT_ANCHOR_POINT.x,
		  REGION2_TOPLEFT_ANCHOR_POINT.y);
    Point region2_pointB = new Point(
		  REGION2_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
		  REGION2_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
    Point region3_pointA = new Point(
		  REGION3_TOPLEFT_ANCHOR_POINT.x,
		  REGION3_TOPLEFT_ANCHOR_POINT.y);
    Point region3_pointB = new Point(
		  REGION3_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
		  REGION3_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);

    int barcodePosition;
    int barcodeConfidence;



    void convertInput(Mat input) {
	   //Imgproc.cvtColor(input, convertedInput, Imgproc.COLOR_RGB2YCrCb);
	   Imgproc.cvtColor(input, convertedInput, Imgproc.COLOR_RGB2HSV);
	   //Core.extractChannel(convertedInput, x, 1);
	   //Imgproc.GaussianBlur(x, x, new Size(31.0, 31.0), 0.00);


    }

    @Override
    public void init(Mat firstFrame) {
        barcodePosition = -1;
	   barcodeConfidence = 0;
	   convertInput(firstFrame);
    }


    @Override
    public Mat processFrame(Mat input) {
	   convertInput(input);
	   //Scalar lowHSV = new Scalar(62, 222, 41);
	   // Scalar highHSV = new Scalar(65, 54, 224);
	   Scalar lowHSV = new Scalar(60, 70, 10);
	   Scalar highHSV = new Scalar(99, 255, 255);
	   Mat thresh = new Mat();

	   Core.inRange(convertedInput, lowHSV, highHSV, thresh);

	   Mat region1, region2, region3;
	   region1 = thresh.submat(new Rect(region1_pointA, region1_pointB));
	   region2 = thresh.submat(new Rect(region2_pointA, region2_pointB));
	   region3 = thresh.submat(new Rect(region3_pointA, region3_pointB));

	   int avg1, avg2, avg3, avg4, avg5, avg6;
	   avg1 = (int) Core.mean(region1).val[0];
	   avg2 = (int) Core.mean(region2).val[0];
	   avg3 = (int) Core.mean(region3).val[0];

	   int maxOneTwo = Math.max(avg1, avg2);
	   int max = Math.max(maxOneTwo, avg3);

	   if (max == avg1) {
		  barcodePosition = 1;
		  barcodeConfidence = avg1;
//		  z = Math.abs(avg1 - mean) / sd;

	   } else if (max == avg2) {
		  barcodePosition = 2;
		  barcodeConfidence = avg2;
//		  z = Math.abs(avg2 - mean) / sd;

	   } else if (max == avg3) {
		  barcodePosition = 3;
		  barcodeConfidence = avg3;
//		  z = Math.abs(avg3 - mean) / sd;
	   } else {
		  barcodePosition = -1;
		  barcodeConfidence = 0;
//		  z = 0;
	   }


	   return thresh;
    }

}
