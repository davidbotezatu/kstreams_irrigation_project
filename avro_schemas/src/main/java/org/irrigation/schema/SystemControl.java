/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package org.irrigation.schema;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class SystemControl extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 5877704321239191220L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"SystemControl\",\"namespace\":\"org.irrigation.schema\",\"fields\":[{\"name\":\"act\",\"type\":\"boolean\"},{\"name\":\"RV\",\"type\":\"int\"},{\"name\":\"PV\",\"type\":[\"null\",\"int\"],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<SystemControl> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<SystemControl> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<SystemControl> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<SystemControl> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<SystemControl> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this SystemControl to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a SystemControl from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a SystemControl instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static SystemControl fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private boolean act;
  private int RV;
  private java.lang.Integer PV;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public SystemControl() {}

  /**
   * All-args constructor.
   * @param act The new value for act
   * @param RV The new value for RV
   * @param PV The new value for PV
   */
  public SystemControl(java.lang.Boolean act, java.lang.Integer RV, java.lang.Integer PV) {
    this.act = act;
    this.RV = RV;
    this.PV = PV;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return act;
    case 1: return RV;
    case 2: return PV;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: act = (java.lang.Boolean)value$; break;
    case 1: RV = (java.lang.Integer)value$; break;
    case 2: PV = (java.lang.Integer)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'act' field.
   * @return The value of the 'act' field.
   */
  public boolean getAct() {
    return act;
  }


  /**
   * Sets the value of the 'act' field.
   * @param value the value to set.
   */
  public void setAct(boolean value) {
    this.act = value;
  }

  /**
   * Gets the value of the 'RV' field.
   * @return The value of the 'RV' field.
   */
  public int getRV() {
    return RV;
  }


  /**
   * Sets the value of the 'RV' field.
   * @param value the value to set.
   */
  public void setRV(int value) {
    this.RV = value;
  }

  /**
   * Gets the value of the 'PV' field.
   * @return The value of the 'PV' field.
   */
  public java.lang.Integer getPV() {
    return PV;
  }


  /**
   * Sets the value of the 'PV' field.
   * @param value the value to set.
   */
  public void setPV(java.lang.Integer value) {
    this.PV = value;
  }

  /**
   * Creates a new SystemControl RecordBuilder.
   * @return A new SystemControl RecordBuilder
   */
  public static org.irrigation.schema.SystemControl.Builder newBuilder() {
    return new org.irrigation.schema.SystemControl.Builder();
  }

  /**
   * Creates a new SystemControl RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new SystemControl RecordBuilder
   */
  public static org.irrigation.schema.SystemControl.Builder newBuilder(org.irrigation.schema.SystemControl.Builder other) {
    if (other == null) {
      return new org.irrigation.schema.SystemControl.Builder();
    } else {
      return new org.irrigation.schema.SystemControl.Builder(other);
    }
  }

  /**
   * Creates a new SystemControl RecordBuilder by copying an existing SystemControl instance.
   * @param other The existing instance to copy.
   * @return A new SystemControl RecordBuilder
   */
  public static org.irrigation.schema.SystemControl.Builder newBuilder(org.irrigation.schema.SystemControl other) {
    if (other == null) {
      return new org.irrigation.schema.SystemControl.Builder();
    } else {
      return new org.irrigation.schema.SystemControl.Builder(other);
    }
  }

  /**
   * RecordBuilder for SystemControl instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<SystemControl>
    implements org.apache.avro.data.RecordBuilder<SystemControl> {

    private boolean act;
    private int RV;
    private java.lang.Integer PV;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.irrigation.schema.SystemControl.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.act)) {
        this.act = data().deepCopy(fields()[0].schema(), other.act);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.RV)) {
        this.RV = data().deepCopy(fields()[1].schema(), other.RV);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.PV)) {
        this.PV = data().deepCopy(fields()[2].schema(), other.PV);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing SystemControl instance
     * @param other The existing instance to copy.
     */
    private Builder(org.irrigation.schema.SystemControl other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.act)) {
        this.act = data().deepCopy(fields()[0].schema(), other.act);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.RV)) {
        this.RV = data().deepCopy(fields()[1].schema(), other.RV);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.PV)) {
        this.PV = data().deepCopy(fields()[2].schema(), other.PV);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'act' field.
      * @return The value.
      */
    public boolean getAct() {
      return act;
    }


    /**
      * Sets the value of the 'act' field.
      * @param value The value of 'act'.
      * @return This builder.
      */
    public org.irrigation.schema.SystemControl.Builder setAct(boolean value) {
      validate(fields()[0], value);
      this.act = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'act' field has been set.
      * @return True if the 'act' field has been set, false otherwise.
      */
    public boolean hasAct() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'act' field.
      * @return This builder.
      */
    public org.irrigation.schema.SystemControl.Builder clearAct() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'RV' field.
      * @return The value.
      */
    public int getRV() {
      return RV;
    }


    /**
      * Sets the value of the 'RV' field.
      * @param value The value of 'RV'.
      * @return This builder.
      */
    public org.irrigation.schema.SystemControl.Builder setRV(int value) {
      validate(fields()[1], value);
      this.RV = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'RV' field has been set.
      * @return True if the 'RV' field has been set, false otherwise.
      */
    public boolean hasRV() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'RV' field.
      * @return This builder.
      */
    public org.irrigation.schema.SystemControl.Builder clearRV() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'PV' field.
      * @return The value.
      */
    public java.lang.Integer getPV() {
      return PV;
    }


    /**
      * Sets the value of the 'PV' field.
      * @param value The value of 'PV'.
      * @return This builder.
      */
    public org.irrigation.schema.SystemControl.Builder setPV(java.lang.Integer value) {
      validate(fields()[2], value);
      this.PV = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'PV' field has been set.
      * @return True if the 'PV' field has been set, false otherwise.
      */
    public boolean hasPV() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'PV' field.
      * @return This builder.
      */
    public org.irrigation.schema.SystemControl.Builder clearPV() {
      PV = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public SystemControl build() {
      try {
        SystemControl record = new SystemControl();
        record.act = fieldSetFlags()[0] ? this.act : (java.lang.Boolean) defaultValue(fields()[0]);
        record.RV = fieldSetFlags()[1] ? this.RV : (java.lang.Integer) defaultValue(fields()[1]);
        record.PV = fieldSetFlags()[2] ? this.PV : (java.lang.Integer) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<SystemControl>
    WRITER$ = (org.apache.avro.io.DatumWriter<SystemControl>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<SystemControl>
    READER$ = (org.apache.avro.io.DatumReader<SystemControl>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeBoolean(this.act);

    out.writeInt(this.RV);

    if (this.PV == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeInt(this.PV);
    }

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.act = in.readBoolean();

      this.RV = in.readInt();

      if (in.readIndex() != 1) {
        in.readNull();
        this.PV = null;
      } else {
        this.PV = in.readInt();
      }

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.act = in.readBoolean();
          break;

        case 1:
          this.RV = in.readInt();
          break;

        case 2:
          if (in.readIndex() != 1) {
            in.readNull();
            this.PV = null;
          } else {
            this.PV = in.readInt();
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










