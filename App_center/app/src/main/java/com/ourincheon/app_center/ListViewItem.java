package com.ourincheon.app_center;

/**
 * Created by SanJuku on 2018-02-23.
 */

public class ListViewItem {
    private String clubImageURL;
    private String clubNameStr;
    private String clubExpStr;
    private String clubPlaceStr;

    public ListViewItem(){
    }

    public ListViewItem(String clubImageURL,String clubNameStr,String clubExpStr,String clubPlaceStr){
        this.clubImageURL=clubImageURL;
        this.clubNameStr=clubNameStr;
        this.clubExpStr=clubExpStr;
        this.clubPlaceStr=clubPlaceStr;
    }

    public String getclubImageURL()
    {
        return clubImageURL;
    }

    public String getclubNameStr()
    {
        return clubNameStr;
    }

    public String getclubExpStr()
    {
        return clubExpStr;
    }

    public String getclubPlaceStr()
    {
        return clubPlaceStr;
    }

    public void setclubImageURL(String clubImageURL){
        this.clubImageURL=clubImageURL;
    }

    public void setclubNameStr(String clubNameStr){
        this.clubNameStr=clubNameStr;
    }

    public void setclubExpStr(String clubExpStr){
        this.clubExpStr=clubExpStr;
    }

    public void setclubPlaceStr(String clubPlaceStr){
        this.clubPlaceStr=clubPlaceStr;
    }

}
