/*****************************************************************************
 * 프로그램명  : DisplayUtil.java
 * 설     명  : 화면표시부품(권한에따라화면표시제어)
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.09.25  LYS    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.common.util;

/**
 * 화면에출력하는HTML문자열을구성하고편집관리하는기능
 *
 * @author eaction
 * @version 1.0
 */
public class DisplayUtil {

    public static String getSoundFileNew(final String status) {
        if (status.equals("CRI")) {
            // return "/critical_ems.wav";
            return "/PCF_Critical.wav";
        } else if (status.equals("MAJ")) {
            // return "/major_ems.wav";
            return "/PCF_Major.wav";
        } else if (status.equals("MIN")) {
            // return "/minor_ems.wav";
            return "/PCF_Minor.wav";
        } else if (status.equals("WAN")) {
            return "/warning_ems.wav";
        }
        return "/online.wav";
    }

}
