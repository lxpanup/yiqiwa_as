package com.ssd.yiqiwa.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class LoginUserBean implements Parcelable {

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

    protected LoginUserBean(Parcel in) {
        birthday = in.readString();
        cardBank = in.readString();
        cardNumber = in.readString();
        contactPhone = in.readString();
        createDate = in.readString();
        lastLoginTime = in.readString();
        leftScore = in.readInt();
        loginPhone = in.readString();
        myCode = in.readString();
        nickName = in.readString();
        password = in.readString();
        portrait = in.readString();
        recommendCode = in.readString();
        salt = in.readString();
        status = in.readInt();
        totalScore = in.readInt();
        type = in.readInt();
        uId = in.readInt();
    }

    public static final Creator<LoginUserBean> CREATOR = new Creator<LoginUserBean>() {
        @Override
        public LoginUserBean createFromParcel(Parcel in) {
            return new LoginUserBean(in);
        }

        @Override
        public LoginUserBean[] newArray(int size) {
            return new LoginUserBean[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(birthday);
        dest.writeString(cardBank);
        dest.writeString(cardNumber);
        dest.writeString(contactPhone);
        dest.writeString(createDate);
        dest.writeString(lastLoginTime);
        dest.writeInt(leftScore);
        dest.writeString(loginPhone);
        dest.writeString(myCode);
        dest.writeString(nickName);
        dest.writeString(password);
        dest.writeString(portrait);
        dest.writeString(recommendCode);
        dest.writeString(salt);
        dest.writeInt(status);
        dest.writeInt(totalScore);
        dest.writeInt(type);
        dest.writeInt(uId);
    }
}
