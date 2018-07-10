package xmlContent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.NONE)
public class Resolution
{
    private String high;

    private String low;

    @XmlElement(name="high")
    public String getHigh ()
    {
        return high;
    }

    public void setHigh (String high)
    {
        this.high = high;
    }

    @XmlElement(name="low")
    public String getLow ()
    {
        return low;
    }

    public void setLow (String low)
    {
        this.low = low;
    }

    @Override
    public String toString()
    {
        return " [high = "+high+", low = "+low+"]";
    }
}
