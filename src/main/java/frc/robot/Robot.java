package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.lib5k.roborio.FaultReporter;
import frc.lib5k.utils.RobotLogger;
import frc.lib5k.utils.RobotLogger.Level;
import frc.robot.commands.DriveControl;
import frc.robot.subsystems.DriveTrain;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

	/* Robot I/O helpers */
	RobotLogger logger = RobotLogger.getInstance();
	FaultReporter m_faultReporter = FaultReporter.getInstance();

	/* Robot Subsystems */
	private DriveTrain m_drivetrain = DriveTrain.getInstance();

	/* Robot Commands */
	private DriveControl m_driveControl;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {

		// Create control commands
		logger.log("Robot", "Constructing Commands", Level.kRobot);
		m_driveControl = new DriveControl();

		// Register all subsystems
		logger.log("Robot", "Registering Subsystems", Level.kRobot);
		m_drivetrain.setDefaultCommand(m_driveControl);
	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {

		// Run all scheduled WPILib commands
		CommandScheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {

	}

	@Override
	public void teleopPeriodic() {

		// Run all scheduled WPILib commands
		CommandScheduler.getInstance().run();
	}

}
