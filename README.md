# registration
Funkids school students registration


openssl pkcs12 -in yourfile.pfx -nocerts -out key.pem -nodes
openssl pkcs12 -in yourfile.pfx -clcerts -nokeys -out cert.pem


openssl pkcs12 -export -in cert.pem -inkey key.pem -out yourfile.p12 -name youralias -passout pass:yourpassword


openssl pkcs12 -info -in certificate.p12 -noout -passin pass:mypassword

openssl pkcs12 -info -in certificate.p12 -noout -passin pass:mypassword

Extract the Private Key
Run the following command to extract the private key from the .p12 file:

openssl pkcs12 -in GenSADA.p12 -nocerts -out key.pem -nodes -passin pass:pocpwd

Extract the Certificate
Run the following command to extract the certificate from the .p12 file:
openssl pkcs12 -in GenSADA.p12 -clcerts -nokeys -out cert.pem -passin pass:pocpwd


Combine into a New PKCS12 File
If both the certificate and the private key are extracted successfully, you can combine them back into a new .p12 file:

openssl pkcs12 -export -in cert.pem -inkey key.pem -out new_certificate.p12 -name myappkey -passout pass:pocpwd

After performing these steps, check the new .p12 file to ensure it contains the private key:

openssl pkcs12 -info -in new_certificate.p12 -noout -passin pass:pocpwd

To open .pfx
openssl pkcs12 -info -in yourfile.pfx -noout -passin pass:yourpassword

export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk-11.0.1.jdk/Contents/Home"
export PATH="$JAVA_HOME/bin:$PATH"
# Java environment
export JAVA_HOME="/Library/Java/JavaVirtualMachines/jdk-11.0.1.jdk/Contents/Home"

# Maven environment
export MAVEN_HOME="/opt/apache-maven-3.8.1"

# Update PATH
export PATH="$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH"

# Compiled class files
*.class

# Log files
*.log

# BlueJ files
*.ctxt

# Mobile Tools for Java (J2ME)
.mtj.tmp/

# Package Files #
*.jar
*.war
*.ear

# Virtual machine crash logs, see http://www.java.com/en/download/help/error_hotspot.xml
hs_err_pid*

# IntelliJ IDEA
.idea/
*.iws
*.iml
*.ipr

# IntelliJ IDEA log directory
idea.log

# IntelliJ IDEA files
*.iws
*.iml
*.ipr

# Maven
target/
pom.xml.tag
pom.xml.releaseBackup
pom.xml.versionsBackup
pom.xml.next
release.properties

# Gradle
.gradle/
build/

# Other
.settings/
.classpath
.project
.tooling/

# Eclipse
.classpath
.project
.settings/
.metadata/

# Windows
Thumbs.db
ehthumbs.db
