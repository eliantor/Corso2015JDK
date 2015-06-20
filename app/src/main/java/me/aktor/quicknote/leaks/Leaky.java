package me.aktor.quicknote.leaks;

/**
 * Created by Andrea Tortorella on 6/20/15.
 */
public class Leaky {

    MyThread2 t2;
    public void doWork(){

        new Thread(){
            @Override
            public void run() {
                super.run();
                Leaky.this.toString();
            }
        }.start();

        MyThread t = new MyThread();
        t.start();

        t2 = new MyThread2(this);
        t2.start();
    }

    private void  stop(){
        t2.container = null;
    }

    public static class MyThread2 extends Thread{

        private Leaky container;
        MyThread2(Leaky l){
            container = l;

        }
        @Override
        public void run() {
            super.run();
            container.toString();
        }
    }

    public class MyThread extends Thread{

        @Override
        public void run() {
            super.run();
            Leaky.this.toString();
        }
    }


}
