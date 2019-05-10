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

import java.util.List;
import java.util.Map;

/**
 * <p>Database manager for file-based RDBMS (SQLite/H2).
 * It can create and change current database (file),
 * backup DB to global storage and restore from it,
 * also can delete database. Additional it encrypt/decrypt all log files.</p>
 *
 * @author Yury Demidenko
 */
public interface IMngDb {

  /**
   * <p>Lists databases.</p>
   * @param pRvs request scoped vars
   * @return List<String> list of database files.
   * @throws Exception - an exception
   **/
  List<String> retLst(Map<String, Object> pRvs) throws Exception;

  /**
   * <p>Retrieves current DB name.</p>
   * @param pRvs request scoped vars
   * @return String DB name.
   * @throws Exception - an exception
   **/
  String retCurDbNm(Map<String, Object> pRvs) throws Exception;

  /**
   * <p>Creates new database.</p>
   * @param pRvs request scoped vars
   * @param pDbNm database name without extension
   * @param pDbId database ID
   * @throws Exception - an exception
   **/
  void createDb(Map<String, Object> pRvs, String pDbNm,
    int pDbId) throws Exception;

  /**
   * <p>Changes database.</p>
   * @param pRvs request scoped vars
   * @param pDbNm database name without extension
   * @throws Exception - an exception
   **/
  void changeDb(Map<String, Object> pRvs, String pDbNm) throws Exception;

  /**
   * <p>Lists backup databases.</p>
   * @param pRvs request scoped vars
   * @return List<String> list of backup database files.
   * @throws Exception - an exception
   **/
  List<String> retBckLst(Map<String, Object> pRvs) throws Exception;

  /**
   * <p>Deletes database.</p>
   * @param pRvs request scoped vars
   * @param pDbNm database name without extension
   * @throws Exception - an exception
   **/
  void deleteDb(Map<String, Object> pRvs, String pDbNm) throws Exception;

  /**
   * <p>Backups database.</p>
   * @param pRvs request scoped vars
   * @param pDbNm database name without extension
   * @throws Exception - an exception
   **/
  void backupDb(Map<String, Object> pRvs, String pDbNm) throws Exception;

  /**
   * <p>Restores database.</p>
   * @param pRvs request scoped vars
   * @param pDbNm database name without extension
   * @throws Exception - an exception
   **/
  void restoreDb(Map<String, Object> pRvs, String pDbNm) throws Exception;

  /**
   * <p>Encrypts log files into backup directory.</p>
   * @param pRvs request scoped vars
   * @throws Exception - an exception
   **/
  void encryptLogs(Map<String, Object> pRvs) throws Exception;

  /**
   * <p>Decrypts log files in backup directory.</p>
   * @param pRvs request scoped vars
   * @throws Exception - an exception
   **/
  void decryptLogs(Map<String, Object> pRvs) throws Exception;

  /**
   * <p>Getter for backupDir.</p>
   * @return String
   **/
  String getBackupDir();

  /**
   * <p>Setter for backupDir.</p>
   * @param pBackupDir reference
   **/
  void setBackupDir(String pBackupDir);
}
