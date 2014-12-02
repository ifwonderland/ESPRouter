package router.model.esp.mandrill;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
import router.model.AbstractEmailJson;

/**
 * Created by shuang on 11/26/2014.
 */
public class MandrillEmailJson extends AbstractEmailJson {


    private String key;

    private MandrillMessageJson message;

    private boolean async;


    @JsonProperty("ip_pool")
    private String ip_pool;

    private String send_at;

    public MandrillEmailJson(String key, MandrillMessageJson message) {
        this.key = key;
        this.message = message;
        this.async = false;
        this.ip_pool = "";
        this.send_at = "";
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public MandrillMessageJson getMessage() {
        return message;
    }

    public void setMessage(MandrillMessageJson message) {
        this.message = message;
    }

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public String getIp_pool() {
        return ip_pool;
    }

    public void setIp_pool(String ip_pool) {
        this.ip_pool = ip_pool;
    }

    public String getSend_at() {
        return send_at;
    }

    public void setSend_at(String send_at) {
        this.send_at = send_at;
    }
}
