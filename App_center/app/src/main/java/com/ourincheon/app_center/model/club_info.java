package com.ourincheon.app_center.model;

/**
 * Created by SanJuku on 2018-02-23.
 */

public class club_info {

        private int num;
        private String location;
        private String category;
        private String clubname;
        private String image1;
        private String image2;
        private String image3;
        private String image4;

        public club_info(int num, String location, String category, String clubname, String image1, String image2, String image3, String image4){
                num = this.num;
                location = this.location;
                category = this.category;
                clubname = this.clubname;
                image1 =this.image1;
                image2 =this.image2;
                image3 =this.image3;
                image4 =this.image4;
        }

        public int getNum(){
                return num;
        }

        public String getLocation(){
                return location;
        }

        public String getCategory(){
                return category;
        }

}
