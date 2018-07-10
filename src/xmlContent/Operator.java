package xmlContent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.NONE)
public class Operator
{
    private String value;

    private Resolution resolution;

    private String sampleName;

    @XmlElement(name="value")
   public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
    }

    @XmlElement(name="resolution")
   public Resolution getResolution ()
    {
        return resolution;
    }

    public void setResolution (Resolution resolution)
    {
        this.resolution = resolution;
    }

    @XmlElement(name="sampleName")
   public String getSampleName ()
    {
        return sampleName;
    }

    public void setSampleName (String sampleName)
    {
        this.sampleName = sampleName;
    }

    @Override
    public String toString()
    {
        return " [value = "+value+", resolution = "+resolution+", sampleName = "+sampleName+"]";
    }
}
