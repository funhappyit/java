package com.example.study;


import java.io.*;

class FileViewer {
    public static void main(String args[]) throws IOException {
        int[] score = {100,90,95,85,40};
        try {
            FileOutputStream fos = new FileOutputStream("score.dat");
            DataOutputStream dos = new DataOutputStream(fos);

            for(int i=0;i<score.length;i++){
                dos.writeInt(score[i]);
            }
            dos.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
