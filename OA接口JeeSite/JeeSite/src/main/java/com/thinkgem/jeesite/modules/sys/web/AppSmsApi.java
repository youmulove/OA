package com.thinkgem.jeesite.modules.sys.web;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.util.json.JSONObject;

import com.google.common.collect.Maps;

public class AppSmsApi {

	public static AppSmsSendResult SmsCodeSend(String phone, String code,
			HttpServletResponse response) {
		AppSmsSendResult SmsResult = new AppSmsSendResult();
		SmsResult.setReceiveNum(phone);
		JSONObject jo = new JSONObject();
		Map<String, Object> map = Maps.newHashMap();
		try {
			URL url = new URL("https://sms.yunpian.com/v2/sms/single_send.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			// 验证码类信息，内容变量长度不能超过10个字。
			// 修改有效时间
			String time = "5分钟";

			String content = "您的验证码是" + code + "如非本人操作，请忽略本短信";
			SmsResult.setContent(content);
			String data = "apikey=70fec55375ac988f390433856a25c3d3&mobile="
					+ phone + "&text=" + content + "";
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
			// map.put("jsonResult", jsonResult);
			// jo.put("map", map);
			jo.put("jsonResult", jsonResult);
			System.out.println(jo.toString());
			response.getWriter().write(jo.toString());
			response.getWriter().flush();
			response.getWriter().close();
			// System.out.println("返回的JSON数据为：" + jsonResult);

			JSONObject jsonObject = new JSONObject(jsonResult);
			String result = jsonObject.getString("msg");
			System.out.println(result);

			if (!("发送成功".equals(result))) {
				SmsResult.setResult(false);
				SmsResult.setMessage(result);
				return SmsResult;
			} else {
				SmsResult.setResult(true);
			}
			breader.close();
			isreader.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
			SmsResult.setResult(false);
			return SmsResult;
		}
		return SmsResult;
	}

	public static AppSmsSendResult SmsRegistNotify(String phone) {
		AppSmsSendResult SmsResult = new AppSmsSendResult();
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
			String data = "apikey=70fec55375ac988f390433856a25c3d3&mobile="
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
	public static AppSmsSendResult SmsDepositNotify(String receiverPhoneNum,
			String trackingNum, String latticeNum, String address,
			String radomPassword) {
		AppSmsSendResult SmsResult = new AppSmsSendResult();
		SmsResult.setReceiveNum(receiverPhoneNum);
		try {
			URL url = new URL("https://sms.yunpian.com/v2/sms/single_send.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			String content = "您的快递单号为：" + trackingNum + "的包裹已经送达" + address
					+ ",箱号为:" + latticeNum + ",开箱密码为:" + radomPassword + "。";
			SmsResult.setContent(content);
			String data = "apikey=70fec55375ac988f390433856a25c3d3&mobile="
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
	public static AppSmsSendResult SmsDeliveredTakenNotify(
			String receiverPhoneNum, String trackingNum) {
		AppSmsSendResult SmsResult = new AppSmsSendResult();
		SmsResult.setReceiveNum(receiverPhoneNum);
		try {
			URL url = new URL("https://sms.yunpian.com/v2/sms/single_send.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			String content = "【陆港智能快递柜】您的快递单号为：" + trackingNum + "的包裹已被快递员"
					+ receiverPhoneNum + "取回。";
			SmsResult.setContent(content);
			String data = "apikey=70fec55375ac988f390433856a25c3d3&mobile="
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
	public static AppSmsSendResult SmsExpiredTakenNotify(
			String receiverPhoneNum, String trackingNum) {
		AppSmsSendResult SmsResult = new AppSmsSendResult();
		SmsResult.setReceiveNum(receiverPhoneNum);
		try {
			URL url = new URL("https://sms.yunpian.com/v2/sms/single_send.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			String content = "您的快递单号为：" + trackingNum + "的包裹由于长时间未取，已被快递员"
					+ receiverPhoneNum + "取回。";
			SmsResult.setContent(content);
			String data = "apikey=70fec55375ac988f390433856a25c3d3&mobile="
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
	public static AppSmsSendResult SmsAdminTakenNotify(String receiverPhoneNum,
			String adminphone, String address) {
		AppSmsSendResult SmsResult = new AppSmsSendResult();
		SmsResult.setReceiveNum(receiverPhoneNum);
		try {
			URL url = new URL("https://sms.yunpian.com/v2/sms/single_send.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			String content = "【陆港智能快递柜】您存放在" + address + "的包裹已被管理员"
					+ adminphone + "取回。";
			SmsResult.setContent(content);
			String data = "apikey=70fec55375ac988f390433856a25c3d3&mobile="
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
	public static AppSmsSendResult SmsPersonalStockNotify(
			String senderPhoneNum, String receiverPhoneNum,
			String personalMessage, String latticeNum, String address,
			String radomPassword) {
		AppSmsSendResult SmsResult = new AppSmsSendResult();
		SmsResult.setReceiveNum(receiverPhoneNum);
		try {
			URL url = new URL("https://sms.yunpian.com/v2/sms/single_send.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			String content = "【陆港智能快递柜】" + senderPhoneNum + "将一个包裹给您送达至"
					+ address + ",备注为：" + personalMessage + ",箱号为:"
					+ latticeNum + ",开箱密码为:" + radomPassword + "。";
			SmsResult.setContent(content);
			String data = "apikey=70fec55375ac988f390433856a25c3d3&mobile="
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
	public static AppSmsSendResult SmsUnTakenNotify(String receiverPhoneNum,
			String trackingNum, String latticeNum, String address,
			String radomPassword) {
		AppSmsSendResult SmsResult = new AppSmsSendResult();
		SmsResult.setReceiveNum(receiverPhoneNum);
		try {
			URL url = new URL("https://sms.yunpian.com/v2/sms/single_send.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			String content = "【陆港智能快递柜】您快递单号为：" + trackingNum + "的包裹长时间未取,地址在："
					+ address + ",箱号为:" + latticeNum + ",开箱密码为:"
					+ radomPassword + "，请及时取件，避免逾期产生费用。";
			SmsResult.setContent(content);
			String data = "apikey=70fec55375ac988f390433856a25c3d3&mobile="
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

	public static AppSmsSendResult ResSendSms(String receiverPhoneNum,
			String content) {
		AppSmsSendResult SmsResult = new AppSmsSendResult();
		SmsResult.setReceiveNum(receiverPhoneNum);
		try {
			URL url = new URL("https://sms.yunpian.com/v2/sms/single_send.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			SmsResult.setContent(content);
			String data = "apikey=70fec55375ac988f390433856a25c3d3&mobile="
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
