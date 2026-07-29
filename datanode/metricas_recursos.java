// ORM class for table 'metricas_recursos'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Wed Jul 22 01:16:26 UTC 2026
// For connector: org.apache.sqoop.manager.MySQLManager
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;
import com.cloudera.sqoop.lib.JdbcWritableBridge;
import com.cloudera.sqoop.lib.DelimiterSet;
import com.cloudera.sqoop.lib.FieldFormatter;
import com.cloudera.sqoop.lib.RecordParser;
import com.cloudera.sqoop.lib.BooleanParser;
import com.cloudera.sqoop.lib.BlobRef;
import com.cloudera.sqoop.lib.ClobRef;
import com.cloudera.sqoop.lib.LargeObjectLoader;
import com.cloudera.sqoop.lib.SqoopRecord;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class metricas_recursos extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("cpu_uso", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        metricas_recursos.this.cpu_uso = (java.math.BigDecimal)value;
      }
    });
    setters.put("disco_uso", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        metricas_recursos.this.disco_uso = (java.math.BigDecimal)value;
      }
    });
    setters.put("fecha", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        metricas_recursos.this.fecha = (java.sql.Timestamp)value;
      }
    });
    setters.put("id_metrica", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        metricas_recursos.this.id_metrica = (Integer)value;
      }
    });
    setters.put("ram_uso", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        metricas_recursos.this.ram_uso = (java.math.BigDecimal)value;
      }
    });
    setters.put("servidor_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        metricas_recursos.this.servidor_id = (Integer)value;
      }
    });
  }
  public metricas_recursos() {
    init0();
  }
  private java.math.BigDecimal cpu_uso;
  public java.math.BigDecimal get_cpu_uso() {
    return cpu_uso;
  }
  public void set_cpu_uso(java.math.BigDecimal cpu_uso) {
    this.cpu_uso = cpu_uso;
  }
  public metricas_recursos with_cpu_uso(java.math.BigDecimal cpu_uso) {
    this.cpu_uso = cpu_uso;
    return this;
  }
  private java.math.BigDecimal disco_uso;
  public java.math.BigDecimal get_disco_uso() {
    return disco_uso;
  }
  public void set_disco_uso(java.math.BigDecimal disco_uso) {
    this.disco_uso = disco_uso;
  }
  public metricas_recursos with_disco_uso(java.math.BigDecimal disco_uso) {
    this.disco_uso = disco_uso;
    return this;
  }
  private java.sql.Timestamp fecha;
  public java.sql.Timestamp get_fecha() {
    return fecha;
  }
  public void set_fecha(java.sql.Timestamp fecha) {
    this.fecha = fecha;
  }
  public metricas_recursos with_fecha(java.sql.Timestamp fecha) {
    this.fecha = fecha;
    return this;
  }
  private Integer id_metrica;
  public Integer get_id_metrica() {
    return id_metrica;
  }
  public void set_id_metrica(Integer id_metrica) {
    this.id_metrica = id_metrica;
  }
  public metricas_recursos with_id_metrica(Integer id_metrica) {
    this.id_metrica = id_metrica;
    return this;
  }
  private java.math.BigDecimal ram_uso;
  public java.math.BigDecimal get_ram_uso() {
    return ram_uso;
  }
  public void set_ram_uso(java.math.BigDecimal ram_uso) {
    this.ram_uso = ram_uso;
  }
  public metricas_recursos with_ram_uso(java.math.BigDecimal ram_uso) {
    this.ram_uso = ram_uso;
    return this;
  }
  private Integer servidor_id;
  public Integer get_servidor_id() {
    return servidor_id;
  }
  public void set_servidor_id(Integer servidor_id) {
    this.servidor_id = servidor_id;
  }
  public metricas_recursos with_servidor_id(Integer servidor_id) {
    this.servidor_id = servidor_id;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof metricas_recursos)) {
      return false;
    }
    metricas_recursos that = (metricas_recursos) o;
    boolean equal = true;
    equal = equal && (this.cpu_uso == null ? that.cpu_uso == null : this.cpu_uso.equals(that.cpu_uso));
    equal = equal && (this.disco_uso == null ? that.disco_uso == null : this.disco_uso.equals(that.disco_uso));
    equal = equal && (this.fecha == null ? that.fecha == null : this.fecha.equals(that.fecha));
    equal = equal && (this.id_metrica == null ? that.id_metrica == null : this.id_metrica.equals(that.id_metrica));
    equal = equal && (this.ram_uso == null ? that.ram_uso == null : this.ram_uso.equals(that.ram_uso));
    equal = equal && (this.servidor_id == null ? that.servidor_id == null : this.servidor_id.equals(that.servidor_id));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof metricas_recursos)) {
      return false;
    }
    metricas_recursos that = (metricas_recursos) o;
    boolean equal = true;
    equal = equal && (this.cpu_uso == null ? that.cpu_uso == null : this.cpu_uso.equals(that.cpu_uso));
    equal = equal && (this.disco_uso == null ? that.disco_uso == null : this.disco_uso.equals(that.disco_uso));
    equal = equal && (this.fecha == null ? that.fecha == null : this.fecha.equals(that.fecha));
    equal = equal && (this.id_metrica == null ? that.id_metrica == null : this.id_metrica.equals(that.id_metrica));
    equal = equal && (this.ram_uso == null ? that.ram_uso == null : this.ram_uso.equals(that.ram_uso));
    equal = equal && (this.servidor_id == null ? that.servidor_id == null : this.servidor_id.equals(that.servidor_id));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.cpu_uso = JdbcWritableBridge.readBigDecimal(1, __dbResults);
    this.disco_uso = JdbcWritableBridge.readBigDecimal(2, __dbResults);
    this.fecha = JdbcWritableBridge.readTimestamp(3, __dbResults);
    this.id_metrica = JdbcWritableBridge.readInteger(4, __dbResults);
    this.ram_uso = JdbcWritableBridge.readBigDecimal(5, __dbResults);
    this.servidor_id = JdbcWritableBridge.readInteger(6, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.cpu_uso = JdbcWritableBridge.readBigDecimal(1, __dbResults);
    this.disco_uso = JdbcWritableBridge.readBigDecimal(2, __dbResults);
    this.fecha = JdbcWritableBridge.readTimestamp(3, __dbResults);
    this.id_metrica = JdbcWritableBridge.readInteger(4, __dbResults);
    this.ram_uso = JdbcWritableBridge.readBigDecimal(5, __dbResults);
    this.servidor_id = JdbcWritableBridge.readInteger(6, __dbResults);
  }
  public void loadLargeObjects(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void loadLargeObjects0(LargeObjectLoader __loader)
      throws SQLException, IOException, InterruptedException {
  }
  public void write(PreparedStatement __dbStmt) throws SQLException {
    write(__dbStmt, 0);
  }

  public int write(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeBigDecimal(cpu_uso, 1 + __off, 3, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(disco_uso, 2 + __off, 3, __dbStmt);
    JdbcWritableBridge.writeTimestamp(fecha, 3 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeInteger(id_metrica, 4 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(ram_uso, 5 + __off, 3, __dbStmt);
    JdbcWritableBridge.writeInteger(servidor_id, 6 + __off, 4, __dbStmt);
    return 6;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeBigDecimal(cpu_uso, 1 + __off, 3, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(disco_uso, 2 + __off, 3, __dbStmt);
    JdbcWritableBridge.writeTimestamp(fecha, 3 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeInteger(id_metrica, 4 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(ram_uso, 5 + __off, 3, __dbStmt);
    JdbcWritableBridge.writeInteger(servidor_id, 6 + __off, 4, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.cpu_uso = null;
    } else {
    this.cpu_uso = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.disco_uso = null;
    } else {
    this.disco_uso = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.fecha = null;
    } else {
    this.fecha = new Timestamp(__dataIn.readLong());
    this.fecha.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.id_metrica = null;
    } else {
    this.id_metrica = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.ram_uso = null;
    } else {
    this.ram_uso = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.servidor_id = null;
    } else {
    this.servidor_id = Integer.valueOf(__dataIn.readInt());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.cpu_uso) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.cpu_uso, __dataOut);
    }
    if (null == this.disco_uso) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.disco_uso, __dataOut);
    }
    if (null == this.fecha) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.fecha.getTime());
    __dataOut.writeInt(this.fecha.getNanos());
    }
    if (null == this.id_metrica) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.id_metrica);
    }
    if (null == this.ram_uso) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.ram_uso, __dataOut);
    }
    if (null == this.servidor_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.servidor_id);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.cpu_uso) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.cpu_uso, __dataOut);
    }
    if (null == this.disco_uso) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.disco_uso, __dataOut);
    }
    if (null == this.fecha) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.fecha.getTime());
    __dataOut.writeInt(this.fecha.getNanos());
    }
    if (null == this.id_metrica) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.id_metrica);
    }
    if (null == this.ram_uso) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.ram_uso, __dataOut);
    }
    if (null == this.servidor_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.servidor_id);
    }
  }
  private static final DelimiterSet __outputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  public String toString() {
    return toString(__outputDelimiters, true);
  }
  public String toString(DelimiterSet delimiters) {
    return toString(delimiters, true);
  }
  public String toString(boolean useRecordDelim) {
    return toString(__outputDelimiters, useRecordDelim);
  }
  public String toString(DelimiterSet delimiters, boolean useRecordDelim) {
    StringBuilder __sb = new StringBuilder();
    char fieldDelim = delimiters.getFieldsTerminatedBy();
    __sb.append(FieldFormatter.escapeAndEnclose(cpu_uso==null?"null":cpu_uso.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(disco_uso==null?"null":disco_uso.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(fecha==null?"null":"" + fecha, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(id_metrica==null?"null":"" + id_metrica, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ram_uso==null?"null":ram_uso.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(servidor_id==null?"null":"" + servidor_id, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(cpu_uso==null?"null":cpu_uso.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(disco_uso==null?"null":disco_uso.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(fecha==null?"null":"" + fecha, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(id_metrica==null?"null":"" + id_metrica, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ram_uso==null?"null":ram_uso.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(servidor_id==null?"null":"" + servidor_id, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 44, (char) 10, (char) 0, (char) 0, false);
  private RecordParser __parser;
  public void parse(Text __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharSequence __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(byte [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(char [] __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(ByteBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  public void parse(CharBuffer __record) throws RecordParser.ParseError {
    if (null == this.__parser) {
      this.__parser = new RecordParser(__inputDelimiters);
    }
    List<String> __fields = this.__parser.parseRecord(__record);
    __loadFromFields(__fields);
  }

  private void __loadFromFields(List<String> fields) {
    Iterator<String> __it = fields.listIterator();
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.cpu_uso = null; } else {
      this.cpu_uso = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.disco_uso = null; } else {
      this.disco_uso = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.fecha = null; } else {
      this.fecha = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id_metrica = null; } else {
      this.id_metrica = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.ram_uso = null; } else {
      this.ram_uso = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.servidor_id = null; } else {
      this.servidor_id = Integer.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.cpu_uso = null; } else {
      this.cpu_uso = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.disco_uso = null; } else {
      this.disco_uso = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.fecha = null; } else {
      this.fecha = java.sql.Timestamp.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id_metrica = null; } else {
      this.id_metrica = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.ram_uso = null; } else {
      this.ram_uso = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.servidor_id = null; } else {
      this.servidor_id = Integer.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    metricas_recursos o = (metricas_recursos) super.clone();
    o.fecha = (o.fecha != null) ? (java.sql.Timestamp) o.fecha.clone() : null;
    return o;
  }

  public void clone0(metricas_recursos o) throws CloneNotSupportedException {
    o.fecha = (o.fecha != null) ? (java.sql.Timestamp) o.fecha.clone() : null;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("cpu_uso", this.cpu_uso);
    __sqoop$field_map.put("disco_uso", this.disco_uso);
    __sqoop$field_map.put("fecha", this.fecha);
    __sqoop$field_map.put("id_metrica", this.id_metrica);
    __sqoop$field_map.put("ram_uso", this.ram_uso);
    __sqoop$field_map.put("servidor_id", this.servidor_id);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("cpu_uso", this.cpu_uso);
    __sqoop$field_map.put("disco_uso", this.disco_uso);
    __sqoop$field_map.put("fecha", this.fecha);
    __sqoop$field_map.put("id_metrica", this.id_metrica);
    __sqoop$field_map.put("ram_uso", this.ram_uso);
    __sqoop$field_map.put("servidor_id", this.servidor_id);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
