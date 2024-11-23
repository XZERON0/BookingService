import java.math.BigInteger;

class Main {
    public static void main(String[] args) {
        BigInteger base = BigInteger.valueOf(688);
        int exponent = 2020;

        BigInteger result = base.pow(exponent);
        System.out.println("Результат: " + result);
    }
}
