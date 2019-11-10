/*****************************************************************************
 * 프로그램명  : CookieMng.java
 * 설     명  : ȸ��rhdxhdqnvna쿠키관리클래스
 * 참고  사항  : 없음
 *****************************************************************************
 * Date       Author  Version Description
 * ---------- ------- ------- -----------------------------------------------
 * 2008.12.23    LYS    1.0     초기작성
 *****************************************************************************/
package com.aot.pcfems.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * ȸ��rhdxhdqnvna쿠키관리 클래스
 *
 * @author eaction
 * @version 1.0
 */
public class CookieMng {

    /**
     * 쿠키리스트 (CookieWrapper객체의 리스트 저장)
     */
    private final List<Cookie> cookies = new ArrayList<>();

    public CookieMng() {

    }

    /**
     * request정보로 부터 쿠키정보를 모두 취득해서 리스트에 담아놓는다
     *
     * @param request 요청정보
     */
    public CookieMng(final HttpServletRequest request) {
        if (this.cookies != null) {
            this.setCookies(request.getCookies());
        }
    }

    /**
     * response정보에 쿠키리스트 정보를 모두 설정한다
     *
     * @param response
     *            응답객체
     */
    // public void setCookieList(final HttpServletResponse response) {
    // for (Cookie cw : this.cookies) {
    // response.addCookie(cw);
    // }
    // }

    /**
     * 쿠키리스트에 쿠키래퍼객체를 하나 추가한다
     *
     * @param wrappedCookie 쿠키래퍼객체
     */
    public void addCookie(final Cookie cookie) {
        this.cookies.add(cookie);
    }

    /**
     * 쿠키래퍼를 설정해서 관리리스트에 추가한다
     *
     * @param name
     *            쿠키명칭
     * @param value
     *            쿠키값
     * @param maxAge
     *            쿠키보관기간
     */
    // public void addCookie(final String name, final String value) {
    // Cookie cookie = new Cookie(name, value);
    // this.addCookie(cookie);
    // }

    /**
     * 쿠키래퍼를 설정해서 관리리스트에 추가한다
     *
     * @param name
     *            쿠키명칭
     * @param value
     *            쿠키값
     * @param maxAge
     *            쿠키보관기간
     */
    // public void addCookie(final String name, final String value, final int maxAge) {
    // Cookie cookie = new Cookie(name, value);
    // cookie.setMaxAge(maxAge);
    // this.addCookie(cookie);
    // }

    /**
     * 쿠키를 쿠키래퍼에 설정해서 보관리스트에 저장한다
     *
     * @param cookies 쿠키리스트
     */
    public void setCookies(final Cookie[] cookies) {
        if (cookies != null) {
            for (final Cookie cookie : cookies) {
                this.addCookie(cookie);
            }
        }
    }

    /**
     * 쿠키래퍼클래스 보관 리스트로 부터 쿠키의 리스트를 취득한다
     *
     * @return ArrayList 쿠키리스트
     */
    // private List<Cookie> getCookieList() {
    // return this.cookies;
    // }

    /**
     * 쿠키벼열를 취득한다
     *
     * @return Cookie[]쿠키배열을 취득
     */
    // public Cookie[] getCookies() {
    // return (Cookie[]) this.getCookieList().toArray();
    // }

    /**
     * 쿠키리스트로 부터 쿠키를 검색한다
     *
     * @param name 검색할 쿠키명칭
     * @return CookieWrapper 쿠키저장객체
     */
    private Cookie searchCookie(final String name) {
        for (final Cookie cw : this.cookies) {
            if (cw.getName().equals(name)) {
                return cw;
            }
        }
        return null;
    }

    /**
     * 쿠키이름으로 검색하여 해당 쿠키의 값을 취득한다
     *
     * @param name 쿠키이름
     * @return String 쿠키값ꍇ
     */
    public String getValue(final String name) {
        String strResult = "";
        final Cookie cw = this.searchCookie(name);

        if (cw != null) {
            strResult = cw.getValue();
        }

        return strResult;
    }

    /**
     * 쿠키이름으로 쿠키를 검색해서 쿠키값을 수정한다
     *
     * @param name  쿠키이름
     * @param value 쿠키이름ꍇ
     */
    public void setValue(final String name, final String value) {

        final Cookie cw = this.searchCookie(name);

        if (cw != null) {
            cw.setValue(value);
        }
    }

    /**
     * 쿠키이름으로 검색해서 값과 보관기간을 수정한다
     *
     * @param name
     *            쿠키이름
     * @param value
     *            쿠키값
     * @param maxAge
     *            쿠키저장기간ꍇ
     */
    // public void setValue(final String name, final String value, final int maxAge) {
    // this.setValue(name, value);
    // this.setMaxAge(name, maxAge);
    // }

    /**
     * 쿠키저장기간
     *
     * @param name
     *            쿠키명칭
     * @return int 쿠키저장기간ꍇ
     */
    // public int getMaxAge(final String name) {
    // int age = 0;
    // Cookie cw = this.searchCookie(name);
    //
    // if (cw != null) {
    // age = cw.getMaxAge();
    // }
    //
    // return age;
    // }

    /**
     * 쿠키명칭으로 쿠키를 검색해서 쿠키저장기간을 갱신한다
     *
     * @param name
     *            쿠키명칭
     * @param maxAge
     *            쿠키저장기간ꍇ
     */
    // public void setMaxAge(final String name, final int maxAge) {
    // Cookie cw = this.searchCookie(name);
    //
    // if (cw != null) {
    // cw.setMaxAge(maxAge);
    // }
    // }

}
