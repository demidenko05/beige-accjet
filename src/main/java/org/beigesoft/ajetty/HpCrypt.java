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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.SecureRandom;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.CipherOutputStream;

import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.Digest;

/**
 * <p>Service that loads/holds keystore, performs encryption/decryption.
 *  This is for localhost only!</p>
 * @author Yury Demidenko
 */
public class HpCrypt implements IHpCrypt {

  /**
   * <p>Keystore.</p>
   **/
  private KeyStore keyStore;

  /**
   * <p>Keystore folder path.</p>
   **/
  private String ksDirPath;

  /**
   * <p>Keystore password.</p>
   **/
  private char[] ksPassword;

  /**
   * <p>Our A-Jetty number.</p>
   **/
  private Integer ajettyIn;

  /**
   * <p>Crypto-provider name.</p>
   **/
  private String cryptoProvider = "BC";

  /**
   * <p>Public key of our A-Jetty for file exchange.</p>
   **/
  private PublicKey ourPublicKey;

  /**
   * <p>Private key of our A-Jetty for file exchange.</p>
   **/
  private PrivateKey ourPrivateKey;

  /**
   * <p>Public key (of another A-Jetty) folder (external).</p>
   **/
  private String publicKeyDir;

  /**
   * <p>Public key of another A-Jetty.</p>
   **/
  private PublicKey publicKeyAnotherAjetty;

  /**
   * <p>Calculate SHA1 for given bytes (public key).</p>
   * @param pPublicKey bytes
   * @return SHA1 bytes array
   * @throws Exception an Exception
   */
  @Override
  public final byte[] calcSha1(final byte[] pPublicKey) throws Exception {
    Digest sha1 = new SHA1Digest();
    sha1.update(pPublicKey, 0, pPublicKey.length);
    byte[] digest = new byte[sha1.getDigestSize()];
    sha1.doFinal(digest, 0);
    return digest;
  }

  /**
   * <p>Lazy load keystore
   * (for farther SSL trusted certificate initialization).</p>
   * @return Keystore
   * @throws Exception - an exception
   **/
  @Override
  public final KeyStore lazKeystore() throws Exception {
    if (this.keyStore == null && this.ksPassword != null) {
      File ksDir = new File(this.ksDirPath);
      if (ksDir.exists() && !ksDir.isFile()) {
        File[] lstFl = ksDir.listFiles();
        if (lstFl != null && lstFl.length == 1) {
          String ajettyInStr = lstFl[0].getName()
            .replace("ajettykeystore.", "");
          this.ajettyIn = Integer.parseInt(ajettyInStr);
          this.keyStore = KeyStore.getInstance("PKCS12", this.cryptoProvider);
          FileInputStream fis = null;
          try {
            fis = new FileInputStream(lstFl[0]);
            this.keyStore.load(fis, this.ksPassword);
          } finally {
            if (fis != null) {
              try {
                fis.close();
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
          }
        }
      }
    }
    return this.keyStore;
  }

  /**
   * <p>Encrypts file.</p>
   * @param pFilePath File Path
   * @param pEncryptedPath Encrypted Path
   * @throws Exception - an exception
   **/
  @Override
  public final void encryptFile(final String pFilePath,
    final String pEncryptedPath) throws Exception {
    KeyGenerator keyGenAes = KeyGenerator.
      getInstance("AES", this.cryptoProvider);
    keyGenAes.init(256, new SecureRandom());
    SecretKey sskAes = keyGenAes.generateKey();
    Cipher cipherRsa = Cipher.getInstance("RSA", this.cryptoProvider);
    cipherRsa.init(Cipher.ENCRYPT_MODE, lazPublicKeyAnotherAjetty());
    byte[] encryptedSsk = cipherRsa.doFinal(sskAes.getEncoded());
    Signature sigMk = Signature.getInstance("SHA256withRSA");
    sigMk.initSign(lazOurPrivateKey(), new SecureRandom());
    sigMk.update(encryptedSsk);
    byte[] sigSsk = sigMk.sign();
    Cipher cipherAes = Cipher.getInstance("AES/ECB/PKCS7Padding",
      this.cryptoProvider);
    cipherAes.init(Cipher.ENCRYPT_MODE, sskAes);
    BufferedInputStream bis = null;
    CipherOutputStream cous = null;
    byte[] buffer = new byte[1024];
    int len;
    try {
      bis = new BufferedInputStream(new FileInputStream(pFilePath));
      cous = new CipherOutputStream(new FileOutputStream(pEncryptedPath),
        cipherAes);
      while ((len = bis.read(buffer)) > 0) {
        cous.write(buffer, 0, len);
      }
      cous.flush();
    } finally {
      if (bis != null) {
        try {
          bis.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      if (cous != null) {
        try {
          cous.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    bis = null;
    try {
      sigMk.initSign(lazOurPrivateKey(), new SecureRandom());
      bis = new BufferedInputStream(new FileInputStream(pEncryptedPath));
      while ((len = bis.read(buffer)) > 0) {
        sigMk.update(buffer, 0, len);
      }
    } finally {
      if (bis != null) {
        try {
          bis.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    byte[] sigDt = sigMk.sign();
    // write SSK, signatures
    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream(pEncryptedPath + ".sken");
      fos.write(encryptedSsk);
      fos.flush();
    } finally {
      if (fos != null) {
        try {
          fos.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    fos = null;
    try {
      fos = new FileOutputStream(pEncryptedPath + ".sken.sig");
      fos.write(sigSsk);
      fos.flush();
    } finally {
      if (fos != null) {
        try {
          fos.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    fos = null;
    try {
      fos = new FileOutputStream(pEncryptedPath + ".sig");
      fos.write(sigDt);
      fos.flush();
    } finally {
      if (fos != null) {
        try {
          fos.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * <p>Decrypts file.</p>
   * @param pEncryptedPath Encrypted Path
   * @param pDecryptedPath Decrypted Path
   * @throws Exception - an exception
   **/
  @Override
  public final void decryptFile(final String pEncryptedPath,
    final String pDecryptedPath) throws Exception {
    FileInputStream fis = null;
    byte[] encryptedSsk = null;
    try {
      fis = new FileInputStream(pEncryptedPath + ".sken");
      ByteArrayOutputStream baus = new ByteArrayOutputStream();
      int b;
      while ((b = fis.read()) != -1) {
        baus.write(b);
      }
      encryptedSsk = baus.toByteArray();
    } finally {
      if (fis != null) {
        try {
          fis.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    fis = null;
    byte[] sigSsk = null;
    try {
      fis = new FileInputStream(pEncryptedPath + ".sken.sig");
      ByteArrayOutputStream baus = new ByteArrayOutputStream();
      int b;
      while ((b = fis.read()) != -1) {
        baus.write(b);
      }
      sigSsk = baus.toByteArray();
    } finally {
      if (fis != null) {
        try {
          fis.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    fis = null;
    byte[] sigDt = null;
    try {
      fis = new FileInputStream(pEncryptedPath + ".sig");
      ByteArrayOutputStream baus = new ByteArrayOutputStream();
      int b;
      while ((b = fis.read()) != -1) {
        baus.write(b);
      }
      sigDt = baus.toByteArray();
    } finally {
      if (fis != null) {
        try {
          fis.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    if (encryptedSsk != null && sigSsk != null && sigDt != null) {
      Signature sigMk = Signature.getInstance("SHA256withRSA");
      sigMk.initVerify(lazPublicKeyAnotherAjetty());
      sigMk.update(encryptedSsk);
      if (sigMk.verify(sigSsk)) {
        sigMk.initVerify(lazPublicKeyAnotherAjetty());
        BufferedInputStream bis = null;
        byte[] buffer = new byte[1024];
        int len;
        try {
          bis = new BufferedInputStream(new FileInputStream(
            pEncryptedPath));
          while ((len = bis.read(buffer)) > 0) {
            sigMk.update(buffer, 0, len);
          }
        } finally {
          if (bis != null) {
            try {
              bis.close();
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        }
        if (sigMk.verify(sigDt)) {
          Cipher cipherRsa = Cipher.getInstance("RSA", this.cryptoProvider);
          cipherRsa.init(Cipher.DECRYPT_MODE, lazOurPrivateKey());
          byte[] decryptedSsk = cipherRsa.doFinal(encryptedSsk);
          SecretKeySpec sskAesRec = new SecretKeySpec(decryptedSsk, "AES");
          Cipher cipherAes = Cipher.getInstance("AES/ECB/PKCS7Padding",
            this.cryptoProvider);
          cipherAes.init(Cipher.DECRYPT_MODE, sskAesRec);
          CipherOutputStream cous = null;
          try {
            bis = new BufferedInputStream(new FileInputStream(pEncryptedPath));
            cous = new CipherOutputStream(new FileOutputStream(pDecryptedPath),
              cipherAes);
            while ((len = bis.read(buffer)) > 0) {
              cous.write(buffer, 0, len);
            }
            cous.flush();
          } finally {
            if (bis != null) {
              try {
                bis.close();
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
            if (cous != null) {
              try {
                cous.close();
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
          }
        }
      }
    }
  }

  /**
   * <p>Getter for our private key in lazy mode.</p>
   * @return our ASK PrivateKey
   * @throws Exception - an exception
   **/
  public final PrivateKey lazOurPrivateKey() throws Exception {
    if (this.ourPrivateKey == null && lazKeystore() != null) {
      KeyStore.ProtectionParameter protParam = new KeyStore.
        PasswordProtection(this.ksPassword);
      KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry)
        this.keyStore.getEntry("AJettyFileExch" + this.ajettyIn, protParam);
      this.ourPrivateKey = pkEntry.getPrivateKey();
    }
    return this.ourPrivateKey;
  }

  /**
   * <p>Getter for ourPublicKey in lazy mode.</p>
   * @return our APK PublicKey
   * @throws Exception - an exception
   **/
  @Override
  public final PublicKey lazOurPublicKey() throws Exception {
    if (this.ourPublicKey == null && lazKeystore() != null) {
      this.ourPublicKey = this.keyStore.getCertificate("AJettyFileExch"
        + this.ajettyIn).getPublicKey();
    }
    return this.ourPublicKey;
  }

  /**
   * <p>Lazy getter of public key of another A-Jetty.</p>
   * @return public key of another A-Jetty
   * @throws Exception - an exception
   **/
  @Override
  public final PublicKey lazPublicKeyAnotherAjetty() throws Exception {
    if (this.publicKeyAnotherAjetty == null && this.publicKeyDir != null
      && lazOurPublicKey() != null) {
      File pkDir = new File(this.publicKeyDir);
      if (pkDir.exists()) {
        File[] lstFl = pkDir.listFiles();
        if (lstFl != null && lstFl.length == 1 && lstFl[0].isFile()) {
          FileInputStream fis = null;
          try {
            fis = new FileInputStream(lstFl[0]);
            ByteArrayOutputStream baus = new ByteArrayOutputStream();
            int b;
            while ((b = fis.read()) != -1) {
              baus.write(b);
            }
            byte[] encKey = baus.toByteArray();
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
            KeyFactory keyFactory = KeyFactory
              .getInstance("RSA", this.cryptoProvider);
            PublicKey pkaj = keyFactory.generatePublic(pubKeySpec);
            if (!lazOurPublicKey().equals(pkaj)) {
              this.publicKeyAnotherAjetty = pkaj;
            }
          } finally {
            if (fis != null) {
              try {
                fis.close();
              } catch (Exception e) {
                e.printStackTrace();
              }
            }
          }
        }
      }
    }
    return this.publicKeyAnotherAjetty;
  }

  //Simple getters and setters:
  /**
   * <p>Setter for keyStore.</p>
   * @param pKeyStore reference
   **/
  public final void setKeyStore(final KeyStore pKeyStore) {
    this.keyStore = pKeyStore;
  }

  /**
   * <p>Getter for ksDirPath.</p>
   * @return String
   **/
  public final String getKsDirPath() {
    return this.ksDirPath;
  }

  /**
   * <p>Setter for ksDirPath.</p>
   * @param pKsDirPath reference
   **/
  public final void setKsDirPath(final String pKsDirPath) {
    this.ksDirPath = pKsDirPath;
  }

  /**
   * <p>Getter for ksPassword.</p>
   * @return char[]
   **/
  public final char[] getKsPassword() {
    return this.ksPassword;
  }

  /**
   * <p>Setter for ksPassword.</p>
   * @param pKsPassword reference
   **/
  public final void setKsPassword(final char[] pKsPassword) {
    this.ksPassword = pKsPassword;
  }

  /**
   * <p>Getter for ajettyIn.</p>
   * @return Integer
   **/
  public final Integer getAjettyIn() {
    return this.ajettyIn;
  }

  /**
   * <p>Setter for ajettyIn.</p>
   * @param pAjettyIn reference
   **/
  public final void setAjettyIn(final Integer pAjettyIn) {
    this.ajettyIn = pAjettyIn;
  }

  /**
   * <p>Getter for publicKeyDir.</p>
   * @return String
   **/
  public final String getPublicKeyDir() {
    return this.publicKeyDir;
  }

  /**
   * <p>Setter for publicKeyDir.</p>
   * @param pPublicKeyDir reference
   **/
  public final void setPublicKeyDir(final String pPublicKeyDir) {
    this.publicKeyDir = pPublicKeyDir;
  }

  /**
   * <p>Getter for cryptoProvider.</p>
   * @return String
   **/
  public final String getCryptoProvider() {
    return this.cryptoProvider;
  }

  /**
   * <p>Setter for cryptoProvider.</p>
   * @param pCryptoProvider reference
   **/
  public final void setCryptoProvider(final String pCryptoProvider) {
    this.cryptoProvider = pCryptoProvider;
  }
}
