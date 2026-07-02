package modelo;

public class Usuario 
{

    private int id;
    private String nombre;
    private String correo;
    private String password;
    private String codigo;
    private boolean verificado;
    private boolean activo;

    public Usuario() 
    {
    }

    public Usuario(int id, String nombre, String correo, String password,
                    String codigo, boolean verificado, boolean activo) 
    {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.codigo = codigo;
        this.verificado = verificado;
        this.activo = activo;
    }

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public String getCorreo() 
    {
        return correo;
    }

    public void setCorreo(String correo) 
    {
        this.correo = correo;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getCodigo() 
    {
        return codigo;
    }

    public void setCodigo(String codigo) 
    {
        this.codigo = codigo;
    }

    public boolean isVerificado() 
    {
        return verificado;
    }

    public void setVerificado(boolean verificado) 
    {
        this.verificado = verificado;
    }

    public boolean isActivo() 
    {
        return activo;
    }

    public void setActivo(boolean activo) 
    {
        this.activo = activo;
    }
}
