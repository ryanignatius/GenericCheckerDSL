import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class C0 {

	/**
	 * @param args
	 */
	public static int correctFile; 
	public static int allFile;
	public static String bufRightPath;
	public static String bufWrongPath;
	public static String nim;
	public static boolean isFirst;
	
	public static boolean walk( String path ) {
		File root = new File( path );
        if (root == null || !root.isDirectory()) {
        	System.out.println("Invalid directory");
        	return false;
        }

        File[] list = root.listFiles();
        boolean isCorrect = true;
        boolean isHExist = false;
        boolean isCppExist = false;
        boolean isNim = false;

		String[] p = path.split("\\D");
		for (int i=0; i<p.length; i++){
			if (p[i].contains("135") || p[i].contains("s22") || p[i].contains("922")){
				nim = p[i];
				isNim = true;
				break;
			}
		}
        for ( File f : list ) {
		//System.out.println(isCorrect);
            if ( f.isDirectory() ) {
				if (!f.getName().equals("all_documents")) {
					if (isFirst){
						isFirst = false;
						isCorrect = walk( f.getAbsolutePath() ) && isCorrect;
						isFirst = true;
						//System.out.println( "Dir:" + f.getAbsoluteFile() );
					} else if (f.getPath().contains("_1")){
						isCorrect = walk( f.getAbsolutePath() ) && isCorrect;
						//System.out.println( "Dir:" + f.getAbsoluteFile() );
					}
				} else isCorrect = false;
            }
            else {
				//System.out.println(f.getPath());
                //System.out.println( "File:" + f.getAbsoluteFile() );
            	if (f.getPath().contains(".h")){
            		isCorrect = detectFieldH(f.getPath()) && isCorrect;
					isHExist = true;
            		allFile++;
            	} else if (f.getPath().contains(".cpp") || f.getName().contains(".cc")){
            		isCorrect = detectFieldCpp(f.getPath()) && isCorrect;
					isCppExist = true;
            		allFile++;
            	}//else isCorrect = false;
            }
        }
		if (isCppExist && !isHExist){
			if (isNim) bufWrongPath += nim + "; tidak ada file header " + System.lineSeparator();
			isCorrect = false;
		}
		if (!isCppExist && !isHExist){
			//if (isNim) bufWrongPath += nim + "; tidak ada file .h dan .cpp " + System.lineSeparator();
			isNim = false;
			isCorrect = false;
		}
		if (isCorrect){
        //System.out.println(path);
			if (isNim) bufRightPath += nim + " 100 " + System.lineSeparator();
	        correctFile++;
        } else {
			if (isNim) bufRightPath += nim + " 0 " + System.lineSeparator();
		}
        return isCorrect;
    }
	
	public static String preProcess(String line, String separator){
		line = line.trim();
		line = line.replaceAll("\\s+", separator);
		return line;
	}
	
	public static boolean detectFieldH(String path){
		//System.out.println(path);
		String par;
		InputStream fis;
		BufferedReader br;
		String line;
		boolean isOk = false;
		try{
			fis = new FileInputStream(path);
			br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
			boolean isClass = false;
			boolean isConstructor = false;
			while((line = br.readLine()) != null) {
				line = preProcess(line,"");
				if(line.contains("class")) isClass = true;
				if(line.contains("Proses()")) isConstructor = true;
				if(line.contains("proses()")) isConstructor = true;
			}
			isOk = isClass && isConstructor;
			if (isOk) { 
				//System.out.println(path);
				//bufRightPath += path + System.lineSeparator();
				//correctFile++;
			}
			else {
				if (!isClass) bufWrongPath += nim +"; tidak ada deklarasi kelas "+ System.lineSeparator();
				if (!isConstructor) bufWrongPath += nim +"; tidak ada konstruktor "+ System.lineSeparator();
			}
			fis.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		return isOk;
	}
	
	public static boolean detectFieldCpp(String path){
		//System.out.println(path);
		String par;
		InputStream fis;
		BufferedReader br;
		String line;
		boolean isOk = false;
		try{
			fis = new FileInputStream(path);
			br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
			int cTrue = 0;
			boolean isMain = false;
			boolean isInclude = false;
			boolean isPrint = false;
			while((line = br.readLine()) != null) {
				line = preProcess(line," ");
				if(line.contains("#include") && line.contains(".h\"")) isInclude = true;
				if(line.contains("main")) isMain = true;
				if(line.contains("printf")) isPrint = true;
				if(line.contains("cout")) isPrint = true;
			}
			if (!isMain) isOk = true;
			else isOk = !isPrint && isInclude;
			if (isOk) { 
				//System.out.println(path);
				//bufRightPath += path + System.lineSeparator();
				//correctFile++;
			}
			else {
				if (!isInclude) bufWrongPath += nim +"; tidak ada include header "+ System.lineSeparator();
				if (isPrint) bufWrongPath += nim +"; tidak print dengan konstruktor "+ System.lineSeparator();
				/*
				if (isPointer) bufWrongPath += nim +"; menggunakan pointer "+ System.lineSeparator();
				if ((cTrue & (1<<0)) == 0) bufWrongPath += nim +"; tidak ada deklarasi atribut integer x "+ System.lineSeparator();
				if ((cTrue & (1<<1)) == 0) bufWrongPath += nim +"; tidak ada deklarasi atribut integer y "+ System.lineSeparator();
				*/
			}
			fis.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		return isOk;
	}		
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		
		
		/*Sebelum digunakan, ubah dulu parameter direktori di bawah ini. 
		 *Masukkan rangkaian file yang akan diperiksa ke dalam direktori ini. 
		 *Default nama direktori: data/
		 * */
		allFile = 0;
        correctFile = 0;
        bufRightPath = "";
        bufWrongPath = "";
		isFirst = false;
        walk("Praktikum1/");
        
        try{
        	File rfile = new File("right_output_c1.txt");
        	if(!rfile.exists()) rfile.createNewFile();
        	FileWriter  rfw = new FileWriter(rfile);
        	BufferedWriter rbw = new BufferedWriter(rfw);
        	rbw.write(bufRightPath);
        	rbw.close();
        }
        catch (IOException e){
        	
        }
        
        try{
        	File wfile = new File("wrong_output_c1.txt");
        	if(!wfile.exists()) wfile.createNewFile();
        	FileWriter  wfw = new FileWriter(wfile);
        	BufferedWriter wbw = new BufferedWriter(wfw);
        	wbw.write(bufWrongPath);
        	wbw.close();
        }
        catch (IOException e){
        	
        }                		
		//System.out.println( "Right:\n" + bufRightPath);
		//System.out.println( "Wrong:\n" + bufWrongPath);
		System.out.println( "Total File Checked: " + allFile);
        System.out.println( "Correct: " + correctFile);
	}

}
