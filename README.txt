site: https://sites.google.com/site/beigesoftware

beige-accjet-bin.zip is archive of standalone JEE web application that runs on embedded A-Jetty.

Beigesoft™ Enterprise Information System for MS Windows/Mac/*Nix beige-accjet.bin.zip
you can find in central Maven repository https://search.maven.org/#search%7Cga%7C1%7Cg%3A%22org.beigesoft%22
or https://repo1.maven.org/maven2/org/beigesoft/beige-accjet/

This is the first version of the successor to Beige Accounting All In One.
The main goal was successfully achieved, it works noticeably faster on weak devices such as a smartphone than its predecessor.

------------------------------------------------------------------------------------------------------------

At first you should better read articles: https://sites.google.com/site/beigesoftware/eis-bobs-pizza

This application never waste your time because of:
  1. It provides sufficient functionality:  
* Double entry accounting system.
* General ledger, trial balance, balance sheet, stock items and other reports.
* It makes COGS FIFO/LIFO automatically for sales, sales returns, inventory losses.
* It makes sales tax (VAT) automatically for sales, purchases and their returns.
* Sales tax (VAT) methods: item/invoice basis, destination-based, aggregate rate, tax included in the price.
* Tracking payments for sales/purchases in national and foreign currencies.
* Payroll - automatic calculation of the taxes using withholding tax tables.
* Production - automatic calculation of the cost of a product from used materials, direct labor costs and other costs.
* Inventory - two dimensional (warehouse, place) registration, moving items inside warehouse, etc.
* Multi-databases (organizations).
* Embedded Web-Store. Pricing - multicurrency, price depends on buyer's category, exporting price list to CSV.
* There is Beigesoft™ EIS version for Android, so you can work with the same database anywhere.

You can check functionality without installation by reading the articles (see above).
  
  2. It's reliable software:
* All its parts are under truly free licenses.
* It's open source software, so fixing an error or customizing is not a problem.
* It will never be downgraded or disappeared. You always can find it (binary and source code) in Central Maven Repository.

Web-Store is included for pricing, study, and tests purposes and you are also able to make full DB copy from cloud version.
You can create price lists in different price categories and export them to a CSV file to your customers or POS.

--------------------------------------------------------------------------------------------------------------
Вам лучше прочесть для начала Пример ведения коммерческого бухгалтерского учета в Беижсофт™ Информационная Система Предприятия: https://sites.google.com/site/beigesoftware/eis-iv

Преимущества Беижсофт™ ЕИС:

  Первое - это функциональность:
* Это готовое решение для ведения коммерческого учета методом двойной записи по рыночным правилам.
* Отчеты - баланс, отчет по счету детальный, остатки склада...
* НДС методы: по отгрузке/оплате, расчет построчно/по документу, НДС включен в цену.
* Отслеживание платежей за покупки/продажи в нациннальной и иностранной валютах.
* Автоматическое вычисление себестоимости методами ФИФО/ЛИФО и по стоимости единицы.
* Производство - автоматическое вычисление себестоимости продукции из стоимости использованных материалов и других прямых затрат (труд).
* Склад - двумерная регистрация хранения "Склад-Место".
* Нет ограничения на количество баз данных (организаций).
* Есть версия Беижсофт™ ИСП для МС Виндоус, Мак, Юникс-подобных ОС. Вы можете работать с одинаковой СКЛайт базой данных везде.
* Веб-магазин включен в локальные версии для изучения, тренировки и возможности копирования полной базы данных из Интернет-версии.
 Вы также можете с помощью Веб-магазина создавать прайс листы с различными ценами (магазин в центре, оптовые покупатели А...) и экспортировать их в CSV файлах оптовым покупателям и в POS-терминалы.
...

  Второе - это надежность, работоспособность:
*Лицензии всех частей гарантируют отсутствие возможных проблем в будущем.
*Открытый код - возможность доработки, исправление ошибок.
*Дистрибутивы и исходный код находятся в центральном Мавен репозитории. Нет риска даунгрэйда, исчезновения.
...
--------------------------------------------------------------------------------------------------------------

Beigesoft EIS is always in secure enabled mode (protected from scams).
It requires user authentication with strong password.
It uses encryption for HTTPS and file exchange - modern algorithms asymmetric RSA 2048bit key size and symmetric AES 256bit size.
In *nix OS you are able to create "accountant" user, then under your regular account (e.g. Bob)
open terminal and login as accountant - "su -l accountant" then install and use beige-accounting.
start beige-accounting under user "accounting" from command line with command "java -jar beige-accjet-jar-with-dependencies.jar cli" - cli means command line interface.
As a result an application (that is running under your regular account) that is affected by scamware can't access your
accounting data, e.g. if your browser has been allowing scamware script in HTML page to read your file system then it can't read "accountant" files cause browser is running under your account e.g. "Bob", not under "accountant".

To start application:
1. unpack ZIP.
2. After installing Java (Oracle) you can run any Java "JAR" file by double click on it.
  Do it with beige-accjet-jar-with-dependencies.jar.
  You also can launch "JAR" file from command prompt (power shell, terminal) with command:
  "java -jar beige-accjet-jar-with-dependencies.jar"
3. Application menu to start/stop server will be appeared. You should enter strong (see below) password to start Beigesoft EIS.
4. Press "Start" button, then wait while server has been started (for the 1-st time it may takes up to 1 minute to create database).
  * If you got error, then see starter.log file in application folder. If it's saying:
    a) ...provider BC... then you should install Bouncy Castle crypto-provider in static way:
    download bcprov-jdk15on-1.61.jar from https://repo1.maven.org/maven2/org/bouncycastle/bcprov-jdk15on/1.61/
    and bcpkix-jdk15on-1.61.jar from https://repo1.maven.org/maven2/org/bouncycastle/bcpkix-jdk15on/1.61/
    then put them into [java-home]/lib/ext e.g. "C:\Program Files (x86)\Java\jre[version#8]\lib\ext"
    Then add entry "security.provider.[next_number]=org.bouncycastle.jce.provider.BouncyCastleProvider"
    into [JAVA_HOME]/lib/security/java.security
    On MS Windows copy java.security to your documents folder, edit it then copy back into "C:\Program Files (x86)\Java\jre[version#8]\lib\security"
    b)...org.bouncycastle.operator.OperatorCreationException: unable to create OutputEncryptor: Illegal key size or default parameters...
    then you should install "Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files for Java 8" into
    folder [JAVA_HOME]/lib/security. For MS Windows you can find these files
    in folder "C:\Program Files (x86)\Java\jre[version#8]\lib\security\policy\unlimited",
    otherwise download these from Oracle site.
    Encryption with strong keys (long size) protects you (your information) from scams, so any law must protect you too.
5. A-Jetty CA certificate ajetty-ca.pem will be at the application folder. You have to install it
  as trusted Certificate Authority in the browser.
  Certificate Authorities that aren't signed by global trusted CA are often used to create private (non-public) intranets, using digital signatures inside organization and its partners.
  Here A-Jetty CA used to create HTTPS intranet inside only computer and for encrypted file exchange between your computers/tablets...
6. press button "https://localhost:8443/bsa8443" to start the browser. *If you are in *nix and accounting is running under accountant (as described above) and browser under your regular account then type the address in the browser by hand.
7. Empty database requires to add the first (only) user with strong password.
  To make strong and ease to remember password use method similar to this:
  a. use at least 3 words, e.g. raccoon eat stone
  b. change these words with a rule e.g. "last letter to first position upper case" e.g. Nraccoo Tea Eston
  c. add several digits, e.g. result is "NraccooTeaEston165" or "165NraccooTeaEston" or "165NraccooTeaEston165"...
8. To make second (third ...) local beige-accounting e.g. for using web-services to import data from
  tax accounting to market one (at the same computer) you need copy folder "ks" with keystore into second (3-d...)
  and register A-Jetty CA certificate ajetty-ca.pem in the Java. You can do it by using command line tool "keytool",
  on *nix OS run in terminal:
  keytool -import -trustcacerts -alias ajettyca -file "ajetty-ca.pem" -keystore /usr/lib/jvm/java-1.8.0-openjdk/jre/lib/security/cacerts
  It requires ROOT permission, so do not care about keystore password (default is changeit).
  on MS Windows open Power Shell as ADMIN in beige-accounting folder and run:
  & "C:\Program Files (x86)\Java\jre[version#8]\bin\keytool.exe" -import -trustcacerts -alias ajettyca -file "ajetty-ca.pem" -keystore "C:\Program Files (x86)\Java\jre[version#8]\lib\security\cacerts"

It's based on previous beigesoft-accountingoio-ajetty project.

debug range #15 (15000..15999)

-------------------------------------------------------------------------------
license / лицензия:
BSD 2-Clause License
https://sites.google.com/site/beigesoftware/bsd2csl

3-D PARTY LICENSES / лицензии стороннего ПО:

Oracle Java Servlet/JSP API implementations and other JEE files:
CDDLv1 + GPLv2 with classpath exception
https://javaee.github.io/glassfish/LICENSE

https://github.com/demidenko05/a-jetty-all - Jetty 9.2 by Mort Bay Consulting Pty. Ltd adapted for Android:
The Eclipse Public License, Version 1.0
https://www.eclipse.org/legal/epl-v10.html

https://github.com/demidenko05/a-tomcat-all - part of Apache Tomcat/JSTL by Apache Software Foundation, adapted for Android to precompile and run JSP/JSTL:
The Apache Software License, Version 2.0
https://www.apache.org/licenses/LICENSE-2.0.txt

https://github.com/demidenko05/a-javabeans8 - adapted OpenJDK8 javabeans for Android:
GNU General Public License, version 2, with the Classpath Exception
https://openjdk.java.net/legal/gplv2+ce.html

SQLite JDBC (included SQLite database) by Taro L. Saito (https://xerial.org/software/)
The Apache Software License, Version 2.0
https://github.com/xerial/sqlite-jdbc/blob/master/LICENSE

SQLite database is Public Domain:
https://www.sqlite.org/copyright.html

HikariCP JDBC-pool by Brett Wooldridge:
The Apache Software License, Version 2.0
https://github.com/brettwooldridge/HikariCP/blob/dev/LICENSE

JavaMail API plus JavaBeans(TM) Activation Framework: CDDL/GPLv2+CE:
https://javaee.github.io/javamail/LICENSE

Bouncy Castle Crypto APIs by the Legion of the Bouncy Castle Inc:
Bouncy Castle License (actually MIT)
https://www.bouncycastle.org/licence.html

DejaVu fonts by Bitstream:
https://dejavu-fonts.github.io/License.html 

CSS/Javascript framework Bootstrap by Twitter, Inc and the Bootstrap Authors:
MIT License
https://github.com/twbs/bootstrap/blob/master/LICENSE

JQuery by JS Foundation and other contributors:
MIT license
https://jquery.org/license

Open Iconic icon fonts by Waybury:
SIL OPEN FONT LICENSE Version 1.1
http://scripts.sil.org/cms/scripts/page.php?item_id=OFL_web

Open Iconic to Bootstrap CSS by Waybury:
MIT License
https://github.com/iconic/open-iconic/blob/master/ICON-LICENSE

flag-icon-css collection of all country flags in SVG by Panayiotis Lipiridis
MIT License
https://github.com/lipis/flag-icon-css
