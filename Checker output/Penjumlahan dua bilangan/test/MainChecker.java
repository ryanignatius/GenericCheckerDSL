import java.io.*;
import java.util.*;

class MainChecker {
	// argument 1 (args[0]), source code file
	// argument 2 (args[1]), executable file

	public static void pipeStream(InputStream input, OutputStream output) throws IOException
	{
		byte buffer[] = new byte[1024];
		int numRead = 0;

		do
		{
			numRead = input.read(buffer);
			output.write(buffer, 0, numRead);
		} while (input.available() > 0);

		output.flush();
	}
	
	public static String preProcess(String line, String separator){
		line = line.trim();
		line = line.replaceAll("\\s+", separator);
		return line;
	}
	
	public static void main(String[] args) throws Exception {
		long scoreChange = 0;
		String templ = "[[CHECKER]]";
		
		File inTempFile = new File("temp/1.in");
		inTempFile.getParentFile().mkdirs();
		inTempFile.createNewFile();
		File outTempFile = new File("temp/1.out");
		outTempFile.createNewFile();
		
		FileWriter tempOutput = new FileWriter(inTempFile);
		Scanner sc = new Scanner(System.in);
		String line;
		while (sc.hasNextLine()){
			line = sc.nextLine();
			tempOutput.write(line);
			tempOutput.write("\n");
		}
		tempOutput.close();
		
		
		FileInputStream fileIn = new FileInputStream(inTempFile);
		FileOutputStream fileOut = new FileOutputStream(outTempFile);
		
		OutputStream procIn = null;
		InputStream procOut = null;
		
		Process process = null;

		try {
			process = Runtime.getRuntime().exec (args[1]);
			procIn = process.getOutputStream();
			procOut = process.getInputStream();

			pipeStream(fileIn, procIn);
			pipeStream(procOut, fileOut);
		} catch (IOException e) {}
		process.destroy();
		
		ExampleOutputChecker ex = new ExampleOutputChecker();
		int score = ex.check("temp/");
		if (score == 100) System.out.println("OK");
		else System.out.println("Wrong Answer");
	}
}
