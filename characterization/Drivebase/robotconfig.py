{
    # Class names of motor controllers used.
    # Options:
    # 'WPI_TalonSRX'
    # 'WPI_TalonFX' (for Falcon 500 motors)
    # 'WPI_VictorSPX'
    # Note: The first motor on each side should always be a Talon SRX/FX, as the
    # VictorSPX does not support encoder connections
    "rightControllerTypes": ["WPI_TalonSRX", "WPI_TalonSRX"],
    "leftControllerTypes": ["WPI_TalonSRX", "WPI_TalonSRX"],
    # Ports for the left-side motors
    "leftMotorPorts": [0, 1],
    # Ports for the right-side motors
    "rightMotorPorts": [2, 3],
    # Inversions for the left-side motors
    "leftMotorsInverted": [False, False],
    # Inversions for the right side motors
    "rightMotorsInverted": [False, False],
    # Wheel diameter (in units of your choice - will dictate units of analysis)
    "wheelDiameter": 0.333,
    # If your robot has only one encoder, remove all of the right encoder fields
    # Encoder pulses-per-revolution (*NOT* cycles per revolution!)
    # This value should be the pulses per revolution *of the wheels*, and so
    # should take into account gearing between the encoder and the wheels
<<<<<<< HEAD:characterization/robotconfig.py
    "encoderPPR": 512,
=======
    "encoderPPR": 4096,
>>>>>>> master:characterization/Drivebase/robotconfig.py
    # Whether the left encoder is inverted
    "leftEncoderInverted": False,
    # Whether the right encoder is inverted:
    "rightEncoderInverted": False,
    # Your gyro type (one of "NavX", "Pigeon", "ADXRS450", "AnalogGyro", or "None")
<<<<<<< HEAD:characterization/robotconfig.py
    "gyroType": "None",
=======
    "gyroType": "NavX",
>>>>>>> master:characterization/Drivebase/robotconfig.py
    # Whatever you put into the constructor of your gyro
    # Could be:
    # "SPI.Port.kMXP" (MXP SPI port for NavX or ADXRS450),
    # "I2C.Port.kOnboard" (Onboard I2C port for NavX)
    # "0" (Pigeon CAN ID or AnalogGyro channel),
    # "new WPI_TalonSRX(3)" (Pigeon on a Talon SRX),
    # "leftSlave" (Pigeon on the left slave Talon SRX/FX),
    # "" (NavX using default SPI, ADXRS450 using onboard CS0, or no gyro)
<<<<<<< HEAD:characterization/robotconfig.py
    "gyroPort": "",
}

=======
    "gyroPort": "SPI.Port.kMXP",
}












>>>>>>> master:characterization/Drivebase/robotconfig.py
