/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.projet.entites.User;
import com.projet.entites.UserStatic;
import com.projet.services.UserCRUD;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Amine
 */
public class Profil extends Form{

    public Profil() {
          setTitle("Profil");
         setLayout(BoxLayout.y());
          SpanLabel nom = new SpanLabel(UserStatic.utilisateur.getNom());
            SpanLabel prenom = new SpanLabel(UserStatic.utilisateur.getPrenom());
            SpanLabel email = new SpanLabel(UserStatic.utilisateur.getEmail());
            SpanLabel password = new SpanLabel(UserStatic.utilisateur.getPassword());
        Button btn = new Button("delete my account");
         ArrayList<User> liste = UserCRUD.getInstance().getAllOUsers(email.getText(), password.getText());
      btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  for (User c : liste) {
                      //System.out.println(c.getId());
              UserCRUD.getInstance().delete(UserStatic.utilisateur.getEmail(),UserStatic.utilisateur.getPassword());
              new Login().show();
          }
            }
      });
        
        
          addAll(nom,prenom,email,password,btn);
    }
    
          
}
