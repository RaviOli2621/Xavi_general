package vista;

import java.util.ArrayList;

public class Vista {
    public static void mostrarUnMisatgeGeneric(String string)
    {
        System.out.println(string);
    }
    public static void mostrarPreg1(ArrayList<String> missatge)
    {
        System.out.printf("%-10s %-40s\n","id","nombre");
        for (int i = 0; i < missatge.size(); i++)
        {
            System.out.printf("%-10s %-40s\n",missatge.get(i),missatge.get(i+1));
            i++;
        }
    }
}
