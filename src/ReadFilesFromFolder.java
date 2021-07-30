import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class ReadFilesFromFolder {
	
	static String fileSeparator = "=====================================================================================================================================================================";
	static String exceptionSeparator = "----------------------------------------------------------------------------------------------------------------------------------------------------------------";
	
	
	public static void main(String[] args) throws Exception {
		String folderLocation = "C:\\Users\\Lattice\\Downloads\\log\\log";
		File folder = new File(folderLocation);
		File[] listOfFiles = folder.listFiles();
		String longText = "";
		String path;
		DecimalFormat df = new DecimalFormat("###.##");
		
		try {
			longText += "Total Number of Files Read: " + listOfFiles.length + "\n" + fileSeparator +"\n";
			
			float len = listOfFiles.length;
			for (int i = 0; i < len; i++) {
				if (listOfFiles[i].isFile()) {				
					path = folderLocation + "\\" + listOfFiles[i].getName();
					String out = readLogFile(path);
					if(out != null) {
					   longText += out + fileSeparator+ "\n";
					}
		
				}
				float num = i+1;
				float cur= (num/len) *100;
				System.out.println(df.format(cur) +"%");
			}
			givenWritingStringToFile_whenUsingFileOutputStream_thenCorrect(longText);
			
			
		}catch(Exception e) {
			System.out.println(e);
		}

	}
	
	public static String readLogFile(String path) throws ParseException, IOException {
		String text = null;
		String separator = "\\";
		String[] arrValues = path.split(Pattern.quote(separator));
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(
				new FileReader(path));
		StringBuilder toFile = new StringBuilder();;
		String line, nextLine;
		toFile.append("File Name: "+arrValues[arrValues.length - 1]);
		toFile.append(System.lineSeparator());
		Map<Integer, String> map = new HashMap<Integer, String>();
		int currentLine = 0, previousPosition;
		while ((line = reader.readLine()) != null) {
			reader.mark(5000);
			reader.markSupported();
			currentLine++;
			map.put(currentLine, line);
			if (line.contains("Exception")) {

				// To print the line where exception occurred
				toFile.append("Line Number: " + currentLine);
				toFile.append(System.lineSeparator());

				// To read previous 10 lines from the current exception
				previousPosition = currentLine - 20;
				for (int i = 0; i <=20; i++) {
					if (map.get(previousPosition) != null) {
						toFile.append(map.get(previousPosition));
						toFile.append(System.lineSeparator());
						
					}
					previousPosition++;
				}
				
				// To read next 10 lines from the current exception
				for (int i = currentLine; i < (currentLine + 20); i++) {
					nextLine = reader.readLine();
					if (nextLine != null) {
						toFile.append(nextLine);
						toFile.append(System.lineSeparator());
						
					} else {
						break;
					}

				}
//				System.out.println(toFile.toString());
				toFile.append(exceptionSeparator);
				toFile.append(System.lineSeparator());
				text = toFile.toString();
				
				
				
			}
			reader.reset();
			
		}
		return text;
	}


	public static void givenWritingStringToFile_whenUsingFileOutputStream_thenCorrect(String exceptionText) throws IOException {
		SimpleDateFormat sdf3 = new SimpleDateFormat("_yyyy_MM_dd_HH_mm_ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String fileName = "C:\\Users\\Lattice\\Desktop\\Exception_File"+sdf3.format(timestamp)+".log";
		FileOutputStream outputStream = new FileOutputStream(fileName);
		byte[] strToBytes = exceptionText.getBytes();
		outputStream.write(strToBytes);
		outputStream.close();
		System.out.println("File is generated! Please to this location "+fileName);
	}
	

}
