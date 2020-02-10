package frc.robot.autonomous.paths.score;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.autonomous.AutonomousStartpoints;
import frc.robot.autonomous.actions.LinePath;
import frc.robot.autonomous.actions.TurnToCommand;
import frc.robot.autonomous.actions.VisionAlign;
import frc.robot.autonomous.helpers.SpeedConstraint;
import frc.robot.autonomous.paths.AutonomousPath;
import frc.robot.autonomous.actions.DriveDistance;
import frc.robot.autonomous.actions.DriveToCommand;

// Built with Point Interpeter
public class ScoreGetBalls extends AutonomousPath {
    
    // Starts in front of the trench facing the target
    @Override
    public Pose2d getStartingPose() {
        return new Pose2d(AutonomousStartpoints.SECTOR_LINE_RIGHT, Rotation2d.fromDegrees(152));
    }



    @Override
    protected SequentialCommandGroup getCommand() {
        // Create output command group
        SequentialCommandGroup output = new SequentialCommandGroup();

        double startx = getStartingPose().getTranslation().getX();
        double starty = getStartingPose().getTranslation().getY();
        
        // Ensure robot is facing the correct angle at the start
        output.addCommands(new TurnToCommand((152), 2));

        // Shoot Command TODO change to actual shoot command
        output.addCommands(new WaitCommand(3));

        // Drives to the balls in the trench
        output.addCommands(new DriveToCommand(new Pose2d(startx + 2, starty, Rotation2d.fromDegrees(0)),
                                             new SpeedConstraint(1, 1), false, false));

        // Drives over the balls
        output.addCommands(new DriveToCommand(new Pose2d(startx + 4, starty, Rotation2d.fromDegrees(0)),
                                              new SpeedConstraint(.5, .5), false));

        output.addCommands(new TurnToCommand(Rotation2d.fromDegrees(0), 2));

        return output;
    }

    
}