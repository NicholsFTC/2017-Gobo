

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="Glyph Bot", group="Iterative Opmode")
//@Disabled
public class GlyphBot extends OpMode
{
    // Declare OpMode members.
    private static ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontMotor = null;
    private DcMotor rightFrontMotor = null;
    private DcMotor leftBackMotor = null;
    private DcMotor rightBackMotor = null;
    private DcMotor elevatorMotor1 = null;
    private DcMotor elevatorMotor2 = null;
    private DcMotor leftGrabber = null;
    private DcMotor rightGrabber = null;
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // step (using the FTC Robot Controller app on the phone).
        leftFrontMotor = hardwareMap.get(DcMotor.class, "leftFrontMotor");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "rightFrontMotor");
        leftBackMotor = hardwareMap.get(DcMotor.class, "leftBackMotor");
        rightBackMotor = hardwareMap.get(DcMotor.class, "rightBackMotor");
        elevatorMotor1 = hardwareMap.get(DcMotor.class, "elevatorMotor1");
        elevatorMotor2 = hardwareMap.get(DcMotor.class, "elevatorMotor2");
        leftGrabber = hardwareMap.get(DcMotor.class, "leftGrabber" );
        rightGrabber = hardwareMap.get(DcMotor.class, "rightGrabber" );


        leftFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotor.Direction.FORWARD);
        rightBackMotor.setDirection(DcMotor.Direction.REVERSE);

        //elevatorMotor.setMode(DcMotor.ZeroPowerBehavior.BRAKE);



        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    public static boolean closed = true;

    @Override
    public void loop() {
        double leftPower;
        double rightPower;

        leftPower = gamepad1.left_stick_y;
        rightPower = gamepad1.right_stick_y;

        leftFrontMotor.setPower(leftPower);
        leftBackMotor.setPower(leftPower);
        rightFrontMotor.setPower(rightPower);
        rightBackMotor.setPower(rightPower);

        if (gamepad1.left_bumper){
            leftFrontMotor.setPower(1.0);
            leftBackMotor.setPower(-1.0);
            rightFrontMotor.setPower(-1.0);
            rightBackMotor.setPower(1.0);
        }
        else if (gamepad1.right_bumper){
            leftFrontMotor.setPower(-1.0);
            leftBackMotor.setPower(1.0);
            rightFrontMotor.setPower(1.0);
            rightBackMotor.setPower(-1.0);
        }
        if(gamepad2.dpad_up) {
            elevatorMotor1.setPower(1);
            elevatorMotor2.setPower(-1);
        }
        else if(gamepad2.dpad_down) {
            elevatorMotor1.setPower(-1);
            elevatorMotor2.setPower(1);
        }
        else{
            elevatorMotor1.setPower(0);
            elevatorMotor2.setPower(0);
        }
        double timer = 0.0;
        if(gamepad2.right_bumper){
            timer = runtime.milliseconds();
        }
        if(gamepad2.right_bumper && closed ){
            if( runtime.milliseconds() <= timer + 500.0) {
                leftGrabber.setPower(1);
                rightGrabber.setPower(-1);
            }
            else {
                leftGrabber.setPower(0);
                rightGrabber.setPower(0);
            }
            closed = !closed;
        }
        else if(gamepad2.right_bumper && !closed){
            if(runtime.milliseconds() <= timer + 500.0) {
                leftGrabber.setPower(-1);
                rightGrabber.setPower(1);
            }
            else {
                leftGrabber.setPower(0);
                rightGrabber.setPower(0);
            }
            closed = !closed;
        }

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
