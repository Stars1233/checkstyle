/*
HiddenField
ignoreFormat = (default)null
ignoreConstructorParameter = (default)false
ignoreSetter = (default)false
setterCanReturnItsClass = (default)false
ignoreAbstractMethods = (default)false
tokens = (default)VARIABLE_DEF, PARAMETER_DEF, PATTERN_VARIABLE_DEF, LAMBDA, RECORD_COMPONENT_DEF


*/

// Java17
package com.puppycrawl.tools.checkstyle.checks.coding.hiddenfield;

import java.util.Locale;

public class InputHiddenFieldRecords {
    public record MyRecord1() {
        private static int myHiddenInt = 2;

        public MyRecord1 {
            int myHiddenInt = 5; // violation, ''myHiddenInt' hides a field'
        }

        MyRecord1(String string) {
            this();
            int myHiddenInt = 6; // violation, ''myHiddenInt' hides a field'
        }
    }

    static class MyClass {
        private static int hiddenField = 5;

        MyClass(String string) {
            int hiddenField = 10; // violation, ''hiddenField' hides a field'
        }

        static final Object OBJ = "";
        static String hiddenStaticField = "hiddenStaticField";

        static {

            if (OBJ instanceof String hiddenStaticField) {
            // violation above, ''hiddenStaticField' hides a field'
                System.out.println(hiddenStaticField
                        .toLowerCase(Locale.forLanguageTag(hiddenStaticField)));
                boolean stringCheck = "test".equals(hiddenStaticField);
            }
        }

    }

    public record Keyboard() {
        private static String model = null;
        private static int price = 2;

        public boolean doStuff(Object f) {
            return f instanceof Float price && // violation, ''price' hides a field'
                    price.floatValue() > 0 &&
                    model != null &&
                    price.intValue() == 5;
        }
    }

    record MyRecord13(String string, Integer x) {
        void foo () {
            Integer x = 8; // violation, ''x' hides a field'
        }

        void foo2() {
            String string = "string"; // violation, ''string' hides a field'
        }

    }

    class MyClass13 {
        Integer x = 7;
        String string = "string";

        void foo() {
            Integer x = 8; // violation, ''x' hides a field'
        }

        void foo2() {
            String string = "string"; // violation, ''string' hides a field'
        }
    }

    record MyTestRecord3(String str, Locale treeSet) {
        void foo(Locale hashMap) {
        }
    }

    record MyTestRecord4(int x, int y) {
        public MyTestRecord4(Locale treeSet) { // does not check constructor params
            this(4, 5);
        }
    }
}
