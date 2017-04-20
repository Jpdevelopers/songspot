package com.example.ishaandhamija.sunodilse;

import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;


class ServerAsynkTask extends AsyncTask<Void,Void,Void> {

    List<String> persons;
    @Override
    protected Void doInBackground(Void... params) {
        try {
            if(Looper.myLooper()==null)
                Looper.prepare();

            Log.e("NETWORKKK","doinnnn");

            Boolean end = false;
            ServerSocket ss = new ServerSocket(12345);
            Log.e("NETWORKKK","server created");
            while(!end){
                try
                {
                    //Server is waiting for client here, if needed
                    Log.e("NETWORKKK","inloop");
                    Socket s = ss.accept();
                    Log.d("NETWORKKK", "connected to client");

                /*BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
//               PrintWriter output = new PrintWriter(s.getOutputStream(),true); //Autoflush
                String st = input.readLine();
                Log.d("NETWORKKK", "From client: "+st);*/
//                ByteArrayInputStream bis = new ByteArrayInputStream(bytesFromSocket);
                    ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                    persons = (List<String>) ois.readObject();
                    Log.e("server",persons.toString());
                    s.close();
//                if (st!=null){
//                    end = true;
//                }

                }
                catch (Exception e)
                {
                    Log.e("EXXCEE",e.getMessage());
                }
            }
            ss.close();


        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.d("ERROR", "onCreate: "+ e.getMessage());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.d("ERROR", "onCreate: "+ e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}