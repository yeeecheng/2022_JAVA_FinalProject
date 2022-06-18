package com.plcoding.instagramui.saveplace.mainActivity;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;

import kotlin.text.Charsets;

public class clientSocket {

    private String ip;
    private int port;
    private Socket sc ;
    private OutputStream clientOutputStream;
    private InputStream clientInputStream;
    private String Data;

    public boolean isConnected ;


    public clientSocket(String Ip,int Port){
        Log.d("ppppp","!!!!!!!!!");
        ip =Ip;
        port= Port;
        sc =null;
        clientOutputStream=null;
        clientInputStream=null;
        isConnected =false;
        Data="";
    }


    public void initConnect(){
        try{
            InetSocketAddress addr = new InetSocketAddress(ip,port);
            sc =new Socket();
            sc.connect(addr,10000);

            clientInputStream =sc.getInputStream();
            clientOutputStream=sc.getOutputStream();

            if(sc!=null && clientInputStream!=null && clientOutputStream !=null){
                isConnected =true;
            }
            else {
                initConnect();
            }


        }
        catch (SocketTimeoutException e) {
            Log.d("client","timeout!! connect failed,now retry...");
            e.printStackTrace();
            initConnect();
        }
        catch(IOException e){

            e.printStackTrace();
        }
    }

    public void checkDataVersion(String version) {
        sendMessage("UPDATE|"+version);
    }

    public void uploadDataVersion(String version){
        sendMessage("DOWNLOAD|"+version);
    }

    public void addNewStore(String name,String type,String info,String addr,int ts,int te){
        sendMessage("NEWST|$name|$type|$info|$addr|$ts|$te");
    }

    //send request of otp
    public void sendRequestOfOtp(){
        sendMessage("SHARE|0");
    }

    //check otp message to get id
    public void checkOTPToGetId(String otp){
        sendMessage("VYOTP|$otp");
    }


    public void sendMessage(String message){
        String msg = message;
        try{

            if(isConnected){
                if(clientOutputStream != null && msg != null){

                    byte[] msgToBytes =msg.getBytes(Charsets.UTF_8);
                    clientOutputStream.write(msgToBytes);
                    clientOutputStream.flush();

                    Log.d("client","send message successful");

                }
                else {

                    Log.d("client","The message to be sent is empty or have no connect");

                }

            }
            else {
                Log.d("client","no connect to send message");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receiveMessage(){
        try{

            if(isConnected){
                Data="";

                byte[] readMsg = new byte[4];
                int msgLen =clientInputStream.read(readMsg);
                int dataSize = ((int)readMsg[0] & 0xFF << 24) | ((int)readMsg[1] & 0xFF << 16) | ((int)readMsg[2] & 0xFF << 8) | ((int)readMsg[3] & 0xFF);
                int currentDataSize =0;
                byte[] data = new byte[0];

                while(currentDataSize!=dataSize){
                    readMsg = new byte[1024];
                    msgLen =clientInputStream.read(readMsg);
                    if(msgLen<0){
                        break;
                    }
                    if(readMsg.length != msgLen){
                        byte[] str = new byte[msgLen];
                        System.arraycopy(readMsg,0,str,0,msgLen);
                        readMsg=str;
                    }

                    byte[] con = new byte[data.length+msgLen];
                    System.arraycopy(data,0,con,0,data.length);
                    System.arraycopy(readMsg,0,con,data.length,msgLen);
                    currentDataSize += msgLen;
                    data = con;
                }
                Data  = new String(data,0,data.length);


                return Data;
            }
            else {
                Log.d("client","no connect to receive message");

            }


        } catch (IOException e) {
            Log.i("client","receive message failed");
            e.printStackTrace();
        }
        return "";
    }


    public void closeConnect(){
        try{
            if(clientInputStream!=null){

                clientInputStream.close();
            }
            if(clientOutputStream!=null){

                clientOutputStream.close();
            }
            if(sc!=null){

                sc.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        isConnected=false;
        Log.d("client","close connect");
    }

}
