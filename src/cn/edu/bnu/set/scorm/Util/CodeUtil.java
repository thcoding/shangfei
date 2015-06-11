package cn.edu.bnu.set.scorm.Util;

import java.util.Random;

import android.util.Base64;

public class CodeUtil {

	// 随机产生字符串，参数指定字符串长度
	public static String getEight(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int num = random.nextInt(base.length());
			sb.append(base.charAt(num));
		}
		return sb.toString();
	}

	//加密并在字符串前加上随机字符串，参数表示待加密文件位置
	public static byte [] base64_encode(byte [] buffer) throws Exception {
		byte [] code = Base64.encode(buffer, Base64.DEFAULT);
		code = (getEight(8) + new String(code)).getBytes();
		return code;
	}

	//解码：先将加密后的字符串前8位字符去掉，参数分别表示编码和目标输出文件位置
	public static String base64_decode(String base64Code)
			throws Exception {
		byte [] buffer = base64Code.substring(8).getBytes();
		buffer = Base64.decode(buffer, Base64.DEFAULT);
		return (new String(buffer));
	}
}