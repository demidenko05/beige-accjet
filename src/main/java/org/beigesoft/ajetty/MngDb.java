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

import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.beigesoft.exc.ExcCode;
import org.beigesoft.mdl.IReqDt;
import org.beigesoft.mdl.RqCtxAttr;
import org.beigesoft.fct.IFctAsm;

/**
 * <p>Database manager with encryption.
 * Additional it encrypt/decrypt all log files.</p>
 *
 * @param <RS> platform dependent RDBMS recordset
 * @author Yury Demidenko
 */
public class MngDb<RS> implements IMngDb {

  /**
   * <p>Log files folder (inner).</p>
   **/
  private File logDir;

  /**
   * <p>Databases folder (inner).</p>
   **/
  private String dbDir;

  /**
   * <p>Encrypted backup databases folder (public).</p>
   **/
  private String backupDir;

  /**
   * <p>App beans factory.</p>
   **/
  private IFctAsm<RS> fctApp;

  /**
   * <p>Crypto service.</p>
   **/
  private IHpCrypt hpCrypt;

  /**
   * <p>Database prefix - for JDBC is "jdbc:sqlite:[db-dir]/".</p>
   **/
  private String dbPref = "";

  /**
   * <p>Lists databases.</p>
   * @param pRvs request scoped vars
   * @return List<String> list of database files.
   * @throws Exception - an exception
   **/
  @Override
  public final List<String> retLst(
    final Map<String, Object> pRvs) throws Exception {
    List<String> result = new ArrayList<String>();
    File dbDr = new File(this.dbDir);
    if (dbDr.exists() && dbDr.isDirectory()) {
      String[] files = dbDr.list();
      if (files != null) {
        for (String flNm : files) {
          if (flNm.endsWith(".sqlite")) {
            result.add(flNm.replace(".sqlite", ""));
          }
        }
      }
    } else {
      throw new ExcCode(ExcCode.WRCN,
        "DB directory not found: " + this.dbDir);
    }
    return result;
  }

  /**
   * <p>Lists backup databases.</p>
   * @param pRvs request scoped vars
   * @return List<String> list of backup database files.
   * @throws Exception - an exception
   **/
  @Override
  public final List<String> retBckLst(
    final Map<String, Object> pRvs) throws Exception {
    List<String> result = new ArrayList<String>();
    File dbDr = new File(this.backupDir);
    if (dbDr.exists() && dbDr.isDirectory()) {
      String[] files = dbDr.list();
      if (files != null) {
        for (String flNm : files) {
          if (flNm.endsWith(".sqlten")) {
            result.add(flNm.replace(".sqlten", ""));
          }
        }
      }
    } else {
      throw new ExcCode(ExcCode.WRCN,
        "Backup directory not found: " + this.backupDir);
    }
    return result;
  }

  /**
   * <p>Retrieves current DB name.</p>
   * @param pRvs request scoped vars
   * @return String DB name.
   * @throws Exception - an exception
   **/
  @Override
  public final String retCurDbNm(
    final Map<String, Object> pRvs) throws Exception {
    return this.fctApp.getFctBlc().getFctDt().getDbUrl();
  }

  /**
   * <p>Creates new database.</p>
   * @param pRvs request scoped vars
   * @param pDbNm database name without extension
   * @param pDbId database ID
   * @throws Exception - an exception
   **/
  @Override
  public final void createDb(final Map<String, Object> pRvs,
    final String pDbNm, final int pDbId) throws Exception {
    String dbNm = this.dbPref + pDbNm + ".sqlite";
    synchronized (this.fctApp) {
      if (!this.fctApp.getFctBlc().getFctDt().getDbUrl().equals(dbNm)) {
        this.fctApp.getFctBlc().getFctDt().setDbUrl(dbNm);
        this.fctApp.getFctBlc().getFctDt().setNewDbId(pDbId);
        this.fctApp.getFctBlc().release(pRvs);
        IReqDt rqDt = (IReqDt) pRvs.get("rqDt");
        this.fctApp.init(pRvs, new RqCtxAttr(rqDt));
      }
    }
  }

  /**
   * <p>Changes database.</p>
   * @param pRvs request scoped vars
   * @param pDbNm database name without extension
   * @throws Exception - an exception
   **/
  @Override
  public final void changeDb(final Map<String, Object> pRvs,
    final String pDbNm) throws Exception {
    String dbNm = this.dbPref + pDbNm + ".sqlite";
    synchronized (this.fctApp) {
      if (!this.fctApp.getFctBlc().getFctDt().getDbUrl().equals(dbNm)) {
        this.fctApp.getFctBlc().getFctDt().setDbUrl(dbNm);
        this.fctApp.getFctBlc().release(pRvs);
        IReqDt rqDt = (IReqDt) pRvs.get("rqDt");
        this.fctApp.init(pRvs, new RqCtxAttr(rqDt));
      }
    }
  }

  /**
   * <p>Deletes database in inner DB folder, NOT current DB!</p>
   * @param pRvs request scoped vars
   * @param pDbNm database name without extension
   * @throws Exception - an exception
   **/
  @Override
  public final void deleteDb(final Map<String, Object> pRvs,
    final String pDbNm) throws Exception {
    String dbNm = this.dbPref + pDbNm + ".sqlite";
    if (!dbNm.equals(this.fctApp.getFctBlc().getFctDt().getDbUrl())) {
      File dbFile = new File(this.dbDir + File.separator + pDbNm + ".sqlite");
      if (dbFile.exists() && !dbFile.delete()) {
        throw new ExcCode(ExcCode.WR, "Can't delete file: " + dbFile);
      }
    }
  }

  /**
   * <p>Backups database.</p>
   * @param pRvs request scoped vars
   * @param pDbNm database name without extension
   * @throws Exception - an exception
   **/
  @Override
  public final void backupDb(final Map<String, Object> pRvs,
    final String pDbNm) throws Exception {
    String dbPath = this.dbDir + File.separator + pDbNm + ".sqlite";
    File dbFile = new File(dbPath);
    if (dbFile.exists()) {
      String encPath = this.backupDir + File.separator + pDbNm + ".sqlten";
      File dbBkFile = new File(encPath);
      if (dbBkFile.exists()) {
        Long time = new Date().getTime();
        encPath = this.backupDir + File.separator + pDbNm + time + ".sqlten";
      }
      this.hpCrypt.encryptFile(dbPath, encPath);
    }
  }

  /**
   * <p>Restores database from backup.</p>
   * @param pRvs request scoped vars
   * @param pDbNm database name without extension
   * @throws Exception - an exception
   **/
  @Override
  public final void restoreDb(final Map<String, Object> pRvs,
    final String pDbNm) throws Exception {
    String encPath = this.backupDir + File.separator + pDbNm + ".sqlten";
    File dbBkFile = new File(encPath);
    if (dbBkFile.exists()) {
      this.hpCrypt.decryptFile(encPath,
        this.dbDir + File.separator + pDbNm + ".sqlite");
    }
  }

  /**
   * <p>Encrypts log files into backup directory.</p>
   * @param pRvs request scoped vars
   * @throws Exception - an exception
   **/
  @Override
  public final void encryptLogs(
    final Map<String, Object> pRvs) throws Exception {
    if (this.logDir != null && this.logDir.exists() && !this.logDir.isFile()) {
      File[] files = this.logDir.listFiles();
      if (files != null) {
        for (File fl : files) {
          if (fl.getName().endsWith(".log")) {
            this.hpCrypt.encryptFile(fl.getPath(),
              this.backupDir + File.separator + fl.getName() + "en");
          }
        }
      }
    }
  }

  /**
   * <p>Decrypts log files in backup directory.</p>
   * @param pRvs request scoped vars
   * @throws Exception - an exception
   **/
  @Override
  public final void decryptLogs(
    final Map<String, Object> pRvs) throws Exception {
    File bkDir = new File(this.backupDir);
    if (bkDir.exists() && !bkDir.isFile()) {
      File[] files = bkDir.listFiles();
      if (files != null) {
        for (File fl : files) {
          if (fl.getName().endsWith(".logen")) {
            this.hpCrypt.decryptFile(fl.getPath(), this.backupDir
              + File.separator + fl.getName().replace(".logen", ".log"));
          }
        }
      }
    }
  }

  /**
   * <p>Getter for backupDir.</p>
   * @return String
   **/
  @Override
  public final String getBackupDir() {
    return this.backupDir;
  }

  /**
   * <p>Setter for backupDir.</p>
   * @param pBackupDir reference
   **/
  @Override
  public final void setBackupDir(final String pBackupDir) {
    this.backupDir = pBackupDir;
  }

  //Simple getters and setters:
  /**
   * <p>Getter for logDir.</p>
   * @return File
   **/
  public final File getLogDir() {
    return this.logDir;
  }

  /**
   * <p>Setter for logDir.</p>
   * @param pLogDir reference
   **/
  public final void setLogDir(final File pLogDir) {
    this.logDir = pLogDir;
  }

  /**
   * <p>Getter for dbDir.</p>
   * @return String
   **/
  public final String getDbDir() {
    return this.dbDir;
  }

  /**
   * <p>Setter for dbDir.</p>
   * @param pDbDir reference
   **/
  public final void setDbDir(final String pDbDir) {
    this.dbDir = pDbDir;
  }

  /**
   * <p>Getter for fctApp.</p>
   * @return IFctAsm<RS>
   **/
  public final IFctAsm<RS> getFctApp() {
    return this.fctApp;
  }

  /**
   * <p>Setter for fctApp.</p>
   * @param pFctApp reference
   **/
  public final void setFctApp(final IFctAsm<RS> pFctApp) {
    this.fctApp = pFctApp;
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

  /**
   * <p>Getter for dbPref.</p>
   * @return String
   **/
  public final String getDbPref() {
    return this.dbPref;
  }

  /**
   * <p>Setter for dbPref.</p>
   * @param pDbPref reference
   **/
  public final void setDbPref(final String pDbPref) {
    this.dbPref = pDbPref;
  }
}
