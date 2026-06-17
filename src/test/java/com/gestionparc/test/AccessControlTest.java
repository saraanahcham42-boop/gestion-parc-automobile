package com.gestionparc.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.gestionparc.model.Admin;
import com.gestionparc.model.Chauffeur;
import com.gestionparc.security.AccessControl;

/**
 * Tests unitaires pour le contrôle d'accès
 */
public class AccessControlTest {

    @Test
    public void testAdminAccess() {
        Admin admin = new Admin("Admin", "Test", "admin@test.com", "password");
        assertTrue(AccessControl.hasAccess(admin, "MANAGE_USERS"));
        assertTrue(AccessControl.hasAccess(admin, "DELETE_VEHICLE"));
        assertEquals(5, AccessControl.getAccessLevel(admin));
    }

    @Test
    public void testChauffeurAccess() {
        Chauffeur chauffeur = new Chauffeur("Chauffeur", "Test", "chauffeur@test.com", "password", "ABC123", 1.80);
        assertTrue(AccessControl.hasAccess(chauffeur, "VIEW_MY_MISSIONS"));
        assertFalse(AccessControl.hasAccess(chauffeur, "MANAGE_USERS"));
        assertEquals(1, AccessControl.getAccessLevel(chauffeur));
    }

    @Test
    public void testNullUserAccess() {
        assertFalse(AccessControl.hasAccess(null, "VIEW_VEHICLES"));
        assertEquals(0, AccessControl.getAccessLevel(null));
    }

    @Test
    public void testCommonAccess() {
        Admin admin = new Admin("Admin", "Test", "admin@test.com", "password");
        assertTrue(AccessControl.hasAccess(admin, "VIEW_VEHICLES"));
    }
}
