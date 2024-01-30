package src;
import java.awt.*;


public class Scania extends TransportVehicles {

    private int rampDegree;

    public Scania() {
        super(2, Color.blue, 100, "Scania");
        this.rampDegree = 0;
    }

    /**Increase the angle of the ramp incrementally until it is extended fully.
     * Cannot lower the ramp while moving (currentSpeed > 0).
     *@return   true    if successful, false otherwise.*/
    @Override
    public boolean lowerRamp(){
        if(currentSpeed > 0) return false;
        else rampDegree = Math.min(rampDegree += 10, 70);
        // if implementing a load method in scania that depends on ramp being fully down:
        // need to redefine when ramp is considered down
        if (rampDegree > 0) ramp = true;
        return true;
    }

    /**Decrease the angle of the ramp incrementally until fully folded up.*/
    @Override
    public void liftRamp(){
        rampDegree  = Math.max(rampDegree -= 10, 0);
        if (rampDegree == 0) ramp = false;
    }

    /**@return  the current angle of the ramp*/
    public int getRampDegree(){
        return rampDegree;
    }


}
