
public class isValidEmail{
  public static Boolean isValidEmail(String str){
        if(str.contains("@")){
            String a[] = str.split("@",2);
            
            if(a[1].contains(".")){
                String b[] = a[1].split("\\.",2);
                String checkall = a[0]+b[0]+b[1];
                int len = checkall.length();
                for(int i=0; i<len;i++){
                    if(Character.isLetterOrDigit(checkall.charAt(i))==false){
                        return false;
                    }
                }
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
  
  
}
