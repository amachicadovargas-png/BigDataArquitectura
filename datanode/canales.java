// ORM class for table 'canales'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Fri Jul 24 01:26:04 UTC 2026
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

public class canales extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("id_canal", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        canales.this.id_canal = (Integer)value;
      }
    });
    setters.put("tipo_canal", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        canales.this.tipo_canal = (String)value;
      }
    });
    setters.put("ubicacion", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        canales.this.ubicacion = (String)value;
      }
    });
    setters.put("disponibilidad", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        canales.this.disponibilidad = (java.math.BigDecimal)value;
      }
    });
    setters.put("numero_errores", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        canales.this.numero_errores = (Integer)value;
      }
    });
  }
  public canales() {
    init0();
  }
  private Integer id_canal;
  public Integer get_id_canal() {
    return id_canal;
  }
  public void set_id_canal(Integer id_canal) {
    this.id_canal = id_canal;
  }
  public canales with_id_canal(Integer id_canal) {
    this.id_canal = id_canal;
    return this;
  }
  private String tipo_canal;
  public String get_tipo_canal() {
    return tipo_canal;
  }
  public void set_tipo_canal(String tipo_canal) {
    this.tipo_canal = tipo_canal;
  }
  public canales with_tipo_canal(String tipo_canal) {
    this.tipo_canal = tipo_canal;
    return this;
  }
  private String ubicacion;
  public String get_ubicacion() {
    return ubicacion;
  }
  public void set_ubicacion(String ubicacion) {
    this.ubicacion = ubicacion;
  }
  public canales with_ubicacion(String ubicacion) {
    this.ubicacion = ubicacion;
    return this;
  }
  private java.math.BigDecimal disponibilidad;
  public java.math.BigDecimal get_disponibilidad() {
    return disponibilidad;
  }
  public void set_disponibilidad(java.math.BigDecimal disponibilidad) {
    this.disponibilidad = disponibilidad;
  }
  public canales with_disponibilidad(java.math.BigDecimal disponibilidad) {
    this.disponibilidad = disponibilidad;
    return this;
  }
  private Integer numero_errores;
  public Integer get_numero_errores() {
    return numero_errores;
  }
  public void set_numero_errores(Integer numero_errores) {
    this.numero_errores = numero_errores;
  }
  public canales with_numero_errores(Integer numero_errores) {
    this.numero_errores = numero_errores;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof canales)) {
      return false;
    }
    canales that = (canales) o;
    boolean equal = true;
    equal = equal && (this.id_canal == null ? that.id_canal == null : this.id_canal.equals(that.id_canal));
    equal = equal && (this.tipo_canal == null ? that.tipo_canal == null : this.tipo_canal.equals(that.tipo_canal));
    equal = equal && (this.ubicacion == null ? that.ubicacion == null : this.ubicacion.equals(that.ubicacion));
    equal = equal && (this.disponibilidad == null ? that.disponibilidad == null : this.disponibilidad.equals(that.disponibilidad));
    equal = equal && (this.numero_errores == null ? that.numero_errores == null : this.numero_errores.equals(that.numero_errores));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof canales)) {
      return false;
    }
    canales that = (canales) o;
    boolean equal = true;
    equal = equal && (this.id_canal == null ? that.id_canal == null : this.id_canal.equals(that.id_canal));
    equal = equal && (this.tipo_canal == null ? that.tipo_canal == null : this.tipo_canal.equals(that.tipo_canal));
    equal = equal && (this.ubicacion == null ? that.ubicacion == null : this.ubicacion.equals(that.ubicacion));
    equal = equal && (this.disponibilidad == null ? that.disponibilidad == null : this.disponibilidad.equals(that.disponibilidad));
    equal = equal && (this.numero_errores == null ? that.numero_errores == null : this.numero_errores.equals(that.numero_errores));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.id_canal = JdbcWritableBridge.readInteger(1, __dbResults);
    this.tipo_canal = JdbcWritableBridge.readString(2, __dbResults);
    this.ubicacion = JdbcWritableBridge.readString(3, __dbResults);
    this.disponibilidad = JdbcWritableBridge.readBigDecimal(4, __dbResults);
    this.numero_errores = JdbcWritableBridge.readInteger(5, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.id_canal = JdbcWritableBridge.readInteger(1, __dbResults);
    this.tipo_canal = JdbcWritableBridge.readString(2, __dbResults);
    this.ubicacion = JdbcWritableBridge.readString(3, __dbResults);
    this.disponibilidad = JdbcWritableBridge.readBigDecimal(4, __dbResults);
    this.numero_errores = JdbcWritableBridge.readInteger(5, __dbResults);
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
    JdbcWritableBridge.writeInteger(id_canal, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(tipo_canal, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(ubicacion, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(disponibilidad, 4 + __off, 3, __dbStmt);
    JdbcWritableBridge.writeInteger(numero_errores, 5 + __off, 4, __dbStmt);
    return 5;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(id_canal, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(tipo_canal, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(ubicacion, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(disponibilidad, 4 + __off, 3, __dbStmt);
    JdbcWritableBridge.writeInteger(numero_errores, 5 + __off, 4, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.id_canal = null;
    } else {
    this.id_canal = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.tipo_canal = null;
    } else {
    this.tipo_canal = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.ubicacion = null;
    } else {
    this.ubicacion = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.disponibilidad = null;
    } else {
    this.disponibilidad = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.numero_errores = null;
    } else {
    this.numero_errores = Integer.valueOf(__dataIn.readInt());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.id_canal) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.id_canal);
    }
    if (null == this.tipo_canal) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, tipo_canal);
    }
    if (null == this.ubicacion) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, ubicacion);
    }
    if (null == this.disponibilidad) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.disponibilidad, __dataOut);
    }
    if (null == this.numero_errores) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.numero_errores);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.id_canal) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.id_canal);
    }
    if (null == this.tipo_canal) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, tipo_canal);
    }
    if (null == this.ubicacion) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, ubicacion);
    }
    if (null == this.disponibilidad) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.disponibilidad, __dataOut);
    }
    if (null == this.numero_errores) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.numero_errores);
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
    __sb.append(FieldFormatter.escapeAndEnclose(id_canal==null?"null":"" + id_canal, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(tipo_canal==null?"null":tipo_canal, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ubicacion==null?"null":ubicacion, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(disponibilidad==null?"null":disponibilidad.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(numero_errores==null?"null":"" + numero_errores, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(id_canal==null?"null":"" + id_canal, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(tipo_canal==null?"null":tipo_canal, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ubicacion==null?"null":ubicacion, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(disponibilidad==null?"null":disponibilidad.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(numero_errores==null?"null":"" + numero_errores, delimiters));
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id_canal = null; } else {
      this.id_canal = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.tipo_canal = null; } else {
      this.tipo_canal = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.ubicacion = null; } else {
      this.ubicacion = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.disponibilidad = null; } else {
      this.disponibilidad = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.numero_errores = null; } else {
      this.numero_errores = Integer.valueOf(__cur_str);
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id_canal = null; } else {
      this.id_canal = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.tipo_canal = null; } else {
      this.tipo_canal = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.ubicacion = null; } else {
      this.ubicacion = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.disponibilidad = null; } else {
      this.disponibilidad = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.numero_errores = null; } else {
      this.numero_errores = Integer.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    canales o = (canales) super.clone();
    return o;
  }

  public void clone0(canales o) throws CloneNotSupportedException {
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("id_canal", this.id_canal);
    __sqoop$field_map.put("tipo_canal", this.tipo_canal);
    __sqoop$field_map.put("ubicacion", this.ubicacion);
    __sqoop$field_map.put("disponibilidad", this.disponibilidad);
    __sqoop$field_map.put("numero_errores", this.numero_errores);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("id_canal", this.id_canal);
    __sqoop$field_map.put("tipo_canal", this.tipo_canal);
    __sqoop$field_map.put("ubicacion", this.ubicacion);
    __sqoop$field_map.put("disponibilidad", this.disponibilidad);
    __sqoop$field_map.put("numero_errores", this.numero_errores);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
