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

/**
 * <p>Service that checks if database has any users credentials, if not
 * it creates the first user. It's also used for changing password.
 * This is for localhost only!</p>
 * @author Yury Demidenko
 */
public interface IUsrPwd {

  /**
   * <p>Checks is there any user.</p>
   * @return if there is any user
   * @throws Exception - an exception
   **/
  boolean checkIsThereAnyUser() throws Exception;

  /**
   * <p>Add only user/password/role.</p>
   * @param pUserName User Name
   * @param pPassw User password
   * @param pRole User role
   * @throws Exception - an exception
   **/
  void addUser(String pUserName, String pPassw,
    String pRole) throws Exception;

  /**
   * <p>Change user password.</p>
   * @param pUserName User Name
   * @param pPassw User password
   * @param pPasswOld User password old
   * @return if changed (if there is user with old password)
   * @throws Exception - an exception
   **/
  boolean changePwd(String pUserName, String pPassw,
    String pPasswOld) throws Exception;

  /**
   * <p>Check if password strong.
   * It implements logic:
   * At least 15 letters and digits!
   * 60% of them must be different!
   * At least 50% of them must be letters!
   * At least 3 of them must be digits!
   * No containing qwerty, 12345, admin, user etc!</p>
   * @param pPassword Password
   * @return NULL if strong, otherwise message.
   **/
  String isPasswordStrong(char[] pPassword);

  /**
   * <p>Getter for isThereAnyUser.</p>
   * @return boolean
   **/
  boolean getIsThereAnyUser();

  /**
   * <p>Setter for isThereAnyUser.</p>
   * @param pIsThereAnyUser reference
   **/
  void setIsThereAnyUser(boolean pIsThereAnyUser);
}
