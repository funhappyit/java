package com.example.study;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Customer implements Runnable
{
    private Table table;
    private String food;

    Customer(Table table,String food){
        this.table = table;
        this.food = food;
    }

    public void run() {
        while(true){
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){}
            String name = Thread.currentThread().getName();

            table.remove(food);
            System.out.println(name+ " ate a"+food);
        }//while
    }
}
class Cook implements Runnable{
    private Table table;

    Cook(Table table) {
        this.table = table;
    }

    public void run() {
        while (true){
            int idx = (int)(Math.random()*table.dishNum());
            table.add(table.dishNames[idx]);
            try{
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }//while
    }
}
class Table{
    String[] dishNames = {"donut","donut","burger"}; //donut의 확률을 높인다.
    final int MAX_FOOD = 6;
    private ArrayList<String> dishes = new ArrayList<>();

    private ReentrantLock lock = new ReentrantLock();
    private Condition forCook = lock.newCondition();
    private Condition forCust = lock.newCondition();
    public synchronized void add(String dish){
        lock.lock();
        try {
            while (dishes.size() >= MAX_FOOD){
                String name = Thread.currentThread().getName();
                System.out.println(name+" is waiting.");
                try{
                    forCook.await(); // wait(); Cook쓰레드를 기다리게 한다.
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            dishes.add(dish);
            forCust.signal();
            System.out.println("Dishes:"+dishes.toString());
        }finally {
            lock.unlock();
        }//end try1
    }
    public void remove(String dishName){
            lock.lock();
       // synchronized (this){
            String name = Thread.currentThread().getName();
            try{
                while(dishes.size()>0){
                    System.out.println(name+" is waiting.");
                    try {
                        forCust.await(); //wati(); CUST쓰레드를 기다리게 한다.
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                while(true){
                    for(int i=0; i<dishes.size();i++){
                        if(dishName.equals(dishes.get(i))){
                            dishes.remove(i);
                            forCook.signal(); //notify();잠자고 있는  cook을 깨움
                            return;
                        }
                    }//for문의 끝
                    try {
                        System.out.println(name+" is waiting.");
                        forCust.await(); //CUST쓰레드를 기다리게 한다.
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }//while(true)
            }finally {
                lock.unlock();
            }

    }
    public int dishNum(){
        return dishNames.length;
    }
}
    class ThreadWaitEx4{

        @Test
        public static void main(String[] args) throws Exception{
            Table table = new Table();
            new Thread(new Cook(table),"COOK1").start();
            new Thread(new Customer(table, "donut"),"CUST1").start();
            new Thread(new Customer(table,"burger"),"CUST2").start();

            Thread.sleep(2000);
            System.exit(0);
        }
    }


