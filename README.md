# LController

Step 1. Add the JitPack repository to your build file
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
 ```

Step 2. Add the dependency
```
dependencies {
	        implementation 'com.github.Everglowzz:LController:3.0'
	}
```

Step3.  AVOSCloud.initialize  

```
  LcUtils.init(this,"appID","appKey","objectid")
```
