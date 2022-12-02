
public class isValidEmail{
  public static Boolean isValidEmail(String str){
        if(str.contains("@")){
            String a[] = str.split("@");
            if(a[1].contains(".")){
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
