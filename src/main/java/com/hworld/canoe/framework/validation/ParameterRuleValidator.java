package com.hworld.canoe.framework.validation;


import com.hworld.canoe.framework.exception.CrmValidateException;
import com.hworld.canoe.framework.utils.StringUtils;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Regular expressions 参考標準<br>
 * ASCII (DBC,半角字母,符号,数字) NUT,SOH,STX, ... ,!"#$%&,()*+,-./[0-9]:;<=>?@[A-Z][\]^_[a-z]{|}~DEL
 * 0x00(0)至0x7f(127) regex:[-]<br>
 * 日本語 (DBC,半角kana) ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝｧｨｩｪｫｬｭｮｯｰﾞﾟ
 * 65382(0xff66)至65439(0xff9f) regex:[\uff66-\uff9f]<br>
 * 日本語 (SBC,全角漢字) ex:外貨預金... regex: [\u30fc-\uff65]<br>
 * 日本語 (SBC,全角kana) ex:グプ... regex: [\u30a1-\u30f6]<br>
 * 日本語 (SBC,全角漢字和全角kana) regex: [\u30a1-\u30f6\u30fc-\uff65]<br>
 * 日本語 (SBC,全角kana,含全角'ー') regex: [\u30a1-\u30f6\u30fc] 備注(全角'ー'--> [\u30fc])<br>
 * 日本語 (SBC,全角kana,不含全角'ー') regex: [\u30a1-\u30f6] 備注(全角'ー'--> [\u30fc])<br>
 * 日本語 (SBC,全角kana,含全角'ー',全角Space' ') regex: [\u30a1-\u30f6\u30fc\u3000]<br>
 * 日本語 (SBC,平假名) ex:ぁあ-ん regex: [\u3041-\u3093]<br>
 * 日本語 (SBC,全角漢字,全角kana,平假名,全角 Space' ') regex: [^\040-\177\uff61-\uff9f]<br>
 * 半角英数字,半角kana regex: [0-9a-zA-Z\uff66-\uff9f]<br>
 * 数字 (DBC) 0-9 regex: [0-9]<br>
 * 数字 or '-'(DBC) regex: [-0-9] 英文字母(DBC) a-z,A-Z regex: [a-zA-Z ]<br>
 * 英数字 (DBC) 0-9,a-z,A-Z regex: [0-9a-zA-Z ]<br>
 * 郵箱 (DBC) ex:zhoche2008@hotmail.com regex:
 * [-\w]+(?:\.?[-\w]+)*@[-\w]+\.[-\w]+(?:\.[-\w]+)?(?:\.[-\w]+)?(?:\.[-\w]+)?<br>
 * 日期 (DBC,年月日,不含閏年,閏年単独抽出) ex:2008-12-02 2008-01-21 2007-1-21 regex:<br>
 * ^[1-9][0-9]{3}-((([0]?[2]-)(([2][0-9])|([1][0-9])|([0]?[1-9])))|((([0]?[13578])|([1][02]))-(([3][01])|([2][0-9])|([1][0-9])|([0]?[1-9])))|((([0]?[469])|11)-((30)|([2][0-9])|([1][0-9])|([0]?[1-9]))))$<br>
 * 日期 (DBC,只含年月) ex:2008-12 2008-01 2007-1 regex:<br>
 * ^[1-9][0-9]{3}-((1[0-2])|(0?[1-9]))$ <br>
 * 電話番号(DBC,日本,固定電話) ex:+02-5840-6576 or +021-5840-6576 or +0234-5840-6576 or
 * +021-58406576 ^\+?[0-9]{2,4}-?[0-9]{4}-?[0-9]{4}$ 郵便番号(DBC,日本) ex:000-0000
 * regex: [0-9]{3}-[0-9]{4}
 * ------------------------------------------------------------------------------------------
 * check html input value is or not legal 参照: navi --> common.html
 *
 * @author 鐘城 2009/01/12
 * @author Unicom 呉峰/劉善科 2012/09/15
 */
public class ParameterRuleValidator {

    /** ************************regular expressions start************************ */
    /**
     * 日本語(DBC,半角kana)
     */
    private static Pattern ptnDBCKana = Pattern.compile("[\uff66-\uff9f]*");

    /**
     * 日本語(DBC,半角kana,半角Space)
     */
    private static Pattern ptnDBCKanaSpace = Pattern
            .compile("[\uff66-\uff9f\u0020]*");

    /**
     * 日本語(SBC,全角kana,全角'ー')
     */
    private static Pattern ptnSBCKana = Pattern.compile("[\u30a1-\u30f6\u30fc]*");

    /**
     * 日本語(SBC,全角kana,全角'ー',全角Space' ')
     */
    private static Pattern ptnSBCKanaSpace = Pattern
            .compile("[\u30a1-\u30f6\u30fc\u3000]*");

    /**
     * 日本語(DBC or SBC,半角kana,半角Space,全角kana,全角Space,全角'ー')
     */
    private static Pattern ptnDBCOrSBCKanaSpace = Pattern
            .compile("[\uff66-\uff9f\u30a1-\u30f6\u3000\u0020\u30fc]*");

    /**
     * 日本語(SBC,全角漢字,全角kana,平假名,Full Space' ')
     */
    private static Pattern ptnSBCAll = Pattern
            .compile("[^\040-\177\uff61-\uff9f]*");

    /**
     * 半角英数字,半角kana
     */
    private static Pattern ptnDBCKanaDigitLetter = Pattern
            .compile("[0-9a-zA-Z\uff66-\uff9f]*");

    /**
     * 数字(DBC)0-多个
     */
    private static Pattern ptnDBCDigit = Pattern.compile("[0-9]*");

    /**
     * 数字(DBC)1-多个
     */
    private static Pattern ptnDBCDigitAssert = Pattern.compile("[0-9]+");

    /**
     * 数字 or '-'(DBC)0-多个
     */
    private static Pattern ptnDBCDigitOrBar = Pattern.compile("[-0-9]*");

    /**
     * 半角英数字(DBC)
     */
    private static Pattern ptnDBCDigitOrLetter = Pattern.compile("[0-9a-zA-Z]*");

    /**
     * 郵箱(DBC)
     */
    private static Pattern ptnDBCEmail = Pattern
            .compile("[-\\w]+(?:\\.?[-\\w]+)*@[-\\w]+\\.[-\\w]+(?:\\.[-\\w]+)?(?:\\.[-\\w]+)?(?:\\.[-\\w]+)?");

    /**
     * 日期(DBC,不含閏年,閏年単独抽出
     */
    private static final String regexDBCDate = "^[1-9][0-9]{3}-  \n" // yyyy-->ex:1000~9999
            + "((([0]?[2]-)(([2][0-9])|([1][0-9])|([0]?[1-9])))| \n" // 2月-->ex:2-19,02-19,2-1,2-28
            + "((([0]?[13578])|([1][02]))-(([3][01])|([2][0-9])|([1][0-9])|([0]?[1-9])))| \n" // 大月(1,3,5,7,8,10,12)-->ex:5-31
            + "((([0]?[469])|11)-((30)|([2][0-9])|([1][0-9])|([0]?[1-9]))))$"; // 小月(4,6,9,11)-->ex:4-01,4-1

    /**
     * 日期(DBC,不含閏年,閏年単独抽出
     */
    private static Pattern ptnDBCDate = Pattern.compile(regexDBCDate,
            Pattern.COMMENTS);

    /**
     * 日期 (DBC,只含年月) ex:2008-12 2008-01 2007-1
     */
    private static Pattern ptnDBCDateYM = Pattern
            .compile("^[1-9][0-9]{3}-((1[0-2])|(0?[1-9]))$");

    /**
     * 電話番号(日本,固定電話) (DBC) +,-,0-9
     */
    private static Pattern ptnDBCPhoneResJP = Pattern.compile("^[-+0-9]*$");

    /**
     * 郵便番号(DBC,日本) 000-0000
     */
    private static Pattern ptnDBCPostalCodeJP = Pattern
            .compile("[0-9]{3}-[0-9]{4}");

    /**
     * CIF(DBC) P00000111112222,Y99999888887777,e00000800006532
     */
    private static Pattern ptnCIF = Pattern.compile("^[a-zA-Z][0-9]{14}$");

    /**
     * 英文字(DBC)
     */
    private static Pattern ptnDBCLetter = Pattern.compile(".*[a-zA-Z].*");

    /**
     * 英小文字(DBC)
     */
    private static Pattern ptnDBCLetterLow = Pattern.compile(".*[a-z].*");

    /**
     * 英大文字(DBC)
     */
    private static Pattern ptnDBCLetterUp = Pattern.compile(".*[A-Z].*");

    /**
     * 数字数字(DBC)
     */
    private static Pattern ptnDBCDigital = Pattern.compile(".*[0-9].*");

    /**
     * 半角数字記号 日付入力項目の書式をyyyy/mm/dd
     */
    private static Pattern ptnDigitalSymBol = Pattern
            .compile("[0-9]{4}/[0-9]{2}/[0-9]{2}");

    /** ************************regular expressions end************************ */

    /** ************************checkType's constants start************************ */
    /**
     * checkValue must be not null
     */
    public static final String NN = "NN";

    /**
     * checkValue must be not null when checkParam is not empty
     */
    public static final String NN_WHEN_NOT_EMPTY = "NNWhenNotEmpty";

    /**
     * checkValue must be null when checkParam is not empty
     */
    public static final String N_WHEN_NOT_EMPTY = "NWhenNotEmpty";

    /**
     * checkValue must be not null when checkParam is empty
     */
    public static final String NN_WHEN_EMPTY = "NNWhenEmpty";

    /**
     * checkValue must be null when checkParam is empty
     */
    public static final String N_WHEN_EMPTY = "NWhenEmpty";

    /**
     * checkValue must be not null when checkParam[0] equals checkParam[1]
     */
    public static final String NN_WHEN_EQUAL = "NNWhenEqual";

    /**
     * checkValue must be null when checkParam[0] equals checkParam[1]
     */
    public static final String N_WHEN_EQUAL = "NWhenEqual";

    /**
     * checkValue must be number ex:2009 or new Object[]{"2000","12"}
     */
    public static final String DIGIT_ALL = "DigitAll";

    /**
     * checkValue must be number or bar ex:080-62 or new
     * Object[]{"0110-41","020-22-54"}
     */
    public static final String DIGIT_OR_BAR = "DigitOrBar";

    /**
     * checkValue's bytes length must less than checkParam
     */
    public static final String LEN_B_LESS = "LenBLess";

    /**
     * checkValue's bytes length must greater than checkParam
     */
    public static final String LEN_B_GREATER = "LenBGreater";

    /**
     * checkValue's bytes length must between checkParam[0],checkParam[1]
     */
    public static final String LEN_B_BETWEEN = "LenBBetween";

    /**
     * checkValue's bytes length must equals to checkParam
     */
    public static final String LEN_B_EQUAL = "LenBEqual";

    /**
     * the int value of checkValue must between checkParam[0],checkParam[1]
     */
    public static final String DIGIT_BETWEEN = "DigitBetween";

    /**
     * checkValue must be number or half char. ex:"admin2009"
     */
    public static final String DBC_DIGIT_OR_LETTER = "DBCDigitOrLetter";

    /**
     * checkValue must be full japanese,kana,gana,full space ' ',full char,full
     * digit. ex: "劉さん 私も引き続き考えてみますがＡＢＣ１２３"
     */
    public static final String SBC_ALL = "SBCAll";

    /**
     * checkValue must be half kana. ex:
     * "ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝｧｨｩｪｫｬｭｮｯｰﾞﾟ"
     */
    public static final String DBC_KANA = "DBCKana";

    /**
     * checkValue must be half kana or half space. ex: "ｱｲｳ ｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂ
     * ﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝｧｨｩｪｫｬｭｮｯｰﾞﾟ"
     */
    public static final String DBC_KANA_SPACE = "DBCKanaSpace";

    /**
     * checkValue must be half kana,number,char. ex: "ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁ2009happy"
     */
    public static final String DBC_KANA_DIGIT_LETTER = "DBCKanaDigitLetter";

    /**
     * checkValue must be full kana,macron 'ー'. ex: "システムグループー"
     */
    public static final String SBC_KANA = "SBCKanaMacron";

    /**
     * checkValue must be full kana,space ' '. ex: "シス テムグルプ"
     */
    public static final String SBC_KANA_SPACE = "SBCKanaSpace";

    /**
     * SBC_KAKU_THO
     */
    public static final String SBC_KAKU_THO = "SBCKakutho";

    /**
     * ptnSBCKakutho
     */
    public Pattern ptnSBCKakutho = null;
    ;

    /**
     * @param cifKanaExPtnType String
     */
    public void build(String cifKanaExPtnType) {

        ptnSBCKakutho = Pattern.compile(cifKanaExPtnType);
    }

    /**
     * checkValue must be full kana,space ' ' or half kana space ' '. ex: "ｱｲ ｳシス
     * テムグループー"
     */
    public static final String DBC_OR_SBC_KANA_SPACE = "DBCOrSBCKanaSpace";

    /**
     * checkValue must be email address. ex: "unicom@111.com"
     */
    public static final String EMAIL = "Email";

    /**
     * checkValue array must be date day. ex: new Object[]{"2008","2","28"}
     */
    public static final String DATE_VALID = "DateValid";

    /**
     * checkValue array must be date day like yyyy-mm. ex: new
     * Object[]{"2008","2"}
     */
    public static final String DATE_VALID_YM = "DateValidYM";

    /**
     * the date of checkValue array must after than checkParam array.<br>
     * ex: checkValue = new Object[]{"2008","1","20"}; <br>
     * ex: checkParam = new Object[]{"2008","1","19"};<br>
     */
    public static final String DATE_AFTER = "DateAfter";

    /**
     * the date of checkValue array must after than checkParam array.<br>
     * ex: checkValue = new Object[]{"2008","1"}; <br>
     * ex: checkParam = new Object[]{"2008","3"}; <br>
     */
    public static final String DATE_AFTER_YM = "DateAfterYM";

    /**
     * the date of checkValue array must before than checkParam array.<br>
     * ex: checkValue = new Object[]{"2008","1","19"}; <br>
     * ex: checkParam = new Object[]{"2008","1","20"}; <br>
     */
    public static final String DATE_BEFORE = "DateBefore";

    /**
     * the date of checkValue array must before than checkParam array.<br>
     * ex: checkValue = new Object[]{"2008","3"}; <br>
     * ex: checkParam = new Object[]{"2008","1"}; <br>
     */
    public static final String DATE_BEFORE_YM = "DateBeforeYM";

    /**
     * the date of checkValue array must between checkParam[0] and checkParam[1].<br>
     * ex: checkValue = new Object[]{"2008","1","19"}; <br>
     * ex: checkParam[0] = new Object[]{"2008","1","18"}; <br>
     * ex: checkParam[1] = new Object[]{"2008","1","20"}; <br>
     */
    public static final String DATE_BETWEEN = "DateBetween";

    /**
     * the date of checkValue array must between checkParam[0] and checkParam[1].<br>
     * ex: checkValue = new Object[]{"2008","2"}; <br>
     * ex: checkParam[0] = new Object[]{"2008","1"}; <br>
     * ex: checkParam[1] = new Object[]{"2008","3"}; <br>
     */
    public static final String DATE_BETWEEN_YM = "DateBetweenYM";

    /**
     * the date of checkValue[0] and checkValue[1] must not intersect with
     * checkParam[0] and checkParam[1].<br>
     * ex: checkValue = new Object[] { new String[] { "2222", "1", "1" }, new
     * String[] { "2222", "1", "7" } }; <br>
     * ex: checkParam = new Object[] { new String[] { "2222", "1", "3" }, new
     * String[] { "2222", "1", "9" } }; <br>
     */
    public static final String DATE_NOT_INTERSECT = "DateNotIntersect";

    /**
     * the int value of checkValue must greater than checkParam.<br>
     * ex: checkValue = "25"<br>
     * ex: checkParam = "20"<br>
     */
    public static final String DIGIT_GREATER = "DigitGreater";

    /**
     * the int value of checkValue must less than checkParam.<br>
     * ex: checkValue = "20"<br>
     * ex: checkParam = "15"<br>
     */
    public static final String DIGIT_LESS = "DigitLess";

    /**
     * checkValue must equals checkParam.<br>
     * ex: checkValue = "unicom"<br>
     * ex: checkParam = "unicom"<br>
     */
    public static final String STRING_EQUAL = "StringEqual";

    /**
     * checkValue must not equals checkParam.<br>
     * ex: checkValue = "unicom"<br>
     * ex: checkParam = "microsoft"<br>
     */
    public static final String STRING_NOT_EQUAL = "StringNotEqual";

    /**
     * checkValue array must input all.<br>
     * ex: checkValue = new String[]{"2008","10","12"} --> true<br>
     * ex: checkValue = new String[]{"2008","10",""} --> false<br>
     */
    public static final String MUST_INPUT_ALL = "MustInputAll";

    /**
     * checkValue array must input one or more.<br>
     * ex: checkValue = new String[]{"","b",""} --> true<br>
     * ex: checkValue = new String[]{"","",""} --> false<br>
     */
    public static final String MUST_INPUT_PART = "MustInputPart";

    /**
     * checkValue array must input a part.<br>
     * ex: checkValue = new String[]{"a","b"} --> false<br>
     * ex: checkValue = new String[]{"a",""} --> true<br>
     * ex: checkValue = new String[]{"","b"} --> true<br>
     */
    public static final String CAN_NOT_INPUT_ALL = "CanNotInputAll";

    /**
     * checkValue must be a telephone number for residence in Japan. ex:
     * 000-0000-0000 or 0000-0000-0000 or 00000-0000-0000
     */
    public static final String PHONE_RES_JP = "PhoneResJP";

    /**
     * checkValue must be a postalcode in Japan. ex: 000-0000
     */
    public static final String POSTAL_CODE_JP = "PostalCodeJP";

    /**
     * checkValue must be not null or "0"
     */
    public static final String NN_SELECT = "NNSelect";

    /**
     * checkValue must be a cif id. ex:
     * P00000111112222,Y99999888887777,e00000800006532
     */
    public static final String CIF = "CIF";

    /**
     * checkValue must be customValue
     */
    public static final String CUSTOM_PATTERN = "CustomValue";

    /**
     * checkValue must contain letter
     */
    public static final String CONTAIN_LETTER = "ContainLetter";

    /**
     * checkValue must contain lowercase letter
     */
    public static final String CONTAIN_LETTERLOW = "ContainLetterLow";

    /**
     * checkValue must contain uppercase letter
     */
    public static final String CONTAIN_LETTERUP = "ContainLetterUp";

    /**
     * checkValue must contain digital
     */
    public static final String CONTAIN_DIGITAL = "ContainDigital";

    /**
     * chackValue must contain digital or '/'
     */
    public static final String CONTAIN_DIGTALSYMBOL = "ContainDigtalSymbol";

    /**
     * checkValue must contain customValue
     */
    public static final String CONTAIN_CUSTOM = "ContainCustom";

    /** ************************checkType's constants end************************ */

    /**
     * validate value in checkArray and return a list of error message id. the<br>
     * public method will use by others,like the main(String[]).<br>
     *
     * @param checkArray           checkArray[][0-6] define:<br>
     *                             arr[0] --> input value or array from html page. ex:"11111"<br>
     *                             arr[1] --> validate type. ex:"NN","DigitAll"<br>
     *                             arr[2] --> validate parameters. ex: new Integer[]{1000,9999}<br>
     *                             arr[3] --> error message id. ex:"MSG990501_050"<br>
     *                             arr[4] --> parameters assisting warn message to display. ex:new
     *                             Object[] {"外貨預金 支店"}<br>
     *                             arr[5] --> name of input text or select from html. ex:"branchId"<br>
     *                             arr[6] --> index of input text or select from
     *                             html,element[focusIndex] will set init focus when error happen.
     *                             ex:"0"<br>
     * @param isContinueWhenFailed (false:when one of checkArray is not valid,then
     *                             break, true:continue)<br>
     * @return SurveillanceFeatureList
     * @throws CrmValidateException CrmValidateException
     */

    public SurveillanceFeatureList checkByArray(Object[][] checkArray,
                                                boolean isContinueWhenFailed) throws CrmValidateException {
        SurveillanceFeatureList surFeatList = new SurveillanceFeatureList();
        if (checkArray == null || checkArray.length == 0) {
            return surFeatList;
        }
        for (Object[] arr : checkArray) {
            // array in checkArray's length must at least 7
            if (arr == null || arr.length < 7) {
                throw new CrmValidateException(
                        "[ ASTMN COMMOM ] ParameterRuleValidator.checkByArray()の入力パラメータが非法。",
                        "76050201");
            }
            try {
                if (!checkOne(arr)) {
                    String focusName = (String) arr[5];
                    int focusIndex = (Integer) arr[6];
                    surFeatList.add(new SurveillanceFeature((String) arr[3],
                            (Object[]) arr[4], focusName, focusIndex, true));
                    if (!isContinueWhenFailed) {
                        break;
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return surFeatList;
    }

    /**
     * filter object if null.
     *
     * @param obj Object
     * @return String
     */
    private static String filterNull(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    /**
     * obtain the whole data format according to year,month,day
     *
     * @param year  String
     * @param month String
     * @param day   String
     * @return int ex: 20090122
     */
    private static int getYYYYMMDD(String year, String month, String day) {
        StringBuffer date = new StringBuffer(10);
        date.append(year);
        date.append("-");
        if (month.length() == 1) {
            date.append("0");
        }
        date.append(month);
        date.append("-");
        if (day.length() == 1) {
            date.append("0");
        }
        date.append(day);
        java.sql.Date d = java.sql.Date.valueOf(date.toString());
        return Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(d));
    }

    /**
     * obtain the whole data format according to year,month
     *
     * @param year  String
     * @param month String
     * @return int ex: 200901
     */
    private static int getYYYYMM(String year, String month) {
        StringBuffer date = new StringBuffer(10);
        date.append(year);
        date.append("-");
        date.append(month);
        date.append("-");
        date.append("1");
        java.sql.Date d = java.sql.Date.valueOf(date.toString());
        return Integer.parseInt(new SimpleDateFormat("yyyyMM").format(d));
    }

    /**
     * check the input string is or not a date.<br>
     * input string is yyyy-mm-dd
     *
     * @param yyyy 年
     * @param mm   月
     * @param dd   日
     * @return boolean
     */
    private static boolean isDate(String yyyy, String mm, String dd) {
        // 日期合法性は検証します
        Matcher mt = ptnDBCDate.matcher(yyyy + "-" + mm + "-" + dd);
        if (!mt.matches()) {
            return false;
        }
        // 閏年単独抽出
        int y = Integer.parseInt(yyyy);
        int m = Integer.parseInt(mm);
        int d = Integer.parseInt(dd);
        if (m == 2) {
            boolean leap = (y % 4 == 0 && (y % 100 != 0 || y % 400 == 0));
            if (d == 29 && !leap) {
                return false;
            }
        }
        return true;
    }

    /**
     * check the input string is or not a date yyyy-mm or yyyy-m.<br>
     * input string is yyyy-mm or yyyy-m
     *
     * @param yyyy 年
     * @param mm   月
     * @return boolean
     */
    private static boolean isDate(String yyyy, String mm) {
        // 日期合法性は検証します
        Matcher mt = ptnDBCDateYM.matcher(yyyy + "-" + mm);
        return mt.matches();
    }

    /**
     * validate value is or not a number or number array.
     *
     * @param value Object
     * @return boolean
     */
    private static boolean isNumber(Object value) {
        // value can be a string object.
        if (value instanceof String) {
            Matcher m = ptnDBCDigit.matcher(StringUtils.trim(filterNull(value)));
            return m.matches();
        }

        // value also can be a string array.
        else if (value instanceof Object[]) {
            Object[] checkValue = (Object[]) value;
            for (Object str : checkValue) {
                Matcher m = ptnDBCDigit.matcher(StringUtils.trim(filterNull(str)));
                if (!m.matches()) {
                    return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }

    /**
     * validate value is or not [number or bar] ex:50-12.
     *
     * @param value Object
     * @return boolean
     */
    private static boolean isNumberOrBar(Object value) {
        // value can be a string object.
        if (value instanceof String) {
            Matcher m = ptnDBCDigitOrBar.matcher(StringUtils.trim(filterNull(value)));
            return m.matches();
        }

        // value also can be a string array.
        else if (value instanceof Object[]) {
            Object[] checkValue = (Object[]) value;
            for (Object str : checkValue) {
                Matcher m = ptnDBCDigitOrBar.matcher(StringUtils.trim(filterNull(str)));
                if (!m.matches()) {
                    return false;
                }
            }
        } else {
            return false;
        }

        return true;
    }

    /**
     * judges all of validate value are empty or not.
     *
     * @param obj Object
     * @return boolean
     */
    public static boolean isEmptyAll(Object obj) {
        if (obj == null) {
            return true;
        }

        // value can be a string object.
        if (obj instanceof String) {
            return StringUtils.isEmpty(filterNull(obj));
        }

        // value also can be a string array.
        else if (obj instanceof Object[]) {
            Object[] arr = (Object[]) obj;
            for (Object str : arr) {
                if (!StringUtils.isEmpty(filterNull(str))) {
                    return false;
                }
            }

        }
        return true;
    }

    /**
     * judges one or more of validate value are empty or not.
     *
     * @param obj Object
     * @return boolean
     */
    private static boolean isEmptyPart(Object obj) {
        if (obj == null) {
            return true;
        }

        // value can be a string object.
        if (obj instanceof String) {
            return StringUtils.isEmpty(filterNull(obj));
        }

        // value also can be a string array.
        else if (obj instanceof Object[]) {
            Object[] arr = (Object[]) obj;
            if (arr.length == 0) {
                return true;
            }
            for (Object str : arr) {
                if (StringUtils.isEmpty(filterNull(str))) {
                    return true;
                }
            }

        } else if (obj instanceof List) {
            List arr = (List) obj;
            if (arr == null) {
                return true;

            }
            if (arr.size() == 0) {
                return true;
            }
            for (Object str : arr) {
                if (StringUtils.isEmpty(filterNull(str))) {
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * judges one or more of validate select's value are empty or not.
     *
     * @param obj       Object
     * @param emptyFlag if emptyFlag = "0" means obj's value "0" = ""
     * @return boolean
     */
    private static boolean isEmptyPartSelect(Object obj, String emptyFlag) {
        if (obj == null) {
            return true;
        }

        if (emptyFlag == null) {
            emptyFlag = "0";
        }

        // value can be a string object.
        if (obj instanceof String) {
            return emptyFlag.equals(StringUtils.trim(filterNull(obj)));
        }

        // value also can be a string array.
        else if (obj instanceof Object[]) {
            Object[] arr = (Object[]) obj;
            for (Object str : arr) {
                if (emptyFlag.equals(StringUtils.trim(filterNull(str)))) {
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * the private method will use by checkByArray(Object[][]))<br>
     * validate value by checkType in Object[] and return true or false.<br>
     * arr[0] --> input value or array from html page. ex:"11111"<br>
     * arr[1] --> validate type. ex:"NN","DigitAll"<br>
     * arr[2] --> validate parameters. ex: new Integer[]{1000,9999}<br>
     * arr[3] --> error message id. ex:"MSG990501_050"<br>
     * arr[4] --> parameters assisting warn message to display. ex:new Object[]
     * {"外貨預金 支店"}<br>
     * arr[5] --> name of input text or select from html. ex:"branchId"<br>
     * arr[6] --> index of input text or select from html,element[index] will set
     * init focus when error happen. ex:"0"<br>
     *
     * @param arr Objectp[][]
     * @return boolean true:input data is valid,false:input data isn't valid
     * @throws UnsupportedEncodingException
     */
    private boolean checkOne(Object[] arr) throws UnsupportedEncodingException {
        Object checkValue = arr[0];
        String checkType = (String) arr[1];
        Object checkParam = arr[2];
        // checkValue must be not null
        if (NN.equals(checkType)) {
            if (isEmptyPart(checkValue)) {
                return false;
            }
        }

        // checkValue must be not null when checkParam is not empty
        else if (NN_WHEN_NOT_EMPTY.equals(checkType)) {
            if (!isEmptyAll(checkParam) && isEmptyPart(checkValue)) {
                return false;
            }
        }

        // checkValue must be null when checkParam is not empty
        else if (N_WHEN_NOT_EMPTY.equals(checkType)) {
            if (!isEmptyAll(checkParam) && !isEmptyAll(checkValue)) {
                return false;
            }
        }

        // checkValue must be not null when checkParam is empty
        else if (NN_WHEN_EMPTY.equals(checkType)) {
            if (isEmptyAll(checkParam) && isEmptyPart(checkValue)) {
                return false;
            }
        }

        // checkValue must be null when checkParam is empty
        else if (N_WHEN_EMPTY.equals(checkType)) {
            if (isEmptyAll(checkParam) && !isEmptyAll(checkValue)) {
                return false;
            }
        }

        // checkValue must be not null when checkParam[0] equals checkParam[1]
        else if (NN_WHEN_EQUAL.equals(checkType)) {
            Object[] checkParam0 = (Object[]) checkParam;
            if ((filterNull(checkParam0[0])).equals(filterNull(checkParam0[1]))
                    && isEmptyPart(checkValue)) {
                return false;
            }
        }

        // checkValue must be null when checkParam[0] equals checkParam[1]
        else if (N_WHEN_EQUAL.equals(checkType)) {
            Object[] checkParam0 = (Object[]) checkParam;
            if ((filterNull(checkParam0[0])).equals(filterNull(checkParam0[1]))
                    && !isEmptyAll(checkValue)) {
                return false;
            }
        }

        // checkValue must be number ex:2009 or new Object[]{"2000","12"}
        else if (DIGIT_ALL.equals(checkType)) {
            if (!isNumber(checkValue)) {
                return false;
            }
        }

        // checkValue must be number or bar ex:080-62 or new
        // Object[]{"0110-41","020-22-54"}
        else if (DIGIT_OR_BAR.equals(checkType)) {
            if (!isNumberOrBar(checkValue)) {
                return false;
            }
        }

        // checkValue's bytes length must less than checkParam
        else if (LEN_B_LESS.equals(checkType)) {
            if (isEmptyAll(checkValue)) {
                return true;
            }
            if (StringUtils.trim(checkValue.toString()).getBytes("SHIFT_JIS").length > (Integer) checkParam) {
                return false;
            }
        }

        // checkValue's bytes length must greater than checkParam
        else if (LEN_B_GREATER.equals(checkType)) {
            if (isEmptyAll(checkValue)) {
                return true;
            }
            if (StringUtils.trim(checkValue.toString()).getBytes("SHIFT_JIS").length < (Integer) checkParam) {
                return false;
            }
        }

        // checkValue's bytes length must between checkParam[0],checkParam[1]
        else if (LEN_B_BETWEEN.equals(checkType)) {
            if (isEmptyAll(checkValue)) {
                return true;
            }
            Object[] checkParam0 = (Object[]) checkParam;
            if (StringUtils.trim(checkValue.toString()).getBytes("SHIFT_JIS").length < (Integer) checkParam0[0]
                    || StringUtils.trim(checkValue.toString()).getBytes("SHIFT_JIS").length > (Integer) checkParam0[1]) {
                return false;
            }
        }

        // checkValue's bytes length must equals to checkParam
        else if (LEN_B_EQUAL.equals(checkType)) {
            if (isEmptyAll(checkValue)) {
                return true;
            }
            if (StringUtils.trim(checkValue.toString()).getBytes("SHIFT_JIS").length != (Integer) checkParam) {
                return false;
            }
        }

        // the int value of checkValue must between checkParam[0],checkParam[1]
        else if (DIGIT_BETWEEN.equals(checkType)) {
            if (isEmptyAll(checkValue)) {
                return true;
            }
            Object[] checkParam0 = (Object[]) checkParam;
            // checkValue数字合法性は検証します
            String checkValue0 = StringUtils.trim(filterNull(checkValue));
            Matcher m = ptnDBCDigitAssert.matcher(checkValue0);
            if (!m.matches()) {
                return false;
            }
            // 比較を行います
            if (StringUtils.compareTo(checkValue0, checkParam0[0].toString()) < 0
                    || StringUtils.compareTo(checkValue0, checkParam0[1].toString()) > 0) {
                return false;
            }
        }

        // checkValue must be number or half char. ex:"admin2009"
        else if (DBC_DIGIT_OR_LETTER.equals(checkType)) {
            Matcher m = ptnDBCDigitOrLetter.matcher(StringUtils
                    .trim(filterNull(checkValue)));
            return m.matches();
        }

        // checkValue must be full japanese,kana,gana,full space ' '. ex: "劉さん
        // 私も引き続き考えてみますが"
        else if (SBC_ALL.equals(checkType)) {
            Matcher m = ptnSBCAll.matcher(StringUtils.trim(filterNull(checkValue)));
            return m.matches();
        }

        // checkValue must be half kana. ex:
        // "ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝｧｨｩｪｫｬｭｮｯｰﾞﾟ"
        else if (DBC_KANA.equals(checkType)) {
            Matcher m = ptnDBCKana.matcher(StringUtils.trim(filterNull(checkValue)));
            return m.matches();
        }

        // checkValue must be half kana or half space. ex:
        // "ｱｲｳ ｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂ ﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝｧｨｩｪｫｬｭｮｯｰﾞﾟ"
        else if (DBC_KANA_SPACE.equals(checkType)) {
            Matcher m = ptnDBCKanaSpace.matcher(StringUtils
                    .trim(filterNull(checkValue)));
            return m.matches();
        }

        // checkValue must be half kana,number,char. ex:
        // "ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁ2009happy"
        else if (DBC_KANA_DIGIT_LETTER.equals(checkType)) {
            Matcher m = ptnDBCKanaDigitLetter.matcher(StringUtils
                    .trim(filterNull(checkValue)));
            return m.matches();
        }

        // checkValue must be full kana,macron 'ー'. ex: "システムグループー"
        else if (SBC_KANA.equals(checkType)) {
            Matcher m = ptnSBCKana.matcher(StringUtils.trim(filterNull(checkValue)));
            return m.matches();
        }

        // checkValue must be full kana,space ' ', macron 'ー'. ex: "システ ムグループー"
        else if (SBC_KANA_SPACE.equals(checkType)) {
            Matcher m = ptnSBCKanaSpace.matcher(StringUtils
                    .trim(filterNull(checkValue)));
            return m.matches();
        }

        /********************* AssetManager_2012年保守開発対応 Begin *********************/
        else if (SBC_KAKU_THO.equals(checkType)) {
            Matcher m = ptnSBCKakutho.matcher(StringUtils.trim(filterNull(checkValue)));
            return m.matches();
        }
        /********************* AssetManager_2012年保守開発対応 End ***********************/

        // checkValue must be full kana,space ' ' or half kana space ' '. ex: "ｱｲ
        // ｳシス テムグループー"
        else if (DBC_OR_SBC_KANA_SPACE.equals(checkType)) {
            Matcher m = ptnDBCOrSBCKanaSpace.matcher(StringUtils
                    .trim(filterNull(checkValue)));
            return m.matches();
        }

        // checkValue must be email address. ex: "unicom@1111.com"
        else if (EMAIL.equals(checkType)) {
            if (StringUtils.isEmpty(filterNull(checkValue))) {
                return true;
            }
            Matcher m = ptnDBCEmail.matcher(StringUtils.trim(filterNull(checkValue)));
            return m.matches();
        }

        // checkValue array must be date day. ex: new Object[]{"2008","2","28"}
        else if (DATE_VALID.equals(checkType)) {
            if (checkValue == null || StringUtils.isEmpty(checkValue.toString())
                    || isEmptyAll(checkValue)) {
                return true;
            }
            // 日期合法性は検証します
            String yyyy = StringUtils.trim(((Object[]) checkValue)[0].toString());
            String mm = StringUtils.trim(((Object[]) checkValue)[1].toString());
            String dd = StringUtils.trim(((Object[]) checkValue)[2].toString());
            return isDate(yyyy, mm, dd);
        }

        // checkValue array must be date day. ex: new Object[]{"2008","2"}
        else if (DATE_VALID_YM.equals(checkType)) {
            if (checkValue == null || StringUtils.isEmpty(checkValue.toString())
                    || isEmptyAll(checkValue)) {
                return true;
            }
            // 日期(YYYYMM)合法性は検証します
            String yyyy = StringUtils.trim(((Object[]) checkValue)[0].toString());
            String mm = StringUtils.trim(((Object[]) checkValue)[1].toString());
            return isDate(yyyy, mm);
        }

        // the date of checkValue array must after than checkParam array.
        // ex: checkValue = new Object[]{"2008","1","20"};
        // ex: checkParam = new Object[]{"2008","1","19"};
        else if (DATE_AFTER.equals(checkType)) {
            Object[] arr1 = (Object[]) checkValue;
            Object[] arr2 = (Object[]) checkParam;
            if (isEmptyAll(arr1) || isEmptyAll(arr2)) {
                return true;
            }

            // checkValue&&checkParam日期合法性は検証します
            String yyyy1 = StringUtils.trim(filterNull(arr1[0]));
            String mm1 = StringUtils.trim(filterNull(arr1[1]));
            String dd1 = StringUtils.trim(filterNull(arr1[2]));
            String yyyy2 = StringUtils.trim(filterNull(arr2[0]));
            String mm2 = StringUtils.trim(filterNull(arr2[1]));
            String dd2 = StringUtils.trim(filterNull(arr2[2]));
            if (!isDate(yyyy1, mm1, dd1) || !isDate(yyyy2, mm2, dd2)) {
                return false;
            }

            // 比較を行います
            int date1 = getYYYYMMDD(yyyy1, mm1, dd1);
            int date2 = getYYYYMMDD(yyyy2, mm2, dd2);
            if (date1 < date2) {
                return false;
            }
        }

        // the date of checkValue array must after than checkParam array.
        // ex: checkValue = new Object[]{"2008","3"};
        // ex: checkParam = new Object[]{"2008","2"};
        else if (DATE_AFTER_YM.equals(checkType)) {
            Object[] arr1 = (Object[]) checkValue;
            Object[] arr2 = (Object[]) checkParam;
            if (isEmptyAll(arr1) || isEmptyAll(arr2)) {
                return true;
            }

            // checkValue&&checkParam日期合法性は検証します(YYYYMM or YYYYM)
            String yyyy1 = StringUtils.trim(filterNull(arr1[0]));
            String mm1 = StringUtils.trim(filterNull(arr1[1]));
            String yyyy2 = StringUtils.trim(filterNull(arr2[0]));
            String mm2 = StringUtils.trim(filterNull(arr2[1]));
            if (!isDate(yyyy1, mm1) || !isDate(yyyy2, mm2)) {
                return false;
            }

            // 比較を行います
            int date1 = getYYYYMM(yyyy1, mm1);
            int date2 = getYYYYMM(yyyy2, mm2);
            if (date1 < date2) {
                return false;
            }
        }

        // the date of checkValue array must before than checkParam array.
        // ex: checkValue = new Object[]{"2008","1","19"};
        // ex: checkParam = new Object[]{"2008","1","20"};
        else if (DATE_BEFORE.equals(checkType)) {
            Object[] arr1 = (Object[]) checkValue;
            Object[] arr2 = (Object[]) checkParam;
            if (isEmptyAll(arr1) || isEmptyAll(arr2)) {
                return true;
            }

            // checkValue&&checkParam日期合法性は検証します
            String yyyy1 = StringUtils.trim(filterNull(arr1[0]));
            String mm1 = StringUtils.trim(filterNull(arr1[1]));
            String dd1 = StringUtils.trim(filterNull(arr1[2]));
            String yyyy2 = StringUtils.trim(filterNull(arr2[0]));
            String mm2 = StringUtils.trim(filterNull(arr2[1]));
            String dd2 = StringUtils.trim(filterNull(arr2[2]));
            if (!isDate(yyyy1, mm1, dd1) || !isDate(yyyy2, mm2, dd2)) {
                return false;
            }

            // 比較を行います
            int date1 = getYYYYMMDD(yyyy1, mm1, dd1);
            int date2 = getYYYYMMDD(yyyy2, mm2, dd2);
            if (date1 > date2) {
                return false;
            }
        }

        // the date of checkValue array must before than checkParam array.
        // ex: checkValue = new Object[]{"2008","1"};
        // ex: checkParam = new Object[]{"2008","3"};
        else if (DATE_BEFORE_YM.equals(checkType)) {
            Object[] arr1 = (Object[]) checkValue;
            Object[] arr2 = (Object[]) checkParam;
            if (isEmptyAll(arr1) || isEmptyAll(arr2)) {
                return true;
            }

            // checkValue&&checkParam日期合法性は検証します(YYYYMM or YYYYM)
            String yyyy1 = StringUtils.trim(filterNull(arr1[0]));
            String mm1 = StringUtils.trim(filterNull(arr1[1]));
            String yyyy2 = StringUtils.trim(filterNull(arr2[0]));
            String mm2 = StringUtils.trim(filterNull(arr2[1]));
            if (!isDate(yyyy1, mm1) || !isDate(yyyy2, mm2)) {
                return false;
            }

            // 比較を行います
            int date1 = getYYYYMM(yyyy1, mm1);
            int date2 = getYYYYMM(yyyy2, mm2);
            if (date1 > date2) {
                return false;
            }
        }

        // the date of checkValue array must between checkParam[0] and
        // checkParam[1].
        // ex: checkValue = new Object[]{"2008","1","19"};
        // ex: checkParam[0] = new Object[]{"2008","1","18"};
        // ex: checkParam[1] = new Object[]{"2008","1","20"};
        else if (DATE_BETWEEN.equals(checkType)) {
            Object[] arr1 = (Object[]) checkValue;
            Object[] arr2 = (Object[]) ((Object[]) checkParam)[0];
            Object[] arr3 = (Object[]) ((Object[]) checkParam)[1];
            if (isEmptyAll(arr1) || isEmptyAll(arr2) || isEmptyAll(arr3)) {
                return true;
            }

            // checkValue&&checkParam日期合法性は検証します
            String yyyy1 = StringUtils.trim(filterNull(arr1[0]));
            String mm1 = StringUtils.trim(filterNull(arr1[1]));
            String dd1 = StringUtils.trim(filterNull(arr1[2]));
            String yyyy2 = StringUtils.trim(filterNull(arr2[0]));
            String mm2 = StringUtils.trim(filterNull(arr2[1]));
            String dd2 = StringUtils.trim(filterNull(arr2[2]));
            String yyyy3 = StringUtils.trim(filterNull(arr3[0]));
            String mm3 = StringUtils.trim(filterNull(arr3[1]));
            String dd3 = StringUtils.trim(filterNull(arr3[2]));
            if (!isDate(yyyy1, mm1, dd1) || !isDate(yyyy2, mm2, dd2)
                    || !isDate(yyyy3, mm3, dd3)) {
                return false;
            }

            // 比較を行います
            int date1 = getYYYYMMDD(yyyy1, mm1, dd1);
            int date2 = getYYYYMMDD(yyyy2, mm2, dd2);
            int date3 = getYYYYMMDD(yyyy3, mm3, dd3);
            if (date1 < date2 || date1 > date3) {
                return false;
            }
        }

        // the date of checkValue array must between checkParam[0] and
        // checkParam[1].
        // ex: checkValue = new Object[]{"2008","2"};
        // ex: checkParam[0] = new Object[]{"2008","1"};
        // ex: checkParam[1] = new Object[]{"2008","3"};
        else if (DATE_BETWEEN_YM.equals(checkType)) {
            Object[] arr1 = (Object[]) checkValue;
            Object[] arr2 = (Object[]) ((Object[]) checkParam)[0];
            Object[] arr3 = (Object[]) ((Object[]) checkParam)[1];
            if (isEmptyAll(arr1) || isEmptyAll(arr2) || isEmptyAll(arr3)) {
                return true;
            }

            // checkValue&&checkParam日期合法性は検証します
            String yyyy1 = StringUtils.trim(filterNull(arr1[0]));
            String mm1 = StringUtils.trim(filterNull(arr1[1]));
            String yyyy2 = StringUtils.trim(filterNull(arr2[0]));
            String mm2 = StringUtils.trim(filterNull(arr2[1]));
            String yyyy3 = StringUtils.trim(filterNull(arr3[0]));
            String mm3 = StringUtils.trim(filterNull(arr3[1]));
            if (!isDate(yyyy1, mm1) || !isDate(yyyy2, mm2) || !isDate(yyyy3, mm3)) {
                return false;
            }

            // 比較を行います
            int date1 = getYYYYMM(yyyy1, mm1);
            int date2 = getYYYYMM(yyyy2, mm2);
            int date3 = getYYYYMM(yyyy3, mm3);
            if (date1 < date2 || date1 > date3) {
                return false;
            }
        }

        // the date of checkValue[0] and checkValue[1] must not intersect with
        // checkParam[0] and checkParam[1].
        // 1.following example return true
        // ex: checkValue = new Object[] { new String[] { "2222", "1", "1" }, new
        // String[] { "2222", "1", "3" } };
        // ex: checkParam = new Object[] { new String[] { "2222", "1", "5" }, new
        // String[] { "2222", "1", "7" } };
        // 2.following example return true
        // ex: checkValue = new Object[] { new String[] { "2222", "1", "5" }, new
        // String[] { "2222", "1", "7" } };
        // ex: checkParam = new Object[] { new String[] { "2222", "1", "1" }, new
        // String[] { "2222", "1", "3" } };
        // 3.following example return false
        // ex: checkValue = new Object[] { new String[] { "2222", "1", "1" }, new
        // String[] { "2222", "1", "5" } };
        // ex: checkParam = new Object[] { new String[] { "2222", "1", "3" }, new
        // String[] { "2222", "1", "7" } };
        // 4.following example return false
        // ex: checkValue = new Object[] { new String[] { "2222", "1", "3" }, new
        // String[] { "2222", "1", "7" } };
        // ex: checkParam = new Object[] { new String[] { "2222", "1", "1" }, new
        // String[] { "2222", "1", "5" } };
        // 5.following example return false
        // ex: checkValue = new Object[] { new String[] { "2222", "1", "1" }, new
        // String[] { "2222", "1", "3" } };
        // ex: checkParam = new Object[] { new String[] { "2222", "1", "3" }, new
        // String[] { "2222", "1", "5" } };
        // 6.following example return false
        // ex: checkValue = new Object[] { new String[] { "2222", "1", "3" }, new
        // String[] { "2222", "1", "5" } };
        // ex: checkParam = new Object[] { new String[] { "2222", "1", "1" }, new
        // String[] { "2222", "1", "3" } };
        else if (DATE_NOT_INTERSECT.equals(checkType)) {
            Object[] arr1 = (Object[]) ((Object[]) checkValue)[0];
            Object[] arr2 = (Object[]) ((Object[]) checkValue)[1];
            Object[] arr3 = (Object[]) ((Object[]) checkParam)[0];
            Object[] arr4 = (Object[]) ((Object[]) checkParam)[1];
            if (isEmptyAll(arr1) || isEmptyAll(arr2) || isEmptyAll(arr3)
                    || isEmptyAll(arr4)) {
                return true;
            }

            // checkValue&&checkParam日期合法性は検証します
            String yyyy1 = StringUtils.trim(filterNull(arr1[0]));
            String mm1 = StringUtils.trim(filterNull(arr1[1]));
            String dd1 = StringUtils.trim(filterNull(arr1[2]));
            String yyyy2 = StringUtils.trim(filterNull(arr2[0]));
            String mm2 = StringUtils.trim(filterNull(arr2[1]));
            String dd2 = StringUtils.trim(filterNull(arr2[2]));
            String yyyy3 = StringUtils.trim(filterNull(arr3[0]));
            String mm3 = StringUtils.trim(filterNull(arr3[1]));
            String dd3 = StringUtils.trim(filterNull(arr3[2]));
            String yyyy4 = StringUtils.trim(filterNull(arr4[0]));
            String mm4 = StringUtils.trim(filterNull(arr4[1]));
            String dd4 = StringUtils.trim(filterNull(arr4[2]));
            if (!isDate(yyyy1, mm1, dd1) || !isDate(yyyy2, mm2, dd2)
                    || !isDate(yyyy3, mm3, dd3) || !isDate(yyyy4, mm4, dd4)) {
                return false;
            }

            int date1 = getYYYYMMDD(yyyy1, mm1, dd1);
            int date2 = getYYYYMMDD(yyyy2, mm2, dd2);
            int date3 = getYYYYMMDD(yyyy3, mm3, dd3);
            int date4 = getYYYYMMDD(yyyy4, mm4, dd4);
            if (date1 > date2 || date3 > date4) {// 關聯性合法性は検証します
                return false;
            }

            // 比較を行います
            if (date1 == date3 || (date1 < date3 && date2 >= date3)
                    || (date1 > date3 && date4 >= date1)) {
                return false;
            }
        }

        // the int value of checkValue must greater than checkParam.
        // ex: checkValue = "25"
        // ex: checkParam = "20"
        else if (DIGIT_GREATER.equals(checkType)) {
            if (StringUtils.isEmpty(filterNull(checkValue))
                    || StringUtils.isEmpty(filterNull(checkParam))) {
                return true;
            }
            // checkValue数字合法性は検証します
            String checkValue0 = StringUtils.trim(filterNull(checkValue));
            Matcher m = ptnDBCDigitAssert.matcher(checkValue0);
            if (!m.matches()) {
                return false;
            }

            // 比較を行います
            if (StringUtils.compareTo(checkValue0, checkParam.toString()) < 0) {
                return false;
            }
        }

        // the int value of checkValue must less than checkParam.
        // ex: checkValue = "20"
        // ex: checkParam = "15"
        else if (DIGIT_LESS.equals(checkType)) {
            if (StringUtils.isEmpty(filterNull(checkValue))
                    || StringUtils.isEmpty(filterNull(checkParam))) {
                return true;
            }
            // checkValue数字合法性は検証します
            String checkValue0 = StringUtils.trim(filterNull(checkValue));
            Matcher m = ptnDBCDigitAssert.matcher(checkValue0);
            if (!m.matches()) {
                return false;
            }

            // 比較を行います
            if (StringUtils.compareTo(checkValue0, checkParam.toString()) >= 0) {
                return false;
            }
        }

        // checkValue must equals checkParam.
        // ex: checkValue = "unicom"
        // ex: checkParam = "unicom"
        else if (STRING_EQUAL.equals(checkType)) {
            if (!StringUtils.trim(filterNull(checkValue)).equals(
                    StringUtils.trim(filterNull(checkParam)))) {
                return false;
            }
        }

        // checkValue must not equals checkParam.
        // ex: checkValue = "unicom"
        // ex: checkParam = "microsoft"
        else if (STRING_NOT_EQUAL.equals(checkType)) {
            if (StringUtils.trim(filterNull(checkValue)).equals(
                    StringUtils.trim(filterNull(checkParam)))) {
                return false;
            }
        }

        // checkValue array must input all.
        // ex: checkValue = new String[]{"2008","10","12"} --> true
        // ex: checkValue = new String[]{"2008","10",""} --> false
        else if (MUST_INPUT_ALL.equals(checkType)) {
            if (isEmptyAll(checkValue)) {
                return true;
            }
            return !isEmptyPart(checkValue);
        }

        // checkValue array must input one or more.
        // ex: checkValue = new String[]{"","b",""} --> true
        // ex: checkValue = new String[]{"","",""} --> false
        else if (MUST_INPUT_PART.equals(checkType)) {
            return !isEmptyAll(checkValue);
        }

        // checkValue array must input a part.
        // ex: checkValue = new String[]{"a","b"} --> false
        // ex: checkValue = new String[]{"a",""} --> true
        // ex: checkValue = new String[]{"","b"} --> true
        else if (CAN_NOT_INPUT_ALL.equals(checkType)) {
            if (isEmptyAll(checkValue)) {
                return false;
            }
            return isEmptyPart(checkValue);
        }

        // checkValue must be a telephone number. ex: +02-5840-6576 or
        // +021-5840-6576 or +0234-5840-6576 or +021-58406576
        else if (PHONE_RES_JP.equals(checkType)) {
            if (StringUtils.isEmpty(filterNull(checkValue))) {
                return true;
            }
            Matcher m = ptnDBCPhoneResJP.matcher(StringUtils
                    .trim(filterNull(checkValue)));
            return m.matches();
        }

        // checkValue must be a postalcode in Japan. ex: 000-0000
        else if (POSTAL_CODE_JP.equals(checkType)) {
            if (StringUtils.isEmpty(filterNull(checkValue))) {
                return true;
            }
            Matcher m = ptnDBCPostalCodeJP.matcher(StringUtils
                    .trim(filterNull(checkValue)));
            return m.matches();
        }

        // checkValue must be not null or "0"
        else if (NN_SELECT.equals(checkType)) {
            if (isEmptyPartSelect(checkValue, filterNull(checkParam))) {
                return false;
            }
        }

        // checkValue must be a cif id. ex:
        // P00000111112222,Y99999888887777,e00000800006532
        else if (CIF.equals(checkType)) {
            if (StringUtils.isEmpty(filterNull(checkValue))) {
                return true;
            }
            Matcher m = ptnCIF.matcher(StringUtils.trim(filterNull(checkValue)));
            return m.matches();
        }

        // checkValue must be customValue
        else if (CUSTOM_PATTERN.equals(checkType)) {
            if (StringUtils.isEmpty(filterNull(checkValue))) {
                return true;
            }
            Pattern customPattern = Pattern.compile((String) checkParam);
            Matcher m = customPattern
                    .matcher(StringUtils.trim(filterNull(checkValue)));
            return m.matches();
        }

        // checkValue must contain letter
        else if (CONTAIN_LETTER.equals(checkType)) {
            if (StringUtils.isEmpty(filterNull(checkValue))) {
                return true;
            }
            Matcher m = ptnDBCLetter.matcher(StringUtils.trim(filterNull(checkValue)));
            return m.find();
        }

        // checkValue must contain lowercase letter
        else if (CONTAIN_LETTERLOW.equals(checkType)) {
            if (StringUtils.isEmpty(filterNull(checkValue))) {
                return true;
            }
            Matcher m = ptnDBCLetterLow.matcher(StringUtils
                    .trim(filterNull(checkValue)));
            return m.find();
        }

        // checkValue must contain uppercase letter
        else if (CONTAIN_LETTERUP.equals(checkType)) {
            if (StringUtils.isEmpty(filterNull(checkValue))) {
                return true;
            }
            Matcher m = ptnDBCLetterUp.matcher(StringUtils
                    .trim(filterNull(checkValue)));
            return m.find();
        }

        // checkValue must contain digital
        else if (CONTAIN_DIGITAL.equals(checkType)) {
            if (StringUtils.isEmpty(filterNull(checkValue))) {
                return true;
            }
            Matcher m = ptnDBCDigital
                    .matcher(StringUtils.trim(filterNull(checkValue)));
            return m.find();
        }

        // chackValue must contain digital or '/'
        else if (CONTAIN_DIGTALSYMBOL.equals(checkType)) {
            if (StringUtils.isEmpty(filterNull(checkValue))) {
                return true;
            }
            Matcher m = ptnDigitalSymBol.matcher(StringUtils
                    .trim(filterNull(checkValue)));
            return m.find();
        }
        // checkValue must contain customValue
        else if (CONTAIN_CUSTOM.equals(checkType)) {
            if (StringUtils.isEmpty(filterNull(checkValue))) {
                return true;
            }
            Pattern customPattern = Pattern.compile((String) checkParam);
            Matcher m = customPattern
                    .matcher(StringUtils.trim(filterNull(checkValue)));
            return m.find();
        }
        return true;
    }

}
