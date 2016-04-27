ApplicationLocker
===================
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)](http://www.apache.org/licenses/LICENSE-2.0) [![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)

Android library that let you easily set lock activity for you application .

![ScreenShot](https://raw.githubusercontent.com/soroushjavdan/ApplicationLocker/master/sample.gif)


Description
----------

This library send your user to lock activity after they leave your app and return to it . even if they click on home or recent button .

How to:
-------

Create application class and set it to your AndroidManifest and add this part of code to its onCreate method.
```java 
 registerActivityLifecycleCallbacks(new MyLifecycleHandler("1234"));
``` 
> Using default activity 

just send user password to constructor
```java 
registerActivityLifecycleCallbacks(new MyLifecycleHandler("1234"))
```
also you could send all the string that will use inside default activity 
```java 
registerActivityLifecycleCallbacks(new MyLifecycleHandler("1234","please insert your password","wrong pass","unlock"))
```

> Using your activity as lock page

```java
Intent intent = new (this , CustomLockerActivity.class);
intent.putExtra("passcode","pass");
.
.
.
registerActivityLifecycleCallbacks(intent)
```
Note : when you want to using your custom Locker class your class need to extend BaseLockerActivity .
or you could just add this part of code to your class 
```java 
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
```


## License

```
Copyright 2016 Soroushjavdan

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
