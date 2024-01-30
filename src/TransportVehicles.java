package src;
import java.awt.*;

public abstract class TransportVehicles extends Car{
    // true when ramp is down so that things can be loaded
    // false otherwise
    protected boolean ramp;

    public TransportVehicles(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
        ramp = false;
    }

    /**@return  true    if the ramp is fully lowered, false otherwise*/
    public boolean isLoadable() {
        return ramp;
    }

    /**lowers the ramp.
     *@return   true    if successful, false otherwise.*/
    public abstract boolean lowerRamp();

    /**lifts the ramp*/
    public abstract void liftRamp();

    /**starts the engine by setting a minimal speed, only if the ramp is lifted*/
    @Override
    public void startEngine() {
        if (!ramp) currentSpeed = 0.1;
    }

    /**incrementSpeed and decrementSpeed use this to calculate new currentSpeed.
     *@return   a factor for increasing or decreasing speed. 0 if ramp is extended.*/
    @Override
    protected double speedFactor(){
        if (ramp) return 0;
        return 1;
    }

    /**@return  true    if ramp is extended, false otherwise.*/
    public boolean getBoolRamp() {
        return ramp;
    }
}
