/*****************************************************************************
 * 프로그램명  : StringUtil.java
 * 설     명  : 문자 관련 클래스 util class.
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.09.23  LYS    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.common.util;

import com.aot.pcfems.common.model.CodeInfo;
import com.aot.pcfems.common.model.UserVO;
import com.aot.standard.common.constant.CommonCode;
import com.aot.standard.common.util.AotMessageUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import java.text.DecimalFormat;
import java.util.List;

/**
 * 문자관련 클래스 util class.
 *
 * @author eaction
 * @version 1.0
 */
public class StringUtil {
    // private static Logger LOGGER = LoggerFactory.getLogger(StringUtil.class);

    @Value("#{enviromentProperties['LANGUAGE']}")
    private static String LANGUAGE = AotMessageUtils.getProperty("LANGUAGE");

    /**
     * DB처리에 사용할 공통 IN파라메터를 설정한다
     *
     * @param userInfo 유저정보
     * @return String 결과문자열
     */
    public static String getCommonPrefix() {
        final StringBuffer sbPrefix = new StringBuffer();
        // 언어설정(KOR | ENG | JPN)
        sbPrefix.append("LANG=");
        sbPrefix.append(LANGUAGE);
        return sbPrefix.toString();
    }

    public static String getCommonPrefix(final UserVO userInfo) {
        final StringBuffer sbPrefix = new StringBuffer();
        // 언어설정(KOR | ENG | JPN)
        sbPrefix.append("LANG=");
        sbPrefix.append(LANGUAGE);
        // 로그인아이디설정
        sbPrefix.append(CommonCode.PROC_DATA_SEP);
        sbPrefix.append("LOGINID=");
        sbPrefix.append(userInfo.getUser_id());

        return sbPrefix.toString();
    }

    public static String makeSrcEnv(final UserVO userInfo) {
        final StringBuffer sbPrefix = new StringBuffer();
        sbPrefix.append(userInfo.getUser_lang());
        // 로그인아이디설정
        sbPrefix.append(CommonCode.PROC_DATA_SEP);
        sbPrefix.append(userInfo.getUser_id());
        return sbPrefix.toString();
    }

    public static String round(final double target) {
        return new DecimalFormat("0.##").format(target);
    }

    /**
     * 숫자를 세자리마다 콤마를 찍는다.
     *
     * @param level 메뉴레벨
     * @return String 결과문자열
     */
    public static String toComma(final String inValue) {
        if (inValue != null && inValue.length() > 0) {
            final int idx = inValue.indexOf("."); // 소수점 처리
            String str = inValue;
            String rightVal = "";
            if (idx > 0) {
                str = inValue.substring(0, idx);
                rightVal = inValue.substring(idx + 1);
                // 소수점자리 이하가 0이면 소수점 아래를 없앤다.
                if (StringUtils.isNumeric(rightVal) && Integer.parseInt(rightVal) > 0) {
                    rightVal = inValue.substring(idx);
                } else {
                    rightVal = "";
                }
            }
            str = str + "";
            str = RegExUtils.removeAll(str, ",");
            int tmp1, tmp2, tmp3;
            String statValue = "";
            StringBuilder strValue = new StringBuilder();
            String modValue = "";
            if (str.substring(0, 1).equals("-")) {
                statValue = str.substring(1);
            } else {
                statValue = str;
            }
            tmp1 = statValue.length();

            if (tmp1 > 3) {
                tmp2 = tmp1 / 3;
                tmp3 = tmp1 % 3;

                if (tmp3 > 0) {
                    strValue.append(statValue.substring(0, tmp3) + ",");
                    modValue = statValue.substring(tmp3);
                } else {
                    modValue = statValue;
                }

                for (int i = 0; i < tmp2; i++) {
                    if (i == tmp2 - 1) {
                        strValue.append(modValue, i * 3, i * 3 + 3);
                    } else {
                        strValue.append(modValue.substring(i * 3, i * 3 + 3) + ",");
                    }
                }
            } else {
                strValue = new StringBuilder(statValue);
            }

            String srtValue = "";
            if (str.substring(0, 1).equals("-")) {
                srtValue = "-" + strValue;
            } else {
                srtValue = strValue.toString();
            }

            return srtValue + rightVal;
        } else {
            return "";
        }
    }

    /**
     * 텍스트 데이터의 내용을 화면에 보여주기 위해서 적절히 변환한다
     *
     * @param target 대상문자열
     * @return result 변환된 결과 문자열
     */
    public static String convertText(final String target) {
        // 공백을 &nbsp;로 변환
        String result = RegExUtils.replaceAll(target, " ", "&nbsp;");
        // 개행문자를 <BR>로변환
        result = RegExUtils.replaceAll(result, "\n", "<BR>");
        return result;
    }

    /**
     * 콤보 option 값을 채운다
     *
     * @param List      콤보 데이타
     * @param init_yn   기본값 여부
     * @param init_msg  기본값
     * @param usserInfo User
     * @return String 마지막문자열
     */
    public static String comboString(final List<CodeInfo> list, final boolean init_yn, final String init_code, final String init_msg, final UserVO usserInfo) {
        final StringBuffer sb = new StringBuffer();
        if (init_yn == true) {
            sb.append("<option value = '");
            sb.append(init_code);
            sb.append("'>");
            sb.append(init_msg);
            sb.append("</option>");
        }
        for (int i = 0; i < list.size(); i++) {
            final CodeInfo ov = list.get(i);
            sb.append("<option value = '");
            sb.append(ov.getCode());
            sb.append("'>");
            if ("KOR".equals(usserInfo.getUser_lang())) {
                sb.append(ov.getCodenm_k());
            } else if ("ENG".equals(usserInfo.getUser_lang())) {
                sb.append(ov.getCodenm_e());
            } else {
                sb.append(ov.getCodenm_j());
            }
            sb.append("</option>");
        }

        return sb.toString();
    }

    /**
     * 메뉴에서 레벨당 들여쓰기를 설정한다
     *
     * @param level 메뉴레벨
     * @return String 결과문자열
     */
    public static String printMenuTitle(final String level) {
        final StringBuffer sbLevel = new StringBuffer();
        int nlevel = 0;
        if (StringUtils.isNotEmpty(level)) {
            nlevel = Integer.parseInt(level);
        }
        for (int i = 1; i < nlevel; i++) {
            sbLevel.append("&nbsp;&nbsp;&nbsp;&nbsp;");
        }

        return sbLevel.toString();
    }
}
