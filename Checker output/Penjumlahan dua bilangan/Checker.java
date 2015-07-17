import java.io.*;
import java.util.*;

class Checker {
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
		
		FileWriter tempOutput = new FileWriter("bufferIn.temp");
		Scanner sc = new Scanner(System.in);
		String line;
		int n = -1;
		while (sc.hasNextLine()){
			line = sc.nextLine();
			if (n == -1) n = Integer.parseInt(line);
			tempOutput.write(line);
			tempOutput.write("\n");
		}
		tempOutput.close();
		
		
		FileInputStream fileIn = new FileInputStream("bufferIn.temp");
		FileOutputStream fileOut = new FileOutputStream("bufferOut.temp");
		
		OutputStream procIn = null;
		InputStream procOut = null;

		try {
			Process process = Runtime.getRuntime().exec (args[1]);
			procIn = process.getOutputStream();
			procOut = process.getInputStream();

			pipeStream(fileIn, procIn);
			pipeStream(procOut, fileOut);
		} catch (IOException e) {}
		
		InputStream fisOut = new FileInputStream("bufferOut.temp");
		BufferedReader brOut = new BufferedReader(new InputStreamReader(fisOut));
		String s = "";
		String[] tok;
		boolean ok = true;
		int x = -1,y = -1;
		try {
			while((line = brOut.readLine()) != null) {
				tok = line.split(" ");
				x = Integer.parseInt(tok[0]);
				y = Integer.parseInt(tok[1]);
				break;
			}
			if (x <= 0 || y <= 0 || x+y != n) ok = false;
			if (ok) System.out.println("OK");
			else System.out.println("Wrong Answer");
		} catch (Exception e){System.out.println("Wrong Answer");}
	}
}
