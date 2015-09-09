import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;


//PENGGUNAAN: untuk soal C, detil lihat main

public class C3 {

	/**
	 * @param args
	 */
	public static int correctFile; 
	public static int allFile;
	public static int score;
	public static String bufRightPath;
	public static String bufWrongPath;
	public static String nim;
	public static boolean isFirst;
	public static boolean isInitNbPoint;
	public static boolean[] isUsed;
	
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
        boolean isPrint = false;
		isInitNbPoint = false;

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
					} else if (f.getPath().contains("1b")){
						score = 0;
						isPrint = true;
						isUsed = new boolean[14];
						for (int i=0; i<14; i++){
							isUsed[i] = false;
						}
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
		if (!isCppExist || !isHExist){
			if (isNim) bufWrongPath += nim + "; tidak ada file .h atau .cpp " + System.lineSeparator();
			isNim = false;
			isCorrect = false;
		}
		if (isPrint){
			isPrint = false;
			bufRightPath += nim + " " + score + System.lineSeparator();
		}
		/*
		if (isCorrect){
        //System.out.println(path);
			if (isNim) bufRightPath += nim + " 100 " + System.lineSeparator();
	        correctFile++;
        } else {
			if (isNim) bufRightPath += nim + " 0 " + System.lineSeparator();
		}
		*/
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
		String line,line2;
		boolean isOk = false;
		try{
			fis = new FileInputStream(path);
			br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
			
			boolean isPrivate = false;
			
			while((line = br.readLine()) != null) {
				line = preProcess(line," ");
				line2 = preProcess(line,"");
				if(line.contains("class ToString")){
					if (!isUsed[0]){
						isUsed[0] = true;
						score += 5;
					}
				}
				if(line2.contains("private:")) isPrivate = true;
				if(isPrivate && (line2.contains("int*Data") || line2.contains("int*data"))){
					if (!isUsed[1]){
						isUsed[1] = true;
						score += 5;
					}
				}
				if(isPrivate && line2.contains("int[") && (line2.contains("]Data") || line2.contains("]data"))){
					if (!isUsed[1]){
						isUsed[1] = true;
						score += 5;
					}
				}
				if(isPrivate && (line.contains("int Size") || line.contains("int size"))){
					if (!isUsed[2]){
						isUsed[2] = true;
						score += 5;
					}
				}
				if(isPrivate && (line.contains("const int MaxSize") || line.contains("const int maxSize"))){
					if (!isUsed[3]){
						isUsed[3] = true;
						score += 5;
					}
				}
				if(line.contains("string toString()")){
					if (!isUsed[4]){
						isUsed[4] = true;
						score += 10;
					}
				}
				if(line2.contains("Queen()")){
					if (!isUsed[5]){
						isUsed[5] = true;
						score += 10;
					}
				}
				if(line2.matches("(.*)Queen\\(int(.*),int(.*)\\)(.*)")){
					if (!isUsed[6]){
						isUsed[6] = true;
						score += 10;
					}
				}
				if(line2.contains("Queen(constQueen&")){
					if (!isUsed[7]){
						isUsed[7] = true;
						score += 10;
					}
				}
				if(line2.contains("~Queen()")){
					if (!isUsed[8]){
						isUsed[8] = true;
						score += 10;
					}
				}
				if(line2.contains("Queen&operator=(constQueen&")){
					if (!isUsed[9]){
						isUsed[9] = true;
						score += 10;
					}
				}
				if(line.matches("(.*)int(.*)getSize(.*)")){
					if (!isUsed[10]){
						isUsed[10] = true;
						score += 5;
					}
				}
				if(line.matches("(.*)int(.*)getData(.*)")){
					if (!isUsed[11]){
						isUsed[11] = true;
						score += 5;
					}
				}
				if(line.matches("(.*)void(.*)setSize(.*)")){
					if (!isUsed[12]){
						isUsed[12] = true;
						score += 5;
					}
				}
				if(line.matches("(.*)void(.*)setData(.*)")){
					if (!isUsed[13]){
						isUsed[13] = true;
						score += 5;
					}
				}
			}
			isOk = true;
			if (isOk) { 
				//System.out.println(path);
				//bufRightPath += path + System.lineSeparator();
				//correctFile++;
			}
			else {
				
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
		String line,line2;
		boolean isOk = false;
		try{
			fis = new FileInputStream(path);
			br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
			int cTrue = 0;
			boolean isMain = false;
			boolean isInclude = false;
			while((line = br.readLine()) != null) {
				line = preProcess(line," ");
				line2 = preProcess(line,"");
				if(line.contains("#include") && line.contains(".h\"")) isInclude = true;
				if(line.contains("main")) isMain = true;
			}
			isOk = true;
			if (isOk) { 
				//System.out.println(path);
				//bufRightPath += path + System.lineSeparator();
				//correctFile++;
			}
			else {
				if (!isInclude) bufWrongPath += nim +"; tidak ada include header "+ System.lineSeparator();
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
		isFirst = true;
        walk("312/");
        
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
