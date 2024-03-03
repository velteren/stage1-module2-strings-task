package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        var arr = signatureString.split("\\(");
        String accessModifier = null;
        String outerSignature = arr[0];
        var outerSignatureArr = outerSignature.split(" ");
        int index = 0;
        if (outerSignature.startsWith("private") || outerSignature.startsWith("public") || outerSignature.startsWith("protected")) {
            index = 1;
            accessModifier = outerSignatureArr[0];
        }
        String returnType = outerSignatureArr[index];
        String methodName = outerSignatureArr[index + 1];

        String innerSignature = arr[1].substring(0, arr[1].length() - 1);
        var tokenizer = new StringTokenizer(innerSignature, ", ");
        while (tokenizer.hasMoreTokens()) {
            var type = tokenizer.nextToken();
            var name = tokenizer.nextToken();
            arguments.add(new MethodSignature.Argument(type, name));
        }
        var result = new MethodSignature(methodName, arguments);
        if (accessModifier != null) {
            result.setAccessModifier(accessModifier);
        }
        result.setReturnType(returnType);
        return result;
    }
}
