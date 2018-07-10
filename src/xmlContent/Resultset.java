package xmlContent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="resultset")
@XmlAccessorType(XmlAccessType.NONE)
public class Resultset
{
    private Search search;

    private Header header;

    private Row[] row;

    @XmlElement(name="search")
    public Search getSearch ()
    {
        return search;
    }

    public void setSearch (Search search)
    {
        this.search = search;
    }

    @XmlElement(name="header")
    public Header getHeader ()
    {
        return header;
    }

    public void setHeader (Header header)
    {
        this.header = header;
    }

    @XmlElement(name="row")
    public Row[] getRow ()
    {
        return row;
    }

    public void setRow (Row[] row)
    {
        this.row = row;
    }

    @Override
    public String toString()
    {
        return "[search = "+search+", header = "+header+", row = "+row+"]";
    }
}
			
