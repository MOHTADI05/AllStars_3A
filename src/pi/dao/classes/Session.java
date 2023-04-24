/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dao.classes;
import java.util.HashMap;
import java.util.Map;
import pi.entities.Utilisateur;

/**
 *
 * @author swide
 */
public class Session {
    private static Session instance = null;
    private Utilisateur loggedUser;

    private Session() {}

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public void setLoggedUser(Utilisateur user) {
        loggedUser = user;
    }

    public Utilisateur getLoggedUser() {
        return loggedUser;
    }

    public void clear() {
        loggedUser = null;
    }
}

