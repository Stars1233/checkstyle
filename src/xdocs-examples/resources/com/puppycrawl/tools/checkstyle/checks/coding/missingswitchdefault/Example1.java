/*xml
<module name="Checker">
  <module name="TreeWalker">
    <module name="MissingSwitchDefault"/>
  </module>
</module>
*/
package com.puppycrawl.tools.checkstyle.checks.coding.missingswitchdefault;

// xdoc section -- start
class Example1 {
  void Example1(int i) {
    switch (i) { // violation, 'switch without "default" clause'
      case 1:
        break;
      case 2:
        break;
    }
  }
}
// xdoc section -- end