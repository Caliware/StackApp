# StackApp

Development Environment Tutorial for Windows:

1. Install Java SDK (JDK 7, I have been using that)
	- http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html
	- Click 'Accept License Agreemnt' under Java SE Development Kit 7u79
	- Select the correct version (I use the Windows x64 version)
	- Follow the installer
	
2. Set Up System Environment Variables
	- Create JAVA_HOME variable with value of the system path to the jdk that you just installed
		- Example: 
					Variable name: JAVA_HOME
					Variable value: C:\Program Files\Java\jdk1.7.0_80
	- Create JDK_HOME variable with value of the system path to the jdk that you just installed
		- Example: 
					Variable name: JDK_HOME
					Variable value: C:\Program Files\Java\jdk1.7.0_80
	- Add the system path to the jdk bin folder to the 'Path' variable
		- Example:
					Variable name: Path
					Variable value: ...les\Common Files\Intel\WirelessCommon\;C:\Program Files\Java\jdk1.7.0_80\bin
					
3. Install and Set Up Eclipse Mars
	-https://www.eclipse.org/downloads/installer-instructions.php
	
4. Install and Set Up Android ADT
	-http://developer.android.com/sdk/installing/installing-adt.html