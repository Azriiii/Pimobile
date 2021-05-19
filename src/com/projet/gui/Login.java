/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.gui;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.projet.entites.User;
import com.projet.entites.UserStatic;
import com.projet.services.UserCRUD;
import java.util.ArrayList;

/**
 *
 * @author Amine
 */
public class Login extends Form {

    public Login() {
        TextField temail = new TextField("email");
        TextField tpas = new TextField("password");
        Button btn = new Button("se connecter");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((temail.getText().length() == 0) || (tpas.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));

                } else {
                    try {
                        User u = new User(temail.getText(), tpas.getText());
                        if (UserCRUD.getInstance().login(u)) {

                            ArrayList<User> liste = UserCRUD.getInstance().getAllOUsers(temail.getText(), tpas.getText());
                            for (User c : liste) {
                                User ucon = new User(c.getNom(), u.getPrenom(), c.getEmail(), c.getPassword());
                                UserStatic.utilisateur = ucon;
                                Dialog.show("Success", "Welcome "+c.getNom(), new Command("OK"));
                                        new Profil().show();
                            }
                        } else {
                            Dialog.show("ERROR", "erreur check information", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }
            }
        });
        addAll(temail, tpas, btn);
    }

}
