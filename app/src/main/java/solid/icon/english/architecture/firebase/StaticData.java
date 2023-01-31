package solid.icon.english.architecture.firebase;


public class StaticData {

    public static String email = getEmail();
    public static String path = null; //before use required call FirebaseOperation.getPath();


    private static String getEmail(){
        return "admin@gmail.com";
    }
}
