package com.hworld.canoe.framework.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public static String getObjectValue(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    /**
     * split a character according to a separator<br>
     * ex:<br>
     * String TTT[] = myclass.split("aaa:bbb:ccc:ddd",":")<br>
     * TTT[0]="aaa"; TTT[1]="bbb"; TTT[2]="ccc"; TTT[3]="ddd".<!--.ﾂ。-->
     *
     * @param source String
     * @param div    String  the list separator
     * @return String[]
     */
    public static String[] split(String source, String div) {
        int arynum = 0, intIdx = 0, intIdex = 0, div_length = div.length();
        if (source.compareTo("") != 0) {
            if (source.indexOf(div) != -1) {
                intIdx = source.indexOf(div);
                for (int intCount = 1; ; intCount++) {
                    if (source.indexOf(div, intIdx + div_length) != -1) {
                        intIdx = source.indexOf(div, intIdx + div_length);
                        arynum = intCount;
                    } else {
                        arynum += 2;
                        break;
                    }
                }
            } else {
                arynum = 1;
            }
        } else {
            arynum = 0;
        }
        intIdx = 0;
        intIdex = 0;
        String[] returnStr = new String[arynum];
        if (source.compareTo("") != 0) {
            if (source.indexOf(div) != -1) {
                intIdx = (int) source.indexOf(div);
                returnStr[0] = (String) source.substring(0, intIdx);
                for (int intCount = 1; ; intCount++) {
                    if (source.indexOf(div, intIdx + div_length) != -1) {
                        intIdex = (int) source.indexOf(div, intIdx + div_length);
                        returnStr[intCount] = (String) source.substring(
                                intIdx + div_length, intIdex);
                        intIdx = (int) source.indexOf(div, intIdx + div_length);
                    } else {
                        returnStr[intCount] = (String) source.substring(
                                intIdx + div_length, source.length());
                        break;
                    }
                }
            } else {
                returnStr[0] = (String) source.substring(0, source.length());
                return returnStr;
            }
        } else {
            return returnStr;
        }
        return returnStr;
    }


    /**
     * convert java.sql.Date type into String type
     *
     * @param date java.sql.Date  yyyymmdd
     * @return String  ex:yyyymmdd
     */
    public static String getYYMMDDDate(java.sql.Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(date);
    }

    /***********************************************************/
    /**
     * Capitalize a word.<!--.ﾂ。-->
     *
     * @param str String
     * @return String
     */
    public static String cap(String str) {
        if (str == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(Character.toUpperCase(str.charAt(0)));
        sb.append(str.substring(1).toLowerCase());
        return sb.toString();
    }

    /***********************************************************/
    /**
     * Returns true if the string represents a word,
     * i.e. every char is either a-z, A-Z, 0-9, _.<!--.ﾂ。-->
     *
     * @param str String
     * @return String
     */
    public static boolean isWord(String str) {
        if (str == null) {
            return false;
        }
        char[] ch = str.toCharArray();
        int i;
        for (i = 0; i < str.length(); i++) {
            if ((!Character.isLetterOrDigit(ch[i])) && (ch[i] != '_')) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determine if it is in yyyy/mm/dd format
     *
     * @param dt String
     * @return boolean true if it is in yyyy/mm/dd format
     */
    public static boolean isDateString(String dt) {
        if (dt == null || dt.length() != 10) {
            return false;
        }
        int firstSlash = dt.indexOf("/");
        int lastSlash = dt.lastIndexOf("/");
        String yr = dt.substring(0, firstSlash);
        String mn = dt.substring(firstSlash + 1, lastSlash);
        String dy = dt.substring(lastSlash + 1);
        if (isNum(yr) && isNum(mn) && isNum(dy)) {
            return true;
        } else {
            return false;
        }
    }

    /***********************************************************/
    /**
     * Return true if the string represents a number.<!--.ﾂ。-->
     *
     * @param str String
     * @return boolean
     */
    public static boolean isNum(String str) {
        if (str == null || str.length() <= 0) {
            return false;
        }
        char[] ch = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(ch[i])) {
                return false;
            }
        }
        return true;
    }

    /***********************************************************/
    /**
     * Return true if the string represents a real number.<!--.ﾂ。-->
     *
     * @param str String
     * @return boolean
     */
    public static boolean isNumEx(String str) {
        if (str == null || str.length() <= 0) {
            return false;
        }
        char[] ch = str.toCharArray();
        for (int i = 0, comcount = 0; i < str.length(); i++) {
            if (!Character.isDigit(ch[i])) {
                if (ch[i] != '.') {
                    return false;
                } else if (i == 0 || i == str.length() - 1) {
                    return false; // .12122 or 423423.  is not a real number
                } else if (++comcount > 1) {
                    return false; // 12.322.23 is not a real number
                }
            }
        }
        return true;
    }

    /***********************************************************/
    /**
     * Input is a string and the index of which position you want to get from the dataArray<br>
     * eturn a object: null or object or Vector<br>
     * if index out of bouds then return null.<!--.ﾂ。-->
     *
     * @param str   String
     * @param index int
     * @return Object
     */
    public static Object getStringStr(String str, int index) {
        Vector<String> reStr = new Vector<String>();
        Object obj = getStringNumber(str, 0);
        // no number or str is null
        if (obj == null) {
            if (index > 1) {
                return null;
            }
            return str;
        } else {
            for (int i = 0; i < ((Vector<?>) obj).size(); i++) {
                int indexOfString = str.indexOf((String) (((Vector<?>) obj)
                        .elementAt(i)));
                if (indexOfString != 0) {
                    reStr.addElement(str.substring(0, indexOfString));
                }
                str = str.substring(indexOfString
                        + ((String) (((Vector<?>) obj).elementAt(i))).length());
            }
            if (str.length() != 0) {
                reStr.addElement(str);

            }
        }
        // return as the index
        if (index <= 0) {
            return reStr;
        }
        if (index > reStr.size()) {
            return null;
        }
        return reStr.elementAt(index - 1);
    }

    /***********************************************************/
    /**
     * get String Length about byte.<!--.ﾂ。-->
     *
     * @param str String
     * @return int
     */
    public static int getStringBytesLengthRough(String str) {
        int len = 0;
        if (str != null) {
            byte[] temp = str.getBytes();
            len = temp.length;
        }
        return len;
    }

    /***********************************************************/
    /**
     * used for both Japanese character and English character.<!--.ﾂ。-->
     *
     * @param str String
     * @return int
     */
    public static int getStringBytesLength(String str) {
        if (str == null) {
            return 0;
        }
        int n = 0;
        try {
            byte bt[] = str.getBytes("UTF8");
            //n = (new String(bt)).getBytes().length;
            n = bt.length;
        } catch (java.io.UnsupportedEncodingException use) {
        }
        return n;
    }

    /*************************************************************************************/
    /**
     * Input is a string and the index of which position you want to get from the dataArray<br>
     * Return a object: null or object or Vector<br>
     * index 0 : return a Vector which include all intArray.<!--.ﾂ。-->
     *
     * @param str   String
     * @param index int
     * @return Object
     */
    public static Object getStringNumber(String str, int index) {
        if (str == null) {
            return null;
        }
        char[] ch = str.toCharArray();
        int i;
        String tempStr = "";
        Vector<String> returnNumber = new Vector<String>();
        for (i = 0; i < str.length(); i++) {
            if (Character.isDigit(ch[i])) {
                tempStr += ch[i];
            } else {
                if (!"".equals(tempStr)) {
                    returnNumber.addElement(tempStr);
                }
                tempStr = "";
            }
        }
        if (!"".equals(tempStr)) {
            returnNumber.addElement(tempStr);
        }
        if (returnNumber.isEmpty() || (index > returnNumber.size())) {
            return null;
        } else {
            if (index <= 0) {
                return returnNumber;
            } else {
                return returnNumber.elementAt(index - 1);
            }
        }
    }

    /*************************************************************************************/
    /**
     * Replace the string with another string,these two string's length must equal.<!--.ﾂ。-->
     *
     * @param sReplace String
     * @param sOld     String
     * @param sNew     String
     * @return String
     */
    public static String replaceStrEq(String sReplace, String sOld, String sNew) {
        if (sReplace == null || sOld == null || sNew == null) {
            return null;
        }
        int iLen = sReplace.length();
        int iLenOldStr = sOld.length();
        int iLenNewStr = sNew.length();
        if (iLen <= 0 || iLenOldStr <= 0 || iLenNewStr <= 0) {
            return sReplace;
        }
        if (iLenOldStr != iLenNewStr) {
            return sReplace;
        }
        int[] iIndex = new int[iLen];
        iIndex[0] = sReplace.indexOf(sOld, 0);
        if (iIndex[0] == -1) {
            return sReplace;
        }
        int iIndexNum = 1; //get the iIndex of all sOld substring
        while (true) {
            iIndex[iIndexNum] = sReplace.indexOf(sOld, iIndex[iIndexNum - 1] + 1);
            if (iIndex[iIndexNum] == -1) {
                break;
            }
            iIndexNum++;
        }
        char[] caReplace = sReplace.toCharArray();
        char[] caNewStr = sNew.toCharArray();
        for (int i = 0; i < iIndexNum; i++) {
            for (int j = 0; j < iLenOldStr; j++) {
                caReplace[j + iIndex[i]] = caNewStr[j];
            }
        }
        return new String(caReplace);
    }

    /*************************************************************************************/

    /**
     * replace old string by new string
     *
     * @param line      String the string which required filter
     * @param oldString String the replaced string
     * @param newString String the new string
     * @return String
     */
    public static String replace(String line, String oldString, String newString) {
        int i = 0;
        int temp = 0;
        StringBuffer sb = new StringBuffer();
        if (line != null && oldString != null && newString != null
                && oldString.length() > 0 && (i = line.indexOf(oldString, i)) >= 0) {
            int oLength = oldString.length();
            sb.append(line.substring(0, i) + newString);
            i += oLength;
            temp = i;
            i = line.indexOf(oldString, temp);
            for (; i < line.length() && i > 0; ) {
                sb.append(line.substring(temp, i) + newString);
                temp = i + oLength;
                i = line.indexOf(oldString, temp);
            }
            sb.append(line.substring(temp));
            return sb.toString();
        }
        return line;
    }

    /**
     * Convert the string to upper case between separator.<!--.ﾂ。-->
     *
     * @param sConvert   String
     * @param sSeparator String
     * @return String
     */
    public static String convertUpperCase(String sConvert, String sSeparator) {
        if (sConvert == null || sSeparator == null) {
            return null;
        }
        int iLen = sConvert.length();
        int iLen1 = sSeparator.length();
        if (iLen <= 0 || iLen1 <= 0) {
            return sConvert;
        }
        //get the first separator string index
        int[] iIndex = new int[iLen];
        iIndex[0] = sConvert.indexOf(sSeparator, 0);
        if (iIndex[0] == -1) {
            return sConvert;
        }
        //get all separator string index
        int iIndexNum = 1;
        while (true) {
            iIndex[iIndexNum] = sConvert.indexOf(sSeparator,
                    iIndex[iIndexNum - 1] + 1);
            if (iIndex[iIndexNum] == -1) {
                break;
            }
            iIndexNum++;
        }
        //get all sub string split by separator,and strored in a vector
        Vector<String> vStore = new Vector<String>();
        int subNum = iIndexNum / 2;
        if ((iIndexNum % 2) != 0) {
            return sConvert;
        }
        for (int i = 0; i < subNum; i++) {
            vStore.add(sConvert.substring(iIndex[i * 2] + 1, iIndex[i * 2 + 1]));
        }
        //convert all sub string to upper case and replace old sub string
        for (int i = 0; i < subNum; i++) {
            String sOld = (String) vStore.get(i);
            String sNew = sOld.toUpperCase();
            sConvert = replaceStrEx(sConvert, sOld, sNew);
        }
        return sConvert;
    }

    /*************************************************************/
    /**
     * contact a String[] with a contacter(such as ",").<!--.ﾂ。-->
     *
     * @param saStr      String[]
     * @param sContacter String
     * @return String
     */
    public static String contactStr(String[] saStr, String sContacter) {
        if (saStr == null || saStr.length <= 0 || sContacter == null
                || sContacter.length() <= 0) {
            return null;
        }
        StringBuffer sRet = new StringBuffer("");
        for (int i = 0; i < saStr.length; i++) {
            if (i == saStr.length - 1) {
                sRet.append(saStr[i]);
            } else {
                sRet.append(saStr[i] + sContacter);
            }
        }
        return sRet.toString();
    }

    /*************************************************************/
    /**
     * ort string array by string length.<!--.ﾂ。-->
     *
     * @param saSource String[]
     * @param bAsc     boolean
     * @return String[]
     */
    public static String[] sortByLength(String[] saSource, boolean bAsc) {
        if (saSource == null || saSource.length <= 0) {
            return null;
        }
        int iLength = saSource.length;
        String[] saDest = new String[iLength];
        //duplicate source string buffer
        for (int i = 0; i < iLength; i++) {
            saDest[i] = saSource[i];
        }
        String sTemp = "";
        int j = 0, k = 0;
        //bubble sort
        for (j = 0; j < iLength; j++) {
            for (k = 0; k < iLength - j - 1; k++) { //one time bubble sort
                if (saDest[k].length() > saDest[k + 1].length() && bAsc) { //asc sort
                    sTemp = saDest[k];
                    saDest[k] = saDest[k + 1];
                    saDest[k + 1] = sTemp;
                } else if (saDest[k].length() < saDest[k + 1].length() && !bAsc) { //desc sort
                    sTemp = saDest[k];
                    saDest[k] = saDest[k + 1];
                    saDest[k + 1] = sTemp;
                }
            }
        }
        return saDest;
    }

    /*************************************************************/
    /**
     * erase the redundance blank between the words<br>
     * The blanks at the beginning of string will not be erased.<!--.ﾂ。-->
     *
     * @param str String
     * @return String
     */
    public static String compactStr(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() <= 0) {
            return "";
        }
        String sDes = new String(str); //make a copy of source string
        int iBlanksAtStart = 0;
        int iLen = str.length();
        while (sDes.charAt(iBlanksAtStart) == ' ') {
            if (++iBlanksAtStart >= iLen) {
                break;
            }
        }
        String[] saDes = split(sDes.trim(), " ");
        if (saDes == null) {
            return null;
        }
        int i = 0;
        for (i = 0; i < saDes.length; i++) {
            saDes[i] = saDes[i].trim();
        }
        sDes = contactStr(saDes, " ");
        StringBuffer sBlank = new StringBuffer("");
        for (i = 0; i < iBlanksAtStart; i++) {
            sBlank.append(" ");
        }
        return sBlank.toString() + sDes;
    }

    /*************************************************************/
    /**
     * judge whether the email url is correct.<!--.ﾂ。-->
     *
     * @param str String
     * @return boolean
     */
    public static boolean isEmailUrl(String str) {
        if ((str == null) || (str.length() == 0)) {
            return false;
        }
        if ((str.indexOf('@') > 0) && (str.indexOf('@') == str.lastIndexOf('@'))) {
            if ((str.indexOf('.') > 0) && (str.lastIndexOf('.') > str.indexOf('@'))) {
                return true;
            }
        }
        return false;
    }

    /*************************************************************/
    /**
     * judge whether the email address is correct.<!--.ﾂ。-->
     *
     * @param str String
     * @return boolean
     */
    public static boolean isEmailAddress(String str) {
        if (str == null || str.length() <= 0) {
            return false;
        }
        int iAltCount = 0;
        char[] chEmail = str.trim().toCharArray();
        for (int i = 0; i < chEmail.length; i++) {
            if (chEmail[i] == ' ') {
                return false; //find blank in email address
            } else if (chEmail[i] == '.') { //common at the beginning or end of email address
                if (i == 0 || i == chEmail.length - 1) {
                    return false;
                }
            } else if (chEmail[i] == '@') { //find '.' more than one,or at the beginning or end of email address
                if (++iAltCount > 1 || i == 0 || i == chEmail.length - 1) {
                    return false;
                }
            }
        }
        if (str.indexOf('.') < str.indexOf('@')) {
            return false;
        }
        return true;
    }

    /*************************************************************/
    /**
     * limit the appointed String length.<!--.ﾂ。-->
     *
     * @param strold String
     * @param strlen int
     * @return String
     */
    public static String CutLong(String strold, int strlen) {
        int j = 0;
        String strnew = "";
        char index;
        for (int i = 0; i < strold.length(); i++) {
            index = strold.charAt(i);
            if (index > 32 && index < 127) {
                j++;
            } else {
                j = 0;
            }
            strnew = strnew + index;
            if (j >= strlen || index == 13) {
                strnew = strnew + "<br>";
                j = 0;
            }
        }
        return strnew;
    }

    /**
     * convert the char type to unicode
     *
     * @param c char
     * @return char
     */
    private static char win2JavaChar(char c) {
        char rtnC_ = c;
        switch (c) {
            //         case 0xff0d:    // MINUS SIGN  -->
            //     rtnC_ = 0x2212;      // MINUS SIGN
            //     break;
            //         case 0x2225:    // PARALLELTO  -->
            //     rtnC_ = 0x2016;      // DOUBLE VERTICAL LINE
            //     break;
            //         case 0xffe2:    // FULLWIDTH NOT SIGN  -->
            //     rtnC_ = 0x00ac;      // NOT SIGN
            //     break;
            //         case 0xffe1:    // FULLWIDTH POUND SIGN  -->
            //     rtnC_ = 0x00a3;      // POUND SIGN
            //     break;
            //         case 0xffe0:    // FULLWIDTH C GN  -->
            //     rtnC_ = 0x00a2;      // CENT SIGN
            //     break;
            //         case 0x9ad9:  //\uFFFDﾂ≫\uFFFDﾂ坂\uFFFD
            //     rtnC_ = 0x9ad8;
            //     break;
            //         case 0xfa11:  //\uFFFDﾂ≫\uFFFDﾂ催ｨ
            //     rtnC_ = 0x5d0e;
            //     break;
            //         case 0xf9dc:  //\uFFFDﾂ≫\uFFFD窶板ｲ

            //     rtnC_ = 0x9686;
            //     break;
            case 0x301c: // FULL WIDTH TILDE      --> ﾂ\u301C modify by tokyo on 2002/09/17
                rtnC_ = 0xff5e; // WAVE DASH
                break;
        }
        return rtnC_;
    }

    /**
     * check the string type
     *
     * @param unicode String
     * @return boolean
     */
    public static boolean isWinString(String unicode) {
        for (int i = 0; i < unicode.length(); i++) {
            if (unicode.charAt(i) != win2JavaChar(unicode.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * convert the String type to unicode
     *
     * @param unicode String
     * @return String
     */
    public static String win2JavaString(String unicode) {
        if (unicode == null) {
            return null;
        }
        StringBuffer bf_ = new StringBuffer();
        for (int i = 0; i < unicode.length(); i++) {
            bf_.append(win2JavaChar(unicode.charAt(i)));
        }
        return new String(bf_);
    }

    /**
     * DBC case --->SBC case
     *
     * @param time int
     * @return String
     */
    public static String getSBCCaseString(int time) {
        String strOld = String.valueOf(time);
        String newStr = "";
        int index = 0;
        for (int i = 0; i < strOld.length(); i++) {
            index = strOld.charAt(i);
            index += 65248;
            newStr += (char) index;
        }
        return newStr;
    }

    /**
     * solving CRLF issue
     *
     * @param msg String
     * @return String
     */
    public static String convertCRLF(String msg) {
        if (msg == null) {
            return "";
        }
        byte[] b = msg.getBytes();
        byte[] temp = new byte[b.length];
        boolean flag = false;
        for (int i = 0, j = 0; i < b.length; i++, j++) {
            boolean isNext = true;
            if (isNext && b[i] == 13) {
                flag = true;
                isNext = false;
            }
            if (isNext && b[i] == 10 && !flag) {
                byte[] t = new byte[temp.length + 1];
                System.arraycopy(temp, 0, t, 0, temp.length);
                temp = t;
                temp[j] = 13;
                j++;
            }
            if (isNext && b[i] != 10 && flag) {
                byte[] t = new byte[temp.length + 1];
                System.arraycopy(temp, 0, t, 0, temp.length);
                temp = t;
                temp[j] = 10;
                j++;
            }
            if (b[i] != 13) {
                flag = false;
            }
            temp[j] = b[i];
        }
        return new String(temp);
    }

    /**
     * check the string is not null or ""
     *
     * @param str String
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str) || "null".equals(str.toLowerCase())) {
            return true;
        }
        return false;
    }

    /**
     * user in ActionForm set properties value
     *
     * @param obj Object
     * @return Object
     */
    public static Object filter(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof String && isEmpty(((String) obj))) {
            return "";
        }
        return obj;
    }

    /**
     * Filter unwanted characters for html display purpose
     *
     * @param str String
     * @return String
     */
    public static String filterChar(String str) {
        if (str == null || "".equals(str.trim())) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        boolean hasCR = false;
        boolean hasLF = false;
        int n = str.length();
        for (int i = 0; i < n; i++) {
            char c = str.charAt(i);
            switch (c) {
                case '&':
                    sb.append("&amp;");
                    hasCR = false;
                    hasLF = false;
                    break;
                case '<':
                    sb.append("&lt;");
                    hasCR = false;
                    hasLF = false;
                    break;
                case '>':
                    sb.append("&gt;");
                    hasCR = false;
                    hasLF = false;
                    break;
                case '"':
                    sb.append("&quot;");
                    hasCR = false;
                    hasLF = false;
                    break;
                //                case ' ':
                //                    sb.append("&nbsp;");
                //                    hasCR = false;
                //                    hasLF = false;
                //                    break;
                case 10:
                    if (!hasCR) {
                        sb.append("<br>");
                        hasLF = true;
                    }
                    break;
                case 13:
                    if (!hasLF) {
                        sb.append("<br>");
                        hasCR = true;
                    }
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * To delete the space on beginning and end of the string.
     * Both single-byte and double-byte space will be deleted.
     *
     * @param str String
     * @return string trimed
     */
    public static String trim(String str) {
        if (str == null || "".equals(str.trim())) {
            return "";
        }
        String newStr = str.trim();
        boolean startFull = newStr.startsWith(" "); //12288
        boolean endFull = newStr.endsWith(" "); //12288
        boolean startHalf = newStr.startsWith(" "); //97
        boolean endHalf = newStr.endsWith(" "); //97
        while (startFull || endFull || startHalf || endHalf) {
            startFull = newStr.startsWith(" "); //12288
            endFull = newStr.endsWith(" "); //12288
            if (startFull) {
                if (newStr.length() == 1) {
                    return "";
                }
                newStr = newStr.substring(1);
            }
            if (endFull) {
                if (newStr.length() == 1) {
                    return "";
                }
                newStr = newStr.substring(0, newStr.length() - 1);
            }
            startHalf = newStr.startsWith(" "); //97
            endHalf = newStr.endsWith(" "); //97
            if (startHalf) {
                newStr = newStr.substring(1);
            }
            if (endHalf) {
                newStr = newStr.substring(0, newStr.length() - 1);
            }
        }
        return newStr;
    }

    /**
     * Convert DBC case to SBC case. <br>
     * ex: "0123456789" --> "０１２３４５６７８９" <br>
     * ex: "abcdefghijklmnopqrstuvwxyz" --> "ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ" <br>
     * ex: "ABCDEFGHIJKLMNOPQRSTUVWXYZ" --> "ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ" <br>
     * "~!`@#$%^&*()-=\\_+|[]{};:'\",<.>/?" --> <br>
     * ex: "～！｀＠＃＄％＾＆＊（）－＝＼＿＋｜［］｛｝；：＇＂，＜．＞／？" <br>
     *
     * @param str String
     * @return String
     */
    public static String dbc2sbc(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // 半角スペースの場合
            if (c == 32) {
                sb.append((char) (c + 12256));
            }

            // 半角英数字の場合
            else if (c >= 0 && c <= 127) {
                sb.append((char) (c + 65248));
            }

            // その他の場合
            else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * format string,put prefix before strInput.
     *
     * @param strInput input string
     * @param prefix   fill char
     * @param length   >0
     * @return String
     */
    public static String stringFormat(String strInput, String prefix, int length) {
        String strResult = "";
        if (!StringUtils.isEmpty(strInput) && !StringUtils.isEmpty(prefix)
                && length > 0) {
            strResult = StringUtils.trim(strInput);
            while (strResult.length() < length) {
                strResult = prefix + strResult;
            }
        }
        return strResult;
    }

    /**
     * return the truncated string. <br>
     * ex: "顧a客",1 return "" <br>
     * ex: "顧a客",2 return "顧" <br>
     * ex: "顧a客",3 return "顧a" <br>
     * ex: "顧a客",4 return "顧a" <br>
     * ex: "ｱｲｳｴ",1 return "ｱ" <br>
     * ex: "ｱｲｳｴ",2 return "ｱｲ" <br>
     *
     * @param str    String
     * @param offset int
     * @return String
     */
    public static String subStringByte(String str, int offset) {
        return subStringByte(str, 0, offset);
    }

    /**
     * return the truncated string by byte position and offset. <br>
     * ex: "シaスbテムc管理", 4,4 return "スbテ" <br>
     * ex: "シaスbテムc管理", 3,5 return "スbテ" <br>
     * ex: "シaスbテムc管理", 2,6 return "aスbテ" <br>
     * ex: "シaスbテムc管理", 2,5 return "aスb" <br>
     * ex: "ｱｲｳｴ",1,2 return "ｲｳ" <br>
     * ex: "ｱｲｳｴ",2,1 return "ｳ" <br>
     *
     * @param str      String
     * @param position int
     * @param offset   int
     * @return String
     */
    public static String subStringByte(String str, int position, int offset) {
        if (StringUtils.isEmpty(str) || position < 0 || offset < 1) {
            // 010101.jsp the tag like: <c:out
            // value="${fnsub:subByte(commandObj.custAttr.sexst,4)}" default="&nbsp;"
            // escapeXml="false"/>, can not return "" here, because 'default'
            // attribute in <c:out></c:out> only effective by null string.
            return null;
        }

        int beginIndex = countNeedLength(str, position);
        int endIndex = countNeedLength(str, position + offset);
        byte[] arr = new byte[endIndex - beginIndex];
        System.arraycopy(str.getBytes(), beginIndex, arr, 0, arr.length);
        return new String(arr);
    }

    /**
     * judge the char is or not a DBC char. <br>
     * ex: 'a','=','ｱ' return true <br>
     * ex: '顧','検','索' return false <br>
     * the letters' ASCII code is 0(0x00)~127(0x7f) <br>
     * half kana "ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝｧｨｩｪｫｬｭｮｯｰﾞﾟ" <br>
     * 65382(0xff66)-65439(0xff9f) <br>
     *
     * @param c char
     * @return boolean
     */
    public static boolean isDBCChar(char c) {
        return String.valueOf(c).getBytes().length == 1;
    }

    /**
     * return the length of string. <br>
     * ex: "a" return 1 <br>
     * ex: "顧客2" return 5 <br>
     *
     * @param str String
     * @return int
     */
    public static int byteLength(String str) {
        if (str == null || "".equals(str)) {
            return 0;
        }
        return str.getBytes().length;
    }

    /**
     * return actual needed length for subStringByte. <br>
     * ex: "顧a客",1 return 0 <br>
     * ex: "顧a客",2 return 2 <br>
     * ex: "顧a客",3 return 3 <br>
     * ex: "顧a客",4 return 3 <br>
     * ex: "ｱｲｳｴ",1 return 1 <br>
     * ex: "ｱｲｳｴ",2 return 2 <br>
     *
     * @param str    String
     * @param length int
     * @return int
     */
    private static int countNeedLength(String str, int length) {
        if (str == null || "".equals(str) || length < 1) {
            return 0;
        }
        char c[] = str.toCharArray();
        int needLength = 0;
        if (length == 1) {
            if (isDBCChar(c[0])) {
                needLength = 1;
            }
        } else {
            for (int i = 0; i < c.length; i++) {
                if (isDBCChar(c[i])) {
                    needLength++;
                } else {
                    needLength += 2;
                }
                if (needLength > length - 2) {
                    if (needLength == length - 1 && c.length > i + 1
                            && isDBCChar(c[i + 1])) {
                        needLength++;
                    }
                    break;
                }
            }
        }

        int actualLength = byteLength(str);
        if (needLength > actualLength) {
            return actualLength;
        }

        return needLength;
    }

    /*****************************************************************************
     * Compares this BigDecimal with the specified BigDecimal. Two BigDecimal
     * objects that are equal in value but have a different scale (like 2.0 and
     * 2.00) are considered equal by this method. This method is provided in
     * preference to individual methods for each of the six boolean comparison
     * operators (<, ==, >, >=, !=, <=). The suggested idiom for performing these
     * comparisons is: (x.compareTo(y) <op> 0), where <op> is one of the six
     * comparison operators.
     * @param s1 String
     * @param s2 String
     * @return int
     */
    public static int compareTo(String s1, String s2) {
        BigDecimal dec1 = new BigDecimal(s1);
        BigDecimal dec2 = new BigDecimal(s2);
        int result = dec1.compareTo(dec2);
        return result;
    }

    /**
     * set the return value "" when the param is null
     *
     * @param strInput String
     * @return String
     */
    public static String filterNull(String strInput) {
        if (StringUtils.isEmpty(strInput)) {
            return "";
        } else {
            return strInput;
        }
    }

    /**
     * Replace the string with another string
     *
     * @param sReplace String
     * @param sOld     String
     * @param sNew     String
     * @return String
     */
    public static String replaceStrEx(String sReplace, String sOld, String sNew) {
        if (sReplace == null || sOld == null || sNew == null) {
            return null;
        }
        int iLen = sReplace.length();
        int iLenOldStr = sOld.length();
        int iLenNewStr = sNew.length();
        if (iLen <= 0 || iLenOldStr <= 0 || iLenNewStr < 0) {
            return sReplace;
        }
        // get the first sOld string index
        int[] iIndex = new int[iLen];
        iIndex[0] = sReplace.indexOf(sOld, 0);
        if (iIndex[0] == -1) {
            return sReplace;
        }
        // get all sOld string index
        int iIndexNum = 1; // get the iIndex of all sOld substring
        while (true) {
            iIndex[iIndexNum] = sReplace.indexOf(sOld, iIndex[iIndexNum - 1] + 1);
            if (iIndex[iIndexNum] == -1) {
                break;
            }
            iIndexNum++;
        }
        // get all sub string split by sOld,and strored in a vector
        Vector<String> vStore = new Vector<String>();
        String sub = sReplace.substring(0, iIndex[0]);
        if (sub != null) {
            vStore.add(sub);
        }
        int i = 1;
        for (i = 1; i < iIndexNum; i++) {
            vStore.add(sReplace.substring(iIndex[i - 1] + iLenOldStr, iIndex[i]));
        }
        vStore.add(sReplace.substring(iIndex[i - 1] + iLenOldStr, iLen));
        // contact all sub string with sNew
        StringBuffer sbReplaced = new StringBuffer("");
        for (i = 0; i < iIndexNum; i++) {
            sbReplaced.append(vStore.get(i) + sNew);
        }
        sbReplaced.append(vStore.get(i));
        return sbReplaced.toString();
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        String str = "ⅸⅹⅠⅣⅩ￤＇＂㈱№℡∵纊褜彅丨ⅱⅲⅳⅴⅵⅶⅷⅸⅹⅠⅡⅢⅣⅤⅥⅦⅧⅨⅩ￤＇"
                + "＂㈱№℡∵纊褜鍈銈蓜俉炻昱棈鋹曻彅丨仡仼伀伃伹佖侒侊侚侔俍偀倢俿倞偆偰偂傔僴僘兊兤冝"
                + "冾凬刕劜劦勀勛匀匇匤卲厓厲叝﨎咜咊咩哿喆坙坥垬埈埇﨏塚增墲夋奓奛奝奣妤妺孖寀甯寘寬尞"
                + "岦岺峵崧嵓﨑嵂嵭嶸嶹巐弡弴彧德忞恝悅悊惞惕愠惲愑愷愰憘戓抦揵摠撝擎敎昀昕昻昉昮鍰鍗鎤"
                + "鏆鏞鏸鐱鑅鑈閒隆﨩隝隯霳霻靃靍靏靑靕顗顥飯飼餧館馞驎髙髜魵魲鮏鮱鮻鰀鵰鵫鶴鸙黑① ② ③"
                + " ④ ⑤ ⑥ ⑦ ⑧ ⑨ ⑩ ⑪ ⑫ ⑬ ⑭ ⑮ ⑯ ⑰ ⑱ ⑲ ⑳ Ⅰ Ⅱ Ⅲ Ⅳ ⅤⅥ Ⅶ Ⅷ Ⅸ Ⅹ ㍉ ㌔ ㌢ ㍍ ㌘ ㌧"
                + " ㌃ ㌶ ㍑ ㍗ ㌍ ㌦㌣ ㌫ ㍊ ㌻ ㎜ ㎝ ㎞ ㎎ ㎏㏄㎡ ㍻ 〝 〟 № ㏍ ℡ ㊤ ㊥ ㊦ ㊧ ㊨ ㈱ ㈲ ㈹ ㍾ ㍽ ㍼"
                + " ≒ ≡ ∫ ∮ ∑ √ ⊥ ∠ ∟ ⊿ <br> ⊿ ∵ ∩ ∪～～～∥ － ￡￢□※【】◎○△×";
        String str2 = "~ⅲ～()_+|*&^%$#@!~\";;'><?/.,.'";
        String str3 = "シaスbテムc管理・アクセスログ管理・アクセスログ検索起動時";
        String str4 = "ｱｲｳｴ";
        String result = subStringByte(str, 8);
        String result2 = subStringByte(str2, 8);
        String result3 = subStringByte(str3, 2, 7);
        String result4 = subStringByte(str4, 1, 5);
        System.out.println(result);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
        System.out
                .println(dbc2sbc("シ~!`@#$%^&*()-=\\_+|[]{};:'\",<.>/? 0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));
    }

    /**
     * 下划线转驼峰法
     *
     * @param line       源字符串
     * @param smallCamel 大小驼峰,是否为小驼峰
     * @return 转换后的字符串
     */
    public static String underline2Camel(String line, boolean smallCamel) {
        if (line == null || "".equals(line)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("([A-Za-z\\d]+)(_)?");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(smallCamel && matcher.start() == 0 ? Character.toLowerCase(word.charAt(0)) : Character.toUpperCase(word.charAt(0)));
            int index = word.lastIndexOf('_');
            if (index > 0) {
                sb.append(word.substring(1, index).toLowerCase());
            } else {
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

    /**
     * 驼峰法转下划线
     *
     * @param line 源字符串
     * @return 转换后的字符串
     */
    public static String camel2Underline(String line) {
        if (line == null || "".equals(line)) {
            return "";
        }
        line = String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("[A-Z]([a-z\\d]+)?");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(word.toUpperCase());
            sb.append(matcher.end() == line.length() ? "" : "_");
        }
        return sb.toString();
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;

    }

    public static String substringBeforeLast(String str, String separator) {
        if (!isEmpty(str) && !isEmpty(separator)) {
            int pos = str.lastIndexOf(separator);
            return pos == -1 ? str : str.substring(0, pos);
        } else {
            return str;
        }
    }
}
