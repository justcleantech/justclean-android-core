# justclean-android-core

Welcome to Justclean android core module.

How to Implement the module in your project:\n
1- clone the module repo. in the same root level directory of your app project.
2- in setting.gradle add the following lines:
   include ':justclean-core'
   project(':justclean-core').projectDir = new File(settingsDir, '../justclean-android-core/justclean_core')
3- in build.gradle (app level) add the following line:
   implementation project(':justclean-core')
4- Welcome to Justclean android core module :D
