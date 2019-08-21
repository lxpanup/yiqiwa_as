package com.ssd.yiqiwa.model.entity;

/**
 * 创建接收服务器返回数据的类
 *
 * @author Joe
 */
public class LoginUserBean {

    /**
     * Code : 1
     * Msg : 操作成功
     * Data : null
     */

    /**
     * 状态码
     */
    private int code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 组合信息
     */
    private Data data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data{

        private String birthday;
        private String cardBank;
        private String cardNumber;
        private String contactPhone;
        private String createDate;
        private String lastLoginTime;
        private int leftScore;
        private String loginPhone;
        private String myCode;
        private String nickName;
        private String password;
        private String portrait;
        private String recommendCode;
        private String salt;
        private int status;
        private int totalScore;
        private int type;
        private int uId;

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getCardBank() {
            return cardBank;
        }

        public void setCardBank(String cardBank) {
            this.cardBank = cardBank;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public String getContactPhone() {
            return contactPhone;
        }

        public void setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(String lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public int getLeftScore() {
            return leftScore;
        }

        public void setLeftScore(int leftScore) {
            this.leftScore = leftScore;
        }

        public String getLoginPhone() {
            return loginPhone;
        }

        public void setLoginPhone(String loginPhone) {
            this.loginPhone = loginPhone;
        }

        public String getMyCode() {
            return myCode;
        }

        public void setMyCode(String myCode) {
            this.myCode = myCode;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getRecommendCode() {
            return recommendCode;
        }

        public void setRecommendCode(String recommendCode) {
            this.recommendCode = recommendCode;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getTotalScore() {
            return totalScore;
        }

        public void setTotalScore(int totalScore) {
            this.totalScore = totalScore;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getuId() {
            return uId;
        }

        public void setuId(int uId) {
            this.uId = uId;
        }
    }
}
