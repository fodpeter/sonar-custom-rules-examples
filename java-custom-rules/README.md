## Assert.assertEquals parameter order check
This rule detects the incorrect usage of Assert.assertEquals. The first parameter of this method is the expected
    value and the second parameter is the calculated value.

junit-4.12-sources.jar!/org/junit/Assert.java:134
```java
    /**
     * Asserts that two objects are equal. If they are not, an
     * {@link AssertionError} without a message is thrown. If
     * <code>expected</code> and <code>actual</code> are <code>null</code>,
     * they are considered equal.
     *
     * @param expected expected value
     * @param actual the value to check against <code>expected</code>
     */
    static public void assertEquals(Object expected, Object actual) {
        assertEquals(null, expected, actual);
    }
```

## Usage:
* Build and install
  * Use the official plugin installation guide: [Writing Custom Java Rules 101](http://docs.sonarqube.org/display/PLUG/Writing+Custom+Java+Rules+101)
* It requires to install the **Java Plugin** **4.2.1** on your SonarQube 5.6+

## Example:

Result on an example project:
![rule only underline](screenshot1_collapsed.png)

![rule with description](screenshot1_expanded.png)
