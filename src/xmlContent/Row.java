package xmlContent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.NONE)
public class Row
{
    private Column[] column;

    @XmlElement(name="column")
    public Column[] getColumn ()
    {
        return column;
    }

    public void setColumn (Column[] column)
    {
        this.column = column;
    }

    @Override
    public String toString()
    {
        return " [column = "+column+"]";
    }
}
			
