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

package org.beigesoft.ajetty;

import java.util.Map;
import java.util.HashMap;
import java.io.File;

import org.beigesoft.fct.IFctPrc;
import org.beigesoft.fct.IFctAsm;
import org.beigesoft.prc.IPrc;

/**
 * <p>Additional factory of processors for admin,
 * secure non-transactional requests.</p>
 *
 * @param <RS> platform dependent record set type
 * @author Yury Demidenko
 */
public class FcPrNtAdAj<RS> implements IFctPrc {

  /**
   * <p>Main factory.</p>
   **/
  private IFctAsm<RS> fctApp;

  //requested data:
  /**
   * <p>Processors map.</p>
   **/
  private final Map<String, IPrc> procs = new HashMap<String, IPrc>();

  /**
   * <p>Get processor in lazy mode (if bean is null then initialize it).</p>
   * @param pRvs request scoped vars
   * @param pPrNm - filler name
   * @return requested processor
   * @throws Exception - an exception
   */
  public final IPrc laz(final Map<String, Object> pRvs, //NOPMD
    final String pPrNm) throws Exception {
    IPrc rz = this.procs.get(pPrNm);
    if (rz == null) {
      synchronized (this) {
        rz = this.procs.get(pPrNm);
        if (rz == null && PrcMngDb.class.getSimpleName().equals(pPrNm)) {
          rz = crPuPrcMngDb(pRvs);
        }
      }
    }
    return rz;
  }

  /**
   * <p>Create and put into the Map PrcMngDb.</p>
   * @param pRvs request scoped vars
   * @return PrcMngDb
   * @throws Exception - an exception
   */
  private PrcMngDb crPuPrcMngDb(
    final Map<String, Object> pRvs) throws Exception {
    PrcMngDb rz = new PrcMngDb();
    IHpCrypt ch = (IHpCrypt) this.fctApp
      .laz(pRvs, IHpCrypt.class.getSimpleName());
    rz.setHpCrypt(ch);
    MngDb<RS> mngDb = new MngDb<RS>();
    mngDb.setFctApp(this.fctApp);
    mngDb.setHpCrypt(ch);
    File webAppDir = new File(this.fctApp.getFctBlc().getFctDt().getAppPth());
    mngDb.setLogDir(webAppDir);
    mngDb.setDbDir(this.fctApp.getFctBlc().getFctDt().getAppPth());
    mngDb.setDbPref("jdbc:sqlite:" + this.fctApp.getFctBlc().getFctDt()
      .getAppPth() + File.separator);
    File bkDir = new File(webAppDir.getParent() + File.separator + "backup");
    if (!bkDir.exists() && !bkDir.mkdir()) {
      throw new Exception("Can't create directory: " + bkDir);
    }
    mngDb.setBackupDir(bkDir.getPath());
    rz.setMngDb(mngDb);
    rz.setLog(this.fctApp.getFctBlc().lazLogStd(pRvs));
    this.procs.put(PrcMngDb.class.getSimpleName(), rz);
    this.fctApp.getFctBlc().lazLogStd(pRvs).info(pRvs, getClass(), PrcMngDb
      .class.getSimpleName() + " has been created.");
    return rz;
  }

  //Simple getters and setters:
  /**
   * <p>Getter for fctApp.</p>
   * @return IFctAsm<RS>
   **/
  public final synchronized IFctAsm<RS> getFctApp() {
    return this.fctApp;
  }

  /**
   * <p>Setter for fctApp.</p>
   * @param pFctApp reference
   **/
  public final synchronized void setFctApp(final IFctAsm<RS> pFctApp) {
    this.fctApp = pFctApp;
  }
}
