package src;
import java.awt.*;

public class Volvo240 extends PrivateCars {

    private final static double trimFactor = 1.25;

    public Volvo240(){
        super(4, Color.black, 100, "Volvo240");
    }

    @Override
    protected double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }
}