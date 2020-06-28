package cj.netos.gateway.wallet.model;

/**
 * Table: trans_shunter_activity
 */
public class TransShunterActivity {
    /**
     * Column: id
     * Remark: 流水号
     */
    private String id;

    /**
     * Column: activity_no
     * Remark: 活动顺序号。从0开始 0已收单 1已确认收单 2已决清 3已确认决清  
     */
    private Integer activityNo;

    /**
     * Column: activity_name
     * Remark: 步骤名与代码对应 已收单 已确认收单 已决清 已确认决清 
     */
    private String activityName;

    /**
     * Column: record_sn
     * Remark: 关联的充值记录号
     */
    private String recordSn;

    /**
     * Column: status
     * Remark: 处理过程的状态码，成功是200
     */
    private Integer status;

    /**
     * Column: message
     * Remark: 处理过程消息
     */
    private String message;

    /**
     * Column: ctime
     * Remark: 创建时间
     */
    private String ctime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(Integer activityNo) {
        this.activityNo = activityNo;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public String getRecordSn() {
        return recordSn;
    }

    public void setRecordSn(String recordSn) {
        this.recordSn = recordSn == null ? null : recordSn.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime == null ? null : ctime.trim();
    }
}