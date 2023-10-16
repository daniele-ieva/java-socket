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

    /**
     * Constructs a new Message with the given status and message text.
     * the status argument is defined in the enumerator Message.Status and it can assume the values of OK or ERROR
     * @param status a status code for the message, currently available: ERROR, OK
     * @see Message.Status
     * @param msg the body of the message to be represented by this class
     **/
    public Message(Status status, String msg) {
        this.status_code = status;
        this.msg = msg;
    }
    /**
     * DO NOT USE, this constructor is ONLY for testing, use the other constructor instead!
     **/
    public Message() {}

    /**
     * Parse a string formatted as Message.toString() ("STATUS::MESSAGE") and convert it into a new Message if able,
     * else, return an error message with status ERROR
     * @param msg the string representation of a message defined as STATUS::MESSAGE
     * @see Message.Status
     * @return a new message representing the given string
    **/
    public static Message parse(String msg) {
        if (msg == null) {
            return new Message(Status.ERROR, "Could not read message!");
        }
        String[] msg_array = msg.split("::");
        Status msg_status = Status.valueOf(msg_array[0]);
        String msg_str = msg_array[1];
        return new Message(msg_status, msg_str);
    }

    /**
     * @return the content of the message
    **/
    public String message() {
        return this.msg;
    }

    /**
     * @return the status code of the message
     * @see Message.Status
     **/
    public Status status() {
        return this.status_code;
    }
    /**
     * Return the stop message defined in this class "OK::."
     * @return a stop message with string representation "OK::."
    **/
    public static Message stop() {
        return stop;
    }

    /**
     * Replace the content of the message
     * @param msg the new content to use
    **/
    public void setMsg(String msg) {
        this.msg = msg;
    }
    /**
     * Replace the status of the message
     * @param status the new status of the message
     * @see Message.Status
     **/
    public void setStatus(Status status) {
        this.status_code = status;
    }
    /**
     * Simplified setter for Message.setStatus
     * Sets the status to OK
     **/
    public void ok() {
        this.setStatus(Status.OK);
    }
    /**
     * Simplified setter for Message.setStatus
     * Sets the status to ERROR
     **/
    public void error() {
        this.setStatus(Status.ERROR);
    }

    /**
     * Override of Object.toString()
     * return a string formatting the message in a human-readable way, and allowing it to be sent through a stream
     * @return format: "STATUS::MESSAGE"
     **/
    @Override
    public String toString() {
        return this.status_code.toString() + "::" + this.msg;
    }
    /**
     * Override of Object.Equals()
     * use the content of the messages and their status codes to compare two messages
    **/
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            return ((Message) obj).msg.equals(this.msg) && ((Message) obj).status_code.equals(this.status_code);
        }
        return super.equals(obj);
    }
}
