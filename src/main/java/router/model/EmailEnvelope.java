package router.model;

/**
 * POJO class reprenting email envelope used for addressing
 *
 * Created by Shaochen Huang on 11/26/14.
 */
public class EmailEnvelope {

    public EmailEnvelope() {
    }

    public EmailEnvelope(String to, String to_name, String from, String from_name, String subject, String body) {
        this.to = to;
        this.to_name = to_name;
        this.from = from;
        this.from_name = from_name;
        this.subject = subject;
        this.body = body;
    }

    private String to;

    private String to_name;

    private String from;

    private String from_name;

    private String subject;

    private String body;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTo_name() {
        return to_name;
    }

    public void setTo_name(String to_name) {
        this.to_name = to_name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom_name() {
        return from_name;
    }

    public void setFrom_name(String from_name) {
        this.from_name = from_name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
