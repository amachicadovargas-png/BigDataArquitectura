// ORM class for table 'logs_sistema'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Wed Jul 22 01:16:37 UTC 2026
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

public class logs_sistema extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("evento", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        logs_sistema.this.evento = (String)value;
      }
    });
    setters.put("fecha", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        logs_sistema.this.fecha = (java.sql.Timestamp)value;
      }
    });
    setters.put("id_log", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        logs_sistema.this.id_log = (Integer)value;
      }
    });
    setters.put("nivel_severidad", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        logs_sistema.this.nivel_severidad = (String)value;
      }
    });
    setters.put("servidor_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        logs_sistema.this.servidor_id = (Integer)value;
      }
    });
  }
  public logs_sistema() {
    init0();
  }
  private String evento;
  public String get_evento() {
    return evento;
  }
  public void set_evento(String evento) {
    this.evento = evento;
  }
  public logs_sistema with_evento(String evento) {
    this.evento = evento;
    return this;
  }
  private java.sql.Timestamp fecha;
  public java.sql.Timestamp get_fecha() {
    return fecha;
  }
  public void set_fecha(java.sql.Timestamp fecha) {
    this.fecha = fecha;
  }
  public logs_sistema with_fecha(java.sql.Timestamp fecha) {
    this.fecha = fecha;
    return this;
  }
  private Integer id_log;
  public Integer get_id_log() {
    return id_log;
  }
  public void set_id_log(Integer id_log) {
    this.id_log = id_log;
  }
  public logs_sistema with_id_log(Integer id_log) {
    this.id_log = id_log;
    return this;
  }
  private String nivel_severidad;
  public String get_nivel_severidad() {
    return nivel_severidad;
  }
  public void set_nivel_severidad(String nivel_severidad) {
    this.nivel_severidad = nivel_severidad;
  }
  public logs_sistema with_nivel_severidad(String nivel_severidad) {
    this.nivel_severidad = nivel_severidad;
    return this;
  }
  private Integer servidor_id;
  public Integer get_servidor_id() {
    return servidor_id;
  }
  public void set_servidor_id(Integer servidor_id) {
    this.servidor_id = servidor_id;
  }
  public logs_sistema with_servidor_id(Integer servidor_id) {
    this.servidor_id = servidor_id;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof logs_sistema)) {
      return false;
    }
    logs_sistema that = (logs_sistema) o;
    boolean equal = true;
    equal = equal && (this.evento == null ? that.evento == null : this.evento.equals(that.evento));
    equal = equal && (this.fecha == null ? that.fecha == null : this.fecha.equals(that.fecha));
    equal = equal && (this.id_log == null ? that.id_log == null : this.id_log.equals(that.id_log));
    equal = equal && (this.nivel_severidad == null ? that.nivel_severidad == null : this.nivel_severidad.equals(that.nivel_severidad));
    equal = equal && (this.servidor_id == null ? that.servidor_id == null : this.servidor_id.equals(that.servidor_id));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof logs_sistema)) {
      return false;
    }
    logs_sistema that = (logs_sistema) o;
    boolean equal = true;
    equal = equal && (this.evento == null ? that.evento == null : this.evento.equals(that.evento));
    equal = equal && (this.fecha == null ? that.fecha == null : this.fecha.equals(that.fecha));
    equal = equal && (this.id_log == null ? that.id_log == null : this.id_log.equals(that.id_log));
    equal = equal && (this.nivel_severidad == null ? that.nivel_severidad == null : this.nivel_severidad.equals(that.nivel_severidad));
    equal = equal && (this.servidor_id == null ? that.servidor_id == null : this.servidor_id.equals(that.servidor_id));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.evento = JdbcWritableBridge.readString(1, __dbResults);
    this.fecha = JdbcWritableBridge.readTimestamp(2, __dbResults);
    this.id_log = JdbcWritableBridge.readInteger(3, __dbResults);
    this.nivel_severidad = JdbcWritableBridge.readString(4, __dbResults);
    this.servidor_id = JdbcWritableBridge.readInteger(5, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.evento = JdbcWritableBridge.readString(1, __dbResults);
    this.fecha = JdbcWritableBridge.readTimestamp(2, __dbResults);
    this.id_log = JdbcWritableBridge.readInteger(3, __dbResults);
    this.nivel_severidad = JdbcWritableBridge.readString(4, __dbResults);
    this.servidor_id = JdbcWritableBridge.readInteger(5, __dbResults);
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
    JdbcWritableBridge.writeString(evento, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeTimestamp(fecha, 2 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeInteger(id_log, 3 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(nivel_severidad, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(servidor_id, 5 + __off, 4, __dbStmt);
    return 5;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(evento, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeTimestamp(fecha, 2 + __off, 93, __dbStmt);
    JdbcWritableBridge.writeInteger(id_log, 3 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(nivel_severidad, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(servidor_id, 5 + __off, 4, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.evento = null;
    } else {
    this.evento = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.fecha = null;
    } else {
    this.fecha = new Timestamp(__dataIn.readLong());
    this.fecha.setNanos(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.id_log = null;
    } else {
    this.id_log = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.nivel_severidad = null;
    } else {
    this.nivel_severidad = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.servidor_id = null;
    } else {
    this.servidor_id = Integer.valueOf(__dataIn.readInt());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.evento) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, evento);
    }
    if (null == this.fecha) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.fecha.getTime());
    __dataOut.writeInt(this.fecha.getNanos());
    }
    if (null == this.id_log) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.id_log);
    }
    if (null == this.nivel_severidad) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, nivel_severidad);
    }
    if (null == this.servidor_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.servidor_id);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.evento) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, evento);
    }
    if (null == this.fecha) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.fecha.getTime());
    __dataOut.writeInt(this.fecha.getNanos());
    }
    if (null == this.id_log) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.id_log);
    }
    if (null == this.nivel_severidad) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, nivel_severidad);
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
    __sb.append(FieldFormatter.escapeAndEnclose(evento==null?"null":evento, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(fecha==null?"null":"" + fecha, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(id_log==null?"null":"" + id_log, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(nivel_severidad==null?"null":nivel_severidad, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(servidor_id==null?"null":"" + servidor_id, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(evento==null?"null":evento, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(fecha==null?"null":"" + fecha, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(id_log==null?"null":"" + id_log, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(nivel_severidad==null?"null":nivel_severidad, delimiters));
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
    if (__cur_str.equals("null")) { this.evento = null; } else {
      this.evento = __cur_str;
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id_log = null; } else {
      this.id_log = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.nivel_severidad = null; } else {
      this.nivel_severidad = __cur_str;
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
    if (__cur_str.equals("null")) { this.evento = null; } else {
      this.evento = __cur_str;
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id_log = null; } else {
      this.id_log = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.nivel_severidad = null; } else {
      this.nivel_severidad = __cur_str;
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
    logs_sistema o = (logs_sistema) super.clone();
    o.fecha = (o.fecha != null) ? (java.sql.Timestamp) o.fecha.clone() : null;
    return o;
  }

  public void clone0(logs_sistema o) throws CloneNotSupportedException {
    o.fecha = (o.fecha != null) ? (java.sql.Timestamp) o.fecha.clone() : null;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("evento", this.evento);
    __sqoop$field_map.put("fecha", this.fecha);
    __sqoop$field_map.put("id_log", this.id_log);
    __sqoop$field_map.put("nivel_severidad", this.nivel_severidad);
    __sqoop$field_map.put("servidor_id", this.servidor_id);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("evento", this.evento);
    __sqoop$field_map.put("fecha", this.fecha);
    __sqoop$field_map.put("id_log", this.id_log);
    __sqoop$field_map.put("nivel_severidad", this.nivel_severidad);
    __sqoop$field_map.put("servidor_id", this.servidor_id);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
