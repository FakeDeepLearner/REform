import Entities.AdminUser;
import Entities.NonAdminUser;
import Entities.UserContainer;
import databaseManagers.UsernamePasswordFileManager;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class AndrewMain {

    public static void main(String[] args) throws IOException {

        UsernamePasswordFileManager manager = new UsernamePasswordFileManager();

        ArrayList<ArrayList<String>> s = new ArrayList<>();

        s = manager.getUsersFromCSV("phase0/LoginSystem/src/databaseManagers/UsernamePassword.csv");

        System.out.println(s);

//        for (ArrayList<String> a : s){
//
//        }


    }
}
