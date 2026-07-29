// ORM class for table 'transacciones'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Wed Jul 22 01:16:47 UTC 2026
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

public class transacciones extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("canal_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        transacciones.this.canal_id = (Integer)value;
      }
    });
    setters.put("estado", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        transacciones.this.estado = (String)value;
      }
    });
    setters.put("fecha", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        transacciones.this.fecha = (java.sql.Timestamp)value;
      }
    });
    setters.put("id_transaccion", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        transacciones.this.id_transaccion = (Integer)value;
      }
    });
    setters.put("monto", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        transacciones.this.monto = (java.math.BigDecimal)value;
      }
    });
  }
  public transacciones() {
    init0();
  }
  private Integer canal_id;
  public Integer get_canal_id() {
    return canal_id;
  }
  public void set_canal_id(Integer canal_id) {
    this.canal_id = canal_id;
  }
  public transacciones with_canal_id(Integer canal_id) {
    this.canal_id = canal_id;
    return this;
  }
  private String estado;
  public String get_estado() {
    return estado;
  }
  public void set_estado(String estado) {
    this.estado = estado;
  }
  public transacciones with_estado(String estado) {
    this.estado = estado;
    return this;
  }
  private java.sql.Timestamp fecha;
  public java.sql.Timestamp get_fecha() {
    return fecha;
  }
  public void set_fecha(java.sql.Timestamp fecha) {
    this.fecha = fecha;
  }
  public transacciones with_fecha(java.sql.Timestamp fecha) {
    this.fecha = fecha;
    return this;
  }
  private Integer id_transaccion;
  public Integer get_id_transaccion() {
    return id_transaccion;
  }
  public void set_id_transaccion(Integer id_transaccion) {
    this.id_transaccion = id_transaccion;
  }
  public transacciones with_id_transaccion(Integer id_transaccion) {
    this.id_transaccion = id_transaccion;
    return this;
  }
  private java.math.BigDecimal monto;
  public java.math.BigDecimal get_monto() {
    return monto;
  }
  public void set_monto(java.math.BigDecimal monto) {
    this.monto = monto;
  }
  public transacciones with_monto(java.math.BigDecimal monto) {
    this.monto = monto;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof transacciones)) {
      return false;
    }
    transacciones that = (transacciones) o;
    boolean equal = true;
    equal = equal && (this.canal_id == null ? that.canal_id == null : this.canal_id.equals(that.canal_id));
    equal = equal && (this.estado == null ? that.estado == null : this.estado.equals(that.estado));
    equal = equal && (this.fecha == null ? that.fecha == null : this.fecha.equals(that.fecha));
    equal = equal && (this.id_transaccion == null ? that.id_transaccion == null : this.id_transaccion.equals(that.id_transaccion));
    equal = equal && (this.monto == null ? that.monto == null : this.monto.equals(that.monto));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof transacciones)) {
      return false;
    }
    transacciones that = (transacciones) o;
    boolean equal = true;
    equal = equal && (this.canal_id == null ? that.canal_id == null : this.canal_id.equals(that.canal_id));
    equal = equal && (this.estado == null ? that.estado == null : this.estado.equals(that.estado));
    equal = equal && (this.fecha == null ? that.fecha == null : this.fecha.equals(that.fecha));
    equal = equal && (this.id_transaccion == null ? that.id_transaccion == null : this.id_transaccion.equals(that.id_transaccion));
    equal = equal && (this.monto == null ? that.monto == null : this.monto.equals(that.monto));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.canal_id = JdbcWritableBridge.readInteger(1, __dbResults);
    this.estado = JdbcWritableBridge.readString(2, __dbResults);
    this.fecha = JdbcWritableBridge.readTimestamp(3, __dbResults);
    this.id_transaccion = JdbcWritableBridge.readInteger(4, __dbResults);
    this.monto = JdbcWritableBridge.readBigDecimal(5, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.canal_id = JdbcWritableBridge.readInteger(1, __dbResults);
    this.estado = JdbcWritableBridge.readString(2, __dbResults);
    this.fecha = JdbcWritableBridge.readTimestamp(3, __dbResults);
    this.id_transaccion = JdbcWritableBridge.readInteger(4, __dbResults);
    this.monto = JdbcWritableBridge.readBigDecimal(5, __dbResults);
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
    JdbcWritableBridge.writeInteger(canal_id, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(estado, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeTimestamp(fecha, 3 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeInteger(id_transaccion, 4 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(monto, 5 + __off, 3, __dbStmt);
    return 5;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(canal_id, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(estado, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeTimestamp(fecha, 3 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeInteger(id_transaccion, 4 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(monto, 5 + __off, 3, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.canal_id = null;
    } else {
    this.canal_id = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.estado = null;
    } else {
    this.estado = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.fecha = null;
    } else {
    this.fecha = new Timestamp(__dataIn.readLong());
    this.fecha.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.id_transaccion = null;
    } else {
    this.id_transaccion = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.monto = null;
    } else {
    this.monto = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.canal_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.canal_id);
    }
    if (null == this.estado) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, estado);
    }
    if (null == this.fecha) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.fecha.getTime());
    __dataOut.writeInt(this.fecha.getNanos());
    }
    if (null == this.id_transaccion) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.id_transaccion);
    }
    if (null == this.monto) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.monto, __dataOut);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.canal_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.canal_id);
    }
    if (null == this.estado) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, estado);
    }
    if (null == this.fecha) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.fecha.getTime());
    __dataOut.writeInt(this.fecha.getNanos());
    }
    if (null == this.id_transaccion) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.id_transaccion);
    }
    if (null == this.monto) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.monto, __dataOut);
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
    __sb.append(FieldFormatter.escapeAndEnclose(canal_id==null?"null":"" + canal_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(estado==null?"null":estado, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(fecha==null?"null":"" + fecha, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(id_transaccion==null?"null":"" + id_transaccion, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(monto==null?"null":monto.toPlainString(), delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(canal_id==null?"null":"" + canal_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(estado==null?"null":estado, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(fecha==null?"null":"" + fecha, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(id_transaccion==null?"null":"" + id_transaccion, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(monto==null?"null":monto.toPlainString(), delimiters));
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.canal_id = null; } else {
      this.canal_id = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.estado = null; } else {
      this.estado = __cur_str;
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id_transaccion = null; } else {
      this.id_transaccion = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.monto = null; } else {
      this.monto = new java.math.BigDecimal(__cur_str);
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.canal_id = null; } else {
      this.canal_id = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.estado = null; } else {
      this.estado = __cur_str;
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id_transaccion = null; } else {
      this.id_transaccion = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.monto = null; } else {
      this.monto = new java.math.BigDecimal(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    transacciones o = (transacciones) super.clone();
    o.fecha = (o.fecha != null) ? (java.sql.Timestamp) o.fecha.clone() : null;
    return o;
  }

  public void clone0(transacciones o) throws CloneNotSupportedException {
    o.fecha = (o.fecha != null) ? (java.sql.Timestamp) o.fecha.clone() : null;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("canal_id", this.canal_id);
    __sqoop$field_map.put("estado", this.estado);
    __sqoop$field_map.put("fecha", this.fecha);
    __sqoop$field_map.put("id_transaccion", this.id_transaccion);
    __sqoop$field_map.put("monto", this.monto);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("canal_id", this.canal_id);
    __sqoop$field_map.put("estado", this.estado);
    __sqoop$field_map.put("fecha", this.fecha);
    __sqoop$field_map.put("id_transaccion", this.id_transaccion);
    __sqoop$field_map.put("monto", this.monto);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
