package src;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class Car implements Movable {

    /** Variables declaration */
    protected Point2D.Double position;
    private Direction facing;
    protected double currentSpeed;
    private int nrDoors;
    public Color color;
    public String modelName;
    protected double enginePower;
    private final int serial;
    private static int counter = 0;

    /** Constructor */
    public Car(int nrDoors, Color color, double enginePower, String modelName){
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        facing = Direction.N;
        position = new Point2D.Double();
        stopEngine();
        serial = counter++;
    }
    /** Methods */


    /**@return      the car's model name*/
    public String getModelName() {
        return modelName;
    }

    /**@return      the car's number of doors*/

    public int getNrDoors() {
        return nrDoors;
    }

    /**@return      the car's current Position*/
    public Point2D.Double getPosition() {
        return new Point2D.Double(position.getX(), position.getY());
    }

    /**@return      the car's current direction*/
    public Direction getDirection() {
        return facing;
    }

    /**@return      the car's engine power*/
    public double getEnginePower(){
        return enginePower;
    }

    /**@return      the car's current speed*/
    public double getCurrentSpeed(){
        return currentSpeed;
    }

    /**@return      the car's color*/
    public Color getColor(){
        return color;
    }

    public int getSerial() {return serial;}

    /**@param   clr the car's new color*/
    public void setColor(Color clr){
        color = clr;
    }

    /**"starts the engine" by setting a minimal currentSpeed*/
    public void startEngine() {
        currentSpeed = 0.1;
    }

    /**stops the engine*/
    public void stopEngine(){
        currentSpeed = 0;
    }

    /**Creates a new position for the car depending on current speed and direction*/
    public void move(){
        double diagonal = currentSpeed / Math.sqrt(2);
        switch(facing){
            case N  -> position.setLocation(position.getX(), position.getY()+currentSpeed);
            case NE -> position.setLocation(position.getX() + diagonal, position.getY() + diagonal);
            case E  -> position.setLocation(position.getX()+currentSpeed, position.getY());
            case SE -> position.setLocation(position.getX() + diagonal, position.getY() - diagonal);
            case S  -> position.setLocation(position.getX(), position.getY()-currentSpeed);
            case SW -> position.setLocation(position.getX() - diagonal, position.getY() - diagonal);
            case W  -> position.setLocation(position.getX()-currentSpeed, position.getY());
            case NW -> position.setLocation(position.getX() - diagonal, position.getY() + diagonal);
        }
    }

    /**Changes direction for the car, if at the "MostLeft" element it starts over*/
    @Override
    public void turnLeft() {
        if (facing == Direction.N) {
            facing = Direction.NW;
        }
        else {
            facing = Direction.values()[facing.ordinal()-1];
        }
    }

    /**Changes direction for the car, if at the "MostRight" element it starts over*/
    @Override
    public void turnRight() {
        if (facing == Direction.NW) {
            facing = Direction.N;
        }
        else {
            facing = Direction.values()[facing.ordinal()+1];
        }
    }

    /**incrementSpeed and decrementSpeed use this to calculate new currentSpeed.
     *@return   a factor for increasing or decreasing speed. Implementation is subclass specific.*/
    protected abstract double speedFactor();

    /**Increase the car's speed.
     *@param amount A percentage between 0 and 1.*/
    protected void incrementSpeed(double amount) {
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    /**Decrease the car's speed.
     *@param amount A percentage between 0 and 1.*/
    protected void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    /**Apply an increase in speed to the car based on passed in percentage.
     * Cannot decrease speed. Cannot increase speed over engine power.
     *@param amount A percentage between 0 and 1. */
    public void gas(double amount){
        double before = currentSpeed;
        if(amount < 0 || amount > 1){
            throw new IllegalArgumentException("Must be between [0, 1]");
        }
        incrementSpeed(amount);
        currentSpeed = Math.max(0, Math.min(enginePower, currentSpeed));
        if(before > currentSpeed){currentSpeed = before;}
    }

    /**Apply a decrease in speed to the car based on passed in percentage.
     * Cannot increase speed. Cannot decrease speed below 0.
     *@param amount A percentage between 0 and 1. */
    public void brake(double amount){
        double before = currentSpeed;
        if(amount < 0 || amount > 1){
            throw new IllegalArgumentException("Must be between [0, 1]");
        }
        decrementSpeed(amount);
        currentSpeed = Math.max(0, Math.min(enginePower, currentSpeed));
        if(before < currentSpeed){currentSpeed = before;}
    }

    /**Compare Car objects based on their serial numbers.
     *@param o Object to compare against.
     *@return   true    If object is of type Car and serials match, false otherwise */
    @Override
    public boolean equals(Object o) {
        return (o instanceof Car && ((Car) o).getSerial() == serial);
    }


}
