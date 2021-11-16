package server;
import java.net.*;

import server.server.Hasmap;

import java.io.*;


public class ServerThread extends Thread
{
    ServerSocket server=null;
    Socket client =null;
    String stringaRicevuta=null;
    String stringaModificata=null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    Hasmap m;

    public ServerThread(Socket socket, ServerSocket server,Hasmap m)
    {
        this.client=socket;
        this.server=server;
        this.m=m;
    }
    public void invia(String messaggio){
        outVersoClient.writeBytes(messaggio+'\n');
    }
    public void run()
    {
        try
        {
            comunica();
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
        }
    }
    public void comunica() throws Exception{
        inDalClient= new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoClient= new DataOutputStream(client.getOutputStream());
do{
boolean b=m.AggiungiNome(inDalClient.readLine(), this);
if(b==false){
    invia("Username ripetuto");
}else{
    invia("aggiunto con successo");
}
}
        while(b==false);
        for(;;)
        {
            stringaRicevuta=inDalClient.readLine();
           
            if(stringaRicevuta.indexOf("P")==0){
                m.Privato(stringaRicevuta);
            } 
            if(stringaRicevuta.indexOf("A")==0){
                m.Privato(stringaRicevuta);
            } 
           if( stringaRicevuta.equals("CHIUDI"))
           {
               outVersoClient.writeBytes(stringaRicevuta+"(-->socket in chiusura)"+'\n');
               System.out.println("Echo sul server in chiusura   :"+ stringaRicevuta);
               outVersoClient.close();
               inDalClient.close();
               client.close();
               break;
           }

        }
    }
}
