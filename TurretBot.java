

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="Turret Bot", group="Iterative Opmode")
//@Disabled
public class TurretBot extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor topRotationMotor = null;
    private DcMotor bottomRotationMotor = null;
    private DcMotor elevatorMotor = null;
    private DcMotor extenderMotor = null;
    private Servo claw = null;
    private Servo wrist = null;
    private AnalogInput colorPot = null;
    private AnalogInput positionPot = null;
    private CRServoImpl CarbonFiberArm = null;
    private ColorSensor colorSensor;
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // step (using the FTC Robot Controller app on the phone).
        topRotationMotor = hardwareMap.get(DcMotor.class, "topRotationMotor");
        bottomRotationMotor = hardwareMap.get(DcMotor.class, "bottomRotationMotor");
        elevatorMotor = hardwareMap.get(DcMotor.class, "elevatorMotor");
        extenderMotor = hardwareMap.get(DcMotor.class, "extenderMotor");
        CarbonFiberArm = hardwareMap.get(CRServoImpl.class, "CFArm" );
        claw = hardwareMap.get(Servo.class, "claw" );
        wrist = hardwareMap.get(Servo.class, "wrist" );
        colorPot = hardwareMap.get(AnalogInput.class, "colorPot" );
        positionPot = hardwareMap.get(AnalogInput.class, "positionPot" );
        colorSensor = hardwareMap.colorSensor.get("colorSensor");


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

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    public static double position = 0.0;
    public static double power = 0.5;
    @Override
    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry

        if(gamepad1.dpad_right) {
            topRotationMotor.setPower(power);
            bottomRotationMotor.setPower(power);
        }
        if(gamepad1.dpad_left) {
            topRotationMotor.setPower(-power);
            bottomRotationMotor.setPower(-power);
        }
        if(gamepad1.a && power < 1.0){
            power += .05;
        }
        if(gamepad1.b && power > 0.0){
            power -= .05;
        }


        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Turret", "power " + power);
        telemetry.addData("sensor", "color sensor = " + colorSensor.red());
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
