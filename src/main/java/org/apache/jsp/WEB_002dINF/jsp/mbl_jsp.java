/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: JspCServletContext/1.0
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class mbl_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ffmt_005fsetLocale_0026_005fvalue_005fscope_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;

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
    _005fjspx_005ftagPool_005ffmt_005fsetLocale_0026_005fvalue_005fscope_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005ffmt_005fsetLocale_0026_005fvalue_005fscope_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, false, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\n');
      out.write('\n');
      out.write('\n');
      if (_jspx_meth_fmt_005fsetLocale_005f0(_jspx_page_context))
        return;
      out.write("\n<!DOCTYPE html>\n<html>\n<head>\n  <meta charset=\"UTF-8\">\n  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n  <script type=\"text/javascript\" src=\"../js/jquery-3.3.1.min.js\"></script>\n  <script type=\"text/javascript\" src=\"../js/bsNum.js\"></script>\n  <script type=\"text/javascript\" src=\"../js/bsInpNum.js\"></script>\n  <script type=\"text/javascript\" src=\"../js/bsAjax.js\"></script>\n  <script type=\"text/javascript\" src=\"../js/bsForm.js\"></script>\n  <script type=\"text/javascript\" src=\"../js/bsAcc.js\"></script>\n  <script type=\"text/javascript\" src=\"../js/bsI18n");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${rvs.upf.lng.iid}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(".js\"></script>\n  <link rel=\"stylesheet\" href=\"../css/bsCmn.css\" />\n  <link rel=\"icon\" type=\"image/png\"  href=\"../img/favicon.png\">\n  <title>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"app_name\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</title>\n</head>\n<body>\n\n  <div class=\"navbar\">\n    <div class=\"dropdown\">\n      <a href=\"#\" class=\"dropdown-btn\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"Accounting\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n      <div class=\"dropdown-content\">\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=AcStg&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"AcStgs\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=I18Acc&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"I18Accs\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=Curr&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"Currs\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=I18Curr&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"I18Currs\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=Expn&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"Expns\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=Bnka&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"Bnkas\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=Acnt&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"Acnts\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=InEntr&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"InEntrs\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=Entr&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"Entrs\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=EnrSrc&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"EnrSrcs\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'ntr?rnd=blnj&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"trial_balance\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'ntr?rnd=blnsj&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"balance_sheet\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'ntr?rnd=ldgj&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"Ledger\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n      </div>\n    </div>\n    <div class=\"dropdown\">\n      <a href=\"#\" class=\"dropdown-btn\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"Common\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n      <div class=\"dropdown-content\">\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=Uom&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"Uoms\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=Wrh&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"Wrhs\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=WrhPl&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"WrhPls\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=Tax&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"Taxs\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=TxCt&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"TxCts\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=TxDst&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"TxDsts\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=ItmCt&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"ItmCts\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=Itm&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"Itms\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=SrvCt&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"SrvCts\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=Srv&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"Srvs\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=DcrCt&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"DcrCts\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=DbCr&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"DbCrs\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=PrepTo&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"PrepTos\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=PurInv&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"PurInvs\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=PaymTo&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"PaymTos\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=PrepFr&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"PrepFrs\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=SalInv&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"SalInvs\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=PaymFr&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"PaymFrs\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=PurRet&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"PurRets\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=SalRet&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"SalRets\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=DriEnrSr&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"DriEnrSrs\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=MovItm&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"MovItms\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=ItmAdd&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"ItmAdds\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=ItmUlb&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"ItmUlbs\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=I18Uom&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"I18Uoms\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=I18Itm&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"I18Itms\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=I18Srv&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"I18Srvs\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=I18Buyr&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"I18Buyrs\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"ntr?prc=PrWrhItm\" target=\"_blank\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"items_warehouse\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n      </div>\n    </div>\n    <div class=\"dropdown\">\n      <a href=\"#\" class=\"dropdown-btn\">...</a>\n      <div class=\"dropdown-content\">\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'ntr?prc=About');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"About\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"srv?rnd=mbl&mbl=mbl\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"Refresh\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"../\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"Home\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsOpnDlg('dlgI18n');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"changeUsPrf\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=Lng&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"Lngs\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=Cntr&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"Cntrs\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=DcSp&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"DcSps\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=DcGrSp&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"DcGrSps\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=UsPrf&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"UsPrfs\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=CsvMth&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"CsvMths\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"#\" onclick=\"bsGtAjx('GET', 'srv?act=lst&rnd=lhj&ent=MaFrn&pg=1&mbl=mbl/');\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"MaFrns\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        <a href=\"ntr?prc=RefrI18n\" target=\"_blank\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"RefrI18n\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</a>\n        ");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("\n        ");
      if (_jspx_meth_c_005fif_005f1(_jspx_page_context))
        return;
      out.write("\n      </div>\n    </div>\n  </div>  \n\n  ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "tml/bum.jsp", out, false);
      out.write("\n\n</body>\n</html>\n");
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

  private boolean _jspx_meth_fmt_005fsetLocale_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  fmt:setLocale
    org.apache.taglibs.standard.tag.rt.fmt.SetLocaleTag _jspx_th_fmt_005fsetLocale_005f0 = (org.apache.taglibs.standard.tag.rt.fmt.SetLocaleTag) _005fjspx_005ftagPool_005ffmt_005fsetLocale_0026_005fvalue_005fscope_005fnobody.get(org.apache.taglibs.standard.tag.rt.fmt.SetLocaleTag.class);
    _jspx_th_fmt_005fsetLocale_005f0.setPageContext(_jspx_page_context);
    _jspx_th_fmt_005fsetLocale_005f0.setParent(null);
    // /WEB-INF/jsp/mbl.jsp(4,0) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fsetLocale_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${rvs.cpf.usLoc}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
    // /WEB-INF/jsp/mbl.jsp(4,0) name = scope type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_fmt_005fsetLocale_005f0.setScope("request");
    int _jspx_eval_fmt_005fsetLocale_005f0 = _jspx_th_fmt_005fsetLocale_005f0.doStartTag();
    if (_jspx_th_fmt_005fsetLocale_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ffmt_005fsetLocale_0026_005fvalue_005fscope_005fnobody.reuse(_jspx_th_fmt_005fsetLocale_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ffmt_005fsetLocale_0026_005fvalue_005fscope_005fnobody.reuse(_jspx_th_fmt_005fsetLocale_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /WEB-INF/jsp/mbl.jsp(91,8) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${not empty pageContext['request'].userPrincipal}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n          <a href=\"../?logoff=true\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext['request'].userPrincipal.name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write(' ');
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"logout\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write("</a>\n        ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent(null);
    // /WEB-INF/jsp/mbl.jsp(94,8) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.servletContext.getInitParameter('ormDir') eq 'sqlite'}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n          <a href=\"ntr?prc=PrcUsrPwd&mbl=mbl/\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i18n.getMsg(\"change_password\", rvs.upf.lng.iid)}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write("</a>\n        ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
    return false;
  }
}
