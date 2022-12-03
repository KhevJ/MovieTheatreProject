public class is16Digits{
  public static Boolean is16Digits(String str){
        int len = str.length();
        if(len == 16){
            for(int i=0; i<len;i++){
                if(Character.isDigit(str.charAt(i))==false){
                    return false;
                }
            }
            return true;
        }
        else{
            return false;
        }
    }
}
