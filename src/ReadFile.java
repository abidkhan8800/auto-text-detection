import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ReadFile {

	
	public static void main(String[] path) throws ParseException, IOException {
		String separator = "\\";
		String path1 = "C:\\Users\\Lattice\\Downloads\\log\\manual_Jun 29_ 2021_ 13_11_01.log";
		String[] arrValues = path1.split(Pattern.quote(separator));
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(
				new FileReader(path1));
		StringBuilder toFile = new StringBuilder();;
		StringBuilder content;
		StringBuilder nextContent;
		String line, nextLine;
		toFile.append(arrValues[arrValues.length - 1]);
		toFile.append(System.lineSeparator());
		Map<Integer, String> map = new HashMap<Integer, String>();
		int currentLine = 0, previousPosition;
		while ((line = reader.readLine()) != null) {
			reader.mark(5000);
			reader.markSupported();
			currentLine++;
			map.put(currentLine, line);
			if (line.contains("Exception")) {

				content = new StringBuilder();
				nextContent = new StringBuilder();
						// To print the line where exception occured
//				System.out.println(currentLine);
				toFile.append(currentLine);
				toFile.append(System.lineSeparator());

				// To read previous 10 lines from the current exception
				previousPosition = currentLine - 3;
				for (int i = 0; i <=3; i++) {
					if (map.get(previousPosition) != null) {
						content.append(map.get(previousPosition));
						content.append(System.lineSeparator());
						toFile.append(map.get(previousPosition));
						toFile.append(System.lineSeparator());
						
					}
					previousPosition++;
				}
//				System.out.println(content.toString());
				
				// To read next 10 lines from the current exception
				for (int i = currentLine; i < (currentLine + 10); i++) {
					nextLine = reader.readLine();
					if (nextLine != null) {
						nextContent.append(nextLine);
						nextContent.append(System.lineSeparator());
						toFile.append(nextLine);
						toFile.append(System.lineSeparator());
						
					} else {
						break;
					}

				}
//				System.out.println(nextContent.toString());
				System.out.println(toFile.toString());
				String out = toFile.toString();
				

			}
			reader.reset();
		}
	}

	


	public static String readAll(String path) throws ParseException, IOException {
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
				toFile.append("Line Number:" +currentLine);
				toFile.append(System.lineSeparator());

				// To read previous 10 lines from the current exception
				previousPosition = currentLine - 2;
				for (int i = 0; i <=2; i++) {
					if (map.get(previousPosition) != null) {
						toFile.append(map.get(previousPosition));
						toFile.append(System.lineSeparator());
						
					}
					previousPosition++;
				}
				
				// To read next 10 lines from the current exception
				for (int i = currentLine; i < (currentLine + 15); i++) {
					nextLine = reader.readLine();
					if (nextLine != null) {
						toFile.append(nextLine);
						toFile.append(System.lineSeparator());
						
					} else {
						break;
					}

				}
				System.out.println(toFile.toString());
				toFile.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
				text = toFile.toString();
				
				
				
			}
			reader.reset();
			
		}
		return text;
	}

}
