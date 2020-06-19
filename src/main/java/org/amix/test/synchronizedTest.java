package org.amix.test;

public class synchronizedTest implements Runnable {
    //共享资源
    static int i =0;
    
    static int num=20;
    
    static String[] names=new String[num];
    
    
    public void fillName(){
    	for (int j =0 ; j<20;j++){
    		names[j]="N"+j;
        }
    }
    
    /**
     * synchronized 修饰实例方法
     */
    public synchronized void increase(){
        i++;
        System.out.println(names[i]);
    }
    
    @Override
    public void run(){
        for (int j =0 ; j<10000;j++){
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        synchronizedTest test = new synchronizedTest();
        synchronizedTest test2 = new synchronizedTest();
        test.fillName();
        
        Thread t1 = new Thread(test);
        Thread t2 = new Thread(test2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
