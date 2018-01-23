
/**
 * Write a description of class Laptop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Laptop extends Product
{
    private String processorSpeed;
    private float screenSize;
    private int memoryCap;
    private int batteryHours;
    
    public Laptop (String lapName,String processSpeed, float size, int memory, int battery)
    {
        super(lapName);
        processorSpeed = processSpeed;
        screenSize = size;
        memoryCap = memory;
        batteryHours = battery;
        
    }
    
    public String getProcessorSpeed()
    {
        return processorSpeed;
    }
    public float getScreenSize()
    {
        return screenSize;
    }
    public int getMemoryCap()
    {
        return memoryCap;
    }
    public int getBatteryHours()
    {
        return batteryHours;
    }
    
}


