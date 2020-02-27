package frc.robot.autonomous.paths.score;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.lib5k.kinematics.purepursuit.Path;
import frc.robot.autonomous.AutonomousStartpoints;
import frc.robot.autonomous.actions.DrivePath;
import frc.robot.autonomous.actions.TurnToCommand;
import frc.robot.autonomous.actions.VisionAlign;
import frc.robot.autonomous.actions.cells.IntakeCells;
import frc.robot.autonomous.actions.cells.ShootCells;
import frc.robot.autonomous.paths.AutonomousPath;

public class CenterScore extends AutonomousPath {

    @Override
    protected SequentialCommandGroup getCommand() {

        // Define some handy helpers
        double startx = getStartingPose().getTranslation().getX();
        double starty = getStartingPose().getTranslation().getY();

        // Make an output group
        SequentialCommandGroup output = new SequentialCommandGroup();

        // Ensure robot faces correct angle
        output.addCommands(new TurnToCommand(getStartingPose().getRotation(), 2.0));

        // // Shoot 3 balls
        output.addCommands(new ShootCells(3).withTimeout(3.5));

        // Turn to trench
        output.addCommands(new TurnToCommand(Rotation2d.fromDegrees(-45), 8.0));

        // Drive to front of trench
        output.addCommands(new DrivePath(new Path(getStartingPose().getTranslation(), new Translation2d(startx + .4 ,starty - 0.3),
        new Translation2d(startx + .5 ,starty - 0.3)),  0.2,
        new Translation2d(0.2, 0.2), 0.025, 0.65));
        output.addCommands(new TurnToCommand(0, 8));
        
        
        // Intake balls command
        CommandBase intakeCommand = new IntakeCells(1);

        // Goes to the first ball from the front of the trench
        DrivePath firstBall = new DrivePath(new Path(new Translation2d(startx + .5 ,starty - 0.5),
        new Translation2d(startx + .8 ,starty - 0.4)),  0.2,
        new Translation2d(0.2, 0.2), 0.025, 0.2);

        // intakes and drives through trench
        output.addCommands(new ParallelRaceGroup(intakeCommand, firstBall));

        // turns towards target
        output.addCommands(new TurnToCommand(Rotation2d.fromDegrees(95), 15.0));
        output.addCommands(new TurnToCommand(Rotation2d.fromDegrees(168), 8.0));

        // shoots three balls
        output.addCommands(new ShootCells(1));

        // Rotates towards rendevous
        // output.addCommands(new TurnToCommand(90, 5));
        

        return output;
    }

    @Override
    public Pose2d getStartingPose() {
        return new Pose2d(AutonomousStartpoints.SECTOR_LINE_INFRONT_OF_GOAL, Rotation2d.fromDegrees(-180));
    }

}