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

import java.util.HashSet;
import java.util.ArrayList;
import java.util.Date;

import org.beigesoft.mdl.IRecSet;
import org.beigesoft.rdb.IRdb;

/**
 * <p>Service that checks if database has any users credentials, if not
 * it creates the first user. It's also used for changing password.
 * This is for localhost only!</p>
 *
 * @param <RS> platform dependent RDBMS recordset
 * @author Yury Demidenko
 */
public class UsrPwd<RS> implements IUsrPwd {

  /**
   * <p>Database service.</p>
   **/
  private IRdb<RS> rdb;

  /**
   * <p>Is there any user cache.</p>
   **/
  private boolean isThereAnyUser = false;

  /**
   * <p>Checks is there any user.</p>
   * @return if there is any user
   * @throws Exception - an exception
   **/
  @Override
  public final boolean checkIsThereAnyUser() throws Exception {
    if (this.isThereAnyUser) {
      return true;
    }
    String query = "select USTMC.USR as USR, ROL from USTMC join USRLTMC"
      + " on USRLTMC.USR = USTMC.USR;";
    boolean result = false;
    IRecSet<RS> recordSet = null;
    try {
      this.rdb.setAcmt(false);
      this.rdb.setTrIsl(IRdb.TRRUC);
      this.rdb.begin();
      recordSet = getRdb().retRs(query);
      if (recordSet.first()) {
        this.isThereAnyUser = true;
        result = true;
      }
      this.rdb.commit();
    } catch (Exception ex) {
      this.rdb.rollBack();
      throw ex;
    } finally {
      this.rdb.release();
      if (recordSet != null) {
        recordSet.close();
      }
    }
    return result;
  }

  /**
   * <p>Add only user/password/role.</p>
   * @param pUserName User Name
   * @param pPwd User password
   * @param pRole User role
   * @throws Exception - an exception
   **/
  @Override
  public final void addUser(final String pUserName,
    final String pPwd, final String pRole) throws Exception {
    String query = "select USTMC.USR as USR, ROL from USTMC join USRLTMC"
      + " on USRLTMC.USR = USTMC.USR;";
    IRecSet<RS> recordSet = null;
    try {
      this.rdb.setAcmt(false);
      this.rdb.setTrIsl(IRdb.TRRUC);
      this.rdb.begin();
      recordSet = getRdb().retRs(query);
      if (recordSet.first()) {
        throw new Exception("There are already user credentials!");
      }
      String addUserQ =
        "insert into USTMC (USR, PWD, VER) values ('" + pUserName
          + "', '" + pPwd + "', 1);";
      String addUserRoleQ =
        "insert into USRLTMC (USR, ROL, VER) values ('" + pUserName
          + "', '" + pRole + "', 1);";
      this.rdb.exec(addUserQ);
      this.rdb.exec(addUserRoleQ);
      this.rdb.commit();
      this.isThereAnyUser = true;
    } catch (Exception ex) {
      this.rdb.rollBack();
      throw ex;
    } finally {
      this.rdb.release();
      if (recordSet != null) {
        recordSet.close();
      }
    }
  }


  /**
   * <p>Change user password.</p>
   * @param pUserName User Name
   * @param pPwd User password
   * @param pPwdOld User password old
   * @return if changed (if there is user with old password)
   * @throws Exception - an exception
   **/
  @Override
  public final boolean changePwd(final String pUserName,
    final String pPwd, final String pPwdOld) throws Exception {
    String query = "select USTMC.USR from USTMC where USR='"
      + pUserName + "' and PWD='" + pPwdOld + "';";
    IRecSet<RS> recordSet = null;
    try {
      this.rdb.setAcmt(false);
      this.rdb.setTrIsl(IRdb.TRRUC);
      this.rdb.begin();
      recordSet = getRdb().retRs(query);
      if (!recordSet.first()) {
        return false;
      }
      String chnPwd = "update USTMC set VER=" + new Date().getTime() + ", PWD='"
        + pPwd + "' where USR='" + pUserName + "';";
      this.rdb.exec(chnPwd);
      this.rdb.commit();
      return true;
    } catch (Exception ex) {
      this.rdb.rollBack();
      throw ex;
    } finally {
      this.rdb.release();
      if (recordSet != null) {
        recordSet.close();
      }
    }
  }

  /**
   * <p>Check if password strong.
   * It implements logic:
   * At least 15 letters and digits!
   * 60% of them must be different!
   * At least 50% of them must be letters!
   * At least 3 of them must be digits!
   * No containing qwerty, 12345, admin, user etc!</p>
   * @param pPwdord Password
   * @return NULL if strong, otherwise message.
   **/
  @Override
  public final String isPasswordStrong(final char[] pPwdord) {
    if (pPwdord == null || pPwdord.length < 15) {
      return "Password15";
    }
    String passw = new String(pPwdord).toLowerCase();
    if (passw.contains("qwert") || passw.contains("qwaszx")
      || passw.contains("qweasd") || passw.contains("qazwsx")
        || passw.contains("wsxedc") || passw.contains("wqsaxz")
          || passw.contains("ewqdsa") || passw.contains("zaqxsw")
            || passw.contains("xswzaq") || passw.contains("qscwdv")
              || passw.contains("csqvdw") || passw.contains("zaxqsc")
                || passw.contains("qscax") || passw.contains("csqxa")
                  || passw.contains("trewq") || passw.contains("asdfg")
                || passw.contains("zxcvb") || passw.contains("bvcxz")
              || passw.contains("gfdsa")) {
      return "noQwerty";
    } else if (passw.contains("raccooneatstone")
      || passw.contains("nraccooteaeston")) {
      return "noDemoPassw";
    } else if (passw.contains("2345") || passw.contains("admin")
      || passw.contains("user") || passw.contains("5432")
        || passw.contains("5678") || passw.contains("9876")
          || passw.contains("password")) {
      return "noAdmin12345";
    }
    HashSet<Character> chars = new HashSet<Character>();
    ArrayList<Character> digits = new ArrayList<Character>();
    ArrayList<Character> letters = new ArrayList<Character>();
    for (char ch : pPwdord) {
      if (!Character.isLetterOrDigit(ch)) {
        return "letterOrDig";
      }
      if (Character.isDigit(ch)) {
        digits.add(ch);
      } else {
        letters.add(ch);
      }
      chars.add(ch);
    }
    double allLn = pPwdord.length;
    double lettersLn = letters.size();
    double distinctLn = chars.size();
    if (lettersLn / allLn < 0.49999999999) {
      return "lettersAtLeast50pr";
    }
    if (distinctLn / allLn < 0.59999999999) {
      return "distinct60pr";
    }
    if (digits.size() < 3) {
      return "atLeast3digits";
    }
    return null;
  }

  /**
   * <p>Getter for isThereAnyUser.</p>
   * @return boolean
   **/
  @Override
  public final boolean getIsThereAnyUser() {
    return this.isThereAnyUser;
  }

  /**
   * <p>Setter for isThereAnyUser.</p>
   * @param pIsThereAnyUser reference
   **/
  @Override
  public final void setIsThereAnyUser(final boolean pIsThereAnyUser) {
    this.isThereAnyUser = pIsThereAnyUser;
  }

  /**
   * <p>Geter for rdb.</p>
   * @return IRdb
   **/
  public final IRdb<RS> getRdb() {
    return this.rdb;
  }

  /**
   * <p>Setter for rdb.</p>
   * @param pRdb reference
   **/
  public final void setRdb(final IRdb<RS> pRdb) {
    this.rdb = pRdb;
  }
}
