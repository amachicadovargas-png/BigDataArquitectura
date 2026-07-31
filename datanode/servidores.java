// ORM class for table 'servidores'
// WARNING: This class is AUTO-GENERATED. Modify at your own risk.
//
// Debug information:
<<<<<<< HEAD
// Generated date: Fri Jul 24 01:25:51 UTC 2026
=======
// Generated date: Wed Jul 22 01:15:59 UTC 2026
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
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

public class servidores extends SqoopRecord  implements DBWritable, Writable {
  private final int PROTOCOL_VERSION = 3;
  public int getClassFormatVersion() { return PROTOCOL_VERSION; }
  public static interface FieldSetterCommand {    void setField(Object value);  }  protected ResultSet __cur_result_set;
  private Map<String, FieldSetterCommand> setters = new HashMap<String, FieldSetterCommand>();
  private void init0() {
<<<<<<< HEAD
=======
    setters.put("direccion_ip", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        servidores.this.direccion_ip = (String)value;
      }
    });
    setters.put("estado", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        servidores.this.estado = (String)value;
      }
    });
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
    setters.put("id_servidor", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        servidores.this.id_servidor = (Integer)value;
      }
    });
    setters.put("nombre", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        servidores.this.nombre = (String)value;
      }
    });
<<<<<<< HEAD
    setters.put("direccion_ip", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        servidores.this.direccion_ip = (String)value;
      }
    });
=======
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
    setters.put("sistema_operativo", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        servidores.this.sistema_operativo = (String)value;
      }
    });
    setters.put("ubicacion", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        servidores.this.ubicacion = (String)value;
      }
    });
<<<<<<< HEAD
    setters.put("estado", new FieldSetterCommand() {
      @Override
      public void setField(Object value) {
        servidores.this.estado = (String)value;
      }
    });
=======
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
  }
  public servidores() {
    init0();
  }
<<<<<<< HEAD
=======
  private String direccion_ip;
  public String get_direccion_ip() {
    return direccion_ip;
  }
  public void set_direccion_ip(String direccion_ip) {
    this.direccion_ip = direccion_ip;
  }
  public servidores with_direccion_ip(String direccion_ip) {
    this.direccion_ip = direccion_ip;
    return this;
  }
  private String estado;
  public String get_estado() {
    return estado;
  }
  public void set_estado(String estado) {
    this.estado = estado;
  }
  public servidores with_estado(String estado) {
    this.estado = estado;
    return this;
  }
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
  private Integer id_servidor;
  public Integer get_id_servidor() {
    return id_servidor;
  }
  public void set_id_servidor(Integer id_servidor) {
    this.id_servidor = id_servidor;
  }
  public servidores with_id_servidor(Integer id_servidor) {
    this.id_servidor = id_servidor;
    return this;
  }
  private String nombre;
  public String get_nombre() {
    return nombre;
  }
  public void set_nombre(String nombre) {
    this.nombre = nombre;
  }
  public servidores with_nombre(String nombre) {
    this.nombre = nombre;
    return this;
  }
<<<<<<< HEAD
  private String direccion_ip;
  public String get_direccion_ip() {
    return direccion_ip;
  }
  public void set_direccion_ip(String direccion_ip) {
    this.direccion_ip = direccion_ip;
  }
  public servidores with_direccion_ip(String direccion_ip) {
    this.direccion_ip = direccion_ip;
    return this;
  }
=======
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
  private String sistema_operativo;
  public String get_sistema_operativo() {
    return sistema_operativo;
  }
  public void set_sistema_operativo(String sistema_operativo) {
    this.sistema_operativo = sistema_operativo;
  }
  public servidores with_sistema_operativo(String sistema_operativo) {
    this.sistema_operativo = sistema_operativo;
    return this;
  }
  private String ubicacion;
  public String get_ubicacion() {
    return ubicacion;
  }
  public void set_ubicacion(String ubicacion) {
    this.ubicacion = ubicacion;
  }
  public servidores with_ubicacion(String ubicacion) {
    this.ubicacion = ubicacion;
    return this;
  }
<<<<<<< HEAD
  private String estado;
  public String get_estado() {
    return estado;
  }
  public void set_estado(String estado) {
    this.estado = estado;
  }
  public servidores with_estado(String estado) {
    this.estado = estado;
    return this;
  }
=======
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof servidores)) {
      return false;
    }
    servidores that = (servidores) o;
    boolean equal = true;
<<<<<<< HEAD
    equal = equal && (this.id_servidor == null ? that.id_servidor == null : this.id_servidor.equals(that.id_servidor));
    equal = equal && (this.nombre == null ? that.nombre == null : this.nombre.equals(that.nombre));
    equal = equal && (this.direccion_ip == null ? that.direccion_ip == null : this.direccion_ip.equals(that.direccion_ip));
    equal = equal && (this.sistema_operativo == null ? that.sistema_operativo == null : this.sistema_operativo.equals(that.sistema_operativo));
    equal = equal && (this.ubicacion == null ? that.ubicacion == null : this.ubicacion.equals(that.ubicacion));
    equal = equal && (this.estado == null ? that.estado == null : this.estado.equals(that.estado));
=======
    equal = equal && (this.direccion_ip == null ? that.direccion_ip == null : this.direccion_ip.equals(that.direccion_ip));
    equal = equal && (this.estado == null ? that.estado == null : this.estado.equals(that.estado));
    equal = equal && (this.id_servidor == null ? that.id_servidor == null : this.id_servidor.equals(that.id_servidor));
    equal = equal && (this.nombre == null ? that.nombre == null : this.nombre.equals(that.nombre));
    equal = equal && (this.sistema_operativo == null ? that.sistema_operativo == null : this.sistema_operativo.equals(that.sistema_operativo));
    equal = equal && (this.ubicacion == null ? that.ubicacion == null : this.ubicacion.equals(that.ubicacion));
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
    return equal;
  }
  public boolean equals0(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof servidores)) {
      return false;
    }
    servidores that = (servidores) o;
    boolean equal = true;
<<<<<<< HEAD
    equal = equal && (this.id_servidor == null ? that.id_servidor == null : this.id_servidor.equals(that.id_servidor));
    equal = equal && (this.nombre == null ? that.nombre == null : this.nombre.equals(that.nombre));
    equal = equal && (this.direccion_ip == null ? that.direccion_ip == null : this.direccion_ip.equals(that.direccion_ip));
    equal = equal && (this.sistema_operativo == null ? that.sistema_operativo == null : this.sistema_operativo.equals(that.sistema_operativo));
    equal = equal && (this.ubicacion == null ? that.ubicacion == null : this.ubicacion.equals(that.ubicacion));
    equal = equal && (this.estado == null ? that.estado == null : this.estado.equals(that.estado));
=======
    equal = equal && (this.direccion_ip == null ? that.direccion_ip == null : this.direccion_ip.equals(that.direccion_ip));
    equal = equal && (this.estado == null ? that.estado == null : this.estado.equals(that.estado));
    equal = equal && (this.id_servidor == null ? that.id_servidor == null : this.id_servidor.equals(that.id_servidor));
    equal = equal && (this.nombre == null ? that.nombre == null : this.nombre.equals(that.nombre));
    equal = equal && (this.sistema_operativo == null ? that.sistema_operativo == null : this.sistema_operativo.equals(that.sistema_operativo));
    equal = equal && (this.ubicacion == null ? that.ubicacion == null : this.ubicacion.equals(that.ubicacion));
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
    return equal;
  }
  public void readFields(ResultSet __dbResults) throws SQLException {
    this.__cur_result_set = __dbResults;
<<<<<<< HEAD
    this.id_servidor = JdbcWritableBridge.readInteger(1, __dbResults);
    this.nombre = JdbcWritableBridge.readString(2, __dbResults);
    this.direccion_ip = JdbcWritableBridge.readString(3, __dbResults);
    this.sistema_operativo = JdbcWritableBridge.readString(4, __dbResults);
    this.ubicacion = JdbcWritableBridge.readString(5, __dbResults);
    this.estado = JdbcWritableBridge.readString(6, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.id_servidor = JdbcWritableBridge.readInteger(1, __dbResults);
    this.nombre = JdbcWritableBridge.readString(2, __dbResults);
    this.direccion_ip = JdbcWritableBridge.readString(3, __dbResults);
    this.sistema_operativo = JdbcWritableBridge.readString(4, __dbResults);
    this.ubicacion = JdbcWritableBridge.readString(5, __dbResults);
    this.estado = JdbcWritableBridge.readString(6, __dbResults);
=======
    this.direccion_ip = JdbcWritableBridge.readString(1, __dbResults);
    this.estado = JdbcWritableBridge.readString(2, __dbResults);
    this.id_servidor = JdbcWritableBridge.readInteger(3, __dbResults);
    this.nombre = JdbcWritableBridge.readString(4, __dbResults);
    this.sistema_operativo = JdbcWritableBridge.readString(5, __dbResults);
    this.ubicacion = JdbcWritableBridge.readString(6, __dbResults);
  }
  public void readFields0(ResultSet __dbResults) throws SQLException {
    this.direccion_ip = JdbcWritableBridge.readString(1, __dbResults);
    this.estado = JdbcWritableBridge.readString(2, __dbResults);
    this.id_servidor = JdbcWritableBridge.readInteger(3, __dbResults);
    this.nombre = JdbcWritableBridge.readString(4, __dbResults);
    this.sistema_operativo = JdbcWritableBridge.readString(5, __dbResults);
    this.ubicacion = JdbcWritableBridge.readString(6, __dbResults);
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
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
<<<<<<< HEAD
    JdbcWritableBridge.writeInteger(id_servidor, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(nombre, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(direccion_ip, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(sistema_operativo, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(ubicacion, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(estado, 6 + __off, 12, __dbStmt);
    return 6;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeInteger(id_servidor, 1 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(nombre, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(direccion_ip, 3 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(sistema_operativo, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(ubicacion, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(estado, 6 + __off, 12, __dbStmt);
=======
    JdbcWritableBridge.writeString(direccion_ip, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(estado, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(id_servidor, 3 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(nombre, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(sistema_operativo, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(ubicacion, 6 + __off, 12, __dbStmt);
    return 6;
  }
  public void write0(PreparedStatement __dbStmt, int __off) throws SQLException {
    JdbcWritableBridge.writeString(direccion_ip, 1 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(estado, 2 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeInteger(id_servidor, 3 + __off, 4, __dbStmt);
    JdbcWritableBridge.writeString(nombre, 4 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(sistema_operativo, 5 + __off, 12, __dbStmt);
    JdbcWritableBridge.writeString(ubicacion, 6 + __off, 12, __dbStmt);
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
  }
  public void readFields(DataInput __dataIn) throws IOException {
this.readFields0(__dataIn);  }
  public void readFields0(DataInput __dataIn) throws IOException {
    if (__dataIn.readBoolean()) { 
<<<<<<< HEAD
=======
        this.direccion_ip = null;
    } else {
    this.direccion_ip = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.estado = null;
    } else {
    this.estado = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
        this.id_servidor = null;
    } else {
    this.id_servidor = Integer.valueOf(__dataIn.readInt());
    }
    if (__dataIn.readBoolean()) { 
        this.nombre = null;
    } else {
    this.nombre = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
<<<<<<< HEAD
        this.direccion_ip = null;
    } else {
    this.direccion_ip = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
=======
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
        this.sistema_operativo = null;
    } else {
    this.sistema_operativo = Text.readString(__dataIn);
    }
    if (__dataIn.readBoolean()) { 
        this.ubicacion = null;
    } else {
    this.ubicacion = Text.readString(__dataIn);
    }
<<<<<<< HEAD
    if (__dataIn.readBoolean()) { 
        this.estado = null;
    } else {
    this.estado = Text.readString(__dataIn);
    }
  }
  public void write(DataOutput __dataOut) throws IOException {
=======
  }
  public void write(DataOutput __dataOut) throws IOException {
    if (null == this.direccion_ip) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, direccion_ip);
    }
    if (null == this.estado) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, estado);
    }
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
    if (null == this.id_servidor) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.id_servidor);
    }
    if (null == this.nombre) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, nombre);
    }
<<<<<<< HEAD
    if (null == this.direccion_ip) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, direccion_ip);
    }
=======
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
    if (null == this.sistema_operativo) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, sistema_operativo);
    }
    if (null == this.ubicacion) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, ubicacion);
    }
<<<<<<< HEAD
    if (null == this.estado) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, estado);
    }
  }
  public void write0(DataOutput __dataOut) throws IOException {
=======
  }
  public void write0(DataOutput __dataOut) throws IOException {
    if (null == this.direccion_ip) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, direccion_ip);
    }
    if (null == this.estado) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, estado);
    }
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
    if (null == this.id_servidor) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    __dataOut.writeInt(this.id_servidor);
    }
    if (null == this.nombre) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, nombre);
    }
<<<<<<< HEAD
    if (null == this.direccion_ip) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, direccion_ip);
    }
=======
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
    if (null == this.sistema_operativo) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, sistema_operativo);
    }
    if (null == this.ubicacion) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, ubicacion);
    }
<<<<<<< HEAD
    if (null == this.estado) { 
        __dataOut.writeBoolean(true);
    } else {
        __dataOut.writeBoolean(false);
    Text.writeString(__dataOut, estado);
    }
=======
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
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
<<<<<<< HEAD
=======
    __sb.append(FieldFormatter.escapeAndEnclose(direccion_ip==null?"null":direccion_ip, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(estado==null?"null":estado, delimiters));
    __sb.append(fieldDelim);
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
    __sb.append(FieldFormatter.escapeAndEnclose(id_servidor==null?"null":"" + id_servidor, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(nombre==null?"null":nombre, delimiters));
    __sb.append(fieldDelim);
<<<<<<< HEAD
    __sb.append(FieldFormatter.escapeAndEnclose(direccion_ip==null?"null":direccion_ip, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(sistema_operativo==null?"null":sistema_operativo, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ubicacion==null?"null":ubicacion, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(estado==null?"null":estado, delimiters));
=======
    __sb.append(FieldFormatter.escapeAndEnclose(sistema_operativo==null?"null":sistema_operativo, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ubicacion==null?"null":ubicacion, delimiters));
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
    if (useRecordDelim) {
      __sb.append(delimiters.getLinesTerminatedBy());
    }
    return __sb.toString();
  }
  public void toString0(DelimiterSet delimiters, StringBuilder __sb, char fieldDelim) {
<<<<<<< HEAD
=======
    __sb.append(FieldFormatter.escapeAndEnclose(direccion_ip==null?"null":direccion_ip, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(estado==null?"null":estado, delimiters));
    __sb.append(fieldDelim);
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
    __sb.append(FieldFormatter.escapeAndEnclose(id_servidor==null?"null":"" + id_servidor, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(nombre==null?"null":nombre, delimiters));
    __sb.append(fieldDelim);
<<<<<<< HEAD
    __sb.append(FieldFormatter.escapeAndEnclose(direccion_ip==null?"null":direccion_ip, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(sistema_operativo==null?"null":sistema_operativo, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ubicacion==null?"null":ubicacion, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(estado==null?"null":estado, delimiters));
=======
    __sb.append(FieldFormatter.escapeAndEnclose(sistema_operativo==null?"null":sistema_operativo, delimiters));
    __sb.append(fieldDelim);
    __sb.append(FieldFormatter.escapeAndEnclose(ubicacion==null?"null":ubicacion, delimiters));
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
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
<<<<<<< HEAD
=======
    if (__cur_str.equals("null")) { this.direccion_ip = null; } else {
      this.direccion_ip = __cur_str;
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
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id_servidor = null; } else {
      this.id_servidor = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.nombre = null; } else {
      this.nombre = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
<<<<<<< HEAD
    if (__cur_str.equals("null")) { this.direccion_ip = null; } else {
      this.direccion_ip = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
=======
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
    if (__cur_str.equals("null")) { this.sistema_operativo = null; } else {
      this.sistema_operativo = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.ubicacion = null; } else {
      this.ubicacion = __cur_str;
    }

<<<<<<< HEAD
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.estado = null; } else {
      this.estado = __cur_str;
    }

=======
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  private void __loadFromFields0(Iterator<String> __it) {
    String __cur_str = null;
    try {
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
<<<<<<< HEAD
=======
    if (__cur_str.equals("null")) { this.direccion_ip = null; } else {
      this.direccion_ip = __cur_str;
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
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
    if (__cur_str.equals("null") || __cur_str.length() == 0) { this.id_servidor = null; } else {
      this.id_servidor = Integer.valueOf(__cur_str);
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.nombre = null; } else {
      this.nombre = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
<<<<<<< HEAD
    if (__cur_str.equals("null")) { this.direccion_ip = null; } else {
      this.direccion_ip = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
=======
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
    if (__cur_str.equals("null")) { this.sistema_operativo = null; } else {
      this.sistema_operativo = __cur_str;
    }

    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.ubicacion = null; } else {
      this.ubicacion = __cur_str;
    }

<<<<<<< HEAD
    if (__it.hasNext()) {
        __cur_str = __it.next();
    } else {
        __cur_str = "null";
    }
    if (__cur_str.equals("null")) { this.estado = null; } else {
      this.estado = __cur_str;
    }

=======
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
    } catch (RuntimeException e) {    throw new RuntimeException("Can't parse input data: '" + __cur_str + "'", e);    }  }

  public Object clone() throws CloneNotSupportedException {
    servidores o = (servidores) super.clone();
    return o;
  }

  public void clone0(servidores o) throws CloneNotSupportedException {
  }

  public Map<String, Object> getFieldMap() {
    Map<String, Object> __sqoop$field_map = new HashMap<String, Object>();
<<<<<<< HEAD
    __sqoop$field_map.put("id_servidor", this.id_servidor);
    __sqoop$field_map.put("nombre", this.nombre);
    __sqoop$field_map.put("direccion_ip", this.direccion_ip);
    __sqoop$field_map.put("sistema_operativo", this.sistema_operativo);
    __sqoop$field_map.put("ubicacion", this.ubicacion);
    __sqoop$field_map.put("estado", this.estado);
=======
    __sqoop$field_map.put("direccion_ip", this.direccion_ip);
    __sqoop$field_map.put("estado", this.estado);
    __sqoop$field_map.put("id_servidor", this.id_servidor);
    __sqoop$field_map.put("nombre", this.nombre);
    __sqoop$field_map.put("sistema_operativo", this.sistema_operativo);
    __sqoop$field_map.put("ubicacion", this.ubicacion);
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
    return __sqoop$field_map;
  }

  public void getFieldMap0(Map<String, Object> __sqoop$field_map) {
<<<<<<< HEAD
    __sqoop$field_map.put("id_servidor", this.id_servidor);
    __sqoop$field_map.put("nombre", this.nombre);
    __sqoop$field_map.put("direccion_ip", this.direccion_ip);
    __sqoop$field_map.put("sistema_operativo", this.sistema_operativo);
    __sqoop$field_map.put("ubicacion", this.ubicacion);
    __sqoop$field_map.put("estado", this.estado);
=======
    __sqoop$field_map.put("direccion_ip", this.direccion_ip);
    __sqoop$field_map.put("estado", this.estado);
    __sqoop$field_map.put("id_servidor", this.id_servidor);
    __sqoop$field_map.put("nombre", this.nombre);
    __sqoop$field_map.put("sistema_operativo", this.sistema_operativo);
    __sqoop$field_map.put("ubicacion", this.ubicacion);
>>>>>>> c33afd9a2b535eba3c057b6a3454cc32da9cfab3
  }

  public void setField(String __fieldName, Object __fieldVal) {
    if (!setters.containsKey(__fieldName)) {
      throw new RuntimeException("No such field:"+__fieldName);
    }
    setters.get(__fieldName).setField(__fieldVal);
  }

}
