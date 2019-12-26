package sch.xmut.jake.imagestegangraphy.domain.api;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by jake.lin on 2019/12/26
 */
@Entity
@Table(name = "api_sms")
public class ApiSmsEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "sms_content_type")
    private String smsContentType;
    @Column(name = "sms_uid")
    private String smsUid;
    @Column(name = "sms_key")
    private String smsKey;
    @Column(name = "sms_text")
    private String smsText;
    @Column(name = "encrypt")
    private String encrypt;
    @Column(name = "encryption_times")
    private Integer encryptionTimes;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSmsContentType() {
        return smsContentType;
    }

    public void setSmsContentType(String smsContentType) {
        this.smsContentType = smsContentType;
    }

    public String getSmsUid() {
        return smsUid;
    }

    public void setSmsUid(String smsUid) {
        this.smsUid = smsUid;
    }

    public String getSmsKey() {
        return smsKey;
    }

    public void setSmsKey(String smsKey) {
        this.smsKey = smsKey;
    }

    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }

    public Integer getEncryptionTimes() {
        return encryptionTimes;
    }

    public void setEncryptionTimes(Integer encryptionTimes) {
        this.encryptionTimes = encryptionTimes;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
