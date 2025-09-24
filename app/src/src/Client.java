package src;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Client extends Frame implements Runnable, ActionListener {


    TextField textField;
    TextArea textArea;
    Button send;

    ServerSocket serverSocket;
    Socket socket;

    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    Thread chat;




    Client(){

        textArea = new TextArea();
        textField = new TextField();
        send = new Button("Send");
        send.addActionListener(this);
        try{
            socket = new Socket("localhost",1234);

            dataInputStream = new DataInputStream(socket.getInputStream());

            dataOutputStream = new DataOutputStream(socket.getOutputStream());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        chat = new Thread(this);
        chat.setDaemon(true);
        chat.start();

        add(textArea);
        add(textField);
        add(send);


        setSize(500,500);
        setLayout(new FlowLayout());
        setTitle("Keerthana");
        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {


        String msg = textField.getText();
        textArea.append("Keerthana :"+msg+"\n");
        textField.setText("");

        try {
            dataOutputStream.writeUTF(msg);
            dataOutputStream.flush();
        } catch (IOException ex) {

        }


    }


    public static void main(String[] args) {
        new Client();

    }

    public void run() {
        while(true){
            try{
                String msg = dataInputStream.readUTF();
                textArea.append("Tharun :"+ msg +"\n");

            } catch (Exception e) {

            }
        }

    }
}

