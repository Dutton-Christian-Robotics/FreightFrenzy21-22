package org.firstinspires.ftc.teamcode.dcs15815;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class TestPipeline extends OpenCvPipeline {

    Mat convertedInput = new Mat();
    Mat HSV = new Mat();

    Mat Cb = new Mat();
    Mat x = new Mat();

    static final int REGION_WIDTH = 107;
    static final int REGION_HEIGHT = 240;
    static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(0, 0);
    static final Point REGION2_TOPLEFT_ANCHOR_POINT = new Point(106, 0);
    static final Point REGION3_TOPLEFT_ANCHOR_POINT = new Point(212, 0);

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

    Mat region1, region2, region3;

    static final Scalar BLUE = new Scalar(0, 0, 255);
    static final Scalar GREEN = new Scalar(0, 255, 0);

    int barcodePosition;
    double z;

    @Override
    public void init(Mat firstFrame) {
	   barcodePosition = 0;
	   z = 0;
	   convertInput(firstFrame);

	   region1 = x.submat(new Rect(region1_pointA, region1_pointB));
	   region2 = x.submat(new Rect(region2_pointA, region2_pointB));
	   region3 = x.submat(new Rect(region3_pointA, region3_pointB));
    }

    void convertInput(Mat input) {
	   Imgproc.cvtColor(input, convertedInput, Imgproc.COLOR_RGB2YCrCb);
	   //Imgproc.cvtColor(input, convertedInput, Imgproc.COLOR_RGB2HSV);
	   Core.extractChannel(convertedInput, x, 1);
	   Imgproc.GaussianBlur(x, x, new Size(31.0, 31.0), 0.00);
    }



    @Override
    public Mat processFrame(Mat input) {
	   convertInput(input);
	   //Imgproc.cvtColor(inputMat, inputMat, Imgproc.COLOR_RGB2GRAY);

	   Imgproc.rectangle(
			 input, // Buffer to draw on
			 region1_pointA, // First point which defines the rectangle
			 region1_pointB, // Second point which defines the rectangle
			 BLUE, // The color the rectangle is drawn in
			 1); // Thickness of the rectangle lines

	   /*
	    * Draw a rectangle showing sample region 2 on the screen.
	    * Simply a visual aid. Serves no functional purpose.
	    */
	   Imgproc.rectangle(
			 input, // Buffer to draw on
			 region2_pointA, // First point which defines the rectangle
			 region2_pointB, // Second point which defines the rectangle
			 BLUE, // The color the rectangle is drawn in
			 1); // Thickness of the rectangle lines

	   /*
	    * Draw a rectangle showing sample region 3 on the screen.
	    * Simply a visual aid. Serves no functional purpose.
	    */
	   Imgproc.rectangle(
			 input, // Buffer to draw on
			 region3_pointA, // First point which defines the rectangle
			 region3_pointB, // Second point which defines the rectangle
			 BLUE, // The color the rectangle is drawn in
			 1); // Thickness of the rectangle lines


	   int avg1, avg2, avg3;
	   avg1 = (int) Core.mean(region1).val[0];
	   avg2 = (int) Core.mean(region2).val[0];
	   avg3 = (int) Core.mean(region3).val[0];

	   int maxOneTwo = Math.min(avg1, avg2);
	   int max = Math.min(maxOneTwo, avg3);

	   int sum = avg1 + avg2 + avg3;
	   double mean = sum / 3;

	   double standardDeviation = 0.0;
	   standardDeviation += Math.pow((avg1 - mean), 2);
	   standardDeviation += Math.pow((avg2 - mean), 2);
	   standardDeviation += Math.pow((avg3 - mean), 2);

	   double sq = standardDeviation / 3;
	   double sd = Math.sqrt(sq);

	   if (max == avg1) {
		  barcodePosition = 1;
		  z = Math.abs(avg1 - mean) / sd;

	   } else if (max == avg2) {
		  barcodePosition = 2;
		  z = Math.abs(avg2 - mean) / sd;

	   } else if (max == avg3) {
		  barcodePosition = 3;
		  z = Math.abs(avg3 - mean) / sd;
	   } else {
		  barcodePosition = 0;
		  z = 0;
	   }

//			telemetry.addData("r1", avg1);
//			telemetry.addData("r2", avg2);
//			telemetry.addData("r3", avg3);
//			telemetry.update();

	   return x;
    }

}
