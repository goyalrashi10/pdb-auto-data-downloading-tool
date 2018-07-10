package xmlContent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.NONE)
public class Header
{
    private ColumnTitle[] columnTitle;

    @XmlElement(name="columnTitle")
    public ColumnTitle[] getColumnTitle ()
    {
        return columnTitle;
    }

    public void setColumnTitle (ColumnTitle[] columnTitle)
    {
        this.columnTitle = columnTitle;
    }

    @Override
    public String toString()
    {
        return " [columnTitle = "+columnTitle+"]";
    }
}
