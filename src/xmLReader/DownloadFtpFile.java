package xmLReader;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import org.apache.commons.net.ftp.FTPClient;

public class DownloadFtpFile {

//	public void downloadFtpFile(String remoteFile1,String fileName) {
		// TODO Auto-generated method stub

	public static String downloadFolder;
	public static File downloadFtpFile;
	public static File downloadhttpFile;
	
	public static void downloadFromFtp(String embdId){
		String remoteFile1 = "/pub/emdb/structures/EMD-"+embdId+"/map/emd_"+embdId+".map.gz";
		downloadFtpFile = new File(downloadFolder+"/"+"emd_"+embdId+".map.gz");

		if(!(new File(downloadFolder+"/"+"emd_"+embdId+".map").exists())){
			
			try {
				FTPClient ftp = new FTPClient();
				ftp.connect("ftp.wwpdb.org");
				ftp.login("rasghig10","password");
				OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFtpFile));
				boolean success = ftp.retrieveFile(remoteFile1, outputStream1);
				outputStream1.close();
	            System.out.println("embdb file downloaded to : "+downloadFtpFile);
				unZipFtpFile();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("embdb "+embdId+" File already exists");
		}
	}
	public static void downloadFromHttps(String pdbId){
		
		BufferedInputStream bis;
		
		if(!(new File(downloadFolder+"/"+pdbId+".pdb")).exists() 
				&& !(new File(downloadFolder+"/"+pdbId+".pdbx")).exists() 
				&& !(new File(downloadFolder+"/"+pdbId+".cif")).exists() 
				&& !(new File(downloadFolder+"/"+pdbId+".xml")).exists() ){

			try {
				URL url = new URL("https://files.rcsb.org/download/"+pdbId+".pdb.gz");	
				downloadhttpFile = new File(downloadFolder+"/"+pdbId+".pdb.gz");
				if(url.openConnection().getContentLength()==0){
					url = new URL("https://files.rcsb.org/download/"+pdbId.toUpperCase()+".pdb.gz");
					downloadhttpFile = new File(downloadFolder+"/"+pdbId.toUpperCase()+".pdb.gz");
				}else if(url.openConnection().getContentLength()==0){
					url = new URL("https://files.rcsb.org/download/"+pdbId+".cif.gz");
					downloadhttpFile = new File(downloadFolder+"/"+pdbId+".cif.gz");
				}else if(url.openConnection().getContentLength()==0){
					url = new URL("https://files.rcsb.org/download/"+pdbId.toUpperCase()+".cif.gz");
					downloadhttpFile = new File(downloadFolder+"/"+pdbId.toUpperCase()+".cif.gz");
				}else if(url.openConnection().getContentLength()==0){
					url = new URL("https://files.rcsb.org/download/"+pdbId+".xml.gz");
					downloadhttpFile = new File(downloadFolder+"/"+pdbId+".xml.gz");
				}else if(url.openConnection().getContentLength()==0){
					url = new URL("https://files.rcsb.org/download/"+pdbId.toUpperCase()+".xml.gz");
					downloadhttpFile = new File(downloadFolder+"/"+pdbId.toUpperCase()+".xml.gz");					
				}else if(url.openConnection().getContentLength()==0){
					url = new URL("https://files.rcsb.org/download/"+pdbId+".pdbx.gz");
					downloadhttpFile = new File(downloadFolder+"/"+pdbId+".pdbx.gz");
				}else if(url.openConnection().getContentLength()==0){
					url = new URL("https://files.rcsb.org/download/"+pdbId.toUpperCase()+".pdbx.gz");
					downloadhttpFile = new File(downloadFolder+"/"+pdbId.toUpperCase()+".pdbx.gz");
				}else if(url.openConnection().getContentLength()>0){
					System.out.println("File found at"+url);
				
					bis = new BufferedInputStream((InputStream) url.getContent());
					
					BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(downloadhttpFile));
					
					int inByte;
					while((inByte = bis.read()) != -1) bos.write(inByte);
					bis.close();
					bos.close();
		            System.out.println("Pdb file downloaded to : "+downloadhttpFile);
					unZipHttpFile();			

				}else{
					System.out.println("File not found at all");
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}

		}else{
			System.out.println("pdb "+pdbId+" File already exists");
		}

	}
	public static void downloadFileFromHttps(){
		
		
	}
	public static void createFolder(String embdid,String resolution){
		
		downloadFolder=System.getProperty("user.dir")+"/"+embdid+"_"+resolution+"A";
//		new File(System.getProperty("user.dir")+"/"+resolution).mkdir();
//		downloadFolder=System.getProperty("user.dir")+"/"+resolution+"/"+embdid;
		new File(downloadFolder).mkdir();
	}
	public static void unZipFtpFile(){
		
		File unzippedFtpfile=new File(downloadFtpFile.getParent(), downloadFtpFile.getName().replaceAll("\\.gz$", ""));
		
        try {
			GZIPInputStream gis = new GZIPInputStream(new FileInputStream(downloadFtpFile));
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(unzippedFtpfile));
			
			int inByte;
			while((inByte = gis.read()) != -1) bos.write(inByte);
            //close resources
            gis.close();
            bos.close();
            downloadFtpFile.delete();
            System.out.println("Unzipped file to : "+unzippedFtpfile);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void unZipHttpFile(){
		
		File unzippedHttpfile=new File(downloadhttpFile.getParent(), downloadhttpFile.getName().replaceAll("\\.gz$", ""));
		
        try {
			GZIPInputStream gis = new GZIPInputStream(new FileInputStream(downloadhttpFile));
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(unzippedHttpfile));
			
			int inByte;
			while((inByte = gis.read()) != -1) bos.write(inByte);
            //close resources
            gis.close();
            bos.close();
            downloadhttpFile.delete();
            System.out.println("Unzipped file to : "+unzippedHttpfile);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void unZipFile(){
		try {
			Process p=Runtime.getRuntime().exec("gunzip "+downloadFolder+"/*.gz");
			System.out.println(p.exitValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}	
}
