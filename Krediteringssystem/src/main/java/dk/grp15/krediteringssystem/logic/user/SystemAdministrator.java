package dk.grp15.krediteringssystem.logic.user;

//import dk.grp15.krediteringssystem.credits.Production;
import dk.grp15.krediteringssystem.logic.user.Permission;
import dk.grp15.krediteringssystem.logic.user.Producer;
import dk.grp15.krediteringssystem.logic.user.TV2Employee;
import jdk.jfr.Description;

import java.util.ArrayList;

public class SystemAdministrator extends User {

    User newUser;

    private static ArrayList<User> users = new ArrayList();
    private Object Description;


    public SystemAdministrator(String username, String password, Permission permission) {

        super(username, password, permission);
    }

    public void removeProduction(int id) {}



    @Override
    public void createUser(String username, String password,Permission permission) {
        if(permission == Permission.PRODUCER) {
            newUser = new Producer(username, password, permission);
        }
        else if(permission == Permission.CREDITS_MANAGER) {
            newUser = new TV2Employee(username, password, permission);
        }
        else if(permission == Permission.SYSADMIN) {
            newUser = new SystemAdministrator(username, password, permission);
        }

        users.add(newUser);
    }



    private static SystemAdministrator getInstance() {
        return null;
    }

    @Override
    public void showUser(int id) {
        for(User user : users) {
            if(user.getId() == id){
                System.out.println(user.toString());
            }
        else if(user.getId()!=id){
                System.out.println("Brugeren med id nummer " + id + " findes ikke");
                break;
            }

        }
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Permission getPermission() {
        return permission;
    }


    public void updateUser(int id) {



    }



    public void getUsers() {

    }

    public void setUsers(String users) {

    }

    public String getDescription() {

        return null;
    }

    public void setDescription(String description) {
        //this.description = description;
    }
}
