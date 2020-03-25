# justclean-android-core

Welcome to Justclean android core module.

How to Implement the module in your project:
* clone the module repo. in the same root level directory of your app project.
* in setting.gradle add the following lines:
   include ':justclean-core'
   project(':justclean-core').projectDir = new File(settingsDir, '../justclean-android-core/justclean_core')
* in build.gradle (app level) add the following line:
   implementation project(':justclean-core')
* Welcome to Justclean android core module :D
