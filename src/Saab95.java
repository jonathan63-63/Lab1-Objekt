package src;
import java.awt.*;


public class Saab95 extends PrivateCars {

    public boolean turboOn;

    public Saab95() {
        super(2, Color.red, 125, "Saab95");
        turboOn = false;
    }

    /**Activate turbo mode*/
    public void setTurboOn() {
        turboOn = true;
    }

    /**Deactivate turbo mode*/
    public void setTurboOff() {
        turboOn = false;
    }

    /**incrementSpeed and decrementSpeed use this to calculate new currentSpeed
     *@return   a factor to increase or decrease speed by. Depends on turbo*/
    @Override
    protected double speedFactor() {
        double turbo = 1;
        if (turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }
}
