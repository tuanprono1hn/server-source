package com.tuanla.springserver.util;

public class ConstantVar {
	public static final String STATUS = "status"; // status key on activiti
	public static final int STATUS_ERROR = 0; //  ma loi
	public static final int STATUS_AVB_ERROR = 10; //  ma loi
	public static final int STATUS_BCSS_ERROR = 20; //  ma loi
	public static final int STATUS_UNKNOWN_ERROR = 500; // ma looi khong xac dinh
	public static final int STATUS_SUCCESS = 1;   // trang thai thanh cong
	public static final int STATUS_BLACK_LIST = 50; // thue bao trong danh sach blacklist
	public static final int STATUS_WHITE_LIST = 51; // thue bao ngoai danh sach white list
	public static final int STATUS_BNW_NOT_FOUND = 52;// black or white list name not found
	public static final int STATUS_BNW_UNKNOWN = 53;// black or white list name unknown type (not black and not white list)
	public static final int STATUS_OUT_OF_ADS = 60; // thue bao nam ngoai tap quang cao
	public static final int STATUS_SUBSINFO_ERROR = 70; // thue bao nam ngoai tap muc tieu
	public static final int STATUS_POLICY_ERROR = 80; // thue bao vi pham chinh sach

	public static final int STATUS_REG_VASSGATE_ERROR = 2;
	public static final int BLACK_LIST_TYPE = 1;
	public static final int WHITE_LIST_TYPE = 0;
	public static final String ACM_PROGRAM_ID = "PROGRAM_ID";
	public static final String ACM_PROGRAM_CODE = "PROGRAM_CODE";
	public static final String ACM_CHANNEL = "CHANNEL";
	public static final String ACM_LAST_TASK = "LAST_TASK";
	public static final String ACM_EVENT_CODE = "EVENT_CODE";
	public static final String ACM_SMS_BRANDNAME = "SMS_BRANDNAME";
	public static final String ACM_SMS_CONTENT = "SMS_CONTENT";
	public static final String ACM_ISDN = "ISDN";
	public static final String ACM_ADS_CODE = "ADS_CODE";
	public static final String ACM_SUBS_CHECK = "SUBSINFO_CONDITION";
	public static final String ACM_POLICY_CHECK = "POLICY_CONDITION";
	public static final String ACM_AVB_CAMPAIGN="AVB_CAMPAIGN";
	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String FILE_NAME = "name_file";

	public static final int PROGRAM_STATUS_RUN=1;
	public static final int PROGRAM_STATUS_INIT=3;
	public static final int PROGRAM_STATUS_TEMPLATE=4;
	public static final int PROGRAM_ADS_TYPE=2;

	public static final String ACM_CR_CONDITION_NOT_EXIST="<NOT_EXISTS>";
		//ACM_LIMIT_SUBSCRIBER_FOR_CAMPAIGN
		//ACM_LIMIT_SUBSCRIBER_FOR_DAY
		//ACM_LIMIT_SUBSCRIBER_FOR_AVB
		//ACM_LIMIT_SUBSCRIBER_FOR_SMS
		//ACM_LIMIT_ADS_FOR_SUBSCRIBER_ON_AVB
		//ACM_LIMIT_ADS_FOR_SUBSCRIBER_ON_SMS
	public static final String ACM_LIMIT_SUBSCRIBER_FOR_CAMPAIGN="ACM_LIMIT_SUBSCRIBER_FOR_CAMPAIGN";
	public static final String ACM_LIMIT_SUBSCRIBER_FOR_DAY="ACM_LIMIT_SUBSCRIBER_FOR_DAY";
	public static final String ACM_LIMIT_SUBSCRIBER_FOR_AVB="ACM_LIMIT_SUBSCRIBER_FOR_AVB";
	public static final String ACM_LIMIT_SUBSCRIBER_FOR_SMS="ACM_LIMIT_SUBSCRIBER_FOR_SMS";
	public static final String ACM_LIMIT_ADS_FOR_SUBSCRIBER_ON_AVB="ACM_LIMIT_ADS_FOR_SUBSCRIBER_ON_AVB";
	public static final String ACM_LIMIT_ADS_FOR_SUBSCRIBER_ON_SMS="ACM_LIMIT_ADS_FOR_SUBSCRIBER_ON_SMS";

	public static final String ACM_ADS_CHANNEL="ACM_ADS_CHANNEL";
	public static final String ACM_ADS_CHANNEL_PARNAME_AVB="AVB";
	public static final String ACM_ADS_CHANNEL_PARNAME_BCCS="SMS";
	//ACM_POLICY	DEFAULT_POLICY
	//ACM_BNW	DEFAULT_BLACKLIST
	public static final String ACM_GLOBAL_POLICY="DEFAULT_POLICY";
	public static final String ACM_GLOBAL_POLICY_TYPE="ACM_POLICY";
	public static final String ACM_GLOBAL_BLACKLIST_TYPE="ACM_BNW";
	public static final String ACM_GLOBAL_BLACKLIST="DEFAULT_BLACKLIST";
}
