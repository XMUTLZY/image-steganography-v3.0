package sch.xmut.jake.imagestegangraphy.domain.api;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by jake.lin on 2019/12/30
 */
@Table(name = "api_alipay")
@Entity
public class ApiAlipayEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "app_id")
    private String appId;
    @Column(name = "encrypt")
    private String encrypt;
    @Column(name = "mechart_private_key")
    private String mechartPrivateKey;
    @Column(name = "alipay_public_key")
    private String alipayPublicKey;
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }

    public String getMechartPrivateKey() {
        return mechartPrivateKey;
    }

    public void setMechartPrivateKey(String mechartPrivateKey) {
        this.mechartPrivateKey = mechartPrivateKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
