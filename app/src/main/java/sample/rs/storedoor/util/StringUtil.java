package sample.rs.storedoor.util;

public class StringUtil {

    public static String removeAmp(String string){
       return string.replaceAll("&amp;", "&");
    }
}
