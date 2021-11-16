package server.server;
import java.util.HashMap;

import server.ServerThread;

public class Hasmap {
    HashMap <String,ServerThread> A= new HashMap<>();
    public Hasmap(){

    }
    public boolean AggiungiNome(String Nome,ServerThread Server){
        if(A.containsKey(Nome)){
          return false;
        }
        A.put(Nome,Server);

        for(String i : threadsMap.keySet()){
        A.get(i).invia(Lista);
        }
      if(A.size()==1){
          A.get(Nome).invia("Sei l'unico online");
      }
        return true;
    } 
    public boolean Privato(String stringa ){
        String[] messaggio=new String[0];
        messaggio=stringaRicevuta.split(":",3);
        A.get(messaggio[1]).invia(messaggio[2]);
    } 
    public boolean Pubblico(String stringa,String Nome){
        String[] messaggio=new String[0];
        messaggio=stringaRicevuta.split(":",2);   
        for(String i:A.keySet()){
            if(A.containsKey(Nome)){
                continue;
            }
        A.get(i).invia(messaggio[1]);
        }
    
    } 
    public String Lista(){
        String listaclient="NOMI: ";

        for(String i : threadsMap.keySet()){
            listaclient+=i+": ";
        }
        return listaclient;
    }
}
