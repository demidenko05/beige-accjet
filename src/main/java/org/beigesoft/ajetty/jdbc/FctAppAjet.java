/*
BSD 2-Clause License

Copyright (c) 2019, Beigesoft™
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice, this
  list of conditions and the following disclaimer.

* Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.beigesoft.ajetty.jdbc;

import java.util.Set;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.sql.ResultSet;
import java.security.KeyStore;

import org.eclipse.jetty.security.DataBaseLoginService;

import org.beigesoft.fct.IFctPrc;
import org.beigesoft.fct.IFctPrcEnt;
import org.beigesoft.fct.IFctPrcFl;
import org.beigesoft.fct.IFctAsm;
import org.beigesoft.fct.FctBlc;
import org.beigesoft.fct.FctDbCp;
import org.beigesoft.fct.FctFlRep;
import org.beigesoft.fct.IFctCnToSt;
import org.beigesoft.fct.IFcFlFdSt;
import org.beigesoft.hld.IAttrs;
import org.beigesoft.hld.IHlNmClSt;
import org.beigesoft.rdb.IRdb;
import org.beigesoft.rdb.Orm;
import org.beigesoft.web.FctMail;
import org.beigesoft.jdbc.FctSqlite;
import org.beigesoft.acc.fct.FctAcc;
import org.beigesoft.acc.fct.FcEnPrAc;
import org.beigesoft.acc.fct.FcPrNtAc;
import org.beigesoft.acc.fct.FcPrNtAd;
import org.beigesoft.acc.fct.FcPrFlAc;
import org.beigesoft.acc.fct.FcCnToStAi;
import org.beigesoft.acc.fct.FcFlFdAi;
import org.beigesoft.acc.hld.HlAcEnPr;
import org.beigesoft.ws.fct.FctWs;
import org.beigesoft.ws.fct.FcEnPrTr;
import org.beigesoft.ws.hld.HlTrEnPr;
import org.beigesoft.ajetty.IHpCrypt;
import org.beigesoft.ajetty.HpCrypt;
import org.beigesoft.ajetty.FctAjet;
import org.beigesoft.ajetty.FcPrNtAj;
import org.beigesoft.ajetty.FcPrNtAdAj;
import org.beigesoft.ajetty.GetUsrCrd;

/**
 * <p>Final configuration factory for Sqlite JDBC.</p>
 *
 * @author Yury Demidenko
 */
public class FctAppAjet implements IFctAsm<ResultSet> {

  /**
   * <p>Main only factory.</p>
   **/
  private FctBlc<ResultSet> fctBlc;

  /**
   * <p>Only constructor.</p>
   * @throws Exception - an exception
   */
  public FctAppAjet() throws Exception {
    this.fctBlc = new FctBlc<ResultSet>();
    this.fctBlc.getFctsAux().add(new FctSqlite());
    this.fctBlc.getFctsAux().add(new FctDbCp<ResultSet>());
    this.fctBlc.getFctsAux().add(new FctMail<ResultSet>());
    this.fctBlc.getFctsAux().add(new FctAcc<ResultSet>());
    this.fctBlc.getFctsAux().add(new FctWs<ResultSet>());
    this.fctBlc.getFctsAux().add(new FctFlRep<ResultSet>());
    this.fctBlc.getFctsAux().add(new FctAjet<ResultSet>());
    addAux();
  }


  /**
   * <p>Add auxiliary factories.</p>
   * @throws Exception - an exception
   */
  public final void addAux() throws Exception {
    Set<IFctPrcEnt> fcsenpr = new HashSet<IFctPrcEnt>();
    FcEnPrAc<ResultSet> fcep = new FcEnPrAc<ResultSet>();
    fcep.setFctBlc(this.fctBlc);
    fcsenpr.add(fcep);
    FcEnPrTr<ResultSet> fcepws = new FcEnPrTr<ResultSet>();
    fcepws.setFctBlc(this.fctBlc);
    fcsenpr.add(fcepws);
    this.fctBlc.getFctDt().setFctsPrcEnt(fcsenpr);
    Set<IHlNmClSt> hldsBsEnPr = new LinkedHashSet<IHlNmClSt>();
    hldsBsEnPr.add(new HlAcEnPr());
    this.fctBlc.getFctDt().setHldsBsEnPr(hldsBsEnPr);
    HashSet<IFctPrc> fpas = new HashSet<IFctPrc>();
    FcPrNtAj<ResultSet> fctPrcNtrAjb = new FcPrNtAj<ResultSet>();
    fctPrcNtrAjb.setFctApp(this);
    fpas.add(fctPrcNtrAjb);
    FcPrNtAc<ResultSet> fctPrcNtrAc = new FcPrNtAc<ResultSet>();
    fctPrcNtrAc.setFctApp(this);
    fpas.add(fctPrcNtrAc);
    this.fctBlc.getFctDt().setFctsPrc(fpas);
    HashSet<IFctPrc> fpasad = new HashSet<IFctPrc>();
    FcPrNtAdAj<ResultSet> fctPrcNtrAj = new FcPrNtAdAj<ResultSet>();
    fctPrcNtrAj.setFctApp(this);
    fpasad.add(fctPrcNtrAj);
    FcPrNtAd<ResultSet> fctPrcNtrAd = new FcPrNtAd<ResultSet>();
    fctPrcNtrAd.setFctBlc(this.fctBlc);
    fpasad.add(fctPrcNtrAd);
    this.fctBlc.getFctDt().setFctsPrcAd(fpasad);
    Set<IFctPrcFl> fcspf = new HashSet<IFctPrcFl>();
    FcPrFlAc<ResultSet> fcpf = new FcPrFlAc<ResultSet>();
    fcpf.setFctBlc(this.fctBlc);
    fcspf.add(fcpf);
    this.fctBlc.getFctDt().setFctrsPrcFl(fcspf);
    Set<IFctCnToSt> fcsCnToSt = new HashSet<IFctCnToSt>();
    FcCnToStAi<ResultSet> fcnst = new FcCnToStAi<ResultSet>();
    fcnst.setFctBlc(this.fctBlc);
    fcsCnToSt.add(fcnst);
    this.fctBlc.getFctDt().setFcsCnToSt(fcsCnToSt);
    Set<IFcFlFdSt> fcsFlFdSt = new HashSet<IFcFlFdSt>();
    FcFlFdAi<ResultSet> ffdst = new FcFlFdAi<ResultSet>();
    ffdst.setFctBlc(this.fctBlc);
    fcsFlFdSt.add(ffdst);
    this.fctBlc.getFctDt().setFcsFlFdSt(fcsFlFdSt);
    Set<IHlNmClSt> hldsAdEnPr = new LinkedHashSet<IHlNmClSt>();
    hldsAdEnPr.add(new HlTrEnPr());
    this.fctBlc.getFctDt().setHldsAdEnPr(hldsAdEnPr);
  }

  /**
   * <p>Get bean in lazy mode (if bean is null then initialize it).</p>
   * @param pRqVs request scoped vars
   * @param pBnNm - bean name
   * @return Object - requested bean or exception if not found
   * @throws Exception - an exception
   */
  @Override
  public final Object laz(final Map<String, Object> pRqVs,
    final String pBnNm) throws Exception {
    return this.fctBlc.laz(pRqVs, pBnNm);
  }

  /**
   * <p>Releases memory.</p>
   * @param pRqVs request scoped vars
   * @throws Exception - an exception
   */
  @Override
  public final void release(final Map<String, Object> pRqVs) throws Exception {
    this.fctBlc.release(pRqVs);
  }


  /**
   * <p>Puts beans by external AUX factory.</p>
   * @param pRqVs request scoped vars
   * @param pBnNm - bean name
   * @param pBean - bean
   * @throws Exception - an exception, e.g. if bean exists
   **/
  @Override
  public final void put(final Map<String, Object> pRqVs,
    final String pBnNm, final Object pBean) throws Exception {
    this.fctBlc.put(pRqVs, pBnNm, pBean);
  }

  /**
   * <p>Gets main factory for setting configuration parameters.</p>
   * @return Object - requested bean
   */
  @Override
  public final FctBlc<ResultSet> getFctBlc() {
    return this.fctBlc;
  }

  /**
   * <p>Initializes factory.</p>
   * @param pRvs request scoped vars
   * @param pCtxAttrs context attributes
   * @throws Exception - an exception, e.g. if bean exists
   */
  @Override
  public final void init(final Map<String, Object> pRvs,
    final IAttrs pCtxAttrs) throws Exception {
    addAux();
    //creating/upgrading DB on start:
    Orm<ResultSet> orm = this.fctBlc.lazOrm(pRvs);
    orm.init(pRvs);
      //free memory:
    orm.getSetng().release();
    DataBaseLoginService srvDbl = (DataBaseLoginService) pCtxAttrs
      .getAttr("JDBCRealm");
    GetUsrCrd<ResultSet> srvCr = new GetUsrCrd<ResultSet>();
    @SuppressWarnings("unchecked")
    IRdb<ResultSet> rdb = (IRdb<ResultSet>)
      laz(pRvs, IRdb.class.getSimpleName());
    srvCr.setRdb(rdb);
    srvDbl.setSrvGetUserCredentials(srvCr);
    srvDbl.getUsers().clear();
    //crypto init:
    HpCrypt ch = (HpCrypt) laz(pRvs, IHpCrypt.class.getSimpleName());
    KeyStore ks = (KeyStore) pCtxAttrs.getAttr("ajettyKeystore");
    ch.setKeyStore(ks);
    String passw = (String) pCtxAttrs.getAttr("ksPassword");
    ch.setKsPassword(passw.toCharArray());
    Integer ajettyIn = (Integer) pCtxAttrs.getAttr("ajettyIn");
    ch.setAjettyIn(ajettyIn);
  }
}
