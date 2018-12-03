package com.purewhite.ywc.purewhite.bean.main;

import com.purewhite.ywc.purewhite.adapter.recyclerview.bean.BindBeanImp;

import java.util.List;

public class MainBean{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean extends BindBeanImp {
        /**
         * item_id : 576024480955
         * item_title : MintBear7支装化妆刷套装 GreyBear系列 柔软非常触感 抓粉力适中
         * shop_type : B
         * small_images : https://img.alicdn.com/i4/1027209382/TB2JrB3tqAoBKNjSZSyXXaHAVXa_!!1027209382-0-item_pic.jpg|https://img.alicdn.com/i2/1027209382/TB2dQIisYwrBKNjSZPcXXXpapXa_!!1027209382.jpg|https://img.alicdn.com/i3/1027209382/TB2hXHetXkoBKNjSZFEXXbrEVXa_!!1027209382.jpg|https://img.alicdn.com/i1/1027209382/TB2yCEqtr3nBKNjSZFMXXaUSFXa_!!1027209382.jpg|
         * coupon_money : 10
         * coupon_id : c96b77e935734e4bab84b7f8dfa1c7bd
         * item_endprice : 109.0
         * item_pic : https://img.alicdn.com/bao/uploaded/i3/1027209382/TB26InRuljTBKNjSZFDXXbVgVXa_!!1027209382-0-item_pic.jpg
         * item_price : 119
         * item_shorttitle : mintbear7支装化妆刷套装greybear
         * couponstart_time : 1539446400
         * couponend_time : 1546185600
         * seller_id : 1027209382
         * seller_nick : mintbear旗舰店
         * item_sale : 105
         * tkrates : 30.0
         * tk_money : 5.29
         * source : 1
         * guide_article : null
         */

        private String item_id;
        private String item_title;
        private String shop_type;
        private String small_images;
        private String coupon_money;
        private String coupon_id;
        private String item_endprice;
        private String item_pic;
        private String item_price;
        private String item_shorttitle;
        private String couponstart_time;
        private String couponend_time;
        private String seller_id;
        private String seller_nick;
        private int item_sale;
        private String tkrates;
        private String tk_money;
        private int source;
        private Object guide_article;

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public String getItem_title() {
            return item_title;
        }

        public void setItem_title(String item_title) {
            this.item_title = item_title;
        }

        public String getShop_type() {
            return shop_type;
        }

        public void setShop_type(String shop_type) {
            this.shop_type = shop_type;
        }

        public String getSmall_images() {
            return small_images;
        }

        public void setSmall_images(String small_images) {
            this.small_images = small_images;
        }

        public String getCoupon_money() {
            return coupon_money;
        }

        public void setCoupon_money(String coupon_money) {
            this.coupon_money = coupon_money;
        }

        public String getCoupon_id() {
            return coupon_id;
        }

        public void setCoupon_id(String coupon_id) {
            this.coupon_id = coupon_id;
        }

        public String getItem_endprice() {
            return item_endprice;
        }

        public void setItem_endprice(String item_endprice) {
            this.item_endprice = item_endprice;
        }

        public String getItem_pic() {
            return item_pic;
        }

        public void setItem_pic(String item_pic) {
            this.item_pic = item_pic;
        }

        public String getItem_price() {
            return item_price;
        }

        public void setItem_price(String item_price) {
            this.item_price = item_price;
        }

        public String getItem_shorttitle() {
            return item_shorttitle;
        }

        public void setItem_shorttitle(String item_shorttitle) {
            this.item_shorttitle = item_shorttitle;
        }

        public String getCouponstart_time() {
            return couponstart_time;
        }

        public void setCouponstart_time(String couponstart_time) {
            this.couponstart_time = couponstart_time;
        }

        public String getCouponend_time() {
            return couponend_time;
        }

        public void setCouponend_time(String couponend_time) {
            this.couponend_time = couponend_time;
        }

        public String getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(String seller_id) {
            this.seller_id = seller_id;
        }

        public String getSeller_nick() {
            return seller_nick;
        }

        public void setSeller_nick(String seller_nick) {
            this.seller_nick = seller_nick;
        }

        public int getItem_sale() {
            return item_sale;
        }

        public void setItem_sale(int item_sale) {
            this.item_sale = item_sale;
        }

        public String getTkrates() {
            return tkrates;
        }

        public void setTkrates(String tkrates) {
            this.tkrates = tkrates;
        }

        public String getTk_money() {
            return tk_money;
        }

        public void setTk_money(String tk_money) {
            this.tk_money = tk_money;
        }

        public int getSource() {
            return source;
        }

        public void setSource(int source) {
            this.source = source;
        }

        public Object getGuide_article() {
            return guide_article;
        }

        public void setGuide_article(Object guide_article) {
            this.guide_article = guide_article;
        }

    }
}
