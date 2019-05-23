/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Nelson.Amaral
 */
public class ValidaFormes {

        public static String Formulario(String s) {
            s = s.replaceAll("(?i)<script.*?>.*?</script.*?>", "EntradaBloqueada");
            s = s.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "EntradaBloqueada");
            s = s.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "EntradaBloqueada");
            s = s.replaceAll("DELETE FROM", "EntradaBloqueada");
            s = s.replaceAll("\n", "");

            return s;
        }
    

}
