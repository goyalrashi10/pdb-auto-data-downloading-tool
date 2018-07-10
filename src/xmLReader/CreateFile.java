package xmLReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateFile {

	static File downloadFile = new File("resource/download_XML.sh");
	static FileWriter fw;
	static BufferedWriter bw;

	public static void createFile() {
		// TODO Auto-generated method stub
		try {
			fw= new FileWriter(downloadFile.getAbsolutePath());
			bw=new BufferedWriter(fw);
			bw.write("text data");
			bw.newLine();
			bw.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
