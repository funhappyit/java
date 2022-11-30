package com.example.study;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class IOEx1 {
    public static void main(String[] args) {
        byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
        byte[] outSrc = null;
        byte[] temp = new byte[4];

        ByteArrayInputStream input = null;
        ByteArrayOutputStream output = null;

        input = new ByteArrayInputStream(inSrc);
        output = new ByteArrayOutputStream();

        System.out.println("Input Source:"+Arrays.toString(inSrc));

        try {
            while (input.available() >0){
                int len = input.read(temp); //읽어 온 데이터의 개수를 반환합니다.
                output.write(temp,0,len);
                System.out.println("len:"+len);
                outSrc = output.toByteArray();
                printArrays(temp,outSrc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void printArrays(byte[] temp, byte[] outSrc){
        System.out.println("temp   :"+Arrays.toString(temp));
        System.out.println("Output source:"+Arrays.toString(outSrc));
    }
}
