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
import java.util.List;

import org.beigesoft.mdl.IReqDt;
import org.beigesoft.log.ILog;
import org.beigesoft.prc.IPrc;

/**
 * <p>Manager databases processor.</p>
 *
 * @author Yury Demidenko
 */
public class PrcMngDb implements IPrc {

  /**
   * <p>HEX letters for encoding.<p>
   **/
  private final String[] ecncodingLetters = {"0", "1", "2", "3", "4", "5",
      "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

  /**
   * <p>Logger.</p>
   **/
  private ILog log;

  /**
   * <p>DB manager.</p>
   **/
  private IMngDb mngDb;

  /**
   * <p>Crypto helper.</p>
   **/
  private IHpCrypt hpCrypt;

  /**
   * <p>Process request.</p>
   * @param pRvs request scoped vars
   * @param pRqDt Request Data
   * @throws Exception - an exception
   **/
  @Override
  public final void process(final Map<String, Object> pRvs,
    final IReqDt pRqDt) throws Exception {
    String act = pRqDt.getParam("act");
    String dbNm = pRqDt.getParam("dbNm");
    if (dbNm != null && dbNm.length() > 2) {
      if ("change".equals(act)) {
        this.mngDb.changeDb(pRvs, dbNm);
        pRqDt.setAttr("srvlRd", "../");
        pRqDt.setAttr("rnd", "idx");
      } else if ("delete".equals(act)) {
        this.mngDb.deleteDb(pRvs, dbNm);
      } else if ("backup".equals(act)) {
        this.mngDb.backupDb(pRvs, dbNm);
      } else if ("restore".equals(act)) {
        this.mngDb.restoreDb(pRvs, dbNm);
      } else if ("create".equals(act)) {
        String dbIdStr = pRqDt.getParam("dbId");
        int dbId = Integer.parseInt(dbIdStr);
        this.mngDb.createDb(pRvs, dbNm, dbId);
      }
    } else if ("encryptLogs".equals(act)) {
        this.mngDb.encryptLogs(pRvs);
    } else if ("decryptLogs".equals(act)) {
        this.mngDb.decryptLogs(pRvs);
    }
    if (this.hpCrypt.lazOurPublicKey() != null) {
      byte[] ourPublicKey = this.hpCrypt.lazOurPublicKey().getEncoded();
      byte[] ourPkSha1 = this.hpCrypt.calcSha1(ourPublicKey);
      String ourPublicKeyStr = toHexString(ourPkSha1);
      pRqDt.setAttr("ourPublicKeyStr", ourPublicKeyStr);
    }
    if (this.hpCrypt.lazPublicKeyAnotherAjetty() != null) {
      byte[] foreignPublicKey = this.hpCrypt.lazPublicKeyAnotherAjetty()
        .getEncoded();
      byte[] forPkSha1 = this.hpCrypt.calcSha1(foreignPublicKey);
      String foreignPublicKeyStr = toHexString(forPkSha1);
      pRqDt.setAttr("foreignPublicKeyStr", foreignPublicKeyStr);
    }
    List<String> databases = this.mngDb.retLst(pRvs);
    List<String> bkDatabases = this.mngDb.retBckLst(pRvs);
    pRqDt.setAttr("databases", databases);
    pRqDt.setAttr("bkDatabases", bkDatabases);
    pRqDt.setAttr("backupDir", this.mngDb.getBackupDir());
    pRqDt.setAttr("currDb", this.mngDb.retCurDbNm(pRvs));
    pRqDt.setAttr("rnd", "mndb");
  }

  //Utils:
  /**
   * <p>Encoding bytes to hex string.</p>
   * @param pSource bytes
   * @return encoded string
   **/
  public final String toHexString(final byte[] pSource) {
    StringBuffer sb = new StringBuffer();
    for (byte b : pSource) {
      int bi = b;
      bi &= 0x000000FF;
      sb.append(ecncodingLetters[(bi >> 4) & 0x0F]);
      sb.append(ecncodingLetters[bi & 0x0F]);
      sb.append(" ");
    }
    return sb.toString();
  }

  //Simple getters and setters:
  /**
   * <p>Getter for log.</p>
   * @return ILog
   **/
  public final ILog getLog() {
    return this.log;
  }

  /**
   * <p>Setter for log.</p>
   * @param pLog reference
   **/
  public final void setLog(final ILog pLog) {
    this.log = pLog;
  }

  /**
   * <p>Getter for mngDb.</p>
   * @return IMngDb
   **/
  public final IMngDb getMngDb() {
    return this.mngDb;
  }

  /**
   * <p>Setter for mngDb.</p>
   * @param pMngDb reference
   **/
  public final void setMngDb(final IMngDb pMngDb) {
    this.mngDb = pMngDb;
  }

  /**
   * <p>Getter for hpCrypt.</p>
   * @return IHpCrypt
   **/
  public final IHpCrypt getHpCrypt() {
    return this.hpCrypt;
  }

  /**
   * <p>Setter for hpCrypt.</p>
   * @param pHpCrypt reference
   **/
  public final void setHpCrypt(final IHpCrypt pHpCrypt) {
    this.hpCrypt = pHpCrypt;
  }
}
