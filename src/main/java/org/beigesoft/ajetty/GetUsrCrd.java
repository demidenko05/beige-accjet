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

import java.util.ArrayList;

import org.beigesoft.mdl.IRecSet;
import org.beigesoft.rdb.IRdb;

/**
 * <p>Service that get user credentials (from database).</p>
 * @param <RS> platform dependent RDBMS recordset
 * @author Yury Demidenko
 */
public class GetUsrCrd<RS> implements ISrvGetUserCredentials {

  /**
   * <p>Database service.</p>
   **/
  private IRdb<RS> rdb;

  /**
   * <p>Retrieve User Credentials.</p>
   * @param pUserName User Name
   * @return User Credentials
   * @throws Exception - an exception
   **/
  @Override
  public final UserCredentials retrieveUserCredentials(
    final String pUserName) throws Exception {
    String query = "select USTMC.USR as USR, PWD, ROL"
      + " from USTMC join USRLTMC on USRLTMC.USR ="
        + " USTMC.USR where USTMC.USR = '" + pUserName + "';";
    UserCredentials result = null;
    IRecSet<RS> recSet = null;
    try {
      this.rdb.setAcmt(false);
      this.rdb.setTrIsl(IRdb.TRRUC);
      this.rdb.begin();
      recSet = getRdb().retRs(query);
      if (recSet.first()) {
        ArrayList<String> roles = new ArrayList<String>();
        do {
          if (result == null) {
            result = new UserCredentials();
            result.setUserName(recSet.getStr("USR"));
            result.setUserPassword(recSet.getStr("PWD"));
          }
          roles.add(recSet.getStr("ROL"));
        } while (recSet.next());
        result.setUserRoles(roles.toArray(new String[roles.size()]));
      }
      this.rdb.commit();
    } catch (Exception ex) {
      this.rdb.rollBack();
      throw ex;
    } finally {
      this.rdb.release();
      if (recSet != null) {
        recSet.close();
      }
    }
    return result;
  }

  /**
   * <p>Retrieve all Users Credentials.</p>
   * @return Users Credentials
   * @throws Exception - an exception
   **/
  @Override
  public final UserCredentials[] retrieveUsersCredentials() throws Exception {
    String query = "select USTMC.USR as USR, PWD, ROL from USTMC join USRLTMC"
      + " on USRLTMC.USR = USTMC.USR;";
    ArrayList<UserCredentials> result = null;
    IRecSet<RS> recSet = null;
    try {
      this.rdb.setAcmt(false);
      this.rdb.setTrIsl(IRdb.TRRUC);
      this.rdb.begin();
      recSet = getRdb().retRs(query);
      if (recSet != null && recSet.first()) {
        result = new ArrayList<UserCredentials>();
        UserCredentials uc = null;
        ArrayList<String> roles = new ArrayList<String>();
        do {
          String currUser = recSet.getStr("USR");
          if (uc == null) {
            uc = new UserCredentials();
            result.add(uc);
            uc.setUserName(currUser);
            uc.setUserPassword(recSet.getStr("PWD"));
          } else if (!currUser.equals(uc.getUserName())) {
            uc.setUserRoles(roles.toArray(new String[roles.size()]));
            roles.clear();
            uc = new UserCredentials();
            result.add(uc);
            uc.setUserName(currUser);
            uc.setUserPassword(recSet.getStr("PWD"));
          }
          roles.add(recSet.getStr("ROL"));
        } while (recSet.next());
        uc.setUserRoles(roles.toArray(new String[roles.size()]));
      }
      this.rdb.commit();
    } catch (Exception ex) {
      this.rdb.rollBack();
      throw ex;
    } finally {
      this.rdb.release();
      if (recSet != null) {
        recSet.close();
      }
    }
    if (result == null) {
      return null;
    }
    return result.toArray(new UserCredentials[result.size()]);
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
