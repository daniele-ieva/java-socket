package com.networking;

public class Message {
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

    private Status status_code = null;
    private String msg = null;

    public Message(Status status, String msg) {
        this.status_code = status;
        this.msg = msg;
    }
    public Message() {}

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
    public static Message parse(String msg) {
        if (msg == null) {
            return new Message(Status.ERROR, "Could not read message!");
        }
        String[] msg_array = msg.split("::");
        Status msg_status = Status.valueOf(msg_array[0]);
        String msg_str = msg_array[1];
        return new Message(msg_status, msg_str);
    }
    @Override
    public String toString() {
        return this.status_code.toString() + "::" + this.msg;
    }
}
