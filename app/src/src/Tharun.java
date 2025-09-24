package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Tharun extends Frame implements Runnable, ActionListener {


    TextArea textArea;
    TextField textField;
    Button send;

    ServerSocket serverSocket;
    Socket socket;

    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    Thread chat;




    Tharun(){

        textArea = new TextArea();
        textField = new TextField();
        send = new Button("Send");
        send.addActionListener(this);
        try{
            serverSocket = new ServerSocket(1234);
            socket = serverSocket.accept();

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
        setLocation(50,100);
        setTitle("Tharun");
        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {


        String msg = textField.getText();
        textArea.append("Tharun :"+msg+"\n");
        textField.setText("");

        try {
            dataOutputStream.writeUTF(msg);
            dataOutputStream.flush();
        } catch (IOException ex) {

        }


    }


    public static void main(String[] args) {

        new Tharun();

    }

    public void run() {
        while(true){
            try{
                String msg = dataInputStream.readUTF();
                textArea.append("Keerthana :"+ msg +"\n");

            } catch (Exception E) {

            }
        }

    }
}
