

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="Turret Bot Autonomous", group="Iterative Opmode")
//@Disabled
public class TurretBotAutonomous extends LinearOpMode
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
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

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

        waitForStart();
        runtime.reset();

        if(colorSensor.red() < 3 && colorPot.getVoltage() < 6){ // sensor doesn't pick up red and blue alliance
            topRotationMotor.setPower(-1);
            topRotationMotor.setPower(-1);
        }
        else if(colorSensor.red() > 3 && colorPot.getVoltage() < 6){ // sensor does pick up red and blue alliance
            topRotationMotor.setPower(1);
            topRotationMotor.setPower(1);
        }
        else if(colorSensor.red() < 3 && colorPot.getVoltage() > 6){ // sensor doesn't pick up red and red alliance
            topRotationMotor.setPower(1);
            topRotationMotor.setPower(1);
        }
        else if(colorSensor.red() > 3 && colorPot.getVoltage() > 6){ // sensor does pick up red and red alliance
            topRotationMotor.setPower(-1);
            topRotationMotor.setPower(-1);
        }
        while (opModeIsActive() && (runtime.seconds() < .5)) {
            telemetry.addData("Path", "Leg 2: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("sensor", "color sensor = " + colorSensor.red());
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */

}
