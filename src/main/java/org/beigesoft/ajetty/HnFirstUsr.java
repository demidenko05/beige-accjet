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

import org.beigesoft.mdl.IReqDt;
import org.beigesoft.hnd.IHndRq;

/**
 * <p>Non-secure request first user checker/adder.</p>
 *
 * @author Yury Demidenko
 */
public class HnFirstUsr implements IHndRq {

  /**
   * <p>User-password service.</p>
   **/
  private IUsrPwd usrPwd;

  /**
   * <p>Handles request.</p>
   * @param pRvs Request scoped variables
   * @param pRqDt Request Data
   * @throws Exception - an exception
   */
  @Override
  public final void handle(final Map<String, Object> pRvs,
    final IReqDt pRqDt) throws Exception {
    if (!this.usrPwd.checkIsThereAnyUser()) {
      String usr = pRqDt.getParam("usr");
      String errMsg = null;
      if (usr != null) {
        String pwd = pRqDt.getParam("pwd");
        if (pwd.contains(usr)) {
          errMsg = "passwMustNoContainName";
        } else {
          String pwdc = pRqDt.getParam("pwdc");
          if (!pwd.equals(pwdc)) {
            errMsg = "repeatPasswNotMatch";
          } else {
            errMsg = this.usrPwd.isPasswordStrong(pwd.toCharArray());
            if (errMsg == null) {
              this.usrPwd.addUser(usr, pwd, "admin");
            }
          }
        }
      } else { // just form add first user:
        pRqDt.setAttr("rnd", "afu");
      }
      if (errMsg != null) {
        pRqDt.setAttr("rnd", "afu");
        pRqDt.setAttr("errMsg", errMsg);
      }
    }
  }

  //Simple getters and setters:
  /**
   * <p>Getter for usrPwd.</p>
   * @return IUsrPwd
   **/
  public final IUsrPwd getUsrPwd() {
    return this.usrPwd;
  }

  /**
   * <p>Setter for usrPwd.</p>
   * @param pUsrPwd reference
   **/
  public final void setUsrPwd(final IUsrPwd pUsrPwd) {
    this.usrPwd = pUsrPwd;
  }
}
