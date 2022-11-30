package com.example.study;

import java.awt.*;
import java.io.*;

public class FileReaderEx1 {
    public static void main(String[] args) {
        InputThread inThread = new InputThread("InputThread");
        OutputThread outThread = new OutputThread("OutputThread");

        //PipedReader와 PipedWriter를 연결한다.
        inThread.connect(outThread.getOutput());
        inThread.start();
        outThread.start();

    }
}
class InputThread extends Thread{
    PipedReader input = new PipedReader();
    StringWriter sw = new StringWriter();
    InputThread(String name){
        super(name); //Thread(String name);
    }
    public void run(){
        try {
            int data = 0;
            while((data=input.read()) != -1){
                sw.write(data);
            }
            System.out.println(getName()+" received:"+sw.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//run
    public PipedReader getInput(){
        return input;
    }
    public void connect(PipedWriter output){
        try {
            input.connect(output);
        }catch (IOException e){}
    }//connect

}
class OutputThread extends Thread{
    PipedWriter output = new PipedWriter();
    OutputThread(String name){
        super(name);    //Thread(String name);
    }
    public void run(){
        try {
            String msg = "Hello";
            System.out.println(getName()+" sent : "+msg);
            output.write(msg);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//run

    public PipedWriter getOutput() {
        return output;
    }
    public void connect(PipedReader input){
        try {
            output.connect(input);
        }catch (IOException e){

        }
    }
}
/*두 쓰레드가 PipedReader/PipedWriter를 이용해서 서로 메시지를 주고받는 예제이다.
   쓰레드를 시작하기 전에 PipedReader와 PipedWriter를 연결해야한다는 것에 유의하자.
 */