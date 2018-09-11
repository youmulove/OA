package com.thinkgem.jeesite.modules.sys.web;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SmsApi {

	public static SmsSendResult SmsCodeSend(String phone, String code) {
		System.out.println(6);
		SmsSendResult SmsResult = new SmsSendResult();
		System.out.println(7);
		SmsResult.setReceiveNum(phone);
		try {
			System.out.println(8);
			URL url = new URL("https://sms.yunpian.com/v2/sms/single_send.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			// 验证码类信息，内容变量长度不能超过10个字。
			// 修改有效时间
			String time = "5分钟";

			String content = "您的验证码是" + code + "。如非本人操作，请忽略本短信";
			SmsResult.setContent(content);
			// 0570f7b226167c61f6b9c330e1ad5c1f
			/*
			 * String data = "apikey=006fad5e30449a54f304cc5eac338617&mobile=" +
			 * phone + "&text=" + content + "";
			 */
			String data = "apikey=70fec55375ac988f390433856a25c3d3&mobile="
					+ phone + "&text=" + content + "";
			// XXT==6a19842c30c5a10561a672bc4f6827ca
			conn.setDoOutput(true);
			OutputStream ops = conn.getOutputStream();
			ops.write(data.getBytes("utf-8"));
			System.out.println(9);
			ops.flush();
			ops.close();
			System.out.println(10);
			InputStream is = conn.getInputStream();
			InputStreamReader isreader = new InputStreamReader(is, "utf-8");
			BufferedReader breader = new BufferedReader(isreader);
			System.out.println(11);
			String line = null;
			StringBuffer sb = new StringBuffer();
			System.out.println(12);
			while ((line = breader.readLine()) != null) {
				System.out.println(13);
				sb.append(line);
			}
			String jsonResult = sb.toString();
			System.out.println("返回的JSON数据为：" + jsonResult);
			System.out.println(14);
			JSONObject jsonObject = new JSONObject(jsonResult);
			System.out.println(15);
			String result = jsonObject.getString("msg");
			System.out.println(result);

			if (!("发送成功".equals(result))) {
				System.out.println(16);
				SmsResult.setResult(false);
				System.out.println(17);
				SmsResult.setMessage(result);
				System.out.println(18);
				return SmsResult;
			} else {
				SmsResult.setResult(true);
			}
			System.out.println(18);
			breader.close();
			isreader.close();
			is.close();
			System.out.println(19);
		} catch (Exception e) {
			System.out.println(20);
			e.printStackTrace();
			SmsResult.setResult(false);
			return SmsResult;
		}
		return SmsResult;
	}

	public static SmsSendResult SmsRegistNotify(String phone) {
		SmsSendResult SmsResult = new SmsSendResult();
		SmsResult.setReceiveNum(phone);
		URL url;
		try {
			url = new URL("https://sms.yunpian.com/v2/sms/single_send.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
			String time = df.format(Calendar.getInstance().getTime());
			String company = "陆港科技智能快递柜";
			String content = "【陆港智能快递柜】恭喜您的配送员帐号" + phone + "于今天" + time
					+ "审核通过，欢迎使用" + company + "。";
			SmsResult.setContent(content);
			String data = "apikey=d17a4c5bd9f2792453464ef2deea6ae3&mobile="
					+ phone + "&text=" + content + "";

			conn.setDoOutput(true);
			OutputStream ops = conn.getOutputStream();
			ops.write(data.getBytes("utf-8"));
			ops.flush();

			InputStream is = conn.getInputStream();
			InputStreamReader isreader = new InputStreamReader(is, "utf-8");
			BufferedReader breader = new BufferedReader(isreader);

			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = breader.readLine()) != null) {
				sb.append(line);
			}

			String jsonResult = sb.toString();
			System.out.println("返回的JSON数据为：" + jsonResult);

			JSONObject jsonObject = new JSONObject(jsonResult);
			String result = jsonObject.getString("msg");
			System.out.println(result);

			if (!("发送成功".equals(result))) {
				SmsResult.setResult(false);
				return SmsResult;
			}

			breader.close();
			isreader.close();
			is.close();

		} catch (Exception e) {
			SmsResult.setResult(false);
			return SmsResult;
		}
		SmsResult.setResult(true);
		return SmsResult;
	}

	// 寄存通知
	public static SmsSendResult SmsDepositNotify(String receiverPhoneNum,
			String trackingNum, String latticeNum, String address,
			String radomPassword) {
		SmsSendResult SmsResult = new SmsSendResult();
		SmsResult.setReceiveNum(receiverPhoneNum);
		try {
			URL url = new URL("https://sms.yunpian.com/v2/sms/single_send.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			String content = "您的快递单号为：" + trackingNum + "的包裹已经送达" + address
					+ ",箱号为:" + latticeNum + ",开箱密码为:" + radomPassword + "。";
			SmsResult.setContent(content);
			String data = "apikey=d17a4c5bd9f2792453464ef2deea6ae3&mobile="
					+ receiverPhoneNum + "&text=" + content + "";
			System.out.println("短信内容为：" + content);
			conn.setDoOutput(true);
			OutputStream ops = conn.getOutputStream();
			ops.write(data.getBytes("utf-8"));
			ops.flush();

			InputStream is = conn.getInputStream();
			InputStreamReader isreader = new InputStreamReader(is, "utf-8");
			BufferedReader breader = new BufferedReader(isreader);

			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = breader.readLine()) != null) {
				sb.append(line);
			}

			String jsonResult = sb.toString();
			System.out.println("返回的JSON数据为：" + jsonResult);

			JSONObject jsonObject = new JSONObject(jsonResult);
			String result = jsonObject.getString("msg");
			System.out.println(result);

			if (!("发送成功".equals(result))) {
				SmsResult.setResult(false);
				return SmsResult;
			}

			breader.close();
			isreader.close();
			is.close();

		} catch (Exception e) {
			e.printStackTrace();
			SmsResult.setResult(false);
			return SmsResult;
		}
		SmsResult.setResult(true);
		return SmsResult;
	}

	// 包裹被快递员取走通知
	public static SmsSendResult SmsDeliveredTakenNotify(
			String receiverPhoneNum, String trackingNum) {
		SmsSendResult SmsResult = new SmsSendResult();
		SmsResult.setReceiveNum(receiverPhoneNum);
		try {
			URL url = new URL("https://sms.yunpian.com/v2/sms/single_send.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			String content = "【陆港智能快递柜】您的快递单号为：" + trackingNum + "的包裹已被快递员"
					+ receiverPhoneNum + "取回。";
			SmsResult.setContent(content);
			String data = "apikey=d17a4c5bd9f2792453464ef2deea6ae3&mobile="
					+ receiverPhoneNum + "&text=" + content + "";

			conn.setDoOutput(true);
			OutputStream ops = conn.getOutputStream();
			ops.write(data.getBytes("utf-8"));
			ops.flush();

			InputStream is = conn.getInputStream();
			InputStreamReader isreader = new InputStreamReader(is, "utf-8");
			BufferedReader breader = new BufferedReader(isreader);

			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = breader.readLine()) != null) {
				sb.append(line);
			}

			String jsonResult = sb.toString();
			System.out.println("返回的JSON数据为：" + jsonResult);

			JSONObject jsonObject = new JSONObject(jsonResult);
			String result = jsonObject.getString("msg");
			System.out.println(result);

			if (!("发送成功".equals(result))) {
				SmsResult.setResult(false);
				return SmsResult;
			}

			breader.close();
			isreader.close();
			is.close();

		} catch (Exception e) {
			e.printStackTrace();
			SmsResult.setResult(false);
			return SmsResult;
		}
		SmsResult.setResult(true);
		return SmsResult;
	}

	// 包裹被逾期取走通知
	public static SmsSendResult SmsExpiredTakenNotify(String receiverPhoneNum,
			String trackingNum) {
		SmsSendResult SmsResult = new SmsSendResult();
		SmsResult.setReceiveNum(receiverPhoneNum);
		try {
			URL url = new URL("https://sms.yunpian.com/v2/sms/single_send.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			String content = "您的快递单号为：" + trackingNum + "的包裹由于长时间未取，已被快递员"
					+ receiverPhoneNum + "取回。";
			SmsResult.setContent(content);
			String data = "apikey=d17a4c5bd9f2792453464ef2deea6ae3&mobile="
					+ receiverPhoneNum + "&text=" + content + "";

			conn.setDoOutput(true);
			OutputStream ops = conn.getOutputStream();
			ops.write(data.getBytes("utf-8"));
			ops.flush();

			InputStream is = conn.getInputStream();
			InputStreamReader isreader = new InputStreamReader(is, "utf-8");
			BufferedReader breader = new BufferedReader(isreader);

			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = breader.readLine()) != null) {
				sb.append(line);
			}

			String jsonResult = sb.toString();
			System.out.println("返回的JSON数据为：" + jsonResult);

			JSONObject jsonObject = new JSONObject(jsonResult);
			String result = jsonObject.getString("msg");
			System.out.println(result);

			if (!("发送成功".equals(result))) {
				SmsResult.setResult(false);
				return SmsResult;
			}

			breader.close();
			isreader.close();
			is.close();

		} catch (Exception e) {
			e.printStackTrace();
			SmsResult.setResult(false);
			return SmsResult;
		}
		SmsResult.setResult(true);
		return SmsResult;
	}

	// 包裹被管理员取走通知
	public static SmsSendResult SmsAdminTakenNotify(String receiverPhoneNum,
			String adminphone, String address) {
		SmsSendResult SmsResult = new SmsSendResult();
		SmsResult.setReceiveNum(receiverPhoneNum);
		try {
			URL url = new URL("https://sms.yunpian.com/v2/sms/single_send.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			String content = "【陆港智能快递柜】您存放在" + address + "的包裹已被管理员"
					+ adminphone + "取回。";
			SmsResult.setContent(content);
			String data = "apikey=d17a4c5bd9f2792453464ef2deea6ae3&mobile="
					+ receiverPhoneNum + "&text=" + content + "";

			conn.setDoOutput(true);
			OutputStream ops = conn.getOutputStream();
			ops.write(data.getBytes("utf-8"));
			ops.flush();

			InputStream is = conn.getInputStream();
			InputStreamReader isreader = new InputStreamReader(is, "utf-8");
			BufferedReader breader = new BufferedReader(isreader);

			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = breader.readLine()) != null) {
				sb.append(line);
			}

			String jsonResult = sb.toString();
			System.out.println("返回的JSON数据为：" + jsonResult);

			JSONObject jsonObject = new JSONObject(jsonResult);
			String result = jsonObject.getString("msg");
			System.out.println(result);

			if (!("发送成功".equals(result))) {
				SmsResult.setResult(false);
				return SmsResult;
			}

			breader.close();
			isreader.close();
			is.close();

		} catch (Exception e) {
			e.printStackTrace();
			SmsResult.setResult(false);
			return SmsResult;
		}
		SmsResult.setResult(true);
		return SmsResult;
	}

	// 个人寄存通知
	public static SmsSendResult SmsPersonalStockNotify(String senderPhoneNum,
			String receiverPhoneNum, String personalMessage, String latticeNum,
			String address, String radomPassword) {
		SmsSendResult SmsResult = new SmsSendResult();
		SmsResult.setReceiveNum(receiverPhoneNum);
		try {
			URL url = new URL("https://sms.yunpian.com/v2/sms/single_send.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			String content = "【陆港智能快递柜】" + senderPhoneNum + "将一个包裹给您送达至"
					+ address + ",备注为：" + personalMessage + ",箱号为:"
					+ latticeNum + ",开箱密码为:" + radomPassword + "。";
			SmsResult.setContent(content);
			String data = "apikey=d17a4c5bd9f2792453464ef2deea6ae3&mobile="
					+ receiverPhoneNum + "&text=" + content + "";

			conn.setDoOutput(true);
			OutputStream ops = conn.getOutputStream();
			ops.write(data.getBytes("utf-8"));
			ops.flush();

			InputStream is = conn.getInputStream();
			InputStreamReader isreader = new InputStreamReader(is, "utf-8");
			BufferedReader breader = new BufferedReader(isreader);

			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = breader.readLine()) != null) {
				sb.append(line);
			}

			String jsonResult = sb.toString();
			System.out.println("返回的JSON数据为：" + jsonResult);

			JSONObject jsonObject = new JSONObject(jsonResult);
			String result = jsonObject.getString("msg");
			System.out.println(result);

			if (!("发送成功".equals(result))) {
				SmsResult.setResult(false);
				return SmsResult;
			}

			breader.close();
			isreader.close();
			is.close();

		} catch (Exception e) {
			e.printStackTrace();
			SmsResult.setResult(false);
			return SmsResult;
		}
		SmsResult.setResult(true);
		return SmsResult;
	}

	// 定时未取通知。
	public static SmsSendResult SmsUnTakenNotify(String receiverPhoneNum,
			String trackingNum, String latticeNum, String address,
			String radomPassword) {
		SmsSendResult SmsResult = new SmsSendResult();
		SmsResult.setReceiveNum(receiverPhoneNum);
		try {
			URL url = new URL("https://sms.yunpian.com/v2/sms/single_send.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			String content = "【陆港智能快递柜】您快递单号为：" + trackingNum + "的包裹长时间未取,地址在："
					+ address + ",箱号为:" + latticeNum + ",开箱密码为:"
					+ radomPassword + "，请及时取件，避免逾期产生费用。";
			SmsResult.setContent(content);
			String data = "apikey=d17a4c5bd9f2792453464ef2deea6ae3&mobile="
					+ receiverPhoneNum + "&text=" + content + "";

			conn.setDoOutput(true);
			OutputStream ops = conn.getOutputStream();
			ops.write(data.getBytes("utf-8"));
			ops.flush();

			InputStream is = conn.getInputStream();
			InputStreamReader isreader = new InputStreamReader(is, "utf-8");
			BufferedReader breader = new BufferedReader(isreader);

			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = breader.readLine()) != null) {
				sb.append(line);
			}

			String jsonResult = sb.toString();
			System.out.println("返回的JSON数据为：" + jsonResult);

			JSONObject jsonObject = new JSONObject(jsonResult);
			String result = jsonObject.getString("msg");
			System.out.println(result);

			if (!("发送成功".equals(result))) {
				SmsResult.setResult(false);
				return SmsResult;
			}

			breader.close();
			isreader.close();
			is.close();

		} catch (Exception e) {
			e.printStackTrace();
			SmsResult.setResult(false);
			return SmsResult;
		}
		SmsResult.setResult(true);
		return SmsResult;
	}

	public static SmsSendResult ResSendSms(String receiverPhoneNum,
			String content) {
		SmsSendResult SmsResult = new SmsSendResult();
		SmsResult.setReceiveNum(receiverPhoneNum);
		try {
			URL url = new URL("https://sms.yunpian.com/v2/sms/single_send.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			SmsResult.setContent(content);
			String data = "apikey=d17a4c5bd9f2792453464ef2deea6ae3&mobile="
					+ receiverPhoneNum + "&text=" + content + "";

			conn.setDoOutput(true);
			OutputStream ops = conn.getOutputStream();
			ops.write(data.getBytes("utf-8"));
			ops.flush();
			ops.close();

			InputStream is = conn.getInputStream();
			InputStreamReader isreader = new InputStreamReader(is, "utf-8");
			BufferedReader breader = new BufferedReader(isreader);

			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = breader.readLine()) != null) {
				sb.append(line);
			}
			String jsonResult = sb.toString();
			System.out.println("返回的JSON数据为：" + jsonResult);

			JSONObject jsonObject = new JSONObject(jsonResult);
			String result = jsonObject.getString("msg");
			System.out.println(result);

			if (!("发送成功".equals(result))) {
				SmsResult.setResult(false);
				return SmsResult;
			}

			breader.close();
			isreader.close();
			is.close();

		} catch (Exception e) {
			e.printStackTrace();
			SmsResult.setResult(false);
			return SmsResult;
		}
		SmsResult.setResult(true);
		return SmsResult;
	}
}
