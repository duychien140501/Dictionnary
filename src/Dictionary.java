import java.io.*;
import java.util.Arrays;

public class Dictionary {
    private Word [] dictionary;

    public Dictionary () {
        try {
            Word ac = new Word();
            ac.setWord_target(" ");
            ac.setWord_explain(" ");
            dictionary = new Word[1];
            dictionary[0] = new Word();
            dictionary[0] = ac;
            File myFile = new File("E_V.txt");
            FileReader fileReader = new FileReader(myFile);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = null;
            while((line = reader.readLine()) != null) {
                int m = 0;
                for ( int i = 0; i< line.length(); i++) {
                    if (line.charAt(i) == '<') {
                        m = i;
                        break;
                    }
                }
                Word a = new Word();
                a.setWord_target(line.substring(0, m));
                a.setWord_explain(line.substring(m, line.length()));
                dictionary = Arrays.copyOf(dictionary, dictionary.length + 1);
                dictionary[dictionary.length - 1] = new Word();
                dictionary[dictionary.length - 1] = a;

            }
            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void add(String target,String explane) {
        if (search(dictionary, target, 0, dictionary.length - 1) < 0) {
            Word a = new Word();
            String s= "<html><i>"+target+"</i><br/><ul><li><font color='#cc0000'><b>"+explane+"</b></font></li></ul></html>";
            a.setWord_target(target);
            a.setWord_explain(s);
            dictionary = Arrays.copyOf(dictionary, dictionary.length + 1);
            dictionary[dictionary.length - 1] = new Word();
            dictionary[dictionary.length - 1] = a;
        }
    }

    public void  remove(String target){
        if(search(dictionary,target,0,dictionary.length-1)>=0){
            dictionary[search(dictionary,target,0,dictionary.length-1)].setWord_target(null);
            dictionary[search(dictionary,target,0,dictionary.length-1)].setWord_explain(null);
            dictionary = Arrays.copyOf(dictionary, dictionary.length -1);
        }
    }
    public void fixWord(String target){

    }
    public int search(Word[] words, String s, int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (s.compareTo(words[mid].getWord_target()) == 0) {
                return mid;
            }
            if (s.compareTo(words[mid].getWord_target()) > 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }


    public String get(String s) {
        if (search(dictionary, s, 0, dictionary.length - 1) >= 0) {
            return dictionary[search(dictionary, s, 0, dictionary.length - 1)].getWord_explain();
        } else {
            return " ";
        }
    }
    public boolean check_w(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        else {
            if (s1.equals(s2.substring(0, s1.length()))) {
                return true;
            }
        }
        return false;
    }
    public String [] Suggest(String s) {
        String [] suggest = new String[30];
        for (  int i = 0; i< 30; i++) {
            suggest[i] = " ";
        }
        int suggest_n = 0;
        for ( int i = 0; i< dictionary.length ; i++) {
            if (check_w(s, dictionary[i].getWord_target()) && suggest_n < 30) {
                suggest[suggest_n] = dictionary[i].getWord_target();
                suggest_n++;
            }
        }
        return suggest;
    }
}