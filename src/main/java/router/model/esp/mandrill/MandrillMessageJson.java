package router.model.esp.mandrill;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by shuang on 11/26/2014.
 */
@JsonAutoDetect
public class MandrillMessageJson {

    private String html;
    private String text;
    private String subject;
    private String from_email;

    private String from_name;

    private ToJson[] to;

    private HeadersJson headers;

    private boolean important;

    private boolean track_opens;

    private boolean track_clicks;

    private boolean auto_text;

    private boolean auto_html;

    private boolean inline_css;

    private boolean url_strip_qs;

    private boolean preserve_recipients;

    private boolean view_content_link;

    private String bcc_address;

    private String tracking_domain;

    private String signing_domain;

    private String return_path_domain;

    private boolean merge;

    private String merge_language;

    private MergeVars.MergeVar global_merge_vars;

    private MergeVars merge_vars;

    private String[] tags;

    private String subaccount;

    private String[] google_analytics_domains;


    private String[] google_analytics_campaign;


    private Object[] metadata;

    private RecipientMetadata[] recipient_metadata;

    private Attachment[] attachments;

    private Attachment[] images;

    public MandrillMessageJson(String html, String text, String subject, String from_name, String from_email, ToJson[] to, HeadersJson headers) {
        this.html = html;
        this.text = text;
        this.subject = subject;
        this.from_name = from_name;
        this.from_email = from_email;
        this.to = to;
        this.headers = headers;
    }

    public String getHtml() {
        return html;
    }

    public String getText() {
        return text;
    }

    public String getSubject() {
        return subject;
    }

    public String getFrom_email() {
        return from_email;
    }

    public String getFrom_name() {
        return from_name;
    }

    public ToJson[] getTo() {
        return to;
    }

    public HeadersJson getHeaders() {
        return headers;
    }

    public boolean isImportant() {
        return important;
    }

    public boolean isTrack_opens() {
        return track_opens;
    }

    public boolean isTrack_clicks() {
        return track_clicks;
    }

    public boolean isAuto_text() {
        return auto_text;
    }

    public boolean isAuto_html() {
        return auto_html;
    }

    public boolean isInline_css() {
        return inline_css;
    }

    public boolean isUrl_strip_qs() {
        return url_strip_qs;
    }

    public boolean isPreserve_recipients() {
        return preserve_recipients;
    }

    public boolean isView_content_link() {
        return view_content_link;
    }

    public String getBcc_address() {
        return bcc_address;
    }

    public String getTracking_domain() {
        return tracking_domain;
    }

    public String getSigning_domain() {
        return signing_domain;
    }

    public String getReturn_path_domain() {
        return return_path_domain;
    }

    public boolean isMerge() {
        return merge;
    }

    public String getMerge_language() {
        return merge_language;
    }

    public MergeVars.MergeVar getGlobal_merge_vars() {
        return global_merge_vars;
    }

    public MergeVars getMerge_vars() {
        return merge_vars;
    }

    public String[] getTags() {
        return tags;
    }

    public String getSubaccount() {
        return subaccount;
    }

    public String[] getGoogle_analytics_domains() {
        return google_analytics_domains;
    }

    public String[] getGoogle_analytics_campaign() {
        return google_analytics_campaign;
    }

    public Object[] getMetadata() {
        return metadata;
    }

    public RecipientMetadata[] getRecipient_metadata() {
        return recipient_metadata;
    }

    public Attachment[] getAttachments() {
        return attachments;
    }

    public Attachment[] getImages() {
        return images;
    }

    public class Attachment {
        private String type;
        private String name;
        private String content;

        public Attachment(String type, String name, String content) {
            this.type = type;
            this.name = name;
            this.content = content;
        }

        public String getType() {
            return type;
        }

        public String getName() {
            return name;
        }

        public String getContent() {
            return content;
        }
    }

    public class RecipientMetadata {
        private String rcpt; //the email address of the recipient that the metadata is associated with
        private Object[] values;

        public RecipientMetadata(String rcpt, Object[] values) {
            this.rcpt = rcpt;
            this.values = values;
        }

        public String getRcpt() {
            return rcpt;
        }

        public Object[] getValues() {
            return values;
        }
    }


    
    public static class HeadersJson {
        @JsonProperty("Reply-To")
        private String replyTo;

        public HeadersJson(String replyTo) {
            this.replyTo = replyTo;
        }

        public String getReplyTo() {
            return replyTo;
        }
    }


    public class MergeVars {
        @JsonProperty("rcpt")
        private String rcptAddress;

        @JsonProperty("vars")
        private MergeVar[] merge_vars;

        public class MergeVar {
            private String name;
            private String content;

            public MergeVar(String name, String content) {
                this.name = name;
                this.content = content;
            }
        }

    }


    public static class ToJson {
        private String email;
        private String name;

        private String type;


        public ToJson(String email, String name, String type) {
            this.email = email;
            this.name = name;
            this.type = type;
        }


        public String getEmail() {
            return email;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }


    }

}
