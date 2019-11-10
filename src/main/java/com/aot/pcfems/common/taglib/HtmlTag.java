package com.aot.pcfems.common.taglib;

import com.aot.standard.common.constant.CommonCode;
import com.aot.standard.common.util.AotMessageUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

@PropertySource("classpath:enviroment.properties")
public class HtmlTag extends TagSupport {
    private static final Logger LOGGER = LoggerFactory.getLogger(HtmlTag.class);
    private final StringBuilder sb = new StringBuilder();
    private transient static final long serialVersionUID = 3820811796819559757L;

    /**
     * HTML의TITLE태그값(특별한 이름이 들어가는 경우만 설정)
     */
    private String title;

    private String pace;
    private String outdatebrowser;

    /**
     * 달력처리스크립트
     */
    private String picker;

    /**
     * jqGrid 스크립트추가 여부
     */
    private String jqGrid;

    /**
     * 차트 스크립트
     */
    private String chart;

    private String textEditor;
    private String fileDownloader;
    private String chartjs;
    private String dataTables;

    /**
     * 시작 태그 처리
     *
     * @return String HTML태그문자열
     */
    @Override
    public int doStartTag() throws JspException {
        final HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
        this.sb.setLength(0);
        this.sb.append("<!DOCTYPE html>");
        this.sb.append("<html lang=\"ko\">");
        this.sb.append("<head>");
        this.sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
        // sbHtml.append( "<meta charset=\"utf-8\">");

        this.sb.append("<title>");
        // 타이틀을 지정한 경우는 지정한 타이틀을 설정하고 지정하지 않은경우는 기본설정적용
        this.sb.append(StringUtils.defaultIfBlank(this.title, AotMessageUtils.getProperty("PROJECT_TITLE")));
        this.sb.append("</title>");
        this.sb.append("<meta name=\"description\" content=\"\">");
        this.sb.append("<meta name=\"author\" content=\"\">");
        this.sb.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\">");

        this.sb.append("<meta http-equiv=\"Cache-Control\" content=\"no-cache, no-store, must-revalidate\"/>");

        /* ▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼SmartAdmin BootStrap▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼▼ */
        this.makeLinkTag(CommonCode.PATH_PLUGIN + "/normalize/normalize.min.css");
        this.makeLinkTag(CommonCode.PATH_PLUGIN + "/smartAdmin/css/bootstrap.min.css");
        this.makeLinkTag(CommonCode.PATH_PLUGIN + "/smartAdmin/css/font-awesome.min.css");

        /* <!-- SmartAdmin Styles : Caution! DO NOT change the order --> */
        this.makeLinkTag(CommonCode.PATH_PLUGIN + "/smartAdmin/css/smartadmin-production-plugins.min.css");
        this.makeLinkTag(CommonCode.PATH_PLUGIN + "/smartAdmin/css/smartadmin-production.min.css");
        this.makeLinkTag(CommonCode.PATH_PLUGIN + "/smartAdmin/css/smartadmin-skins.min.css");

        /* <!-- SmartAdmin RTL Support --> */
        this.makeLinkTag(CommonCode.PATH_PLUGIN + "/smartAdmin/css/smartadmin-rtl.min.css");

        /*
         * <!-- We recommend you use "your_style.css" to override SmartAdmin specific styles this will also ensure you retrain your customization with each SmartAdmin update. <link rel="stylesheet"
         * type="text/css" media="screen" href="css/your_style.css"> -->
         */
        // this.makeLinkTag(CommonCode.PATH_PUBLISH + "/css/custom_style.css");

        // this.makeLinkTag(CommonCode.PATH_PLUGIN + "/smartAdmin/css/demo.min.css");

        /* <!-- #FAVICONS --> */
        this.sb.append("<link rel=\"shortcut icon\" href=\"" + CommonCode.PATH_PLUGIN + "/smartAdmin/img/favicon/favicon.ico\" type=\"image/x-icon\">");
        this.sb.append("<link rel=\"icon\" href=\"" + CommonCode.PATH_PLUGIN + "/smartAdmin/img/favicon/favicon.ico\" type=\"image/x-icon\">");

        /* <!-- #GOOGLE FONT --> */
        // this.makeLinkTag(CommonCode.PATH_PLUGIN + "/googlefonts/css.css");

        this.makeLinkTag(CommonCode.PATH_CSS + "/AotCommon.css");

        this.makeScriptTag(CommonCode.PATH_PLUGIN + "/jquery/jquery.min.js");
        this.makeScriptTag(CommonCode.PATH_PLUGIN + "/jquery-ui/jquery-ui.min.js");

        this.makeScriptTag(CommonCode.PATH_PLUGIN + "/moment/moment.min.js");
        this.makeScriptTag(CommonCode.PATH_PLUGIN + "/moment/ko.js");

        this.makeScriptTag(CommonCode.PATH_PLUGIN + "/lodash/lodash.min.js");

        /* <!-- BOOTSTRAP JS --> */
        this.makeScriptTag(CommonCode.PATH_PLUGIN + "/bootstrap/bootstrap.min.js");

        /* <!-- CUSTOM NOTIFICATION --> */
        this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/notification/SmartNotification.min.js");
        /* <!-- JARVIS WIDGETS --> */
        this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/smartwidgets/jarvis.widget.min.js");

        /* <!-- SPARKLINES --> */
        this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/plugin/sparkline/jquery.sparkline.min.js");

        /* <!-- JQUERY VALIDATE --> */
        this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/plugin/jquery-validate/jquery.validate.min.js");

        /* <!-- JQUERY MASKED INPUT --> */
        this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/plugin/masked-input/jquery.maskedinput.min.js");

        /* <!-- JQUERY SELECT2 INPUT --> */
        /* sbScript.append("<script src=\""+ CommonCode.PATH_PLUGIN + "/smartAdmin/js" +"/plugin/select2/select2.min.js"); */

        /* <!-- JQUERY UI + Bootstrap Slider --> */
        this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/plugin/bootstrap-slider/bootstrap-slider.min.js");

        /* <!-- browser msie issue fix --> */
        this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/plugin/msie-fix/jquery.mb.browser.min.js");

        /* <!-- FastClick: For mobile devices --> */
        this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/plugin/fastclick/fastclick.min.js");

        /* <!-- Demo purpose only --> */
        // this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/demo.min.js");

        /* <!-- PAGE RELATED PLUGIN(S) --> */
        this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/plugin/jquery-form/jquery-form.min.js");

        this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/plugin/bootstrap-progressbar/bootstrap-progressbar.min.js");

        /* <!--Number --> */
        this.makeScriptTag(CommonCode.PATH_PLUGIN + "/number-format/jquery.number.min.js");
        // this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/plugin/number/jquery.number.js");

        // this.makeLinkTag("https://cdnjs.cloudflare.com/ajax/libs/pace/1.0.2/themes/black/pace-theme-corner-indicator.min.css", CommonCode.PATH_PLUGIN +
        // "/pace/pace-theme-corner-indicator.min.css");

        if (!CommonCode.NO.equals(this.pace)) {
            this.makeLinkTag(CommonCode.PATH_CSS + "/AotPace.css");
            this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/plugin/pace/pace.min.js");
        }
        this.makeScriptTag(CommonCode.PATH_JS + "/AotCommon.js");
        this.makeScriptTag(CommonCode.PATH_JS + "/AotAjax.js");
        this.makeScriptTag(CommonCode.PATH_PLUGIN + "/jquery-popupwindow/jquery.popupwindow.js");
        this.makeScriptTag(CommonCode.PATH_PLUGIN + "/handlebars/handlebars.min.js");
        this.makeScriptTag(CommonCode.PATH_JS + "/AotHandlebars.js");
        //
        this.makeScriptTag(CommonCode.PATH_PLUGIN + "/bootstrap-maxlength/bootstrap-maxlength.min.js");
        this.makeScriptTag(CommonCode.PATH_JS + "/AotMaxlength.js");

        if (CommonCode.YES.equals(this.picker)) {
            // 달력
            // this.makeScriptTag(CommonCode.PATH_PUBLISH + "/js/plugin/bootstrap-datetimepicker/3.1.3/css/bootstrap-datetimepicker.min.css");
            this.makeLinkTag(CommonCode.PATH_PLUGIN + "/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css");

            this.makeScriptTag(CommonCode.PATH_PLUGIN + "/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js");
            // this.makeScriptTag(CommonCode.PATH_PUBLISH + "/js/plugin/bootstrap-datetimepicker/3.1.3/js/bootstrap-datetimepicker.min.js");
            this.makeScriptTag(CommonCode.PATH_JS + "/AotDatetimePicker.js");
            // 시계
            this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/plugin/bootstrap-timepicker/bootstrap-timepicker.min.js");
            this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/plugin/clockpicker/clockpicker.min.js");
        }

        if (CommonCode.YES.equals(this.jqGrid)) {
            // this.makeLinkTag(CommonCode.PATH_CSS + "/AotJqGrid.css");
            this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/plugin/jqgrid/jquery.jqGrid.min.js");
            this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/plugin/jqgrid/grid.locale-en.min.js");
            this.makeScriptTag(CommonCode.PATH_JS + "/AotJqGrid.js");
        }

        if (CommonCode.YES.equals(this.chart)) {
            this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/plugin/flot/jquery.flot.cust.min.js");
            this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/plugin/flot/jquery.flot.resize.min.js");
            this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/plugin/flot/jquery.flot.tooltip.min.js");
            this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/plugin/flot/jquery.flot.time.min.js");
            this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/plugin/flot/jquery.flot.categories.min.js ");
            this.makeScriptTag(CommonCode.PATH_PUBLISH + "/css/flot.js");
        }

        if (CommonCode.YES.equals(this.dataTables)) {
            this.makeLinkTag(CommonCode.PATH_CSS + "/AotDataTables.css");
            this.makeScriptTag(CommonCode.PATH_PLUGIN + "/dataTables/jquery.dataTables.min.js");
            this.makeScriptTag(CommonCode.PATH_PLUGIN + "/dataTables/buttons.colVis.min.js");
            // this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/plugin/datatables/dataTables.tableTools.min.js");
            this.makeScriptTag(CommonCode.PATH_PLUGIN + "/dataTables/dataTables.bootstrap.min.js");
            this.makeScriptTag(CommonCode.PATH_PLUGIN + "/dataTables/dataTables.responsive.min.js");
            this.makeScriptTag(CommonCode.PATH_PLUGIN + "/dataTables/dataTables.fixedColumns.min.js");
            this.makeScriptTag(CommonCode.PATH_JS + "/AotDataTables.js");
        }

        if (StringUtils.equals(this.fileDownloader, CommonCode.YES)) {
            this.makeScriptTag(CommonCode.PATH_PLUGIN + "/jquery-fileDownload/jquery.fileDownload.js");
        }

        if (StringUtils.equals(this.textEditor, CommonCode.YES)) {
            this.makeLinkTag(CommonCode.PATH_CSS + "/AotTextEditor.css");
            this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/plugin/summernote/summernote.min.js");
            this.makeScriptTag(CommonCode.PATH_JS + "/AotTextEditor.js");
        }

        if (StringUtils.equals(this.chartjs, CommonCode.YES)) {
            this.makeScriptTag(CommonCode.PATH_PLUGIN + "/chartjs/Chart.min.js");
            this.makeScriptTag(CommonCode.PATH_JS + "/AotChartjs.js");
        }
        this.makeScriptTag(CommonCode.PATH_JS + "/AotSmartAdmin.js");

        if (StringUtils.equals(this.outdatebrowser, CommonCode.YES)) {
            this.makeLinkTag(CommonCode.PATH_PLUGIN + "/outdatebrowser/outdatedbrowser.min.css");
            this.makeScriptTag(CommonCode.PATH_PLUGIN + "/outdatebrowser/outdatedbrowser.min.js");
            this.makeScriptTag(CommonCode.PATH_JS + "/AotOutdatedBrowser.js");
        }

        this.sb.append("<script type=\"text/javascript\">");
        this.sb.append(" var CONTEXT_PATH = \"");
        this.sb.append(CommonCode.CONTEXT_PATH);
        this.sb.append("\"; ");
        if (request.getAttribute("retMsg") != null && StringUtils.isNotEmpty((String) request.getAttribute("retMsg"))) {
            this.sb.append("AotSmartAdmin.smallBoxInfo('" + request.getAttribute("retMsg") + "');");
        }
        this.sb.append("$(document).ready(function() {");
        this.sb.append("});");
        this.sb.append("</script>");

        this.sb.append("</head>");

        try {
            this.pageContext.getOut().print(this.sb.toString());
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new JspException(ExceptionUtils.getStackTrace(e));
        }

        return EVAL_BODY_INCLUDE;
    }

    private void makeScriptTag(final String path) {
        this.sb.append("<script src=\"").append(path).append("\"></script>");
    }

    private void makeLinkTag(final String path) {
        this.sb.append("<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"").append(path).append("\" />");
    }

    /**
     * 종료 태그 처리
     */
    @Override
    public int doEndTag() throws JspException {
        this.sb.setLength(0);
        try {
            /* <!-- IMPORTANT: APP CONFIG --> */
            this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/app.config.js");
            /* <!-- MAIN APP JS FILE --> */
            this.makeScriptTag(CommonCode.PATH_PLUGIN + "/smartAdmin/js/app.js");
            this.sb.append("<script type=\"text/javascript\">");
            this.sb.append("$(document).ready(function() {");
            this.sb.append("	pageSetUp();");
            this.sb.append("});");
            this.sb.append("</script>");
            this.pageContext.getOut().print(this.sb.toString() + "</html>");
        } catch (final Throwable e) {
            LOGGER.warn(ExceptionUtils.getStackTrace(e));
            throw new JspException(ExceptionUtils.getStackTrace(e));
        }
        return SKIP_PAGE;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setPace(final String pace) {
        this.pace = pace;
    }

    public void setOutdatebrowser(final String outdatebrowser) {
        this.outdatebrowser = outdatebrowser;
    }

    public void setJqGrid(final String jqGrid) {
        this.jqGrid = jqGrid;
    }

    public void setChart(final String chart) {
        this.chart = chart;
    }

    public void setPicker(final String picker) {
        this.picker = picker;
    }

    public void setFileDownloader(final String fileDownloader) {
        this.fileDownloader = fileDownloader;
    }

    public void setTextEditor(final String textEditor) {
        this.textEditor = textEditor;
    }

    public void setChartjs(final String chartjs) {
        this.chartjs = chartjs;
    }

    public void setDataTables(final String dataTables) {
        this.dataTables = dataTables;
    }

}
