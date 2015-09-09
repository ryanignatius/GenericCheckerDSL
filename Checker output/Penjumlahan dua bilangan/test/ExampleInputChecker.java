import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("all")
public class ExampleInputChecker {
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
  
  private int x;
  
  public int getX() {
    return this.x;
  }
  
  public void setX(final int x) {
    this.x = x;
  }
  
  public void readX(final String token) {
    x = intReader(token);
  }
  
  public void writeX() {
    try {
    	writer.write(""+(int)x);
    } catch (Exception e){}
  }
  
  private int y;
  
  public int getY() {
    return this.y;
  }
  
  public void setY(final int y) {
    this.y = y;
  }
  
  public void readY(final String token) {
    y = intReader(token);
  }
  
  public void writeY() {
    try {
    	writer.write(""+(int)y);
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
      sz = 0+1+1;
      if ((line = reader.readLine()) != null){
        line = line.trim();
        line = line.replaceAll("\\s+", " ");
        tokens = line.split(" ");
        if (tokens.length == sz){
          readX(tokens[0]);
          readY(tokens[1]);
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
    int z;
    z = x+y;
    if (z==n) ok = true;
    return ok;
  }
  
  public boolean p2() {
    boolean ok = false;
    if (x>0) ok = true;
    return ok;
  }
  
  public boolean p3() {
    boolean ok = false;
    if (y>0) ok = true;
    return ok;
  }
  
  public boolean p4() {
    boolean ok = false;
    if (n>1) ok = true;
    return ok;
  }
  
  public boolean p5() {
    boolean ok = false;
    if (n<1000000) ok = true;
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
          if (f.getPath().contains(".in")){
            readInput(f.getPath());
            num_files++;
            if (p4() && p5()) total_score += score;
          }
        }
      }
      if (num_files == 0) return 0;
      return total_score/num_files;
    } catch (Exception e){return 0;}
  }
}
