import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    private static final Logger logger = LogManager.getLogger(StringUtils.class);

    private static final String ILLEGAL_SEARCHCAHRACTERS = "请勿输入下列字符\r\'，--，#，/*，*/，;，+，|，$，%，\"，\\,)";

    private static final Pattern ILLEGAL_SEARCH_PATTERN = Pattern.compile(
            "\\'|--|#|/\\*|\\*/|\\+|;|\\||(&lt;)|(&gt;)|\\$|\\%|\"|\\\\|\\)|(%27)|(%23)|(%2f\\*)|(\\*%2f)|(%2B)|(%3B)",
            Pattern.CASE_INSENSITIVE);

    private static final Pattern ESCAPE_STRING_PATTERN = Pattern.compile(
            "\\'|\\\\",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern ESCAPE_STRING_PATTERN_LIKE = Pattern.compile(
//            "\\'|#|\\$|\\%|\"|\\\\|_",
            "\\'|\\%|\\\\|_",
            Pattern.CASE_INSENSITIVE);

    private static final Pattern TOCALMEL_CASE_PATTERN = Pattern.compile("_\\w");

    private static final Pattern TOHUNGARIAN_CASE_PATTERN = Pattern.compile("[A-Z]");

    private static final Pattern EMOJI_PATTERN = Pattern.compile(
            "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
            Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

    /**
     * 判断字符串中是否包含如下字符
     * [\']，[-- ]，[#]，[/*]，[+]，[;]
     * 主要用于判断前端搜索条件中是否有输入可能尝试注入的非法字符
     */
    public static boolean haveIllegalSearchCharacters(String param) {
        boolean flag = false;
//        Pattern pattern = Pattern.compile(
//                "\\'|--|#|/\\*|\\*/|\\+|;|\\||(&lt;)|(&gt;)|\\$|\\%|\"|\\\\|\\)|(%27)|(%23)|(%2f\\*)|(\\*%2f)|(%2B)|(%3B)",
//                Pattern.CASE_INSENSITIVE);
        Matcher match = ILLEGAL_SEARCH_PATTERN.matcher(param);
        while (match.find()) {
            flag = true;
        }
        return flag;
    }

    public static String changeIllegalSearchCharacters(String param) {
        String result = param.replaceAll("\\'|--|#|/\\*|\\*/|\\+|;|\\||&|\\$|\\%|\"|\\\\|\\)|(%27)|(%23)|(%2f\\*)|(\\*%2f)|(%2B)|(%3B)", "");
        return result;
    }

    public static String getIllegalSearchCharactersInfo() {
        return ILLEGAL_SEARCHCAHRACTERS;
    }


    /*
     * 逗号分隔的字符串转换成字符数组
     * */
    public static String[] convertStrToArray(String str) {
        StringTokenizer st = new StringTokenizer(str, ",");//把","作为分割标志，然后把分割好的字符赋予StringTokenizer对象。
        //通过StringTokenizer 类的countTokens方法计算在生成异常之前可以调用此 tokenizer 的 nextToken 方法的次数。
        String[] strArray = new String[st.countTokens()];
        int i = 0;
        while (st.hasMoreTokens()) { //看看此 tokenizer 的字符串中是否还有更多的可用标记。
            strArray[i++] = st.nextToken();//返回此 string tokenizer 的下一个标记。
        }
        return strArray;
    }

    /**
     * 传入数组把值转化为字符串
     *
     * @param arry
     * @param symbol
     * @return
     */
    public static String getStr(String[] arry, String symbol) {
        String str = "";

        if (arry.length > 0) {
            for (int i = 0; i < arry.length - 1; i++) {
                str += arry[i] + symbol;
            }
        }
        str += arry[arry.length - 1];
        return str;
    }


    /**
     * 字符串与application/x-www-form-rulencoded MIME字符串之间的转换
     *
     * @param str
     * @param enc
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String urlEncoder(String str, String enc) throws UnsupportedEncodingException {
        if (enc == null || enc.length() <= 0) {
            enc = "UTF-8";
        }
        String keyWord = URLEncoder.encode(str, enc);
        return keyWord;
    }


    /**
     * 判断字符串是否为空，为空返回true；否则返回false
     *
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        if (null == str || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 空转空串
     *
     * @param str
     * @return
     */
    public static String null2EmptyString(String str) {
        try {
            if (null == str || str.length() <= 0 || "null".equals(str)) {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
        return str;
    }

    /**
     * 判断字符串是否为数字，为空返回true；否则返回false
     *
     * @param str
     * @return
     */
    public static boolean isNum(String str) {
        return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }

    // Delim style
    public static final String DELIM_DEFAULT = ".";

    private StringUtils() {

    }

    /**
     * 将指定对象转换成字符串
     *
     * @param obj 指定对象
     * @return 转换后的字符串
     */
    public static String toString(Object obj) {
        StringBuffer buffer = new StringBuffer();
        if (obj != null) {
            buffer.append(obj);
        }
        return buffer.toString();
    }

    /**
     * 判断指定字符串是否等于null或空字符串
     *
     * @param str 指定字符串
     * @return 如果等于null或空字符串则返回true，否则返回false
     */
    public static boolean isBlank(String str) {
        return str == null || "".equals(str.trim()) || str == "null";
    }

    /**
     * 判断指定字符串是否不等于null和空字符串
     *
     * @param str 指定字符串
     * @return 如果不等于null和空字符串则返回true，否则返回false
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 根据默认分隔符获取字符串前缀
     *
     * @param str 指定字符串
     * @return 返回前缀字符串
     */
    public static String getPrefix(String str) {
        return getPrefix(str, DELIM_DEFAULT);
    }

    /**
     * 根据指定分隔符获取字符串前缀
     *
     * @param str   指定字符串
     * @param delim 指定分隔符
     * @return 返回字符串前缀
     */
    public static String getPrefix(String str, String delim) {
        String prefix = "";
        if (isNotBlank(str) && isNotBlank(delim)) {
            int pos = str.indexOf(delim);
            if (pos > 0) {
                prefix = str.substring(0, pos);
            }
        }
        return prefix;
    }

    /**
     * 根据默认分隔符获取字符串后缀
     *
     * @param str 指定字符串
     * @return 返回字符串后缀
     */
    public static String getSuffix(String str) {
        return getSuffix(str, DELIM_DEFAULT);
    }

    /**
     * 根据指定分隔符获取字符串后缀
     *
     * @param str   指定字符串
     * @param delim 指定分隔符
     * @return 返回字符串后缀
     */
    public static String getSuffix(String str, String delim) {
        String suffix = "";
        if (isNotBlank(str) && isNotBlank(delim)) {
            int pos = str.lastIndexOf(delim);
            if (pos > 0) {
                suffix = str.substring(pos + 1);
            }
        }
        return suffix;
    }

    /**
     * 根据指定字符串和重复次数生成新字符串
     *
     * @param str         指定字符串
     * @param repeatCount 重复次数
     * @return 返回生成的新字符串
     */
    public static String newString(String str, int repeatCount) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < repeatCount; i++) {
            buf.append(str);
        }
        return buf.toString();
    }

    /**
     * 隐藏字符串指定位置的字符
     *
     * @param str    指定字符串
     * @param index  起始位置
     * @param length 字符长度
     * @return 返回隐藏字符后的字符串
     */
    public static String hideChars(String str, int index, int length) {
        return hideChars(str, index, length, true);
    }

    /**
     * 隐藏字符串指定位置的字符
     *
     * @param str       指定字符串
     * @param start     起始位置
     * @param end       结束位置
     * @param confusion 是否混淆隐藏的字符个数
     * @return 返回隐藏字符后的字符串
     */
    public static String hideChars(String str, int start, int end,
                                   boolean confusion) {
        StringBuffer buf = new StringBuffer();
        if (isNotBlank(str)) {
            int startIndex = Math.min(start, end);
            int endIndex = Math.max(start, end);
            // 如果起始位置超出索引范围则默认置为0
            if (startIndex < 0 || startIndex > str.length()) {
                startIndex = 0;
            }
            // 如果结束位置超出索引范围则默认置为字符串长度
            if (endIndex < 0 || endIndex > str.length()) {
                endIndex = str.length();
            }
            String temp = newString("*", confusion ? 4 : endIndex - startIndex);
            buf.append(str).replace(startIndex, endIndex, temp);

        }
        return buf.toString();
    }

    /**
     * 将指定字符串转换成小写
     *
     * @param str 指定字符串
     * @return 返回转换后的小写字符串
     */
    public static String toLowerCase(String str) {
        StringBuffer buffer = new StringBuffer(str);
        for (int i = 0; i < buffer.length(); i++) {
            char temp = buffer.charAt(i);
            buffer.setCharAt(i, Character.toLowerCase(temp));
        }
        return buffer.toString();
    }

    /**
     * 将指定字符串转换成大写
     *
     * @param str 指定字符串
     * @return 返回转换后的大写字符串
     */
    public static String toUpperCase(String str) {
        StringBuffer buffer = new StringBuffer(str);
        for (int i = 0; i < buffer.length(); i++) {
            char temp = buffer.charAt(i);
            buffer.setCharAt(i, Character.toUpperCase(temp));
        }
        return buffer.toString();
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String firstStringToUpperCase(String str) {
        if (isNull(str)) {
            return str;
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 取在str中第一次出现separator之后的字符串
     *
     * @param str
     * @param separator
     * @return
     */
    public static String substringAfterLast(String str, String separator) {
        if (isNull(str)) {
            return str;
        } else if (isNull(separator)) {
            return "";
        } else {
            int pos = str.lastIndexOf(separator);
            return pos != -1 && pos != str.length() - separator.length() ? str.substring(pos + separator.length()) : "";
        }
    }

    /**
     * 将指定字符串转换成驼峰命名方式
     *
     * @param str 指定字符串
     * @return 返回驼峰命名方式
     */
    public static String toCalmelCase(String str) {
        StringBuffer buffer = new StringBuffer(str);
        if (buffer.length() > 0) {
            // 将首字母转换成小写
            char tempChar = buffer.charAt(0);
            buffer.setCharAt(0, Character.toLowerCase(tempChar));
            Matcher matcher = TOCALMEL_CASE_PATTERN.matcher(buffer.toString());
            while (matcher.find()) {
                String temp = matcher.group(); // 匹配的字符串
                int index = buffer.indexOf(temp); // 匹配的位置
                // 去除匹配字符串中的下划线，并将剩余字符转换成大写
                buffer.replace(index, index + temp.length(),
                        temp.replace("_", "").toUpperCase());
            }
        }
        return buffer.toString();
    }

    /**
     * 将指定字符串转换成匈牙利命名方式
     *
     * @param str 指定字符串
     * @return 转换后的匈牙利命名方式
     */
    public static String toHungarianCase(String str) {
        StringBuffer buffer = new StringBuffer(str);
        if (buffer.length() > 0) {
            Matcher matcher = TOHUNGARIAN_CASE_PATTERN.matcher(buffer.toString());
            while (matcher.find()) {
                String temp = matcher.group(); // 匹配的字符串
                int index = buffer.indexOf(temp); // 匹配的位置
                // 在匹配的字符串前添加下划线，并将其余字符转换成大写
                buffer.replace(index, index + temp.length(), (index > 0
                        ? "_"
                        : "") + temp.toLowerCase());
            }
        }
        return buffer.toString();
    }

    /**
     * 将指定字符串首字母转换成大写字母
     *
     * @param str 指定字符串
     * @return 返回首字母大写的字符串
     */
    public static String firstCharUpperCase(String str) {
        StringBuffer buffer = new StringBuffer(str);
        if (buffer.length() > 0) {
            char temp = buffer.charAt(0);
            buffer.setCharAt(0, Character.toUpperCase(temp));
        }
        return buffer.toString();
    }

    /**
     * 将指定字符串首字母转换成小写字母
     *
     * @param str 指定字符串
     * @return 返回首字母小写的字符串
     */
    public static String firstCharLowerCase(String str) {
        StringBuffer buffer = new StringBuffer(str);
        if (buffer.length() > 0) {
            char temp = buffer.charAt(0);
            buffer.setCharAt(0, Character.toLowerCase(temp));
        }
        return buffer.toString();
    }

    /**
     * 将指定数组转换成字符串
     *
     * @param objs 指定数组
     * @return 返回转换后的字符串
     */
    public static String array2String(Object[] objs) {
        StringBuffer buffer = new StringBuffer();
        if (objs != null) {
            for (int i = 0; i < objs.length; i++) {
                buffer.append(objs[i]).append(",");
            }
        }
        buffer.deleteCharAt(buffer.length() - 1);
        return buffer.toString();
    }

    /**
     * 比较两个字符串是否相等
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        }
        return str1.equals(str2);
    }

    /**
     * to HexString
     * 转换为hex编码
     */
    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    /**
     * 把null替换为""
     *
     * @param str
     * @return
     */
    public static String replaceNullString(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    /**
     * 计算str在oriStr的出现次数
     *
     * @param str
     * @param oriStr
     * @return
     */
    public static int strRepetitions(String str, String oriStr) {
        int count = 0;
        for (String tmp = str; tmp != null && tmp.length() >= oriStr.length(); ) {
            if (tmp.indexOf(oriStr) == 0) {
                count++;
            }
            tmp = tmp.substring(1);
        }
        return count;
    }

    /*
     * 直接使用正则表达式匹配编码范围，然后替换
     * mysql报错：java.sql.SQLException: Incorrect string value: '\xF0\x9F\x90\x94"
     * */
    public static String filterEmoji(String source) {

        if (source != null) {
//            Pattern emoji = Pattern.compile(
//                    "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
//                    Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
            Matcher emojiMatcher = EMOJI_PATTERN.matcher(source);
            if (emojiMatcher.find()) {
                source = emojiMatcher.replaceAll("*");
                source = StringUtils.filterEmoji2(source);
                return source;
            }
            source = StringUtils.filterEmoji2(source);
            return source;
        }
        return source;
    }

    /**
     * emoji表情替换
     *
     * @param source 原字符串
     * @return 过滤后的字符串
     */
    public static String filterEmoji2(String source) {
        if (source.contains("*")) {
            //TODO
        }
        if (StringUtils.isBlank(source)) {
            return source;
        }
        StringBuilder buf = null;
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isNotEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
                buf.append(codePoint);
            }
        }
        if (buf == null) {
            return source;
        } else {
            if (buf.length() == len) {
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
    }

    private static boolean isNotEmojiCharacter(char codePoint) {
        return (codePoint == 0x0)
                || (codePoint == 0x9)
                || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 获取随机字母数字组合
     *
     * @param length 字符串长度
     * @return
     */
    public static String getRandomCharAndNumr(Integer length) {
        String str = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            boolean flag = random.nextBoolean();
            if (flag) { // 字符串
                // int choice = random.nextBoolean() ? 65 : 97; 取得65大写字母还是97小写字母
                str += (char) (65 + random.nextInt(26));// 取得大写字母
            } else { // 数字
                str += String.valueOf(random.nextInt(10));
            }
        }
        return str;
    }

    /**
     * 比较两个字符串是否相等，英文字母不区分大小写
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        }
        return str1.equalsIgnoreCase(str2);
    }

    /**
     * 校验表名和表字段长度是否符合要求
     *
     * @param param  需要校验的参数
     * @param length 指定参数的最大长度
     * @return
     */
    public static boolean checkTable(String param, int length) {
        if (null == param) {
            return false;
        }
        return param.length() <= length;
    }

    public static void main(String[] args) {
    }

}
