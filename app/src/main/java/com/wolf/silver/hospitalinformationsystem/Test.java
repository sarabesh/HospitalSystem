package com.wolf.silver.hospitalinformationsystem;

/**
 * Created by sarabesh on 11/26/2017.
 */

public class Test
{

    String test_name,result,ref,date,spec,lid;

    public Test(String i,String a,String b,String c,String d,String e)
    {
        this.lid=i;
        this.test_name=a;
        this.spec=b;
        this.result=c;
        this.ref=d;
        this.date=e;

    }
    public void setSpec(String a)
    {
        this.spec=a;
    }
    public String getSpec()
    {
        return spec;
    }
    public void setLid(String i)
    {
        lid=i;
    }
    public void setTest_name(String a)
    {
        this.test_name=a;
    }
    public void setResult(String a)
    {
        this.result=a;
    }
    public void setRef(String a)
    {
        this.ref=a;
    }
    public void setDate(String a)
    {
        this.date=a;
    }
    public String getLid()
    {
        return lid;
    }
    public String getTest_name()
    {
        return test_name;
    }
    public String getResult()
    {
        return result;
    }
    public String getRef()
    {
        return ref;
    }
    public String getDate()
    {
        return date;
    }


}
