package src;
import java.awt.*;


public class Scania extends TransportVehicles {



    public Scania() {
        super(2, Color.blue, 100, "Scania");
    }

    /**Increase the angle of the ramp incrementally until it is extended fully.
     * Cannot lower the ramp while moving (currentSpeed > 0).
     *@return   true    if successful, false otherwise.*/
    @Override
    public boolean lowerRamp(){
        if(currentSpeed > 0) return false;
        else ramp.incDegree();
        return true;
    }

    /**Decrease the angle of the ramp incrementally until fully folded up.*/
    @Override
    public void liftRamp(){
        ramp.decDegree();
    }

    /**@return  the current angle of the ramp*/
    public int getRampDegree(){
        return ramp.getRampDegree();
    }


}
