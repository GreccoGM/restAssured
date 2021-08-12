package org.gerenciador.dados;

import java.util.HashMap;
import java.util.Map;

public class LoginDados {

  public Map dadosAdmin(){
        Map<String,Object> loginAdmin = new HashMap<String, Object>();
            loginAdmin.put("email","admin@email.com");
            loginAdmin.put("senha", "654321");

        return loginAdmin;
    }

    public Map dadosUsuario(){
        Map<String,Object> loginUsuario = new HashMap<String, Object>();
        loginUsuario.put("email","usuario@email.com");
        loginUsuario.put("senha", "123456");

        return loginUsuario;
    }
}
