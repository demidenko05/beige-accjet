/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: JspCServletContext/1.0
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.rep;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class ldgf_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\n');
      out.write('\n');
      java.util.Date now = null;
      now = (java.util.Date) _jspx_page_context.getAttribute("now", javax.servlet.jsp.PageContext.PAGE_SCOPE);
      if (now == null){
        now = new java.util.Date();
        _jspx_page_context.setAttribute("now", now, javax.servlet.jsp.PageContext.PAGE_SCOPE);
      }
      out.write("\n<dialog id=\"frmRepDlg\" class=\"dlg\" oncancel=\"return false;\">\n  <div class=\"form-std\">\n    <div class=\"dialog-title\">\n      ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"Ledger\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\n      <button onclick=\"bsClsDlgCf('frmRep')\" class=\"btn-close\">x</button>\n    </div>\n    <form id=\"frmRepFrm\" action=\"ntr\" method=\"GET\" target=\"_blank\">\n      <input type=\"hidden\" name=\"prc\" value=\"PrLdgr\">\n      <table class=\"tbl-fieldset\">\n        <tr>\n          <td>\n            <label for=\"dt1\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"dt1\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(":</label>\n          </td>\n        </tr>\n        <tr>\n          <td>\n            <input autofocus type=\"datetime-local\" required name=\"dt1\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${srvDt.to8601DateTime(now)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" onchange=\"bsInpChn(this);\"/> \n          </td>\n        </tr>\n        <tr>\n          <td>\n            <label for=\"dt2\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"dt2\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(":</label>\n          </td>\n        </tr>\n        <tr>\n          <td>\n            <input type=\"datetime-local\" required name=\"dt2\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${srvDt.to8601DateTime(now)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\" onchange=\"bsInpChn(this);\"/> \n          </td>\n        </tr>\n        <tr>\n          <td>\n            <label for=\"ldgAccApVsb\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"Acnt\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(":</label>\n          </td>\n        <tr>\n        </tr>\n          <td>\n            <div class=\"input-line\">\n              <input class=\"picked-appearence\" id=\"ldgAccApVsb\" disabled=\"disabled\" type=\"text\">\n              <input id=\"ldgAccId\" required type=\"hidden\" name=\"accId\">\n              <input id=\"ldgAccAp\" required type=\"hidden\" name=\"accNm\">\n              <button type=\"button\" class=\"btn\" onclick=\"bsPick('Acnt','ldg','Acc','&mbl=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.mbl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("');\">...</button>\n              <button type=\"button\" class=\"btn\" onclick=\"bsClrSelEnt('ldgAcc');bsClearSac('ldg','Sac');\">X</button>\n            </div>\n          </td>\n        </tr>\n        <tr>\n          <td>\n            <label for=\"ldgSacApVsb\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"subacc\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(":</label>\n          </td>\n        <tr>\n        </tr>\n          <td>\n            <div class=\"input-line\">\n              <input class=\"picked-appearence\" id=\"ldgSacApVsb\" disabled=\"disabled\" type=\"text\">\n              <input id=\"ldgSacId\" type=\"hidden\" name=\"saId\">\n              <input id=\"ldgSacAp\" type=\"hidden\" name=\"saNm\">\n              <button id=\"ldgAccSaPi\" type=\"button\" class=\"btn\" onclick=\"bsPickSac('Acc','ldg','Sac','&mbl=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.mbl}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("');\">...</button>\n              <button id=\"ldgAccSaCl\" type=\"button\" class=\"btn\" onclick=\"bsClearSac('ldg','Sac');\">X</button>\n            </div>\n          </td>\n        </tr>\n      </table>\n      <div class=\"form-actions\">\n        <button type=\"button\" onclick=\"bsSbFrmNw('frmRepFrm', false);\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"Report\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</button>\n        <button type=\"button\" onclick=\"bsClsDlgCf('frmRep');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"Close\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n      </div>\n    </form>\n  </div>\n</dialog>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
