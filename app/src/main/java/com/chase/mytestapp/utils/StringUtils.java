package com.chase.mytestapp.utils;

import android.annotation.SuppressLint;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String Utils
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2011-7-22
 */
public class StringUtils {

	private StringUtils() {
		throw new AssertionError();
	}

	@SuppressLint({ "NewApi" })
	public static boolean isMacthIp(String ip) {
		if ((ip != null) && (!ip.isEmpty())) {
			String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";

			if (ip.matches(regex)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * is null or its length is 0 or it is made by space
	 * 
	 * <pre>
	 * isBlank(null) = true;
	 * isBlank(&quot;&quot;) = true;
	 * isBlank(&quot;  &quot;) = true;
	 * isBlank(&quot;a&quot;) = false;
	 * isBlank(&quot;a &quot;) = false;
	 * isBlank(&quot; a&quot;) = false;
	 * isBlank(&quot;a b&quot;) = false;
	 * </pre>
	 * 
	 * @param str
	 * @return if string is null or its size is 0 or it is made by space, return
	 *         true, else return false.
	 */
	public static boolean isBlank(String str) {
		return (str == null || str.trim().length() == 0);
	}

	/**
	 * is null or its length is 0
	 * 
	 * <pre>
	 * isEmpty(null) = true;
	 * isEmpty(&quot;&quot;) = true;
	 * isEmpty(&quot;  &quot;) = false;
	 * </pre>
	 * 
	 * @param str
	 * @return if string is null or its size is 0, return true, else return
	 *         false.
	 */
	public static boolean isEmpty(CharSequence str) {
		return (str == null || str.length() == 0);
	}

	public static boolean isNullOrEmpty(String str) {
		if ((str == null) || ("".equals(str)) || ("null".equals(str))) {
			return true;
		}
		return false;
	}

	/**
	 * null Object to empty string
	 * 
	 * <pre>
	 * nullStrToEmpty(null) = &quot;&quot;;
	 * nullStrToEmpty(&quot;&quot;) = &quot;&quot;;
	 * nullStrToEmpty(&quot;aa&quot;) = &quot;aa&quot;;
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static String nullStrToEmpty(Object str) {
		return (str == null ? "" : (str instanceof String ? (String) str : str
				.toString()));
	}

	public static boolean isDigital(String str) {
		if (!isEmpty(str))
			return str.matches("[0-9]+");
		return false;
	}

	public static int length(String value) {
		int valueLength = 0;
		String chinese = "[Α-￥]";

		for (int i = 0; i < value.length(); i++) {
			String temp = value.substring(i, i + 1);

			if (temp.matches(chinese)) {
				valueLength += 2;
			} else {
				valueLength++;
			}
		}
		return valueLength;
	}

	public static List<String> stringsToList(String[] items) {
		List lists = new ArrayList();
		for (int i = 0; i < items.length; i++) {
			lists.add(items[i]);
		}
		return lists;
	}

	public static String fill(String sour, String fillStr, int len,
                              boolean isLeft) {
		if (sour == null) {
			sour = "";
		}
		int fillLen = len - length(sour);
		String fill = "";
		for (int i = 0; i < fillLen; i++) {
			fill = fill + fillStr;
		}
		if (isLeft) {
			return fill + sour;
		}
		return sour + fill;
	}

	/**
	 * @param strData
	 * @param nLen
	 * @param subStr
	 * @param nOption  0-前面,1-后面,2-前后
	 * @return
	 */
	public static String paddingString(String strData, int nLen, String subStr,
                                       int nOption) {
		String strHead = "";
		String strEnd = "";
		int addCharLen;

		int i = strData.length();
		if (i >= nLen) {
			return strData;
		}

		switch (nOption) {
		case 0:
			addCharLen = (nLen - i) / subStr.length();
			for (i = 0; i < addCharLen; i++) {
				strHead = strHead + subStr;
			}
			return strHead + strData;
		case 1:
			addCharLen = (nLen - i) / subStr.length();
			for (i = 0; i < addCharLen; i++) {
				strEnd = strEnd + subStr;
			}
			return strData + strEnd;
		case 2:
			addCharLen = (nLen - i) / (subStr.length() * 2);
			for (i = 0; i < addCharLen; i++) {
				strHead = strHead + subStr;
				strEnd = strEnd + subStr;
			}
			return strHead + strData + strEnd;
		}
		return strData;
	}

	public static String intToBcd(int value, int bytesNum) {
		switch (bytesNum) {
		case 1:
			if ((value >= 0) && (value <= 99)) {
				return paddingString(String.valueOf(value), 2, "0", 0);
			}
			break;
		case 2:
			if ((value >= 0) && (value <= 999)) {
				return paddingString(String.valueOf(value), 4, "0", 0);
			}

			break;
		case 3:
			if ((value >= 0) && (value <= 999)) {
				return paddingString(String.valueOf(value), 3, "0", 0);
			}
			break;
		}

		return "";
	}

	public static String hexToStr(String value)
			throws UnsupportedEncodingException {
		return new String(BytesUtils.hexToBytes(value), "GBK");
	}

	public static String strToHex(String value) {
		return BytesUtils.bytesToHex(BytesUtils.getBytes(value));
	}

	public static String paddingZeroToHexStr(String value, int option) {
		if (value.length() % 2 == 0) {
			return value;
		}

		if (option == 0) {
			return "0" + value;
		}
		if (option == 1) {
			return value + "0";
		}

		return value;
	}

	public static boolean checkHexStr(String value) {
		if (value == null)
			return false;

		int len = value.length();
		if (len == 0)
			return false;

		for (int i = 0; i < len; i++) {
			if (((value.charAt(i) < '0') || (value.charAt(i) > '9'))
					&& ((value.charAt(i) < 'a') || (value.charAt(i) > 'f'))
					&& ((value.charAt(i) < 'A') || (value.charAt(i) > 'F'))) {
				return false;
			}
		}
		return true;
	}

	public static String binaryToHex(String value) {
		String result = "";
		char[] hexVocable = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		String[] binString = { "0000", "0001", "0010", "0011", "0100", "0101",
				"0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101",
				"1110", "1111" };

		int len = value.length();
		for (int i = 0; i < len; i += 4) {
			for (int j = 0; j < 16; j++) {
				if (binString[j].equals(value.substring(i, i + 4))) {
					result = result + hexVocable[j];
					break;
				}
			}
		}

		return result;
	}

	public static String hexToBinary(String value) {
		String result = "";
		char[] hexVocable = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		String[] binString = { "0000", "0001", "0010", "0011", "0100", "0101",
				"0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101",
				"1110", "1111" };

		int len = value.length();
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < 16; j++) {
				if (value.charAt(i) == hexVocable[j]) {
					result = result + binString[j];
					break;
				}
			}
		}

		return result;
	}

	public static String getBinaryString(byte[] value) {
		String result = "";

		int len = value.length;

		for (int i = 0; i < len; i++) {
			result = result + String.valueOf(value[i]);
		}

		return result;
	}

	/**
	 * capitalize first letter
	 * 
	 * <pre>
	 * capitalizeFirstLetter(null)     =   null;
	 * capitalizeFirstLetter("")       =   "";
	 * capitalizeFirstLetter("2ab")    =   "2ab"
	 * capitalizeFirstLetter("a")      =   "A"
	 * capitalizeFirstLetter("ab")     =   "Ab"
	 * capitalizeFirstLetter("Abc")    =   "Abc"
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static String capitalizeFirstLetter(String str) {
		if (isEmpty(str)) {
			return str;
		}

		char c = str.charAt(0);
		return (!Character.isLetter(c) || Character.isUpperCase(c)) ? str
				: new StringBuilder(str.length())
						.append(Character.toUpperCase(c))
						.append(str.substring(1)).toString();
	}

	/**
	 * encoded in utf-8
	 * 
	 * <pre>
	 * utf8Encode(null)        =   null
	 * utf8Encode("")          =   "";
	 * utf8Encode("aa")        =   "aa";
	 * utf8Encode("啊啊啊啊")   = "%E5%95%8A%E5%95%8A%E5%95%8A%E5%95%8A";
	 * </pre>
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 *             if an error occurs
	 */
	public static String utf8Encode(String str) {
		if (!isEmpty(str) && str.getBytes().length != str.length()) {
			try {
				return URLEncoder.encode(str, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(
						"UnsupportedEncodingException occurred. ", e);
			}
		}
		return str;
	}

	/**
	 * encoded in utf-8, if exception, return defultReturn
	 * 
	 * @param str
	 * @param defultReturn
	 * @return
	 */
	public static String utf8Encode(String str, String defultReturn) {
		if (!isEmpty(str) && str.getBytes().length != str.length()) {
			try {
				return URLEncoder.encode(str, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				return defultReturn;
			}
		}
		return str;
	}

	/**
	 * get innerHtml from href
	 * 
	 * <pre>
	 * getHrefInnerHtml(null)                                  = ""
	 * getHrefInnerHtml("")                                    = ""
	 * getHrefInnerHtml("mp3")                                 = "mp3";
	 * getHrefInnerHtml("&lt;a innerHtml&lt;/a&gt;")                    = "&lt;a innerHtml&lt;/a&gt;";
	 * getHrefInnerHtml("&lt;a&gt;innerHtml&lt;/a&gt;")                    = "innerHtml";
	 * getHrefInnerHtml("&lt;a&lt;a&gt;innerHtml&lt;/a&gt;")                    = "innerHtml";
	 * getHrefInnerHtml("&lt;a href="baidu.com"&gt;innerHtml&lt;/a&gt;")               = "innerHtml";
	 * getHrefInnerHtml("&lt;a href="baidu.com" title="baidu"&gt;innerHtml&lt;/a&gt;") = "innerHtml";
	 * getHrefInnerHtml("   &lt;a&gt;innerHtml&lt;/a&gt;  ")                           = "innerHtml";
	 * getHrefInnerHtml("&lt;a&gt;innerHtml&lt;/a&gt;&lt;/a&gt;")                      = "innerHtml";
	 * getHrefInnerHtml("jack&lt;a&gt;innerHtml&lt;/a&gt;&lt;/a&gt;")                  = "innerHtml";
	 * getHrefInnerHtml("&lt;a&gt;innerHtml1&lt;/a&gt;&lt;a&gt;innerHtml2&lt;/a&gt;")        = "innerHtml2";
	 * </pre>
	 * 
	 * @param href
	 * @return <ul>
	 *         <li>if href is null, return ""</li>
	 *         <li>if not match regx, return source</li>
	 *         <li>return the last string that match regx</li>
	 *         </ul>
	 */
	public static String getHrefInnerHtml(String href) {
		if (isEmpty(href)) {
			return "";
		}

		String hrefReg = ".*<[\\s]*a[\\s]*.*>(.+?)<[\\s]*/a[\\s]*>.*";
		Pattern hrefPattern = Pattern
				.compile(hrefReg, Pattern.CASE_INSENSITIVE);
		Matcher hrefMatcher = hrefPattern.matcher(href);
		if (hrefMatcher.matches()) {
			return hrefMatcher.group(1);
		}
		return href;
	}

/**
     * process special char in html
     * 
     * <pre>
     * htmlEscapeCharsToString(null) = null;
     * htmlEscapeCharsToString("") = "";
     * htmlEscapeCharsToString("mp3") = "mp3";
     * htmlEscapeCharsToString("mp3&lt;") = "mp3<";
     * htmlEscapeCharsToString("mp3&gt;") = "mp3\>";
     * htmlEscapeCharsToString("mp3&amp;mp4") = "mp3&mp4";
     * htmlEscapeCharsToString("mp3&quot;mp4") = "mp3\"mp4";
     * htmlEscapeCharsToString("mp3&lt;&gt;&amp;&quot;mp4") = "mp3\<\>&\"mp4";
     * </pre>
     * 
     * @param source
     * @return
     */
	public static String htmlEscapeCharsToString(String source) {
		return StringUtils.isEmpty(source) ? source : source
				.replaceAll("&lt;", "<").replaceAll("&gt;", ">")
				.replaceAll("&amp;", "&").replaceAll("&quot;", "\"");
	}

	/**
	 * transform half width char to full width char
	 * 
	 * <pre>
	 * fullWidthToHalfWidth(null) = null;
	 * fullWidthToHalfWidth("") = "";
	 * fullWidthToHalfWidth(new String(new char[] {12288})) = " ";
	 * fullWidthToHalfWidth("！＂＃＄％＆) = "!\"#$%&";
	 * </pre>
	 * 
	 * @param s
	 * @return
	 */
	public static String fullWidthToHalfWidth(String s) {
		if (isEmpty(s)) {
			return s;
		}

		char[] source = s.toCharArray();
		for (int i = 0; i < source.length; i++) {
			if (source[i] == 12288) {
				source[i] = ' ';
				// } else if (source[i] == 12290) {
				// source[i] = '.';
			} else if (source[i] >= 65281 && source[i] <= 65374) {
				source[i] = (char) (source[i] - 65248);
			} else {
				source[i] = source[i];
			}
		}
		return new String(source);
	}

	/**
	 * transform full width char to half width char
	 * 
	 * <pre>
	 * halfWidthToFullWidth(null) = null;
	 * halfWidthToFullWidth("") = "";
	 * halfWidthToFullWidth(" ") = new String(new char[] {12288});
	 * halfWidthToFullWidth("!\"#$%&) = "！＂＃＄％＆";
	 * </pre>
	 * 
	 * @param s
	 * @return
	 */
	public static String halfWidthToFullWidth(String s) {
		if (isEmpty(s)) {
			return s;
		}

		char[] source = s.toCharArray();
		for (int i = 0; i < source.length; i++) {
			if (source[i] == ' ') {
				source[i] = (char) 12288;
				// } else if (source[i] == '.') {
				// source[i] = (char)12290;
			} else if (source[i] >= 33 && source[i] <= 126) {
				source[i] = (char) (source[i] + 65248);
			} else {
				source[i] = source[i];
			}
		}
		return new String(source);
	}

	/**
	 *
	 * @param str  原字符串
	 * @param subStr   替换字符串
	 * @param left   左边保留几位
	 * @param right   右边保留几位
	 * @return
	 */
	public static String relpase(String str, String subStr, int left, int right) {
		if(null == str) {
			return "";
		}

		int len = str.length();
		if(len <= left + right) {
			return str;
		}

		String tmp = "";
		int fillLen = len-left-right;
		for(int i=0; i<fillLen; i++) {
			tmp += subStr;
		}

		String newStr = str.substring(0, left) + tmp.substring(0, fillLen) + str.substring(len-right);

		return newStr;
	}

}
