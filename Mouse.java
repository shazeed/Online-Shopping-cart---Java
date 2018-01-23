
/**
 * Write a description of class Mouse here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mouse extends Product
{
    private String type;

    public Mouse(String name, String newType)
    {
        super(name);
        type = newType;
    }
    public String getType()
    {
        return type;
    }
}

