// ORM class for table 'agg_transacciones_diario'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Wed Jul 22 02:58:24 UTC 2026
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

public class agg_transacciones_diario extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("canal_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        agg_transacciones_diario.this.canal_id = (Integer)value;
      }
    });
    setters.put("fecha", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        agg_transacciones_diario.this.fecha = (java.sql.Date)value;
      }
    });
    setters.put("monto_total", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        agg_transacciones_diario.this.monto_total = (java.math.BigDecimal)value;
      }
    });
    setters.put("tasa_fallo_pct", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        agg_transacciones_diario.this.tasa_fallo_pct = (java.math.BigDecimal)value;
      }
    });
    setters.put("tipo_canal", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        agg_transacciones_diario.this.tipo_canal = (String)value;
      }
    });
    setters.put("total_transacciones", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        agg_transacciones_diario.this.total_transacciones = (Integer)value;
      }
    });
    setters.put("transacciones_fallidas", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        agg_transacciones_diario.this.transacciones_fallidas = (Integer)value;
      }
    });
    setters.put("ubicacion", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        agg_transacciones_diario.this.ubicacion = (String)value;
      }
    });
  }
  public agg_transacciones_diario() {
    init0();
  }
  private Integer canal_id;
  public Integer get_canal_id() {
    return canal_id;
  }
  public void set_canal_id(Integer canal_id) {
    this.canal_id = canal_id;
  }
  public agg_transacciones_diario with_canal_id(Integer canal_id) {
    this.canal_id = canal_id;
    return this;
  }
  private java.sql.Date fecha;
  public java.sql.Date get_fecha() {
    return fecha;
  }
  public void set_fecha(java.sql.Date fecha) {
    this.fecha = fecha;
  }
  public agg_transacciones_diario with_fecha(java.sql.Date fecha) {
    this.fecha = fecha;
    return this;
  }
  private java.math.BigDecimal monto_total;
  public java.math.BigDecimal get_monto_total() {
    return monto_total;
  }
  public void set_monto_total(java.math.BigDecimal monto_total) {
    this.monto_total = monto_total;
  }
  public agg_transacciones_diario with_monto_total(java.math.BigDecimal monto_total) {
    this.monto_total = monto_total;
    return this;
  }
  private java.math.BigDecimal tasa_fallo_pct;
  public java.math.BigDecimal get_tasa_fallo_pct() {
    return tasa_fallo_pct;
  }
  public void set_tasa_fallo_pct(java.math.BigDecimal tasa_fallo_pct) {
    this.tasa_fallo_pct = tasa_fallo_pct;
  }
  public agg_transacciones_diario with_tasa_fallo_pct(java.math.BigDecimal tasa_fallo_pct) {
    this.tasa_fallo_pct = tasa_fallo_pct;
    return this;
  }
  private String tipo_canal;
  public String get_tipo_canal() {
    return tipo_canal;
  }
  public void set_tipo_canal(String tipo_canal) {
    this.tipo_canal = tipo_canal;
  }
  public agg_transacciones_diario with_tipo_canal(String tipo_canal) {
    this.tipo_canal = tipo_canal;
    return this;
  }
  private Integer total_transacciones;
  public Integer get_total_transacciones() {
    return total_transacciones;
  }
  public void set_total_transacciones(Integer total_transacciones) {
    this.total_transacciones = total_transacciones;
  }
  public agg_transacciones_diario with_total_transacciones(Integer total_transacciones) {
    this.total_transacciones = total_transacciones;
    return this;
  }
  private Integer transacciones_fallidas;
  public Integer get_transacciones_fallidas() {
    return transacciones_fallidas;
  }
  public void set_transacciones_fallidas(Integer transacciones_fallidas) {
    this.transacciones_fallidas = transacciones_fallidas;
  }
  public agg_transacciones_diario with_transacciones_fallidas(Integer transacciones_fallidas) {
    this.transacciones_fallidas = transacciones_fallidas;
    return this;
  }
  private String ubicacion;
  public String get_ubicacion() {
    return ubicacion;
  }
  public void set_ubicacion(String ubicacion) {
    this.ubicacion = ubicacion;
  }
  public agg_transacciones_diario with_ubicacion(String ubicacion) {
    this.ubicacion = ubicacion;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof agg_transacciones_diario)) {
      return false;
    }
    agg_transacciones_diario that = (agg_transacciones_diario) o;
    boolean equal = true;
    equal = equal && (this.canal_id == null ? that.canal_id == null : this.canal_id.equals(that.canal_id));
    equal = equal && (this.fecha == null ? that.fecha == null : this.fecha.equals(that.fecha));
    equal = equal && (this.monto_total == null ? that.monto_total == null : this.monto_total.equals(that.monto_total));
    equal = equal && (this.tasa_fallo_pct == null ? that.tasa_fallo_pct == null : this.tasa_fallo_pct.equals(that.tasa_fallo_pct));
    equal = equal && (this.tipo_canal == null ? that.tipo_canal == null : this.tipo_canal.equals(that.tipo_canal));
    equal = equal && (this.total_transacciones == null ? that.total_transacciones == null : this.total_transacciones.equals(that.total_transacciones));
    equal = equal && (this.transacciones_fallidas == null ? that.transacciones_fallidas == null : this.transacciones_fallidas.equals(that.transacciones_fallidas));
    equal = equal && (this.ubicacion == null ? that.ubicacion == null : this.ubicacion.equals(that.ubicacion));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof agg_transacciones_diario)) {
      return false;
    }
    agg_transacciones_diario that = (agg_transacciones_diario) o;
    boolean equal = true;
    equal = equal && (this.canal_id == null ? that.canal_id == null : this.canal_id.equals(that.canal_id));
    equal = equal && (this.fecha == null ? that.fecha == null : this.fecha.equals(that.fecha));
    equal = equal && (this.monto_total == null ? that.monto_total == null : this.monto_total.equals(that.monto_total));
    equal = equal && (this.tasa_fallo_pct == null ? that.tasa_fallo_pct == null : this.tasa_fallo_pct.equals(that.tasa_fallo_pct));
    equal = equal && (this.tipo_canal == null ? that.tipo_canal == null : this.tipo_canal.equals(that.tipo_canal));
    equal = equal && (this.total_transacciones == null ? that.total_transacciones == null : this.total_transacciones.equals(that.total_transacciones));
    equal = equal && (this.transacciones_fallidas == null ? that.transacciones_fallidas == null : this.transacciones_fallidas.equals(that.transacciones_fallidas));
    equal = equal && (this.ubicacion == null ? that.ubicacion == null : this.ubicacion.equals(that.ubicacion));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.canal_id = JdbcWritableBridge.readInteger(1, __dbResults);
    this.fecha = JdbcWritableBridge.readDate(2, __dbResults);
    this.monto_total = JdbcWritableBridge.readBigDecimal(3, __dbResults);
    this.tasa_fallo_pct = JdbcWritableBridge.readBigDecimal(4, __dbResults);
    this.tipo_canal = JdbcWritableBridge.readString(5, __dbResults);
    this.total_transacciones = JdbcWritableBridge.readInteger(6, __dbResults);
    this.transacciones_fallidas = JdbcWritableBridge.readInteger(7, __dbResults);
    this.ubicacion = JdbcWritableBridge.readString(8, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.canal_id = JdbcWritableBridge.readInteger(1, __dbResults);
    this.fecha = JdbcWritableBridge.readDate(2, __dbResults);
    this.monto_total = JdbcWritableBridge.readBigDecimal(3, __dbResults);
    this.tasa_fallo_pct = JdbcWritableBridge.readBigDecimal(4, __dbResults);
    this.tipo_canal = JdbcWritableBridge.readString(5, __dbResults);
    this.total_transacciones = JdbcWritableBridge.readInteger(6, __dbResults);
    this.transacciones_fallidas = JdbcWritableBridge.readInteger(7, __dbResults);
    this.ubicacion = JdbcWritableBridge.readString(8, __dbResults);
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
    JdbcWritableBridge.writeDate(fecha, 2 + __off, 91, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(monto_total, 3 + __off, 3, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(tasa_fallo_pct, 4 + __off, 3, __dbStmt);
    JdbcWritableBridge.writeString(tipo_canal, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(total_transacciones, 6 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(transacciones_fallidas, 7 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(ubicacion, 8 + __off, 12, __dbStmt);
    return 8;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(canal_id, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeDate(fecha, 2 + __off, 91, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(monto_total, 3 + __off, 3, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(tasa_fallo_pct, 4 + __off, 3, __dbStmt);
    JdbcWritableBridge.writeString(tipo_canal, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(total_transacciones, 6 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(transacciones_fallidas, 7 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(ubicacion, 8 + __off, 12, __dbStmt);
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
        this.fecha = null;
    } else {
    this.fecha = new Date(__dataIn.readLong());
    }
    if (__dataIn.readBoolean()) { 
        this.monto_total = null;
    } else {
    this.monto_total = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.tasa_fallo_pct = null;
    } else {
    this.tasa_fallo_pct = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.tipo_canal = null;
    } else {
    this.tipo_canal = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.total_transacciones = null;
    } else {
    this.total_transacciones = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.transacciones_fallidas = null;
    } else {
    this.transacciones_fallidas = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.ubicacion = null;
    } else {
    this.ubicacion = Text.readString(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.canal_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.canal_id);
    }
    if (null == this.fecha) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.fecha.getTime());
    }
    if (null == this.monto_total) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.monto_total, __dataOut);
    }
    if (null == this.tasa_fallo_pct) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.tasa_fallo_pct, __dataOut);
    }
    if (null == this.tipo_canal) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, tipo_canal);
    }
    if (null == this.total_transacciones) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.total_transacciones);
    }
    if (null == this.transacciones_fallidas) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.transacciones_fallidas);
    }
    if (null == this.ubicacion) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, ubicacion);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.canal_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.canal_id);
    }
    if (null == this.fecha) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeLong(this.fecha.getTime());
    }
    if (null == this.monto_total) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.monto_total, __dataOut);
    }
    if (null == this.tasa_fallo_pct) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.tasa_fallo_pct, __dataOut);
    }
    if (null == this.tipo_canal) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, tipo_canal);
    }
    if (null == this.total_transacciones) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.total_transacciones);
    }
    if (null == this.transacciones_fallidas) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.transacciones_fallidas);
    }
    if (null == this.ubicacion) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, ubicacion);
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
    __sb.append(FieldFormatter.escapeAndEnclose(fecha==null?"null":"" + fecha, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(monto_total==null?"null":monto_total.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(tasa_fallo_pct==null?"null":tasa_fallo_pct.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(tipo_canal==null?"null":tipo_canal, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(total_transacciones==null?"null":"" + total_transacciones, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(transacciones_fallidas==null?"null":"" + transacciones_fallidas, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ubicacion==null?"null":ubicacion, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(canal_id==null?"null":"" + canal_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(fecha==null?"null":"" + fecha, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(monto_total==null?"null":monto_total.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(tasa_fallo_pct==null?"null":tasa_fallo_pct.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(tipo_canal==null?"null":tipo_canal, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(total_transacciones==null?"null":"" + total_transacciones, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(transacciones_fallidas==null?"null":"" + transacciones_fallidas, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ubicacion==null?"null":ubicacion, delimiters));
  }
  private static final DelimiterSet __inputDelimiters = new DelimiterSet((char) 1, (char) 10, (char) 0, (char) 0, false);
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.fecha = null; } else {
      this.fecha = java.sql.Date.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.monto_total = null; } else {
      this.monto_total = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.tasa_fallo_pct = null; } else {
      this.tasa_fallo_pct = new java.math.BigDecimal(__cur_str);
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.total_transacciones = null; } else {
      this.total_transacciones = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.transacciones_fallidas = null; } else {
      this.transacciones_fallidas = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.ubicacion = null; } else {
      this.ubicacion = __cur_str;
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.fecha = null; } else {
      this.fecha = java.sql.Date.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.monto_total = null; } else {
      this.monto_total = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.tasa_fallo_pct = null; } else {
      this.tasa_fallo_pct = new java.math.BigDecimal(__cur_str);
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.total_transacciones = null; } else {
      this.total_transacciones = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.transacciones_fallidas = null; } else {
      this.transacciones_fallidas = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.ubicacion = null; } else {
      this.ubicacion = __cur_str;
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    agg_transacciones_diario o = (agg_transacciones_diario) super.clone();
    o.fecha = (o.fecha != null) ? (java.sql.Date) o.fecha.clone() : null;
    return o;
  }

  public void clone0(agg_transacciones_diario o) throws CloneNotSupportedException {
    o.fecha = (o.fecha != null) ? (java.sql.Date) o.fecha.clone() : null;
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("canal_id", this.canal_id);
    __sqoop$field_map.put("fecha", this.fecha);
    __sqoop$field_map.put("monto_total", this.monto_total);
    __sqoop$field_map.put("tasa_fallo_pct", this.tasa_fallo_pct);
    __sqoop$field_map.put("tipo_canal", this.tipo_canal);
    __sqoop$field_map.put("total_transacciones", this.total_transacciones);
    __sqoop$field_map.put("transacciones_fallidas", this.transacciones_fallidas);
    __sqoop$field_map.put("ubicacion", this.ubicacion);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("canal_id", this.canal_id);
    __sqoop$field_map.put("fecha", this.fecha);
    __sqoop$field_map.put("monto_total", this.monto_total);
    __sqoop$field_map.put("tasa_fallo_pct", this.tasa_fallo_pct);
    __sqoop$field_map.put("tipo_canal", this.tipo_canal);
    __sqoop$field_map.put("total_transacciones", this.total_transacciones);
    __sqoop$field_map.put("transacciones_fallidas", this.transacciones_fallidas);
    __sqoop$field_map.put("ubicacion", this.ubicacion);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
