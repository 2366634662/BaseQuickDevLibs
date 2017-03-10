package com.basequickdevlibs.utils;

import android.net.Uri;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 判断字符串是否为null或长度为0 isEmpty
 * 判断字符串是否为null或全为空格 isSpace
 * null转为长度为0的字符串 null2Length0
 * 返回字符串长度 length
 * 首字母大写 upperFirstLetter
 * 首字母小写 lowerFirstLetter
 * 转化为半角字符 toDBC
 * 转化为全角字符 toSBC
 */
public class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("********----------********");
    }

    /**
     * 判断字符串是否为null或长度为0
     *
     * @param string 待校验字符串
     * @return {@code true}: 空<br> {@code false}: 不为空
     */
    public static boolean isEmpty(CharSequence string) {
        return string == null || string.length() == 0;
    }

    /**
     * 判断字符串是否为null或全为空格
     *
     * @param string 待校验字符串
     * @return {@code true}: null或全空格<br> {@code false}: 不为null且不全空格
     */
    public static boolean isSpace(String string) {
        return (string == null || string.trim().length() == 0);
    }


    /**
     * null转为长度为0的字符串
     *
     * @param string 待转字符串
     * @return string为null转为长度为0字符串，否则不改变
     */
    public static String null2Length0(String string) {
        return string == null ? "" : string;
    }

    /**
     * 返回字符串长度
     *
     * @param string 字符串
     * @return null返回0，其他返回自身长度
     */
    public static int length(CharSequence string) {
        return string == null ? 0 : string.length();
    }

    /**
     * 首字母大写
     *
     * @param string 待转字符串
     * @return 首字母大写字符串
     */
    public static String upperFirstLetter(String string) {
        if (isEmpty(string) || !Character.isLowerCase(string.charAt(0))) {
            return string;
        }
        return String.valueOf((char) (string.charAt(0) - 32)) + string.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param string 待转字符串
     * @return 首字母小写字符串
     */
    public static String lowerFirstLetter(String string) {
        if (isEmpty(string) || !Character.isUpperCase(string.charAt(0))) {
            return string;
        }
        return String.valueOf((char) (string.charAt(0) + 32)) + string.substring(1);
    }

    /**
     * 转化为半角字符
     *
     * @param string 待转字符串
     * @return 半角字符串
     */
    public static String toDBC(String string) {
        if (isEmpty(string)) {
            return string;
        }
        char[] chars = string.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == 12288) {
                chars[i] = ' ';
            } else if (65281 <= chars[i] && chars[i] <= 65374) {
                chars[i] = (char) (chars[i] - 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * 转化为全角字符
     *
     * @param string 待转字符串
     * @return 全角字符串
     */
    public static String toSBC(String string) {
        if (isEmpty(string)) {
            return string;
        }
        char[] chars = string.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == ' ') {
                chars[i] = (char) 12288;
            } else if (33 <= chars[i] && chars[i] <= 126) {
                chars[i] = (char) (chars[i] + 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }


    /**
     * @param string
     * @return
     */
    public static boolean isNotEmpty(String string) {
        if (null != string && !"".equals(string.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 去掉一个字符串中的所有的单个空格" "
     *
     * @param string
     */
    public static String replaceSpaceCharacter(String string) {
        if (null == string || "".equals(string)) {
            return "";
        }
        return string.replace(" ", "");
    }

    /**
     * 获取小数位为6位的经纬度
     *
     * @param string
     * @return
     */
    public static String getStringLongitudeOrLatitude(String string) {
        if (StringUtils.isEmpty(string)) {
            return "";
        }
        if (string.contains(".")) {
            String[] splitArray = string.split("\\.");
            if (splitArray[1].length() > 6) {
                String substring = splitArray[1].substring(0, 6);
                return splitArray[0] + "." + substring;
            } else {
                return string;
            }
        } else {
            return string;
        }
    }

    /**
     * 检查是否有中文
     *
     * @param str
     * @return
     */
    public static boolean checkString(String str) {
        boolean res = false;
        if (str != null) {
            for (int i = 0; i < str.length(); i++) {
                // 只要字符串中有中文则为中文
                if (!checkChar(str.charAt(i))) {
                    res = true;
                    break;
                } else {
                    res = false;
                }
            }
        }
        return res;
    }

    // 英文占1byte，非英文（可认为是中文）占2byte，根据这个特性来判断字符
    public static boolean checkChar(char ch) {
        if ((ch + "").getBytes().length == 1) {
            return true;// 英文
        } else {
            return false;// 中文
        }
    }

    /**
     * 转化成int
     *
     * @param s
     * @return
     */
    public static int getInteger(String s) {
        return isEmpty(s) ? 0 : Integer.parseInt(s);
    }

    /**
     * 获取数量s
     *
     * @param s
     * @return
     */
    public static String getNumString(String s) {
        return isEmpty(s) || "null".equals(s) ? "0" : s;
    }


    /**
     * 距离处理
     *
     * @param distance
     */
    public static String getDistance(String distance) {
        if (StringUtils.isEmpty(distance) || "null".equals(distance))
            return "";
        try {
            float dis = Float.parseFloat(distance);
            if (dis > 1000) {

                String ds = "" + dis / 1000;

                int index = ds.indexOf(".");
                if (index != -1)
                    return ds.substring(0, index + 2) + "km";
                return ds + "km";

            } else {
                return "" + (int) dis + "m";
            }
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取简介
     *
     * @param desc
     * @return
     */
    public static String getDesc(String desc) {
        return isEmpty(desc) ? "无" : desc;
    }

    /**
     * 判断是不是一个url
     *
     * @param url
     * @return
     */
    public static boolean isCorrectUrl(String url) {
        if (isEmpty(url))
            return false;

        if (url.startsWith("http"))
            return true;
        if (url.startsWith("fttp"))
            return true;

        return false;
    }

    /**
     * 显示消息的数量
     *
     * @param count
     * @return
     */
    public static String getMsgCount(int count) {
        if (count > 99)
            return "99+";
        return "" + count;
    }

    /**
     * 通过string获取uri
     *
     * @param url
     * @return
     */
    public static Uri getUri(String url) {
        return Uri.parse(isEmpty(url) ? "" : url);
    }

    /**
     * 保留两位小数
     *
     * @return
     */
    public static String doubleFormat(String value) {
        DecimalFormat df = new DecimalFormat("######.##");
        return df.format(Double.parseDouble(value));
    }

    public static String longToStrng(Long time, String format) {
        Date date = new Date(time);
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String getTimes(String tm) {
        long now_time = new Date().getTime();
        long dtime = Long.parseLong(tm) * 1000;
        long delay = (now_time - dtime) / 1000;
        if (delay > 0 && delay <= 60) {
            return delay + "秒前";
        } else if (delay <= 0) {
            return "刚刚";
        } else if (delay > 60 && delay < 3600) {
            return (delay / 60) + "分钟前";
        } else {
            String st = TimeUntil.timeStampT(dtime);
            return st.substring(st.indexOf("-") + 1);
        }
    }
    public  static  String  getTimeStr(String tm){
        long now_time=new Date().getTime();
        long dtime=Long.parseLong(tm)*1000;
        long delay=(now_time-dtime)/1000;
        if(delay>0&&delay<=60){
            return delay+"秒前";
        }else if(delay<0){
            return "刚刚";
        }else if(delay>60&&delay<=180){
            return (delay/60)+"刚刚";
        }else if(delay>180&&delay<=3600){
            return (delay/60)+"分钟前";
        }else if(delay>3600&&delay<=3600*12){
            return  (delay/3600)+"小时前";
        }else {
            String st = TimeUntil.timeStampT(dtime);
            return st.substring(st.indexOf("-")+1);
        }
    }
}
