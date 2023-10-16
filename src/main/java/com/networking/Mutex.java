package com.networking;

public class Mutex {
    private volatile boolean status;

    public Mutex(boolean status) {
        this.status = status;
    }
    public boolean status() {
        return this.status;
    }
    public void swap() {
        this.status = !this.status;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Mutex) {
            return ((Mutex) obj).status == this.status;
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).equals(this.status);
        }
        return super.equals(obj);
    }
}
