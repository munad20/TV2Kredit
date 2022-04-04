package dk.grp15.krediteringssystem.persistence;

import dk.grp15.krediteringssystem.logic.credits.Contributor;
import dk.grp15.krediteringssystem.logic.credits.Production;
import dk.grp15.krediteringssystem.logic.user.Permission;
import dk.grp15.krediteringssystem.logic.user.Producer;
import dk.grp15.krediteringssystem.logic.user.SystemAdministrator;
import dk.grp15.krediteringssystem.logic.user.User;
import dk.grp15.krediteringssystem.logic.user.SystemAdministrator;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class DataController {

    public User login(String username, String password, Permission permission) {
        User user = null;
        String SQL = "SELECT * FROM users WHERE username = '" + username + "'";
        DatabaseHandler.getInstance().processQuery(SQL);
        if (DatabaseHandler.getInstance().getCurrentData().size() > 0) {
            int resultId = Integer.parseInt(DatabaseHandler.getInstance().getCurrentData().get(0).get(0));
            String resultUsername = DatabaseHandler.getInstance().getCurrentData().get(0).get(1);
            String resultPassword = DatabaseHandler.getInstance().getCurrentData().get(0).get(2);
            String resultPermission = DatabaseHandler.getInstance().getCurrentData().get(0).get(3);
            System.out.println(resultUsername + "   " + resultPassword);
            if (password.equals(resultPassword)) {
                if (resultPermission.equals("SYSADMIN")) {
                    user = new SystemAdministrator(username, password, permission);
                    user.setId(resultId);
                    user.setPermission(Permission.SYSADMIN);
                } else if (resultPermission.equals("CREDITS_MANAGER")) {
                    user = new Producer(username, password, permission);
                    user.setId(resultId);
                    user.setPermission(Permission.CREDITS_MANAGER);
                } else if (resultPermission.equals("PRODUCER")) {
                    user = new Producer(username, password, permission);
                    user.setId(resultId);
                    user.setPermission(Permission.PRODUCER);
                }
            }
        }

        return user;
    }

    public void createProduction(Production production) {
        String title = production.getTitle();
        int lengthHours = production.getLengthHours();
        int lengthMinutes = production.getLengthMinutes();
        Date releaseDate = production.getReleaseDate();
        String resume = production.getResume();
        int owner = production.getOwner_id();
        boolean visible = production.isVisible();
        String SQL = "INSERT INTO productions(title, length_hours, length_minutes, release_date, description, owner_id, visible) VALUES(" +
                "'" + title + "', '" + lengthHours + "', '" + lengthMinutes + "', '" + releaseDate + "', '" + resume + "', '" + owner + "', '" + visible + "')";

        DatabaseHandler.getInstance().handleStatement(SQL);
    }

    public void createContributor(Contributor contributor) {
        String name = new String(contributor.getName().getBytes(), StandardCharsets.UTF_8);
        String description = new String(contributor.getDescription().getBytes(), StandardCharsets.UTF_8);
        String SQL = "INSERT INTO contributors(name, description) VALUES('" + name + "', '" + description + "')";

        DatabaseHandler.getInstance().handleStatement(SQL);
    }

    public void createUser(SystemAdministrator systemAdministrator) {
        String username = systemAdministrator.getUsername();
        String password = systemAdministrator.getPassword();
        Permission permission = systemAdministrator.getPermission();
        String SQL = "INSERT INTO contributors(username, permission) VALUES('" + username + "', '" + permission + "')";
        DatabaseHandler.getInstance().handleStatement(SQL);

    }
}
