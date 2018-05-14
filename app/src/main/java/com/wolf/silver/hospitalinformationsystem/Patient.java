package com.wolf.silver.hospitalinformationsystem;

/**
 * Created by sarabesh on 11/21/2017.
 */

public class Patient {
    private int id;
    private String name,address,sex,phone,date,ward,age;

    public Patient(int a,String b,String c,String d,String e,String f,String g,String h)
    {
        this.id=a;
        this.name=b;
        this.age=c;
        this.sex=d;
        this.address=e;
        this.phone=f;
        this.date=g;
        this.ward=h;
    }
    public void setId(int a)
    {
        this.id=a;
    }

    public void setName(String a)
    {
        name=a;
    }

    public void setAge(String a)
    {
        age=a;
    }

    public void setSex(String a)
    {
        sex=a;
    }

    public void setAddress(String a)
    {
        address=a;
    }

    public void setPhone(String a)
    {
         phone=a;
    }

    public void setDate(String a)
    {
        date=a;
    }

    public void setWard(String a)
    {
        ward=a;
    }
    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getAge()
    {
        return age;
    }

    public String getSex()
    {
        return sex;
    }

    public String getAddress()
    {
        return address;
    }

    public String getPhone()
    {
        return phone;
    }

    public String getDate()
    {
        return date;
    }

    public String getWard()
    {
        return ward;
    }


}
