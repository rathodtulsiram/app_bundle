# app_bundle

Android App Bundle Showcase.

1. Dynamic delivery module "feature_sign_up".
2. Developer can create there own feature module by File->New Module->Select Dynamic Feature Module.
3. Ensure SplitCompat activity & AppLication class is initialized.
4. Use "api" instead of "implementation" in base module, As using "api" developer can access those dependencies in feature module.
5. Include "base_project" in "feature_module" to utilize resources from base module.
