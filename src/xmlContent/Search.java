package xmlContent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.NONE)
public class Search
{
    private Operator operator;

    public Operator getOperator ()
    {
        return operator;
    }

    @XmlElement(name="operator")
    public void setOperator (Operator operator)
    {
        this.operator = operator;
    }

    @Override
    public String toString()
    {
        return " [operator = "+operator+"]";
    }
}
