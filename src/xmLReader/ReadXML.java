package xmLReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import xmlContent.Column;
import xmlContent.ColumnTitle;
import xmlContent.Header;
import xmlContent.Operator;
import xmlContent.Resultset;
import xmlContent.Row;
import xmlContent.Search;

public class ReadXML {

	static File f= new File("resource/pdb.xml");
	public static DownloadFtpFile downloadFtpFile = new DownloadFtpFile();

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		JAXBContext jaxbContext;
		try {

			jaxbContext = JAXBContext.newInstance(Resultset.class);
			Unmarshaller unmarshaller= jaxbContext.createUnmarshaller();
			Resultset resultSet=(Resultset) unmarshaller.unmarshal(f);

			Row[] row=resultSet.getRow();
			ArrayList<Row> finalRows = new ArrayList<Row>();
				
			System.out.println("Initiating download process.............");
		
			for(int i=0;i<row.length;i++){
				
				String embdid=null;
				String embdidstr=null;
				String pdbid;
				String pdbidstr=null;
				String resolution=null;

				Column[] column = row[i].getColumn();
				for(int j=0;j<column.length;j++){
					
					
					if(column[j].getType().equalsIgnoreCase("emdbid")){
						embdid=column[j].getContent();
//						embdidstr="wget \"ftp://ftp.wwpdb.org/pub/emdb/structures/EMD-"+embdid+"/map/emd_"+embdid+".map.gz\"";						
						
					}
					if(column[j].getType().equalsIgnoreCase("resolution")){
						resolution=column[j].getContent();
					}

					if(!column[j].getContent().isEmpty() && column[j].getType().equalsIgnoreCase("pdbId")){

						System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

						pdbid=column[j].getContent();
//						pdbidstr="curl -o "+pdbid+".pdb.gz \"https://files.rcsb.org/download/"+pdbid+".pdb.gz\"";
						downloadFtpFile.createFolder(embdid,resolution);
						System.out.println("Processing Embdid:"+embdid+" .............");
						for(String val : embdid.split(";")){
							downloadFtpFile.downloadFromFtp(val.trim());
						}
						System.out.println("Processing pdbid:"+pdbid+" .............");
						for(String val : pdbid.split(";")){
							downloadFtpFile.downloadFromHttps(val.trim());
						}
						
					}
				}
			}
			
			Row[] rows=new Row[finalRows.size()];
			rows=finalRows.toArray(rows);
			Resultset finalResultSet=new Resultset();
			finalResultSet.setRow(rows);
			
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			File file = new File("resource/pdbNew.xml");
			jaxbMarshaller.marshal(finalResultSet, file);


			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

}
