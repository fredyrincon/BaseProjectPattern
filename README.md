# BaseProjectPattern
This project will explain how to use RepositoryPattern as well as CallBack.

Also include flavour to set the settings for each of the versions.

Getting started Jenkins – Android Studio – Github - Espresso 
-	Download software
o	Download Jenkins war
o	Download Android SDK
o	Download Gradle

-	Set the path (environmental settings)
-	Create an android project (android Studio)
-	Share Project in Github (check other repository)
-	Install Jenkins or place into the web server
-	Check if the project is running (http://localhost:8082/jenkins/)
-	Configure android project with robotium
o	Add the library on the project or use gradle central repository
o	compile 'com.jayway.android.robotium:robotium-solo:5.2.1'
-	Set up the plugins
o	Manage >  Plugins (General and commun)
	GitHub
	Gradle
	Google Play store
	Ant Plugin
	Copy Artifact Plugin
	Credentials Plugin
	CVS Plugin
	External Monitor Job Type Plugin
	GIT client plugin
	GIT plugin
	GitHub API Plugin
	Github Authentication plugin
	GitHub plugin
	GitHub Pull Request Builder
	GitHub SQS Build Trigger Plugin
	Google OAuth Credentials plugin
	Google Play Android Publisher Plugin
	Gradle plugin
	Javadoc Plugin
	JUnit Plugin
	LDAP Plugin
	Mailer Plugin
	Matrix Authorization Strategy Plugin
	Matrix Project Plugin
	Maven Project Plugin
	OAuth Credentials plugin
	OWASP Markup Formatter Plugin
	PAM Authentication Plugin
	Port Allocator Plug-in
	SCM API Plugin
	SSH Agent Plugin
	SSH Credentials Plugin
	SSH Slaves plugin
	Subversion Plugin
	Translation Assistance Plugin
	Windows Slaves Plugin
	Green Balls
	Environment Injector Plugin - EnvInject Plugin
	Android Lint Plugin
	HTML Publisher plugin

- 

-	Set the email service ()
-	 Set up the project to get and commit the source code from the repository
o	Set the clone and git
o	Add Build step
	Gradle command
•	Description: Clean and build
•	Switches: --info –stacktrace
•	Task: clean app:assembleDebug :app:assembleDebugTest connectedCheck uninstallDebug uninstallDebugTest connectedAndroidTest (check to test in a virtual machine)
o	Copy the artefacts Post-build
	**\app\build\outputs\**
o	Copy the lint
Export test files as well
o	Send email in case of failure process.


Add client in google console and google play


Source
-	http://jenkins-ci.org/
-	http://www.vogella.com/tutorials/Robotium/article.html
-	http://blog.zuehlke.com/en/configure-your-android-project-on-jenkins/
-	http://tiku.io/questions/3275380/gradle-clean-build-and-connectedandroidtest
-	http://www.bignerdranch.com/blog/all-in-together-android-studio-gradle-and-robolectric/
-	http://stackoverflow.com/questions/24028607/register-net-framework-4-5-in-iis-7-5

-

Set webService

Create Web project
Create controller
Create project in iis
Set path of the project and check the prot
- go to C:\Windows\Microsoft.NET\Framework64\v4.0.30319
- Executee aspnet_regiis.exe -i

-

Data:

- Project Name: BaseProjectPatter Build and Test
- Source Code Management: Git
- Build Environment: Run an Android emulator during build (4.3, 320, HVGA, 32M )
- Build: 
- From Root Build Script Dir : click
- Build step description: Clean and build (QA)
- Switches: --info --stacktrace
- Tasks: clean app:assemble :app:assembleTest connectedCheck uninstallAll connectedAndroidTest
- Post-build Actions
- Publish Android Lint results: **\app\build\outputs\lint-results-productionRelease-fatal_files\*.xml
- Archive the artifacts: **\app\build\outputs\**
- Publics Junit test result report: **\app\build\outputs\androidTest-results\connected\flavors\*\*.xml
- Notification

-

Configuration images for jenkins

![image](https://cloud.githubusercontent.com/assets/2193853/6122193/c81a278e-b143-11e4-98fd-b2f79e60fbde.png)


-
Get project source code

![image](https://cloud.githubusercontent.com/assets/2193853/6122097/988ea41e-b142-11e4-9f1b-13510af10b3b.png)

-

Set Emulator

![image](https://cloud.githubusercontent.com/assets/2193853/6122135/328c12f4-b143-11e4-8bc9-467822216d95.png)

-

Set Build

![image](https://cloud.githubusercontent.com/assets/2193853/6122164/53f4a80c-b143-11e4-8e35-2960a1d85c34.png)

-

Set post build actions:
  
![image](https://cloud.githubusercontent.com/assets/2193853/6122180/876491a2-b143-11e4-8b7a-b431936b97b4.png)




