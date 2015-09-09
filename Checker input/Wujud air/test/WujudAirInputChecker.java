import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("all")
public class WujudAirInputChecker {
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
  
  private int T;
  
  public int getT() {
    return this.T;
  }
  
  public void setT(final int T) {
    this.T = T;
  }
  
  public void readT(final String token) {
    T = intReader(token);
  }
  
  public void writeT() {
    try {
    	writer.write(""+(int)T);
    } catch (Exception e){}
  }
  
  private String wujud;
  
  public String getWujud() {
    return this.wujud;
  }
  
  public void setWujud(final String wujud) {
    this.wujud = wujud;
  }
  
  public void readWujud(final String token) {
    wujud = stringReader(token);
  }
  
  public void writeWujud() {
    try {
    	writer.write(""+(String)wujud);
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
          readT(tokens[0]);
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
          readWujud(tokens[0]);
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
    if (T<0) ok = true;
    return ok;
  }
  
  public boolean p2() {
    boolean ok = false;
    if (0<T) ok = true;
    return ok;
  }
  
  public boolean p3() {
    boolean ok = false;
    if (T<100) ok = true;
    return ok;
  }
  
  public boolean p4() {
    boolean ok = false;
    if (T>100) ok = true;
    return ok;
  }
  
  public boolean p5() {
    boolean ok = false;
    if (T==0) ok = true;
    return ok;
  }
  
  public boolean p6() {
    boolean ok = false;
    if (T==100) ok = true;
    return ok;
  }
  
  public int check(final String path) {
    int total_score = 0;
    total_score += s1(20,path);
    total_score += s2(20,path);
    total_score += s3(20,path);
    total_score += s4(20,path);
    total_score += s5(20,path);
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
          if (f.getPath().contains(".in")){
            readInput(f.getPath());
            num_files++;
            if (p1()) total_score = score;
          }
        }
      }
      if (num_files == 0) return 0;
      return total_score;
    } catch (Exception e){return 0;}
  }
  
  public int s2(final int score, final String path) {
    try {
      int total_score = 0;
      int num_files = 0;
      File root = new File(path);
      File[] list = root.listFiles();
      for (File f : list) {
        if (!f.isDirectory()){
          if (f.getPath().contains(".in")){
            readInput(f.getPath());
            num_files++;
            if (p2() && p3()) total_score = score;
          }
        }
      }
      if (num_files == 0) return 0;
      return total_score;
    } catch (Exception e){return 0;}
  }
  
  public int s3(final int score, final String path) {
    try {
      int total_score = 0;
      int num_files = 0;
      File root = new File(path);
      File[] list = root.listFiles();
      for (File f : list) {
        if (!f.isDirectory()){
          if (f.getPath().contains(".in")){
            readInput(f.getPath());
            num_files++;
            if (p4()) total_score = score;
          }
        }
      }
      if (num_files == 0) return 0;
      return total_score;
    } catch (Exception e){return 0;}
  }
  
  public int s4(final int score, final String path) {
    try {
      int total_score = 0;
      int num_files = 0;
      File root = new File(path);
      File[] list = root.listFiles();
      for (File f : list) {
        if (!f.isDirectory()){
          if (f.getPath().contains(".in")){
            readInput(f.getPath());
            num_files++;
            if (p5()) total_score = score;
          }
        }
      }
      if (num_files == 0) return 0;
      return total_score;
    } catch (Exception e){return 0;}
  }
  
  public int s5(final int score, final String path) {
    try {
      int total_score = 0;
      int num_files = 0;
      File root = new File(path);
      File[] list = root.listFiles();
      for (File f : list) {
        if (!f.isDirectory()){
          if (f.getPath().contains(".in")){
            readInput(f.getPath());
            num_files++;
            if (p6()) total_score = score;
          }
        }
      }
      if (num_files == 0) return 0;
      return total_score;
    } catch (Exception e){return 0;}
  }
}
