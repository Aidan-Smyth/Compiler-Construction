import java.io.*;
import java.nio.charset.Charset;
import java.util.Hashtable;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Write a description of class scanc here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class scanc
{
    // instance variables - replace the example below with your own
    private int x;
    private File file;

    int content;
    int next;
    int charsRead;

    BufferedReader fr;

    /**
     * Constructor for objects of class scanc
     */
    public scanc()
    {
        file = new File("doc.txt");

        content = 0;
        next = 0;
        charsRead = 0;

        try (BufferedReader reader =
            Files.newBufferedReader(Paths.get("doc.txt"), StandardCharsets.UTF_8)) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void sampleMethod()
    {
        while(content != -1){
            iterate();
            System.out.print((char) content);
        }
    }

    public void sample2(){
        try (BufferedReader reader =
            Files.newBufferedReader(Paths.get("doc.txt"), StandardCharsets.UTF_8)) {
            int line;
            reader.mark(512);
            while ((line = reader.read()) != -1) {
                reader.reset();
                reader.skip(charsRead);
                charsRead++;
                line = reader.read();
                if(line == -1){
                    break;
                }

                System.out.print((char) line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void firstchar(){
        
    }
    
    public String getToken(String lexeme){
        // Call the respective scan method based on content's current character
        // After that method, should be on the character after that lexeme
        return "dummy";
    }
    
    public String scanWord(){
        
        return "dummy";
    }
    
    public String scanNum(){
        
        return "dummy";
    }
    
    // Don't need to check for anything because it's called already and is only one character
    public String scanOp(){
        String lex = "";
        lex += (char) content;
        iterate();
        return lex;
    }

    // If all goes well, this is the only method that needs to read stuff
    public void iterate(){
        try (BufferedReader reader =
            Files.newBufferedReader(Paths.get("doc.txt"), StandardCharsets.UTF_8)) {
            content = next;
            
            //reader.mark(512);
            //reader.reset();
            reader.skip(charsRead);
            charsRead++;
            next = reader.read();;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
