/*
package org.firstinspires.ftc.zzzteamcode.opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class StickObserverPipeline extends OpenCvPipeline {

    public StickObserverPipeline() {}

    @Override

    public Mat processFrame(Mat input){

        Mat mat = new Mat();

        //mat turns into HSV
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        if (mat.empty()){
            return input;
        }

        Scalar lowHSV = new Scalar(20, 70, 80); // lenient lower bound HSV for yellow
        Scalar highHSV = new Scalar(32, 255, 255); // lenient higher bound HSV for yellow

        Mat thresh = new Mat();

        //get a black and white img of yellow objects
        Core.inRange(mat, lowHSV, highHSV, thresh);

        Mat masked = new Mat();
        //color the white portion of thresh in with HSV from mat
        //output into masked
        Core.bitwise_and(mat, mat, masked, thresh);
        //calculate average HSV values of the white thresh values
        Scalar average = Core.mean(masked, thresh);

        Mat scaledMask = new Mat();
        //scale the average saturation to 150;
        masked.convertTo(scaledMask, -1, 150/average.val[1], 0);

        Scalar strictLowHSV = new Scalar(0, 150, 100); //strict lower
        Scalar strictHighHSV = new Scalar(255, 255, 255);

        Mat scaledThresh = new Mat();

        //apply strict HSV filter onto scaledMask to get rid of any yellow othter than pole
        Core.inRange(scaledMask, strictLowHSV, strictHighHSV, scaledThresh);

        Mat finalMask = new Mat();
        //color in scaledThresh with HSV(showing results);
        Core.bitwise_and(mat, mat, finalMask, scaledThresh);

        Mat edges = new Mat();

        //detect edges of final mask

        Imgproc.Canny(finalMask, edges, 100, 200);

        //release all the data

        input.release();
        Imgproc.cvtColor(scaledMask, input, Imgproc.COLOR_HSV2RGB);
        scaledThresh.copyTo(input);
        scaledThresh.release();
        scaledMask.release();
        mat.release();
        masked.release();
        edges.release();
        thresh.release();
        // hierarchy.release();
        finalMask.release();
        return input;


    }

}
*/
