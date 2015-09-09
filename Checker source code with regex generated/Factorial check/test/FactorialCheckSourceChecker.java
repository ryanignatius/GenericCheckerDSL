import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("all")
public class FactorialCheckSourceChecker {
  private Scanner sc;
  
  private BufferedReader buf;
  
  private FileReader fr;
  
  private File fl;
  
  private FileWriter fw;
  
  private InputStream is;
  
  private InputStreamReader ir;
  
  private FileInputStream fi;
  
  private Pattern pat;
  
  private Matcher mat;
  
  private BufferedWriter writer;
  
  private static boolean is_valid;
  
  private static ArrayList cond_arr;
  
  private ArrayList source_arr;
  
  public static void die(final String message) {
    System.out.println(message);
    is_valid = false;
  }
  
  public String preProcess(final String line, final String separator) {
    String ret = line;
    ret = ret.trim();
    ret = ret.replaceAll("\\s+", separator);
    return ret;
  }
  
  public void readSource(final String path) {
    source_arr = new ArrayList<String>();
    InputStream fis;
    BufferedReader br;
    String cur;
    ArrayList<String> lineList = new ArrayList<String>();
    ArrayList<String> lineList2 = new ArrayList<String>();
    String line;
    try {
      fis = new FileInputStream(path);
      br = new BufferedReader(new InputStreamReader(fis));
      int level = 0;
      String[] bk = {"{"};
      String[] tk = {"}"};
      String[] c1 = {"//"};
      String[] bcm = {"/*"};
      String[] tcm = {"*/"};
      boolean isCom = false;
      cur = "";
      while((line = br.readLine()) != null) {
        line = preProcess(line," ");
        for (int i=0; i<c1.length; i++){
          if (line.contains(c1[i])){
            line = line.substring(0,line.indexOf(c1[i]));
            break;
          }
        }
        if (!isCom){
          for (int i=0; i<bcm.length; i++){
            if (line.contains(bcm[i])){
              if (line.contains(tcm[i])){
                line = line.substring(0,line.indexOf(bcm[i])) + line.substring(line.indexOf(tcm[i])+tcm[i].length());
              } else {
                isCom = true;
                line = line.substring(0,line.indexOf(bcm[i]));
              }
              break;
            }
          }
        }
        if (isCom){
          for (int i=0; i<tcm.length; i++){
            if (line.contains(tcm[i])){
              line = line.substring(line.indexOf(tcm[i])+tcm[i].length());
              isCom = false;
              break;
            }
          }
        }
        if (isCom) continue;
        cur += (line+"\n");
        for (int i=0; i<bk.length; i++){
          if (line.contains(bk[i])){
            level++;
            break;
          }
        }
        for (int i=0; i<tk.length; i++){
          if (line.contains(tk[i])){
            level--;
            break;
          }
        }
        if (level < 0) level = 0;
        if (line.contains("class")) level--;
        if (level < -1) level = -1;
        if (level <= 0){
          lineList2.add(cur);
          cur = "";
        }
      }
      fis.close();
      line = lineList2.get(0);
      for (int i=1; i<lineList2.size(); i++){
        if (line.length()>=2 && line.charAt(line.length()-2) == '>'){
          line = line + " " + lineList2.get(i);
        } else if (lineList2.get(i).length() < 2 || lineList2.get(i).charAt(0) == '{'){
          line = line + " " + lineList2.get(i);
        } else {
          lineList.add(line);
          line = lineList2.get(i);
        }
      }
      lineList.add(line);
    } catch (Exception e){
      e.printStackTrace();
    }
    source_arr = lineList;
  }
  
  public boolean p1() {
    boolean ok = false;
    for (int i=0; i<source_arr.size(); i++){
      String s = "(?s)(\\s*)\\bint\\b(\\s*)(\\s+)(\\s*)\\bfac\\b(\\s*)\\((\\s*)\\bint\\b(\\s*)(\\s+)(\\s*)\\bn\\b(\\s*)\\)(.*)(\\s*)\\bfac\\b(\\s*)\\((.*)(\\s*)\\bn\\b(\\s*)(.*)\\)";
      Pattern p = Pattern.compile(s);
      Matcher m = p.matcher((String)source_arr.get(i));
      if (m.find()){
        return true;
      }
    }
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
          if (f.getPath().contains(".cpp")){
            readSource(f.getPath());
            num_files++;
            if (p1()) total_score = score;
          }
        }
      }
      if (num_files == 0) return 0;
      return total_score;
    } catch (Exception e){return 0;}
  }
}
