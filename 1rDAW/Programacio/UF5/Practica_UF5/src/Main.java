public class Main
{
    public static void main(String[] args) {
        try {
            Alimentacio al = new Alimentacio("10.0","Manzana","123456432","10-02-2024");
            System.out.println(al.getData_caducitat());
        }catch (java.text.ParseException e)//exepció en el parser de la data
        {
            System.out.println("El format de la data es incorrecte");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
