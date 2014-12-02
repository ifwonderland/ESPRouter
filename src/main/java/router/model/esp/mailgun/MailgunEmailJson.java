package router.model.esp.mailgun;

import router.model.AbstractEmailJson;

/**
 * Actually this is a form encoded data, but JSON format is fine too.
 * Created by shuang on 12/1/2014.
 */
public class MailgunEmailJson extends AbstractEmailJson {

    private String from;

    private String to;

    private String subject;

    private String text;

    public MailgunEmailJson(String from, String to, String subject, String text) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }
}
