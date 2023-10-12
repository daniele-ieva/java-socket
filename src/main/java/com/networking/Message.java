package com.networking;

public class Message {
    // Subclasses
    public enum Status {
        OK(0), ERROR(1);
        private final Integer code;

        Status(Integer code) {
            this.code = code;
        }
        public Integer code() {
            return this.code;
        }
    }

    // Attributes
    private static final Message stop = new Message(Status.OK, ".");
    private Status status_code = null;
    private String msg = null;

    // Constructors
    public Message(Status status, String msg) {
        this.status_code = status;
        this.msg = msg;
    }
    public Message() {}

    // Methods
    public static Message parse(String msg) {
        if (msg == null) {
            return new Message(Status.ERROR, "Could not read message!");
        }
        String[] msg_array = msg.split("::");
        Status msg_status = Status.valueOf(msg_array[0]);
        String msg_str = msg_array[1];
        return new Message(msg_status, msg_str);
    }

    // Getters
    public String message() {
        return this.msg;
    }

    public Status status() {
        return this.status_code;
    }
    public static Message stop() {
        return stop;
    }

    // Setters
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public void setStatus(Status status) {
        this.status_code = status;
    }

    public void ok() {
        this.setStatus(Status.OK);
    }
    public void error() {
        this.setStatus(Status.ERROR);
    }

    //Overloads
    @Override
    public String toString() {
        return this.status_code.toString() + "::" + this.msg;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            return ((Message) obj).msg.equals(this.msg) && ((Message) obj).status_code.equals(this.status_code);
        }
        return super.equals(obj);
    }
}
