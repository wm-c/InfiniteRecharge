package frc.robot;

import java.util.Map;

import edu.wpi.cscore.HttpCamera;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.lib5k.utils.RobotLogger;
import frc.robot.subsystems.cellmech.Intake;
import frc.robot.subsystems.cellmech.Shooter;

/**
 * Robot status & data interface for driver dashboard
 */
public class Dashboard {
    RobotLogger logger = RobotLogger.getInstance();

    // Singleton
    private static Dashboard s_instance = null;

    // Thread
    private Notifier m_thread;

    /* Subsystems */
    private Shooter m_shooter = Shooter.getInstance();
    private Intake m_intake = Intake.getInstance();

    /* Widgets */
    private ShuffleboardTab m_dashTab;
    private NetworkTableEntry m_shooterVelocity;
    private NetworkTableEntry m_busVoltage;
    private NetworkTableEntry m_ballCount;

    private Dashboard() {

        // Configure thread
        m_thread = new Notifier(this::update);
        m_thread.setName("Dashboard");

        // Load the in-game view tab
        m_dashTab = Shuffleboard.getTab("Game View");

    }

    public static Dashboard getInstance() {
        if (s_instance == null) {
            s_instance = new Dashboard();
        }

        return s_instance;
    }

    public void start() {

        // Start the notifier at 10hz
        m_thread.startPeriodic(0.1);
    }

    public void init() {
        logger.log("Dashboard", "Pushing shuffleboard widgets to driverstation");

        // Display the limelight feed
        m_dashTab.add("Limelight", new HttpCamera("Limelight", "http://10.50.24.11:5800"))
                .withWidget(BuiltInWidgets.kCameraStream).withPosition(0, 0).withSize(6, 4);

        // Display the bus voltage
        m_busVoltage = m_dashTab.add("Bus Voltage", 0.0).withWidget(BuiltInWidgets.kVoltageView)
                .withProperties(Map.of("min", 0, "max", 13)).withPosition(6, 0).getEntry();

        // Display the shooter velocity
        // m_shooterVelocity = m_dashTab.add("Shooter Output",
        // 0.0).withWidget(BuiltInWidgets.kDial)
        // .withProperties(Map.of("min", 0, "max",
        // RobotConstants.Shooter.MOTOR_MAX_RPM)).withPosition(6, 1).getEntry();

        // Display the ball count in the hopper
        m_ballCount = m_dashTab.add("Ball Count", 0.0).withWidget(BuiltInWidgets.kDial)
                .withProperties(Map.of("min", 0, "max", 5)).withPosition(8, 0).withSize(1, 1).getEntry();
    }

    private void update() {

        // Update the bus voltage
        m_busVoltage.setDouble(RobotController.getBatteryVoltage());

        // Update the shooter velocity
        // m_shooterVelocity.setDouble(m_shooter.getOutput() *
        // RobotConstants.Shooter.MOTOR_MAX_RPM);

        // Update the ball count
        // TODO: Uncomment when intake is merged to master
        // m_ballCount.setDouble(m_intake.getBallCount());

    }

    public void publishAutonChooser(Sendable chooser) {
        m_dashTab.add("Autonomous Mode", chooser).withPosition(6, 1).withSize(2, 1);
    }

}