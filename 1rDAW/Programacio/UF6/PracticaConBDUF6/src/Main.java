import java.sql.*;
public class Main {


    public static void main(String[] args) {
        String url = "jdbc:mysql://192.168.56.103:3306/NBAData";
        String username = "perepi";
        String paswrd = "pastanaga";
        Connection con = openCon(url,username,paswrd);

        consultes(con);

        closeCon(con);
    }

    private static Connection openCon(String url,String nom, String contra ) {
        Connection con = null;

        try {
            con = DriverManager.getConnection(url, nom, contra);
            System.out.println("Connexió establerta");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return con;
    }

    private static void closeCon(Connection con) {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void consultes(Connection con)
    {
        try {
            Statement sta = con.createStatement();
            ResultSet rs = sta.executeQuery("SELECT full_name FROM players");

            while (rs.next())
            {
                String nombre = rs.getString("full_name");
                System.out.println(nombre);
            }
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}