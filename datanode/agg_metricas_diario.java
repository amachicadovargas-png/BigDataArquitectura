// ORM class for table 'agg_metricas_diario'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
// Generated date: Wed Jul 22 03:14:49 UTC 2026
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

public class agg_metricas_diario extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
    setters.put("fecha", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        agg_metricas_diario.this.fecha = (String)value;
      }
    });
    setters.put("servidor_id", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        agg_metricas_diario.this.servidor_id = (Integer)value;
      }
    });
    setters.put("nombre_servidor", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        agg_metricas_diario.this.nombre_servidor = (String)value;
      }
    });
    setters.put("ubicacion", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        agg_metricas_diario.this.ubicacion = (String)value;
      }
    });
    setters.put("cpu_promedio", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        agg_metricas_diario.this.cpu_promedio = (java.math.BigDecimal)value;
      }
    });
    setters.put("cpu_maximo", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        agg_metricas_diario.this.cpu_maximo = (java.math.BigDecimal)value;
      }
    });
    setters.put("ram_promedio", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        agg_metricas_diario.this.ram_promedio = (java.math.BigDecimal)value;
      }
    });
    setters.put("disco_promedio", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        agg_metricas_diario.this.disco_promedio = (java.math.BigDecimal)value;
      }
    });
    setters.put("lecturas_criticas", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        agg_metricas_diario.this.lecturas_criticas = (Integer)value;
      }
    });
    setters.put("lecturas_advertencia", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        agg_metricas_diario.this.lecturas_advertencia = (Integer)value;
      }
    });
  }
  public agg_metricas_diario() {
    init0();
  }
  private String fecha;
  public String get_fecha() {
    return fecha;
  }
  public void set_fecha(String fecha) {
    this.fecha = fecha;
  }
  public agg_metricas_diario with_fecha(String fecha) {
    this.fecha = fecha;
    return this;
  }
  private Integer servidor_id;
  public Integer get_servidor_id() {
    return servidor_id;
  }
  public void set_servidor_id(Integer servidor_id) {
    this.servidor_id = servidor_id;
  }
  public agg_metricas_diario with_servidor_id(Integer servidor_id) {
    this.servidor_id = servidor_id;
    return this;
  }
  private String nombre_servidor;
  public String get_nombre_servidor() {
    return nombre_servidor;
  }
  public void set_nombre_servidor(String nombre_servidor) {
    this.nombre_servidor = nombre_servidor;
  }
  public agg_metricas_diario with_nombre_servidor(String nombre_servidor) {
    this.nombre_servidor = nombre_servidor;
    return this;
  }
  private String ubicacion;
  public String get_ubicacion() {
    return ubicacion;
  }
  public void set_ubicacion(String ubicacion) {
    this.ubicacion = ubicacion;
  }
  public agg_metricas_diario with_ubicacion(String ubicacion) {
    this.ubicacion = ubicacion;
    return this;
  }
  private java.math.BigDecimal cpu_promedio;
  public java.math.BigDecimal get_cpu_promedio() {
    return cpu_promedio;
  }
  public void set_cpu_promedio(java.math.BigDecimal cpu_promedio) {
    this.cpu_promedio = cpu_promedio;
  }
  public agg_metricas_diario with_cpu_promedio(java.math.BigDecimal cpu_promedio) {
    this.cpu_promedio = cpu_promedio;
    return this;
  }
  private java.math.BigDecimal cpu_maximo;
  public java.math.BigDecimal get_cpu_maximo() {
    return cpu_maximo;
  }
  public void set_cpu_maximo(java.math.BigDecimal cpu_maximo) {
    this.cpu_maximo = cpu_maximo;
  }
  public agg_metricas_diario with_cpu_maximo(java.math.BigDecimal cpu_maximo) {
    this.cpu_maximo = cpu_maximo;
    return this;
  }
  private java.math.BigDecimal ram_promedio;
  public java.math.BigDecimal get_ram_promedio() {
    return ram_promedio;
  }
  public void set_ram_promedio(java.math.BigDecimal ram_promedio) {
    this.ram_promedio = ram_promedio;
  }
  public agg_metricas_diario with_ram_promedio(java.math.BigDecimal ram_promedio) {
    this.ram_promedio = ram_promedio;
    return this;
  }
  private java.math.BigDecimal disco_promedio;
  public java.math.BigDecimal get_disco_promedio() {
    return disco_promedio;
  }
  public void set_disco_promedio(java.math.BigDecimal disco_promedio) {
    this.disco_promedio = disco_promedio;
  }
  public agg_metricas_diario with_disco_promedio(java.math.BigDecimal disco_promedio) {
    this.disco_promedio = disco_promedio;
    return this;
  }
  private Integer lecturas_criticas;
  public Integer get_lecturas_criticas() {
    return lecturas_criticas;
  }
  public void set_lecturas_criticas(Integer lecturas_criticas) {
    this.lecturas_criticas = lecturas_criticas;
  }
  public agg_metricas_diario with_lecturas_criticas(Integer lecturas_criticas) {
    this.lecturas_criticas = lecturas_criticas;
    return this;
  }
  private Integer lecturas_advertencia;
  public Integer get_lecturas_advertencia() {
    return lecturas_advertencia;
  }
  public void set_lecturas_advertencia(Integer lecturas_advertencia) {
    this.lecturas_advertencia = lecturas_advertencia;
  }
  public agg_metricas_diario with_lecturas_advertencia(Integer lecturas_advertencia) {
    this.lecturas_advertencia = lecturas_advertencia;
    return this;
  }
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof agg_metricas_diario)) {
      return false;
    }
    agg_metricas_diario that = (agg_metricas_diario) o;
    boolean equal = true;
    equal = equal && (this.fecha == null ? that.fecha == null : this.fecha.equals(that.fecha));
    equal = equal && (this.servidor_id == null ? that.servidor_id == null : this.servidor_id.equals(that.servidor_id));
    equal = equal && (this.nombre_servidor == null ? that.nombre_servidor == null : this.nombre_servidor.equals(that.nombre_servidor));
    equal = equal && (this.ubicacion == null ? that.ubicacion == null : this.ubicacion.equals(that.ubicacion));
    equal = equal && (this.cpu_promedio == null ? that.cpu_promedio == null : this.cpu_promedio.equals(that.cpu_promedio));
    equal = equal && (this.cpu_maximo == null ? that.cpu_maximo == null : this.cpu_maximo.equals(that.cpu_maximo));
    equal = equal && (this.ram_promedio == null ? that.ram_promedio == null : this.ram_promedio.equals(that.ram_promedio));
    equal = equal && (this.disco_promedio == null ? that.disco_promedio == null : this.disco_promedio.equals(that.disco_promedio));
    equal = equal && (this.lecturas_criticas == null ? that.lecturas_criticas == null : this.lecturas_criticas.equals(that.lecturas_criticas));
    equal = equal && (this.lecturas_advertencia == null ? that.lecturas_advertencia == null : this.lecturas_advertencia.equals(that.lecturas_advertencia));
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof agg_metricas_diario)) {
      return false;
    }
    agg_metricas_diario that = (agg_metricas_diario) o;
    boolean equal = true;
    equal = equal && (this.fecha == null ? that.fecha == null : this.fecha.equals(that.fecha));
    equal = equal && (this.servidor_id == null ? that.servidor_id == null : this.servidor_id.equals(that.servidor_id));
    equal = equal && (this.nombre_servidor == null ? that.nombre_servidor == null : this.nombre_servidor.equals(that.nombre_servidor));
    equal = equal && (this.ubicacion == null ? that.ubicacion == null : this.ubicacion.equals(that.ubicacion));
    equal = equal && (this.cpu_promedio == null ? that.cpu_promedio == null : this.cpu_promedio.equals(that.cpu_promedio));
    equal = equal && (this.cpu_maximo == null ? that.cpu_maximo == null : this.cpu_maximo.equals(that.cpu_maximo));
    equal = equal && (this.ram_promedio == null ? that.ram_promedio == null : this.ram_promedio.equals(that.ram_promedio));
    equal = equal && (this.disco_promedio == null ? that.disco_promedio == null : this.disco_promedio.equals(that.disco_promedio));
    equal = equal && (this.lecturas_criticas == null ? that.lecturas_criticas == null : this.lecturas_criticas.equals(that.lecturas_criticas));
    equal = equal && (this.lecturas_advertencia == null ? that.lecturas_advertencia == null : this.lecturas_advertencia.equals(that.lecturas_advertencia));
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
    this.fecha = JdbcWritableBridge.readString(1, __dbResults);
    this.servidor_id = JdbcWritableBridge.readInteger(2, __dbResults);
    this.nombre_servidor = JdbcWritableBridge.readString(3, __dbResults);
    this.ubicacion = JdbcWritableBridge.readString(4, __dbResults);
    this.cpu_promedio = JdbcWritableBridge.readBigDecimal(5, __dbResults);
    this.cpu_maximo = JdbcWritableBridge.readBigDecimal(6, __dbResults);
    this.ram_promedio = JdbcWritableBridge.readBigDecimal(7, __dbResults);
    this.disco_promedio = JdbcWritableBridge.readBigDecimal(8, __dbResults);
    this.lecturas_criticas = JdbcWritableBridge.readInteger(9, __dbResults);
    this.lecturas_advertencia = JdbcWritableBridge.readInteger(10, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.fecha = JdbcWritableBridge.readString(1, __dbResults);
    this.servidor_id = JdbcWritableBridge.readInteger(2, __dbResults);
    this.nombre_servidor = JdbcWritableBridge.readString(3, __dbResults);
    this.ubicacion = JdbcWritableBridge.readString(4, __dbResults);
    this.cpu_promedio = JdbcWritableBridge.readBigDecimal(5, __dbResults);
    this.cpu_maximo = JdbcWritableBridge.readBigDecimal(6, __dbResults);
    this.ram_promedio = JdbcWritableBridge.readBigDecimal(7, __dbResults);
    this.disco_promedio = JdbcWritableBridge.readBigDecimal(8, __dbResults);
    this.lecturas_criticas = JdbcWritableBridge.readInteger(9, __dbResults);
    this.lecturas_advertencia = JdbcWritableBridge.readInteger(10, __dbResults);
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
    JdbcWritableBridge.writeString(fecha, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(servidor_id, 2 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(nombre_servidor, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(ubicacion, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(cpu_promedio, 5 + __off, 3, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(cpu_maximo, 6 + __off, 3, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(ram_promedio, 7 + __off, 3, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(disco_promedio, 8 + __off, 3, __dbStmt);
    JdbcWritableBridge.writeInteger(lecturas_criticas, 9 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(lecturas_advertencia, 10 + __off, 4, __dbStmt);
    return 10;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(fecha, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(servidor_id, 2 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(nombre_servidor, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(ubicacion, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(cpu_promedio, 5 + __off, 3, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(cpu_maximo, 6 + __off, 3, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(ram_promedio, 7 + __off, 3, __dbStmt);
    JdbcWritableBridge.writeBigDecimal(disco_promedio, 8 + __off, 3, __dbStmt);
    JdbcWritableBridge.writeInteger(lecturas_criticas, 9 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeInteger(lecturas_advertencia, 10 + __off, 4, __dbStmt);
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
        this.fecha = null;
    } else {
    this.fecha = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.servidor_id = null;
    } else {
    this.servidor_id = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.nombre_servidor = null;
    } else {
    this.nombre_servidor = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.ubicacion = null;
    } else {
    this.ubicacion = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.cpu_promedio = null;
    } else {
    this.cpu_promedio = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.cpu_maximo = null;
    } else {
    this.cpu_maximo = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.ram_promedio = null;
    } else {
    this.ram_promedio = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.disco_promedio = null;
    } else {
    this.disco_promedio = com.cloudera.sqoop.lib.BigDecimalSerializer.readFields(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.lecturas_criticas = null;
    } else {
    this.lecturas_criticas = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.lecturas_advertencia = null;
    } else {
    this.lecturas_advertencia = Integer.valueOf(__dataIn.readInt());
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.fecha) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, fecha);
    }
    if (null == this.servidor_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.servidor_id);
    }
    if (null == this.nombre_servidor) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, nombre_servidor);
    }
    if (null == this.ubicacion) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, ubicacion);
    }
    if (null == this.cpu_promedio) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.cpu_promedio, __dataOut);
    }
    if (null == this.cpu_maximo) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.cpu_maximo, __dataOut);
    }
    if (null == this.ram_promedio) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.ram_promedio, __dataOut);
    }
    if (null == this.disco_promedio) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.disco_promedio, __dataOut);
    }
    if (null == this.lecturas_criticas) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.lecturas_criticas);
    }
    if (null == this.lecturas_advertencia) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.lecturas_advertencia);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.fecha) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, fecha);
    }
    if (null == this.servidor_id) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.servidor_id);
    }
    if (null == this.nombre_servidor) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, nombre_servidor);
    }
    if (null == this.ubicacion) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, ubicacion);
    }
    if (null == this.cpu_promedio) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.cpu_promedio, __dataOut);
    }
    if (null == this.cpu_maximo) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.cpu_maximo, __dataOut);
    }
    if (null == this.ram_promedio) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.ram_promedio, __dataOut);
    }
    if (null == this.disco_promedio) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    com.cloudera.sqoop.lib.BigDecimalSerializer.write(this.disco_promedio, __dataOut);
    }
    if (null == this.lecturas_criticas) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.lecturas_criticas);
    }
    if (null == this.lecturas_advertencia) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.lecturas_advertencia);
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
    __sb.append(FieldFormatter.escapeAndEnclose(fecha==null?"null":fecha, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(servidor_id==null?"null":"" + servidor_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(nombre_servidor==null?"null":nombre_servidor, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ubicacion==null?"null":ubicacion, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cpu_promedio==null?"null":cpu_promedio.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cpu_maximo==null?"null":cpu_maximo.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ram_promedio==null?"null":ram_promedio.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(disco_promedio==null?"null":disco_promedio.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(lecturas_criticas==null?"null":"" + lecturas_criticas, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(lecturas_advertencia==null?"null":"" + lecturas_advertencia, delimiters));
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
    __sb.append(FieldFormatter.escapeAndEnclose(fecha==null?"null":fecha, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(servidor_id==null?"null":"" + servidor_id, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(nombre_servidor==null?"null":nombre_servidor, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ubicacion==null?"null":ubicacion, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cpu_promedio==null?"null":cpu_promedio.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(cpu_maximo==null?"null":cpu_maximo.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ram_promedio==null?"null":ram_promedio.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(disco_promedio==null?"null":disco_promedio.toPlainString(), delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(lecturas_criticas==null?"null":"" + lecturas_criticas, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(lecturas_advertencia==null?"null":"" + lecturas_advertencia, delimiters));
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
    if (__cur_str.equals("null")) { this.fecha = null; } else {
      this.fecha = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.servidor_id = null; } else {
      this.servidor_id = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.nombre_servidor = null; } else {
      this.nombre_servidor = __cur_str;
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.cpu_promedio = null; } else {
      this.cpu_promedio = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.cpu_maximo = null; } else {
      this.cpu_maximo = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.ram_promedio = null; } else {
      this.ram_promedio = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.disco_promedio = null; } else {
      this.disco_promedio = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.lecturas_criticas = null; } else {
      this.lecturas_criticas = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.lecturas_advertencia = null; } else {
      this.lecturas_advertencia = Integer.valueOf(__cur_str);
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
    if (__cur_str.equals("null")) { this.fecha = null; } else {
      this.fecha = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.servidor_id = null; } else {
      this.servidor_id = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.nombre_servidor = null; } else {
      this.nombre_servidor = __cur_str;
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
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.cpu_promedio = null; } else {
      this.cpu_promedio = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.cpu_maximo = null; } else {
      this.cpu_maximo = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.ram_promedio = null; } else {
      this.ram_promedio = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.disco_promedio = null; } else {
      this.disco_promedio = new java.math.BigDecimal(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.lecturas_criticas = null; } else {
      this.lecturas_criticas = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.lecturas_advertencia = null; } else {
      this.lecturas_advertencia = Integer.valueOf(__cur_str);
    }

    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    agg_metricas_diario o = (agg_metricas_diario) super.clone();
    return o;
  }

  public void clone0(agg_metricas_diario o) throws CloneNotSupportedException {
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
    __sqoop$field_map.put("fecha", this.fecha);
    __sqoop$field_map.put("servidor_id", this.servidor_id);
    __sqoop$field_map.put("nombre_servidor", this.nombre_servidor);
    __sqoop$field_map.put("ubicacion", this.ubicacion);
    __sqoop$field_map.put("cpu_promedio", this.cpu_promedio);
    __sqoop$field_map.put("cpu_maximo", this.cpu_maximo);
    __sqoop$field_map.put("ram_promedio", this.ram_promedio);
    __sqoop$field_map.put("disco_promedio", this.disco_promedio);
    __sqoop$field_map.put("lecturas_criticas", this.lecturas_criticas);
    __sqoop$field_map.put("lecturas_advertencia", this.lecturas_advertencia);
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
    __sqoop$field_map.put("fecha", this.fecha);
    __sqoop$field_map.put("servidor_id", this.servidor_id);
    __sqoop$field_map.put("nombre_servidor", this.nombre_servidor);
    __sqoop$field_map.put("ubicacion", this.ubicacion);
    __sqoop$field_map.put("cpu_promedio", this.cpu_promedio);
    __sqoop$field_map.put("cpu_maximo", this.cpu_maximo);
    __sqoop$field_map.put("ram_promedio", this.ram_promedio);
    __sqoop$field_map.put("disco_promedio", this.disco_promedio);
    __sqoop$field_map.put("lecturas_criticas", this.lecturas_criticas);
    __sqoop$field_map.put("lecturas_advertencia", this.lecturas_advertencia);
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
