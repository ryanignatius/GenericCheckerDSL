import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("all")
public class HitungRataRataOutputChecker {
  private Scanner sc;
  
  private BufferedReader buf;
  
  private FileReader fr;
  
  private File fl;
  
  private FileWriter fw;
  
  private BufferedWriter writer;
  
  private static boolean is_valid;
  
  private static ArrayList cond_arr;
  
  public static void die(final String message) {
    System.out.println(message);
    is_valid = false;
  }
  
  public int intReader(final String token) {
    int ret = 0;
    try {
      ret = Integer.parseInt(token);
    } catch (Exception e){
      die("not int");
    }
    return ret;
  }
  
  public long longReader(final String token) {
    long ret = 0;
    try {
      ret = Long.parseLong(token);
    } catch (Exception e){
      die("not long");
    }
    return ret;
  }
  
  public float floatReader(final String token) {
    float ret = 0;
    try {
      ret = Float.parseFloat(token);
    } catch (Exception e){
      die("not float");
    }
    return ret;
  }
  
  public double doubleReader(final String token) {
    double ret = 0;
    try {
      ret = Double.parseDouble(token);
    } catch (Exception e){
      die("not double");
    }
    return ret;
  }
  
  public boolean booleanReader(final String token) {
    boolean ret = false;
    try {
      ret = Boolean.parseBoolean(token);
    } catch (Exception e){
      die("not boolean");
    }
    return ret;
  }
  
  public char charReader(final String token) {
    char ret = ' ';
    if (token.length() == 1){
      ret = token.charAt(0);
    } else {
      die("not char");
    }
    return ret;
  }
  
  public String stringReader(final String token) {
    return token;
  }
  
  private int n;
  
  public int getN() {
    return this.n;
  }
  
  public void setN(final int n) {
    this.n = n;
  }
  
  public void readN(final String token) {
    n = intReader(token);
  }
  
  public void writeN() {
    try {
    	writer.write(""+(int)n);
    } catch (Exception e){}
  }
  
  private ArrayList arr;
  
  public ArrayList getArr() {
    return this.arr;
  }
  
  public void setArr(final ArrayList arr) {
    this.arr = arr;
  }
  
  public int getArr(final int id1) {
    return (int)arr.get(id1);
  }
  
  public void setArr(final int id1, final Object val) {
    arr.set(id1,val);
  }
  
  public void readArr(final String[] tokens) {
    arr = new ArrayList();
    for (int i=0; i<n; i++){
    	arr.add(intReader(tokens[i]));
    }
  }
  
  public void readArr(final String token, final int idx) {
    arr.set(idx,intReader(token));
  }
  
  public void writeArr() {
    try {
    	for (int i=0; i<n; i++){
    		if (i > 0) writer.write(" ");
    		writer.write(""+(int)arr.get(i));
    	}
    	writer.write(System.lineSeparator());
    } catch (Exception e){}
  }
  
  public void writeArr(final int idx) {
    try {
    	writer.write(""+(int)arr.get(idx));
    } catch (Exception e){}
  }
  
  private double ratarata;
  
  public double getRatarata() {
    return this.ratarata;
  }
  
  public void setRatarata(final double ratarata) {
    this.ratarata = ratarata;
  }
  
  public void readRatarata(final String token) {
    ratarata = doubleReader(token);
  }
  
  public void writeRatarata() {
    try {
    	writer.write(""+(double)ratarata);
    } catch (Exception e){}
  }
  
  public void readInput(final String path) {
    int sz = 0;
    is_valid = true;
    String line;
    String[] tokens;
    try {
      BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
      sz = 0+1;
      if ((line = reader.readLine()) != null){
        line = line.trim();
        line = line.replaceAll("\\s+", " ");
        tokens = line.split(" ");
        if (tokens.length == sz){
          readN(tokens[0]);
        } else {
          die("number of elements in lines not match");
        }
      } else {
        die("number of lines not match");
      }
      sz = 0+n;
      if ((line = reader.readLine()) != null){
        line = line.trim();
        line = line.replaceAll("\\s+", " ");
        tokens = line.split(" ");
        if (tokens.length == sz){
          readArr(tokens);
        } else {
          die("number of elements in lines not match");
        }
      } else {
        die("number of lines not match");
      }
      if (reader.readLine() != null){
        die("number of lines not match");
      }
      reader.close();
    } catch (Exception e){}
  }
  
  public void readOutput(final String path) {
    int sz = 0;
    is_valid = true;
    String line;
    String[] tokens;
    try {
      BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
      sz = 0+1;
      if ((line = reader.readLine()) != null){
        line = line.trim();
        line = line.replaceAll("\\s+", " ");
        tokens = line.split(" ");
        if (tokens.length == sz){
          readRatarata(tokens[0]);
        } else {
          die("number of elements in lines not match");
        }
      } else {
        die("number of lines not match");
      }
      if (reader.readLine() != null){
        die("number of lines not match");
      }
      reader.close();
    } catch (Exception e){}
  }
  
  public boolean p1() {
    boolean ok = false;
    int s;
    cond_arr = new ArrayList<Boolean>();
    for (int i=0; i<arr.size(); i++){
    cond_arr.add(true);
    }
    s = LibraryFunction.sum(arr, cond_arr);
    double rata1;
    rata1 = s/n;
    double delta;
    delta = rata1-ratarata;
    double absdelta;
    absdelta = LibraryFunction.abs(delta);
    if (absdelta<0.001) ok = true;
    return ok;
  }
  
  public int check(final String path) {
    int total_score = 0;
    total_score += s1(100,path);
    return total_score;
  }
  
  public int s1(final int score, final String path) {
    try {
      int total_score = 0;
      int num_files = 0;
      File root = new File(path);
      File[] list = root.listFiles();
      for (File f : list) {
        if (!f.isDirectory()){
          if (f.getPath().contains(".out")){
            String s = path+f.getName();
            String ss = s.substring(0,s.lastIndexOf('.'));
            readInput(ss+".in");
            readOutput(f.getPath());
            num_files++;
            if (p1()) total_score += score;
          }
        }
      }
      if (num_files == 0) return 0;
      return total_score/num_files;
    } catch (Exception e){return 0;}
  }
}
