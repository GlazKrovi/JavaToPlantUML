package test;

import java.util.*;

/**
 * traitement des types primitifs UMl et des multiplicités
 */


public class Java2PumlTest1 {
}

class A {
    public static final boolean aBoolean = true;
    static final char aChar = ' ';
    protected static double aDouble;
    private final float aFloat = 0.0F;
    public byte aByte;
    protected short aShort;
    long aLong;
    private int anInt;

    private static float aFloatFunction(float aFloatParam) {
        return 0;
    }

    protected static double aDoubleFunction(double aDoubleParam) {
        return 0;
    }

    static char aCharFunction(char aCharParam) {
        return ' ';
    }

    static public boolean aBooleanFunction(boolean aBooleanParam) {
        return true;
    }

    private int anIntFunction(int anIntParam) {
        return 0;
    }

    protected short aShortFunction(short aShortParam) {
        return 0;
    }

    long aLongFunction(long aLongParam) {
        return 0;
    }

    public byte aByteFunction(byte aByteParam) {
        return 0;
    }
}

abstract class B {
    public static final Boolean aBoolean = true;
    static final Character aCharacter = ' ';
    protected static Double aDouble;
    private final Float aFloat = 0.0F;
    public Byte aByte;
    protected Short aShort;
    Long aLong;
    private Integer anInteger;

    static Character aCharacterFunction(Character aCharacterParam) {
        return ' ';
    }

    public static Boolean aBooleanFunction(Boolean aBooleanParam) {
        return true;
    }

    private Integer anIntFunction(Integer anIntParam) {
        return 0;
    }

    protected Short aShortFunction(Short aShortParam) {
        return 0;
    }

    Long aLongFunction(Long aLongParam) {
        return 0L;
    }

    public Byte aByteFunction(Byte aByteParam) {
        return 0;
    }

    private Float aFloatFunction(Float aFloatParam) {
        return Float.NaN;
    }

    protected abstract Double aDoubleFunction(Double aDoubleParam);
}

class C {
    public static final Set<List<Boolean>> aSetOfBooleanList = null;
    static final Set<Character> aSetOfCharacter = null;
    protected static List<List<Double>> aListOfDoubleList;
    private final List<Float> aFloatList = null;
    public Byte[][] anArrayOfByteArray;
    protected Short[] aShortArray;
    long[][] anArrayOfLongArray;
    private int[] anIntArray;

    private int[] anIntArrayFunction(int[] anIntArrayParam) {
        return null;
    }

    protected Short[] aShortArrayFunction(Short[] aShortArrayParam) {
        return null;
    }

    long[][][] aLong3DArrayFunction(long[][][][] someLongs) {
        return null;
    }

    public Byte[][][][][] aByte5DArrayFunction(Byte[][][][][][] someBytes) {
        return null;
    }

    private List<Float> aFloatListFunction(List<Float> aFloatListParam) {
        return null;
    }

    protected List<List<Double>> aListOfDoubleListFunction(List<List<List<Double>>> someDoubles) {
        return null;
    }

    Set<Character> aCharacterSetFunction(Set<Character> aCharacterSetParam) {
        return null;
    }

    public Set<List<Boolean>> aSetOfBooleanListFunction(Collection<Set<List<Boolean[][]>[]>> someBooleans) {
        return null;
    }
}

abstract class D {
    public static List<List<D>[]> ds;
    protected List<C> cs;
    B[] bs;
    private A a;

    public static Collection<D> dFunction(D d0, Collection<D> d1, HashSet<D> d2, ArrayList<D> d3, HashMap<C, D> d4) {
        return null;
    }

    private A aFunction(A a0, A[] a1, A[][] a2, A[][][] a3) {
        return null;
    }

    B[] bFunction(B b0, List<B>[] b1, List<List<B>[]>[] b2, List<List<List<B>[]>[]>[] b3) {
        return null;
    }

    protected abstract List<C> cFunction(C c0, List<C[]> c1, List<List<C[]>[]> c2, List<List<List<C[]>[]>[]> c3);
}

abstract class E {
    public Map<Double, A[]> mapOfArrayOfA;
    protected Map<Integer, A> mapOfA;
    Map<Short, Double[]> mapOfDoubleArray;
    private Map<Character, Integer> mapOfInt;

    public static Map<Double, A[]> mapOfArrayOfAFunction(Map<Integer, A[]> a0, Map<Double, List<A[]>> a1,
                                                         Map<Double, List<A[]>[]> a2, TreeMap<Double, Map<Double, A[]>> a3) {
        return null;
    }

    private Map<Character, Integer> mapOfIntFunction(Map<Character, Integer> a0, HashMap<Character, Integer[]> a1,
                                                     TreeMap<Character, Integer[][]> a2, Map<Character, Integer[][][]> a3) {
        return null;
    }

    abstract Map<Short, Double[]> mapOfDoubleArrayFunction(Map<Short, Double[]> a0, Map<Short, List<Double>> a1,
                                                           HashMap<Short, ArrayList<Double[]>> a2,
                                                           TreeMap<Short, Map<Short, TreeSet<Double[]>>> a3);

    protected Map<Short, A> mapOfAFunction(Map<Integer, A> a0, Map<Short, List<A>> a1,
                                           Map<Short, List<A>[]> a2, Map<Short, HashMap<Short, A>> a3) {
        return null;
    }
}


