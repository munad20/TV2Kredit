package dk.grp15.krediteringssystem.logic.test;

import dk.grp15.krediteringssystem.logic.user.Permission;
import dk.grp15.krediteringssystem.logic.user.Producer;
import dk.grp15.krediteringssystem.logic.user.SystemAdministrator;
import dk.grp15.krediteringssystem.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SystemAdministratorTest {

    @Test
    public void createUser() {
        SystemAdministrator user = new SystemAdministrator
                ("Muhamad2020", "kode123", Permission.SYSADMIN);
        assertEquals("Muhamad2020", user.getUsername());
        assertEquals("kode123", user.getPassword());
        assertEquals(Permission.SYSADMIN, user.getPermission());

    }
}

