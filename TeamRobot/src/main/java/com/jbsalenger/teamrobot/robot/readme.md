## Current Class Structure

TeamRobot
    \
        - RobotVision
    \
        - REVMotor
            \
                - REVTicker
                
RobotDriver

#### Team Robot
> Team Robot is a wrapper class for all things used in the autonomous portion of the game

RobotVision is for CV aspects of the game,
what ( if anything ) is currently seen with the camera
how far is it
etc.

REVMotor is for all of the motors on the robot there can be N instances of REVMotor on any robot
It includes the subclass REVTicker which holds all information about the encoder, this class may be migrated into just Ticker


#### RobotDriver
> RobotDriver is similar to TeamRobot but is only used in the driver controlled portion of the game