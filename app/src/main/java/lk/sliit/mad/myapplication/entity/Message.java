package lk.sliit.mad.myapplication.entity;

public class Message {
    private long key;
    private String user;
    private String subject;
    private String message;

    public Message( String user, String subject, String message) {

        this.user = user;
        this.subject = subject;
        this.message = message;
    }

    public Message() {
    }

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "key=" + key +
                ", user='" + user + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

