package com.apporio.style_appy.Api_s;

/**
 * Created by saifi45 on 4/26/2016.
 */
public class Apis_url {
    public  static  String Company_id = "6";


    public static String imagebaseurl = "http://apporio.in/style_appy/";
    public  static  String Sign_up = "http://apporio.in/style_appy/api/user/save?user_name=";
    public  static  String Sign_up2 = "&user_email=";
    public  static  String Sign_up3 = "&user_phone=";
    public  static  String Sign_up4 = "&user_password=";
    public  static  String Sign_up5 = "&user_company_id="+Apis_url.Company_id;
    public  static  String Login_email = "http://apporio.in/style_appy/api/user/login?user_company_id="+Apis_url.Company_id+"&user_email=";
    public  static  String Login_email2 = "&user_password=";
    public  static  String Login_phone = "http://apporio.in/style_appy/api/user/login?user_company_id="+Apis_url.Company_id+"&user_phone=";
    public  static  String Login_phone2 = "&user_password=";
    public  static  String Forgotpassword = "http://apporio.in/style_appy/api/user/forgot_password?user_company_id="+Apis_url.Company_id+"&user_email=";
    public  static  String Category = "http://apporio.in/style_appy/api/category/get_all/"+Apis_url.Company_id;
//    public  static  String Sub_Category = "http://apporio.in/style_appy/api/category/suncat/";
    public  static  String Gallery = "http://apporio.in/style_appy/api/gallery/get_all/"+Apis_url.Company_id;
    public  static  String Staff = "http://apporio.in/style_appy/api/staff/get_all/"+Apis_url.Company_id;
    public  static  String Staff_Specific = "http://apporio.in/style_appy/api/staff/get_detail/"+Apis_url.Company_id+"/";
    public  static  String Opening_Hours = "http://apporio.in/style_appy/api/openinghours/view_hour/"+Apis_url.Company_id;
    public  static  String View_location = "http://apporio.in/style_appy/api/location/get_all/"+Apis_url.Company_id;
    public  static  String Classes = "http://apporio.in/style_appy/api/classes/get_all/"+Apis_url.Company_id;
    public  static  String SpecificClasses = "http://apporio.in/style_appy/api/classes/get_detail/"+Apis_url.Company_id+"/";
    public  static  String Accountinfo = "http://apporio.in/style_appy/api/viewaccount/info/";
    public static String Accountsave="http://apporio.in/style_appy/api/user/profile?user_id=";
    public static String Accountsave1="&user_name=";
    public static String Accountsave2="&user_email=";
    public static String Accountsave3="&user_phone=";
    public static String Accountsave4="&user_password=";
    public static String Accountsave5="&user_company_id="+Apis_url.Company_id;
    public static String deleteordr="http://apporio.in/style_appy/api/order/order_delete/"+Apis_url.Company_id+"/";
    public static String recentorder ="http://apporio.in/style_appy/api/order/recent_order/"+Apis_url.Company_id+"/";
    public static String SpecificService ="http://apporio.in/style_appy/api/service/catewise/"+Apis_url.Company_id+"/";
    public static String Locationservice ="http://apporio.in/style_appy/api/service/get_service_locations/"+Apis_url.Company_id+"/";
    public static String Staffservicebystaff ="http://apporio.in/style_appy/api/service/get_service_staff/"+Apis_url.Company_id+"/";
    public static String Timeslotservicebydate ="http://apporio.in/style_appy/api/service/get_service_timeslot/"+Apis_url.Company_id+"/";
    public static String Timeslotservicebystaff ="http://apporio.in/style_appy/api/service/get_staff_timeslot/"+Apis_url.Company_id+"/";
    public static String Staffservicebydate ="http://apporio.in/style_appy/api/service/get_stafflist/"+Apis_url.Company_id+"/";
    public static String add_order="http://apporio.in/style_appy/api/order/add_order/?user_id=";
    public static String serviceprice="http://apporio.in/style_appy/api/service/get_price/"+
            Apis_url.Company_id+"/";


    public static String push_notification="http://apporio.in/style_appy/api/account/update_did?user_id=";

}
