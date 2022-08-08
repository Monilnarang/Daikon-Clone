// ***** This file is automatically generated from SequencesPredicate.java.jpp

package daikon.derive.binary;

import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.interning.qual.Interned;
import daikon.*;
import daikon.derive.*;
import java.util.logging.Logger;
import org.plumelib.util.Intern;

/**
 * Derived variable representing the selecting of elements of one sequence based on the values of
 * another sequence. We only predicate if we know that both sequences came from the same original
 * data structure. Derived type is the same as that of the first sequence.
 */
public final class SequencesPredicate extends BinaryDerivation {
  // We are Serializable, so we specify a version to allow changes to
  // method signatures without breaking serialization.  If you add or
  // remove fields, you should change this number to the current date.
  static final long serialVersionUID = 20020122L;

  /** Debug tracer. */
  public static final Logger debug =
    Logger.getLogger("daikon.derive.binary.SequencesPredicate");

  // Variables starting with dkconfig_ should only be set via the
  // daikon.config.Configuration interface.
  /** Boolean. True iff SequencesPredicate derived variables should be generated. */
  public static boolean dkconfig_enabled = false;

  /**
   * Boolean. True if Daikon should only generate derivations on fields of the same data structure.
   */
  public static boolean dkconfig_fieldOnly = true;

  /** Boolean. True if Daikon should only generate derivations on boolean predicates. */
  public static boolean dkconfig_boolOnly = true;

  @Override
  public VarInfo var1(@GuardSatisfied SequencesPredicate this) {
    return base1;
  }

  @Override
  public VarInfo var2(@GuardSatisfied SequencesPredicate this) {
    return base2;
  }

  /** What value to predicate on. */
  private long choose;

  /** Whether we keep or discard values that match this.choose. */
  private boolean keep;

  /** What this predication is called (e.g. for choose == 0 and 1, use "false" and "true"). */
  private String name;

  /**
   * Create a new SequencesJoin derivation.
   *
   * @param vi1 the first of the two variables this is based on
   * @param vi2 the second of the two variables this is based on
   */
  SequencesPredicate(VarInfo vi1, VarInfo vi2, long argChoose, String argName) {
    this(vi1, vi2, argChoose, argName, true);
  }

  /**
   * Create a new SequencesJoin derivation.
   *
   * @param vi1 the first of the two variables this is based on
   * @param vi2 the second of the two variables this is based on
   */
  SequencesPredicate(
      VarInfo vi1, VarInfo vi2, long argChoose, String argName, boolean argKeep) {
    super(vi1, vi2);
    choose = argChoose;
    name = argName;
    keep = argKeep;
  }

  /**
   * Returns a subset of val1 such that the corresponding element in var2 equals this.choose. It is
   * assumed that val1 and val2 originated from the same, larger data structure.
   *
   * @param full_vt the value tuple of a program point to compute the derived value from
   */
  @Override
  public ValueAndModified computeValueAndModifiedImpl(ValueTuple full_vt) {
    Object val1 = var1().getValue(full_vt);
    Object val2 = var2().getValue(full_vt);

    int length1 = -1;
    int length2 = -2; // They must equal in the end

    if (val1 == null) {
      length1 = 0;
    }

    if (val2 == null) {
      length2 = 0;
    }

    if (val1 instanceof long[]) {
      length1 = ((long[]) val1).length;
    }

    if (val2 instanceof long[]) {
      length2 = ((long[]) val2).length;
    }

    if (val1 instanceof Object[]) {
      length1 = ((Object[]) val1).length;
    }

    assert val2 == null || val2 instanceof long[];

    if (length1 != length2) {
      // This derived variable is no longer interesting
      return new ValueAndModified(null, ValueTuple.MISSING_NONSENSICAL);
    }

    assert length1 == length2;

    int mod = ValueTuple.UNMODIFIED;
    int mod1 = var1().getModified(full_vt);
    int mod2 = var2().getModified(full_vt);
    if (mod1 == ValueTuple.MODIFIED) mod = ValueTuple.MODIFIED;
    if (mod2 == ValueTuple.MODIFIED) mod = ValueTuple.MODIFIED;
    if (mod1 == ValueTuple.MISSING_NONSENSICAL) mod = ValueTuple.MISSING_NONSENSICAL;
    if (mod2 == ValueTuple.MISSING_NONSENSICAL) mod = ValueTuple.MISSING_NONSENSICAL;
    /*
     * v1\v2  Unm  Mod  Mis
     *
     * Unm    Unm  Mod  Mis
     * Mod    Mod  Mod  Mis
     * Mis    Mis  Mis  Mis
     */

    long[] predicate = (long[]) val2;
    int count = 0;
    // Find length of output first
    for (int i = 0; i < predicate.length; i++) {
      if ((predicate[i] == choose) ^ !keep) count += 1;
    }

    if (val1 instanceof long[]) {
      long[] result = new long[count];
      long[] values = (long[]) val1;
      int j = 0;
      for (int i = 0; i < length1; i++) {
        if ((predicate[i] == choose) ^ !keep) {
          result[j] = values[i];
          j++;
        }
      }
      return new ValueAndModified(Intern.intern(result), mod);
    } else if (val1 instanceof Object[]) {
      @Interned Object[] result = new @Interned Object[count];
      @Interned Object[] values = (@Interned Object[]) val1;
      int j = 0;
      for (int i = 0; i < length1; i++) {
        if ((predicate[i] == choose) ^ !keep) {
          result[j] = values[i];
          j++;
        }
      }
      return new ValueAndModified(Intern.intern(result), mod);
    } else if (val1 == null) {
      return new ValueAndModified(null, mod);
    } else {
      throw new RuntimeException("Invalid input arrays");
    }
  }

  @Override
  protected VarInfo makeVarInfo() {
    return VarInfo.make_function("predicateSlice", var1(), var2());
  }

  @SideEffectFree
  @Override
  public String toString(@GuardSatisfied SequencesPredicate this) {
    return "[SequencesPredicate of "
        + var1().name()
        + " "
        + var2().name()
        + " for "
        + name
        + "]";
  }

  @Pure
  @Override
  public boolean isSameFormula(Derivation other) {
    // For Toh (tohn) to do.
    if (other instanceof SequencesPredicate) {
      SequencesPredicate o = (SequencesPredicate) other;
      return o.var1().equals(var1()) && o.var2().equals(var2()) && choose == o.choose;
    }
    return false;
  }

  /** Returns the ESC name. */
  @SideEffectFree
  @Override
  public String esc_name(String index) {
    return String.format("predicate(%s,%s)", var1().esc_name(), var2().esc_name());
  }
}
