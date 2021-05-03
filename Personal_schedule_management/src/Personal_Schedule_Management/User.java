/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personal_Schedule_Management;

import java.util.Date;

/**
 *
 * @author admin
 */
class User {
    private String workname, workprogress, discrible;
    private Date startday, endday;

    public User(String workname, Date startday, Date endday, String workprogress, String discrible) {
        this.workname = workname;
        this.startday = startday;
        this.endday = endday;
        this.workprogress = workprogress;
        this.discrible = discrible;
        
    }

    public String getWorkname() {
        return workname;
    }

    public void setWorkname(String workname) {
        this.workname = workname;
    }

    public String getWorkprogress() {
        return workprogress;
    }

    public void setWorkprogress(String workprogress) {
        this.workprogress = workprogress;
    }

    public String getDiscrible() {
        return discrible;
    }

    public void setDiscrible(String discrible) {
        this.discrible = discrible;
    }

    public Date getStartday() {
        return startday;
    }

    public void setStartday(Date startday) {
        this.startday = startday;
    }

    public Date getEndday() {
        return endday;
    }

    public void setEndday(Date endday) {
        this.endday = endday;
    }
    
    
}
