# app_bundle 

For more details: [refer](https://developer.android.com/platform/technology/app-bundle)

Introduction:
1. Dynamic delivery module "feature_sign_up".
2. Developer can create there own feature module by File->New Module->Select Dynamic Feature Module.
3. Ensure **SplitCompat** activity & **SplitAppLication** class is initialized.
4. Use "api" instead of "implementation" in base module, As using "api" developer can access those dependencies in feature module.
5. Include "base_project" in "feature_module" to utilize resources from base module.
