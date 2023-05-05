/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.entities;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.util.Date;
import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author mohta
 */
public class Regime {
    int id;
    String name,discription;
    int calories,type,end_date;
    Date start_date;

    public Regime() {
    }

    public Regime(String name, String discription, int calories, int type, Date start_date, int end_date) {
        this.name = name;
        this.discription = discription;
        this.calories = calories;
        this.type = type;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.discription);
        hash = 71 * hash + this.calories;
        hash = 71 * hash + this.type;
        hash = 71 * hash + Objects.hashCode(this.start_date);
        hash = 71 * hash + Objects.hashCode(this.end_date);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Regime other = (Regime) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.calories != other.calories) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.discription, other.discription)) {
            return false;
        }
        if (!Objects.equals(this.start_date, other.start_date)) {
            return false;
        }
        if (!Objects.equals(this.end_date, other.end_date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Regime{" + "id=" + id + ", name=" + name + ", discription=" + discription + ", calories=" + calories + ", type=" + type + ", start_date=" + start_date + ", end_date=" + end_date + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(int end_date) {
        this.end_date = end_date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDiscription() {
        return discription;
    }

    public int getCalories() {
        return calories;
    }

    public int getType() {
        return type;
    }

    public Date getStart_date() {
        return start_date;
    }

    public int getEnd_date() {
        return end_date;
    }

    public Regime(int id, String name, String discription, int calories, int type, Date start_date, int end_date) {
        this.id = id;
        this.name = name;
        this.discription = discription;
        this.calories = calories;
        this.type = type;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public String getObjectif() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
