package com.example.medhelp.ui.reminder;

public class ReminderData {
    public String med_name,date,time,repeat,repeat_no,repeat_type,active;
    public ReminderData(){}

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getRepeat_no() {
        return repeat_no;
    }

    public void setRepeat_no(String repeat_no) {
        this.repeat_no = repeat_no;
    }

    public String getRepeat_type() {
        return repeat_type;
    }

    public void setRepeat_type(String repeat_type) {
        this.repeat_type = repeat_type;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
