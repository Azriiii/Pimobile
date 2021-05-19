/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.projet.entites.User;
import com.projet.utils.Statics;
import java.io.IOException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Amine
 */
public class UserCRUD {
     public ArrayList<User> users;
    public boolean test = false ;
    public static UserCRUD instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
     private UserCRUD() {
         req = new ConnectionRequest();
    }
    public static UserCRUD getInstance() {
        if (instance == null) {
            instance = new UserCRUD();
        }
        return instance;
    }
    public ArrayList<User> parseUser(String jsonText){
        try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> userlist = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)userlist.get("root");
              for(Map<String,Object> obj : list){
                User u = new User();
                //float id = Float.parseFloat(obj.get("id").toString());
              //  u.setId((int)id);
                u.setNom(obj.get("nom").toString());
                 u.setPrenom(obj.get("prenom").toString());
                  u.setEmail(obj.get("email").toString());
                   u.setPassword(obj.get("password").toString());
                   
                users.add(u);
            }
        } catch (IOException ex) {
            
        }
        return users;
    }
     public boolean verif(String jsonText){
        try {
            users=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> userlist = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)userlist.get("root");
              for(Map<String,Object> obj : list){
                   return true ;
            }
        } catch (IOException ex) {
            
        }
        return false ;
    }
      public boolean delete(String mail,String pass) {                
        String url = Statics.BASE_URL + "/json_delete/"+mail+"/"+pass ;
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     public boolean login(User u){
         
        String url = Statics.BASE_URL+"/json_user/"+u.getEmail()+"/"+u.getPassword();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                test= verif(new String(req.getResponseData()));
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return test;
    }
     public ArrayList<User> getAllOUsers(String email,String password){
        String url = Statics.BASE_URL+"/json_user/"+email+"/"+password ;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
}
