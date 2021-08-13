package org.gerenciador.dados;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class LoginDados {

    public String dadosAdminObj(){
        return (new JSONObject())
              .put("email", "admin@email.com")
              .put("senha", "654321")
              .toString();
    }

    public Map dadosUsuario(){
        Map<String,Object> loginUsuario = new HashMap<String, Object>();
        loginUsuario.put("email","usuario@email.com");
        loginUsuario.put("senha", "123456");

        return loginUsuario;
    }

    public Map dadosAdmin(){
        Map<String,Object> loginAdmin = new HashMap<String, Object>();
        loginAdmin.put("email","admin@email.com");
        loginAdmin.put("senha", "654321");

        return loginAdmin;
    }
}
