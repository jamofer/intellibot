## IntelliJ/PyCharm Plugin for Robot Automation Framework

### Changes in IntelliBot-0.10.143.383:
#### Custom Built-In variables
Custom variables can be included in Robot Options. This feature could be useful for those who uses `--variable` argument in Robot Framework.
### Changes in IntelliBot-0.10.143.382:
#### Keyword definition with decorator fixes
* Search for keyword decorator in module functions.
* Remove simple quotes on keyword definition with decorator.
* Fix keyword decorator usage with relative/absolute path.
```python
from robot import api
from robot.api import deco

@api.deco.keyword('This works')
def this_works():
    pass
@deco.keyword("And this")
def and_this():
    pass
```

This is a work in progress (the product of a 24 hour hack match), though at this point I have devoted far more time than that.
Related feature request to JetBrains: [IDEA-97678](http://youtrack.jetbrains.com/issue/IDEA-97678).
**Here is a growing list of [features](https://github.com/millennialmedia/intellibot/wiki/Features).**

![Sample](/wiki/features/demo_complete.png)

### Installation & Usage

Note that this plugin should work in either IntelliJ or PyCharm, but that PyCharm is far less used (and thus tested) personally.

**The plugin is now hosted in the JetBrains [repositories](http://plugins.jetbrains.com/plugin/7386?pr=github).**
This means you can install it directly form your IDE.
Just search for 'Intellibot' under 'Browse Repositories...'.

You can also install the plugin manually.
To do so you can either download and [compile](https://github.com/millennialmedia/intellibot/wiki/Development-Setup) the project yourself.
Or download the [IntelliBot-0.10.143.383.zip](https://github.com/jamofer/intellibot/blob/master/IntelliBot-0.10.143.383.zip) file in the project.
You can then install this plugin to your IDE by using the 'Install plugin from disk...' option.
This version may be slightly ahead of the JetBrains repository though potentially slightly less stable.

The plugin will, by default, operate against any ".robot" file in the project.
**You can add ".txt" support by following these [instructions](https://github.com/millennialmedia/intellibot/wiki/Supporting-.txt-Files).**
If you are using PyCharm then any Python libraries should be detected when you setup your interpreter.
If you are using IntelliJ then you can install the Python plugin.
Both instructions can be found in this [wiki page](https://github.com/millennialmedia/intellibot/wiki/Python-Interpreter).
