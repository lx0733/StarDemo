package com.lx.bale;

public class Weired {     
    public static void main(String[] args) {     
        A a = new A();     
        C c = new C();     
        c.shoutc(a.shout(5));     
    }     
}     
////////////////////////////////////////////////////////     
class A {     
    public void shouta() {     
        System.out.println("Hello A");     
    }     

    public A shout(final int arg) {     
        class B extends A {     
            public void shouta() {     
                System.out.println("Hello B" + arg);     
            }     
        }     
        return new B();     
    }     
}     
////////////////////////////////////////////////////////     
class C {     
    void shoutc(A a) {     
        a.shouta();     
    }     
}
