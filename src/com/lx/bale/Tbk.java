package com.lx.bale;

import java.io.File;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.text.Segment;

import org.junit.Test;



public class Tbk implements Serializable{
	public Fibonacci f1 = new Fibonacci();
	
	public Fibonacci getF1(){
		return f1;
	}
	
	public static void main(String[] args) {
		long currentTimeMillis = System.currentTimeMillis();
	
		System.out.println(MessageFormat.format("寄件人地址不能超过{0}个字节", "50"));
		System.out.println(MessageFormat.format("The length of the shippers address cannot exceed {0} characters.", "50"));
		String s = "[@\"id\":33714,\"insertTime\":{2},\"dataStatus\":1,\"status\":0,\"mainWaybillNo\":\"{0}\",\"ordinal\":0,\"originalBarcode\":0,\"createTm\":null,\"barRecordId\":null,\"waybillNo\":\"{0}\",\"zoneCode\":\"SFO01A\",\"opCode\":\"{1}\",\"opAttachInfo\":null,\"courierCode\":\"\",\"barOprCode\":null,\"barUploadTm\":null,\"objTypeCode\":null,\"contnrCode\":\"\",\"payFlg\":null,\"stayWhyCode\":\"\",\"subbillPieceQty\":null,\"barUploadTypeCode\":null,\"weightQty\":null,\"feeAmt\":null,\"accountantCode\":null,\"otherInfo\":null,\"opName\":\"装车\",\"zoneTypeCode\":null,\"encryptString\":null,\"barSn\":null,\"scheduleCode\":null,\"signTypeCode\":null,\"srcContnrCode\":null,\"phoneZone\":null,\"phone\":null,\"stopOverFlg\":null,\"batchCode\":null,\"destZoneCode\":null,\"autoloading\":\"\",\"barScanTm\":1544769660000,\"barScanDt\":null,\"barUploadOprCode\":null,\"extendAttach1\":null,\"extendAttach2\":null,\"extendAttach3\":\"\",\"extendAttach4\":\"\",\"extendAttach5\":null,\"extendAttach6\":null,\"extendAttach7\":null,\"barUploadTmStd\":null,\"barScanTmStd\":null,\"source\":0,\"ext\":null,\"consumerId\":170,\"locationEN\":null,\"locationCN\":null,\"locationCode\":null,\"countryCode\":null,\"version\":\"2\",\"remark\":null,\"lang\":null,\"customerOrderNo\":null,\"countryCn\":null,\"facilityType\":null,\"exts\":null,\"abnormalReason\":\"\",\"isOwsShow\":1,\"lineCode\":\"755A010A9999\",\"cvsName\":\"\",\"lastExpectedDeliveryZoneCode\":\"\",\"lastExpectedDeliveryTmRange\":\"\",\"attribute\":\"\",\"zoneName\":\"美国三藩市收派中心\",\"owsRemark\":\"快件在【美国三藩市收派中心】已装车,准备发往下一站\",\"routeCode\":\"\",\"routekey\":\"-3945241830\",\"oprCode\":\"90100230\",\"wqsRemark\":\"\",\"cvsCode\":\"\",\"distName\":\"三藩市\",\"outsideName\":\"美国三藩市收派中心\",\"oldWaybillNo\":\"\",\"stayWhyName\":\"\",\"dataSource\":null,\"gmt\":null,\"key\":\"68994770986118_994770986118__SFO01A_1544769660000_30\",\"originalRecord\":false,\"extendAttach8\":null,\"extendAttach9\":null,\"extendAttach10\":null,\"extendAttach11\":null,\"extendAttach12\":null,\"extendAttach13\":null,\"extendAttach14\":null,\"extendAttach15\":null,\"extendAttach16\":null,\"extendAttach17\":null,\"extendAttach18\":null,\"extendAttach19\":null,\"extendAttach20\":null,\"extendAttach21\":null,\"extendAttach22\":null,\"extendAttach23\":null,\"extendAttach24\":null,\"extendAttach25\":null,\"extendAttach26\":null,\"extendAttach27\":null,\"extendAttach28\":null,\"extendAttach29\":null,\"extendAttach30\":null,\"extendAttach31\":null,\"extendAttach32\":null,\"extendAttach33\":null,\"extendAttach34\":null,\"extendAttach35\":null,\"extendAttach36\":null,\"extendAttach37\":null,\"extendAttach38\":null,\"extendAttach39\":null,\"extendAttach40\":null,\"extendAttach41\":null,\"extendAttach42\":null,\"extendAttach43\":null,\"extendAttach44\":null,\"extendAttach45\":null,\"extendAttach46\":null,\"extendAttach47\":null,\"extendAttach48\":null,\"extendAttach49\":null,\"extendAttach50\":null,\"originalFlag\":false^]";
		System.out.println(MessageFormat.format(s,"98555","15",String.valueOf(currentTimeMillis)).replace("^", "}").replace("@", "{"));
	}
	@Test
	public void fun() throws CloneNotSupportedException{

		Tbk t1 = new Tbk();
		Tbk t2 = (Tbk) t1.clone();
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t1.f1);
		System.out.println(t2.f1);
		//Tbk.class.get
		
	}
	
}