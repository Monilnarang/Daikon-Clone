// ***** This file is automatically generated from OneOf.java.jpp

package daikon.inv.unary.sequence;

import daikon.*;
import daikon.inv.*;
import daikon.inv.unary.OneOf;

import java.io.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.*;

import org.checkerframework.checker.initialization.qual.Initialized;
import org.checkerframework.checker.interning.qual.Interned;
import org.checkerframework.checker.lock.qual.GuardSatisfied;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.checkerframework.framework.qual.Unused;
import org.plumelib.reflection.Signatures;
import org.plumelib.util.ArraysPlume;
import org.plumelib.util.Intern;
import org.plumelib.util.StringsPlume;
import org.plumelib.util.UtilPlume;
import typequals.prototype.qual.NonPrototype;
import typequals.prototype.qual.Prototype;

// This subsumes an "exact" invariant that says the value is always exactly
// a specific value.  Do I want to make that a separate invariant
// nonetheless?  Probably not, as this will simplify implication and such.

  /**
   * Represents double[] variables that take on only a few distinct values. Prints as either
   * {@code x == c} (when there is only one value) or as {@code x one of {c1, c2, c3}}
   * (when there are multiple values).

   */

@SuppressWarnings("UnnecessaryParentheses")  // generated code, parentheses are sometimes needed
public final class OneOfFloatSequence extends SingleFloatSequence implements OneOf {
  // We are Serializable, so we specify a version to allow changes to
  // method signatures without breaking serialization.  If you add or
  // remove fields, you should change this number to the current date.
  static final long serialVersionUID = 20030822L;

  /** Debugging logger. */
  public static final Logger debug =
    Logger.getLogger(OneOfFloatSequence.class.getName());

  // Variables starting with dkconfig_ should only be set via the
  // daikon.config.Configuration interface.
  /** Boolean. True iff OneOf invariants should be considered. */
  public static boolean dkconfig_enabled = Invariant.invariantEnabledDefault;

  /**
   * Positive integer. Specifies the maximum set size for this type of invariant (x is one of
   * {@code size} items).
   */

  public static int dkconfig_size = 3;

  // Probably needs to keep its own list of the values, and number of each seen.
  // (That depends on the slice; maybe not until the slice is cleared out.
  // But so few values is cheap, so this is quite fine for now and long-term.)

  @Unused(when=Prototype.class)
  private double[] @Interned [] elts;
  @Unused(when=Prototype.class)
  private int num_elts;

  @Prototype OneOfFloatSequence() {
    super();
  }

  OneOfFloatSequence(PptSlice slice) {
    super(slice);

    // Elements are interned, so can test with == (except that NaN != NaN)
    // (in the general online case, it's not worth interning).
    elts = new double[dkconfig_size] @Interned [];

    num_elts = 0;

    // var() is initialized by the super constructor
    assert var().is_array() :
      String.format("In %s constructor, var %s (type=%s, rep_type=%s) should be an array",
                     "OneOfSequenceFloat", var().name(), var().type, var().rep_type);

  }

  private static @Prototype OneOfFloatSequence proto = new @Prototype OneOfFloatSequence();

  /** Returns the prototype invariant for OneOfFloatSequence */
  public static @Prototype OneOfFloatSequence get_proto() {
    return proto;
  }

  /** returns whether or not this invariant is enabled */
  @Override
  public boolean enabled() {
    return dkconfig_enabled;
  }

  /** instantiate an invariant on the specified slice */
  @Override
  public OneOfFloatSequence instantiate_dyn(@Prototype OneOfFloatSequence this, PptSlice slice) {
    return new OneOfFloatSequence(slice);
  }

  @Pure
  public boolean is_boolean(@GuardSatisfied OneOfFloatSequence this) {
    return var().file_rep_type.elementType() == ProglangType.BOOLEAN;
  }
  @Pure
  public boolean is_hashcode(@GuardSatisfied OneOfFloatSequence this) {
    return var().file_rep_type.elementType() == ProglangType.HASHCODE;
  }

  @SideEffectFree
  @Override
  public OneOfFloatSequence clone(@GuardSatisfied OneOfFloatSequence this) {
    OneOfFloatSequence result = (OneOfFloatSequence) super.clone();
    result.elts = elts.clone();

    for (int i = 0; i < num_elts; i++) {
      result.elts[i] = Intern.intern(elts[i].clone());
    }

    result.num_elts = this.num_elts;
    return result;
  }

  @Override
  public int num_elts() {
    return num_elts;
  }

  @Override
  public Object elt() {
    return elt(0);
  }

  public Object elt(int index) {
    if (num_elts <= index) {
      throw new Error("Represents " + num_elts + " elements, index " + index + " not valid");
    }

    return elts[index];
  }

  static Comparator<double[]> comparator = new ArraysPlume.DoubleArrayComparatorLexical();

  private void sort_rep(@GuardSatisfied OneOfFloatSequence this) {
    Arrays.sort(elts, 0, num_elts , comparator);
  }

  public double @Interned [] min_elt() {
    if (num_elts == 0) {
      throw new Error("Represents no elements");
    }
    sort_rep();
    return elts[0];
  }

  public double @Interned [] max_elt() {
    if (num_elts == 0) {
      throw new Error("Represents no elements");
    }
    sort_rep();
    return elts[num_elts - 1];
  }

  // Assumes the other array is already sorted
  public boolean compare_rep(int num_other_elts, double[] @Interned [] other_elts) {
    if (num_elts != num_other_elts) {
      return false;
    }
    sort_rep();
    for (int i = 0; i < num_elts; i++) {
      if (!((elts[i]) == (other_elts[i]))) { // elements are interned
        return false;
      }
    }
    return true;
  }

  private String subarray_rep(@GuardSatisfied OneOfFloatSequence this) {
    // Not so efficient an implementation, but simple;
    // and how often will we need to print this anyway?
    sort_rep();
    StringBuilder sb = new StringBuilder();
    sb.append("{ ");
    for (int i = 0; i < num_elts; i++) {
      if (i != 0) {
        sb.append(", ");
      }

      sb.append(Arrays.toString(elts[i]));

    }
    sb.append(" }");
    return sb.toString();
  }

  @Override
  public String repr(@GuardSatisfied OneOfFloatSequence this) {
    return "OneOfSequenceFloat" + varNames() + ": falsified=" + falsified
      + ", num_elts=" + num_elts
      + ", elts=" + subarray_rep();
  }

  @SideEffectFree
  @Override
  public String format_using(@GuardSatisfied OneOfFloatSequence this, OutputFormat format) {
    sort_rep();

    if (format.isJavaFamily()) {
      return format_java_family(format);
    }

    if (format == OutputFormat.DAIKON) {
      return format_daikon();
    } else if (format == OutputFormat.SIMPLIFY) {
      return format_simplify();
    } else if (format == OutputFormat.ESCJAVA) {
      String result = format_esc();
      return result;
    } else if (format == OutputFormat.CSHARPCONTRACT) {
      return format_csharp_contract();
    } else {
      return format_unimplemented(format);
    }
  }

  public String format_daikon(@GuardSatisfied OneOfFloatSequence this) {
    String varname = var().name();
    if (num_elts == 1) {

          return varname + " == " + Arrays.toString(elts[0]);
    } else {
      return varname + " one of " + subarray_rep();
    }
  }

  public String format_esc(@GuardSatisfied OneOfFloatSequence this) {
    sort_rep();

    String result;

    String length = null;
    String forall = null;

    if (length == null) {
      if (forall == null) {
        return format_unimplemented(OutputFormat.ESCJAVA); // "needs to be implemented"
      } else {
        result = forall;
      }
    } else {
      if (forall == null) {
        result = length;
      } else {
        result = "(" + length + ") && (" + forall + ")";
      }
    }

    return result;
  }

public String format_csharp_contract(@GuardSatisfied OneOfFloatSequence this) {

    @NonNull @Initialized String result;

  result = "(\"oneOf.java.jpp: SEQUENCE unimplemented\" != null)"; // "interned"

    return result;
  }

  public String format_java_family(@GuardSatisfied OneOfFloatSequence this, OutputFormat format) {

    String result;

    // Setting up the name of the unary variable
    String varname = var().name_using(format);

    result = "";
    for (int i = 0; i < num_elts; i++) {
      if (i != 0) { result += " || "; }

      String seq = "new double[] { ";

      for (int j = 0 ; j < elts[i].length ; j++) {
        if (j != 0) { seq += ", "; }
        seq = seq + (Double.isNaN(elts[i][j]) ? "Double.NaN" : String.valueOf(elts[i][j]));
      }
      seq += " }";

      result += "daikon.Quant.pairwiseEqual(" + varname + ", " + seq + ")";
    }

    return result;
  }

  public String format_simplify(@GuardSatisfied OneOfFloatSequence this) {

    sort_rep();

    String result;

    StringBuilder resultBuf = new StringBuilder();
    for (int i = 0; i < num_elts; i++) {
      double @Interned [] seq = elts[i];
      String offset = null;
      String contents = null;
      String[] bounds_name;
      String length = var().get_simplify_size_name();
      // if ((length == null) && var().name.isApplySizeSafe())
      //  System.out.printf("var %s, type %s, is_array %b%n", var().name(),
      //                     var().type, var().type.isArray());
      if (length != null) {
        length = "(EQ " + length + " "+ simplify_format_long(seq.length) + ")";
      } else if ((bounds_name = var().get_simplify_slice_bounds()) != null) {
        String size = "(+ 1 (- " + bounds_name[1] +" " + bounds_name[0] + "))";
        length = "(EQ " + size + " " + simplify_format_long(seq.length) + ")";
        offset = bounds_name[0];
      }

      {
        StringBuilder contentsBuf = new StringBuilder();
        for (int j = 0; j < seq.length; j++) {
          if (j + 3 < seq.length
              && (((seq[j]) == ( seq[j + 1])) || (Double.isNaN(seq[j]) &&Double.isNaN( seq[j + 1])))
              && (((seq[j]) == ( seq[j + 2])) || (Double.isNaN(seq[j]) &&Double.isNaN( seq[j + 2])))
              && (((seq[j]) == ( seq[j + 3])) || (Double.isNaN(seq[j]) &&Double.isNaN( seq[j + 3])))) {
            // Compress a sequence of adjacent values
            int k = j + 4;
            for (; k < seq.length; k++) {
              if (!(((seq[j]) == ( seq[k])) || (Double.isNaN(seq[j]) &&Double.isNaN( seq[k])))) {
                break;
              }
            }
            k--;
            String index_name = VarInfo.get_simplify_free_index(var());
            String cond_left, cond_right;
            if (offset == null) {
              cond_left  = "(<= " + j + " " + index_name + ")";
              cond_right = "(<= " + index_name + " " + k + ")";
            } else {
              cond_left = "(<= (+ " + offset + " " + j + ") "
                + index_name + ")";
              cond_right = "(>= (+ " + offset + " " + k + ") "
                + index_name + ")";
            }
            String cond = "(AND " + cond_left + " " + cond_right + ")";
            String nth = var().get_simplify_selectNth(index_name, true, 0);
            String eq = "(EQ " + nth + " " + simplify_format_double(seq[j]) + ")";
            String implies = "(IMPLIES " + cond + " " + eq + ")";
            String forall = "(FORALL (" + index_name + ") " + implies + ")";
            contentsBuf.append(" " + forall);
            j = k;
          } else {
            // Output a single value
            String nth = var().get_simplify_selectNth_lower(j);
            if (nth == null) {
              String classname = this.getClass().toString().substring(6);
              result = "warning: method " + classname
                + ".format_simplify() needs to fix selectNth(): " + format();
              return result;
            }
            String value = simplify_format_double(seq[j]);
            contentsBuf.append(" (EQ " + nth + " " + value + ")");
            // if (nth.contains ("--orig__a"))
            //   System.out.printf("regular orig__a%n");

          }
        }
        if (seq.length > 1) {
          contents = "(AND " + contentsBuf.toString() + ")";
        } else if (seq.length == 1) {
          contents = contentsBuf.toString().substring(1);
        } else if (seq.length == 0) {
          contents = null; // back from ""
        }
      }
      if (length == null && contents == null) {
        resultBuf.append(" ");
      } else if (length == null && contents != null) {
        resultBuf.append(" " + contents);
      } else if (length != null && contents == null) {
        resultBuf.append(" " + length);
      } else {
        assert length != null && contents != null;
        resultBuf.append(" (AND " + length + " " + contents + ")");
      }

      }
    if (num_elts > 1) {
      result = "(OR" + resultBuf.toString() + ")";
    } else if (num_elts == 1) {
      // chop leading space
      result = resultBuf.toString().substring(1);
    } else if (num_elts == 0) {
      return format_too_few_samples(OutputFormat.SIMPLIFY, null);
    } else {
      throw new Error("this can't happen");
      // result = null;
    }
    if (result.trim().equals("")) {
      result = "format_simplify() failed on a weird OneOf";
    }

    if (result.indexOf("format_simplify") == -1) {
      daikon.simplify.SimpUtil.assert_well_formed(result);
    }
    return result;
  }

  @Override
  public InvariantStatus add_modified(double @Interned [] a, int count) {
    return runValue(a, count, true);
  }

  @Override
  public InvariantStatus check_modified(double @Interned [] a, int count) {
    return runValue(a, count, false);
  }

  private InvariantStatus runValue(double @Interned [] v, int count, boolean mutate) {
    InvariantStatus status;
    if (mutate) {
      status = add_mod_elem(v, count);
    } else {
      status = check_mod_elem(v, count);
    }
    if (status == InvariantStatus.FALSIFIED) {
      if (logOn() && mutate) {
        StringBuilder eltString = new StringBuilder();
        for (int i = 0; i < num_elts; i++) {
          eltString.append(Arrays.toString(elts[i]) + " ");
        }
        log("destroyed on sample %s previous vals = {%s} num_elts = %s",
             Arrays.toString(v), eltString, num_elts);
      }
      return InvariantStatus.FALSIFIED;
    }
    return status;
  }

  /**
   * Adds a single sample to the invariant. Returns
   * the appropriate InvariantStatus from the result
   * of adding the sample to this.
   */
  public InvariantStatus add_mod_elem(double @Interned [] v, int count) {
    InvariantStatus status = check_mod_elem(v, count);
    if (status == InvariantStatus.WEAKENED) {
      elts[num_elts] = v;
      num_elts++;
    }
    return status;
  }

  /**
   * Checks a single sample to the invariant. Returns
   * the appropriate InvariantStatus from the result
   * of adding the sample to this.
   */
  public InvariantStatus check_mod_elem(double @Interned [] v, int count) {

    // Look for v in our list of previously seen values.  If it's
    // found, we're all set.
    for (int i = 0; i < num_elts; i++) {
      // if (logDetail())
      //  log ("add_modified (" + v + ")");
      if (((elts[i]) == ( v))) {
        return InvariantStatus.NO_CHANGE;
      }
    }

    if (num_elts == dkconfig_size) {
      return InvariantStatus.FALSIFIED;
    }

    return InvariantStatus.WEAKENED;
  }

  @Override
  protected double computeConfidence() {
    // This is not ideal.
    if (num_elts == 0) {
      return Invariant.CONFIDENCE_UNJUSTIFIED;
    } else {
      return Invariant.CONFIDENCE_JUSTIFIED;
    }
  }

  @Pure
  @Override
  public @Nullable DiscardInfo isObviousStatically(VarInfo[] vis) {
    // Static constants are necessarily OneOf precisely one value.
    // This removes static constants from the output, which might not be
    // desirable if the user doesn't know their actual value.
    if (vis[0].isStaticConstant()) {
      assert num_elts <= 1;
      return new DiscardInfo(this, DiscardCode.obvious, vis[0].name() + " is a static constant.");
    }
    return super.isObviousStatically(vis);
  }

  /** {@inheritDoc} */
  @Override
  public @Nullable DiscardInfo isObviousDynamically(VarInfo[] vis) {
    DiscardInfo super_result = super.isObviousDynamically(vis);
    if (super_result != null) {
      return super_result;
    }

    VarInfo v = vis[0];

    // We can check if all values in the element sequence match
    // with the ones we know about (useful for booleans and numeric
    // enumerations).
    if (v.aux.hasValue(VarInfoAux.VALID_VALUES)
        && v.aux.hasValue(VarInfoAux.MAXIMUM_LENGTH)
        && v.aux.hasValue(VarInfoAux.MINIMUM_LENGTH)
        && v.aux.getInt(VarInfoAux.MAXIMUM_LENGTH) == 1
        && v.aux.getInt(VarInfoAux.MINIMUM_LENGTH) == 1) {

      String[] vsValidValues       = v.aux.getList(VarInfoAux.VALID_VALUES);
      Set<Double> setValidValues = new TreeSet<>();
      for (String s : vsValidValues) {
        setValidValues.add(Double.valueOf(s));
      }
      Set<Double> setValuesInvariant = new TreeSet<>();
      for (double @Interned [] e : elts) {
        if (e == null) {
  continue;
}
        for (Double b : e) {
          setValuesInvariant.add(b);
        }
      }
      if (setValidValues.equals(setValuesInvariant)) {
        return new DiscardInfo(this, DiscardCode.obvious,
          "The value list matches the allowed value list");
      }
    }

    return null;
  }

  /**
   * Oneof can merge different formulas from lower points to create a single formula at an upper
   * point.
   */
  @Override
  public boolean mergeFormulasOk() {
    return true;
  }

  @Pure
  @Override
  public boolean isSameFormula(Invariant o) {
    OneOfFloatSequence other = (OneOfFloatSequence) o;
    if (num_elts != other.num_elts) {
      return false;
    }
    if (num_elts == 0 && other.num_elts == 0) {
      return true;
    }

    sort_rep();
    other.sort_rep();

    for (int i = 0; i < num_elts; i++) {
      if (!((elts[i]) == (other.elts[i]))) {
        return false;
      }
    }

    return true;
  }

  @Pure
  @Override
  public boolean isExclusiveFormula(Invariant o) {
    if (o instanceof OneOfFloatSequence) {
      OneOfFloatSequence other = (OneOfFloatSequence) o;

      if (num_elts == 0 || other.num_elts == 0) {
        return false;
      }
      for (int i = 0; i < num_elts; i++) {
        for (int j = 0; j < other.num_elts; j++) {
          if (((elts[i]) == (other.elts[j]))) // elements are interned
            return false;
        }
      }

      return true;
    }

    return false;
  }

  @Override
  public boolean hasUninterestingConstant() {

    return false;
  }

  @Pure
  @Override
  public boolean isExact() {
    return num_elts == 1;
  }

  // Look up a previously instantiated invariant.
  public static @Nullable OneOfFloatSequence find(PptSlice ppt) {
    assert ppt.arity() == 1;
    for (Invariant inv : ppt.invs) {
      if (inv instanceof OneOfFloatSequence) {
        return (OneOfFloatSequence) inv;
      }
    }
    return null;
  }

  // Interning is lost when an object is serialized and deserialized.
  // Manually re-intern any interned fields upon deserialization.
  private void readObject(ObjectInputStream in) throws IOException,
    ClassNotFoundException {
    in.defaultReadObject();

    for (int i = 0; i < num_elts; i++) {
      elts[i] = Intern.intern(elts[i]);
    }
  }

  /**
   * Merge the invariants in invs to form a new invariant. Each must be a OneOfFloatSequence invariant.
   * This code finds all of the oneof values from each of the invariants and returns the merged
   * invariant (if any).
   *
   * @param invs list of invariants to merge. The invariants must all be of the same type and should
   *     come from the children of parent_ppt. They should also all be permuted to match the
   *     variable order in parent_ppt.
   * @param parent_ppt slice that will contain the new invariant
   */
  @Override
  public @Nullable Invariant merge(List<Invariant> invs, PptSlice parent_ppt) {

    // Create the initial parent invariant from the first child
    OneOfFloatSequence  first = (OneOfFloatSequence) invs.get(0);
    OneOfFloatSequence result = first.clone();
    result.ppt = parent_ppt;

      for (int i = 0; i < result.num_elts; i++) {
        result.elts[i] = Intern.intern(result.elts[i]);
      }

    // Loop through the rest of the child invariants
    for (int i = 1; i < invs.size(); i++ ) {

      // Get this invariant
      OneOfFloatSequence inv = (OneOfFloatSequence) invs.get(i);

      // Loop through each distinct value found in this child and add
      // it to the parent.  If the invariant is falsified, there is no parent
      // invariant
      for (int j = 0; j < inv.num_elts; j++) {
        double @Interned [] val = inv.elts[j];

        val = Intern.intern(val);

        InvariantStatus status = result.add_mod_elem(val, 1);
        if (status == InvariantStatus.FALSIFIED) {

          result.log("%s", "child value '" + Arrays.toString(val) + "' destroyed oneof");

          return null;
        }
      }
    }

    result.log("Merged '%s' from %s child invariants", result.format(), invs.size());
    return result;
  }

  /**
   * Setup the invariant with the specified elements. Normally used when searching for a specified
   * OneOf. The elements of vals are not necessarily interned; this method interns each element.
   */
  public void set_one_of_val(double[][] vals) {

    num_elts = vals.length;
    for (int i = 0; i < num_elts; i++) {
      elts[i] = Intern.intern(vals[i]);
    }
  }

  /**
   * Returns true if every element in this invariant is contained in the specified state. For
   * example if x = 1 and the state contains 1 and 2, true will be returned.
   */
  @Override
  public boolean state_match(Object state) {

    if (num_elts == 0) {
      return false;
    }

    if (!(state instanceof double[] @Interned [])) {
      // Daikon is about to crash.  Produce some debugging output.
      System.out.printf("state = %s [%s]%n", state, state.getClass());
      System.out.printf("problem with %s%n", this);
    }
    double[] @Interned [] e = (double[] @Interned []) state;
    for (int i = 0; i < num_elts; i++) {
      boolean match = false;
      for (int j = 0; j < e.length; j++) {
        if (elts[i] == e[j]) {
          match = true;
          break;
        }
      }
      if (!match) {
        return false;
      }
    }
    return true;
  }

}
