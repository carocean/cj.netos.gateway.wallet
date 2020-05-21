package cj.netos.gateway.wallet.model;

/**
 * Table: work_day
 */
public class WorkDay {
    /**
     * Column: id
     */
    private String id;

    /**
     * Column: work_day
     */
    private String workDay;

    /**
     * Column: pre_amount
     * Remark: 上期余额账户余额
     */
    private Long preAmount;

    /**
     * Column: unckeck_amount
     */
    private Long unckeckAmount;

    /**
     * Column: checked_amount
     */
    private Long checkedAmount;

    /**
     * Column: pre_freezen_amount
     */
    private Long preFreezenAmount;

    /**
     * Column: unckeck_freezen_amount
     */
    private Long unckeckFreezenAmount;

    /**
     * Column: checked_freezen_amount
     */
    private Long checkedFreezenAmount;

    /**
     * Column: pre_profit_amount
     */
    private Long preProfitAmount;

    /**
     * Column: unckech_profit_amount
     */
    private Long unckechProfitAmount;

    /**
     * Column: checked_profit_amount
     */
    private Long checkedProfitAmount;

    /**
     * Column: day_state
     * Remark: 日切状态: 0 工作状态 1 正在日切 2 日切完成，则余额才可用，即第二天的第一笔从这个余额开始
     */
    private Integer dayState;

    /**
     * Column: note
     * Remark: 审计备注
     */
    private String note;

    /**
     * Column: person
     */
    private String person;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getWorkDay() {
        return workDay;
    }

    public void setWorkDay(String workDay) {
        this.workDay = workDay == null ? null : workDay.trim();
    }

    public Long getPreAmount() {
        return preAmount;
    }

    public void setPreAmount(Long preAmount) {
        this.preAmount = preAmount;
    }

    public Long getUnckeckAmount() {
        return unckeckAmount;
    }

    public void setUnckeckAmount(Long unckeckAmount) {
        this.unckeckAmount = unckeckAmount;
    }

    public Long getCheckedAmount() {
        return checkedAmount;
    }

    public void setCheckedAmount(Long checkedAmount) {
        this.checkedAmount = checkedAmount;
    }

    public Long getPreFreezenAmount() {
        return preFreezenAmount;
    }

    public void setPreFreezenAmount(Long preFreezenAmount) {
        this.preFreezenAmount = preFreezenAmount;
    }

    public Long getUnckeckFreezenAmount() {
        return unckeckFreezenAmount;
    }

    public void setUnckeckFreezenAmount(Long unckeckFreezenAmount) {
        this.unckeckFreezenAmount = unckeckFreezenAmount;
    }

    public Long getCheckedFreezenAmount() {
        return checkedFreezenAmount;
    }

    public void setCheckedFreezenAmount(Long checkedFreezenAmount) {
        this.checkedFreezenAmount = checkedFreezenAmount;
    }

    public Long getPreProfitAmount() {
        return preProfitAmount;
    }

    public void setPreProfitAmount(Long preProfitAmount) {
        this.preProfitAmount = preProfitAmount;
    }

    public Long getUnckechProfitAmount() {
        return unckechProfitAmount;
    }

    public void setUnckechProfitAmount(Long unckechProfitAmount) {
        this.unckechProfitAmount = unckechProfitAmount;
    }

    public Long getCheckedProfitAmount() {
        return checkedProfitAmount;
    }

    public void setCheckedProfitAmount(Long checkedProfitAmount) {
        this.checkedProfitAmount = checkedProfitAmount;
    }

    public Integer getDayState() {
        return dayState;
    }

    public void setDayState(Integer dayState) {
        this.dayState = dayState;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person == null ? null : person.trim();
    }
}