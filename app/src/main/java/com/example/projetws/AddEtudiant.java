package com.example.projetws;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddEtudiant extends AppCompatActivity implements View.OnClickListener {

    private EditText nom, prenom;
    private Spinner ville;
    private RadioButton m, f;
    private Button add;
    private RequestQueue requestQueue;

    private static final String insertUrl = "http://10.0.2.2/project/ws/createEtudiant.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_etudiant);

        // Initialisation des composants
        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        ville = findViewById(R.id.ville);
        m = findViewById(R.id.m);
        f = findViewById(R.id.f);
        add = findViewById(R.id.add);

        requestQueue = Volley.newRequestQueue(this);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == add) {
            envoyerEtudiant();
        }
    }

    private void envoyerEtudiant() {
        // Récupération des valeurs
        String nomStr = nom.getText().toString().trim();
        String prenomStr = prenom.getText().toString().trim();
        String villeStr = ville.getSelectedItem().toString().trim();
        String sexeStr;

        // Vérification du sexe
        if (m.isChecked()) {
            sexeStr = "homme";
        } else if (f.isChecked()) {
            sexeStr = "femme";
        } else {
            Toast.makeText(this, "Veuillez sélectionner le sexe", Toast.LENGTH_SHORT).show();
            return;
        }

        // Vérification des champs obligatoires
        if (nomStr.isEmpty() || prenomStr.isEmpty() || villeStr.isEmpty()) {
            Toast.makeText(this, "Tous les champs sont obligatoires", Toast.LENGTH_SHORT).show();
            return;
        }

        // Requête POST avec Volley
        StringRequest request = new StringRequest(Request.Method.POST, insertUrl,
                response -> {
                    try {
                        JSONObject json = new JSONObject(response);
                        if (json.has("success") && json.getBoolean("success")) {
                            Toast.makeText(this, "Étudiant ajouté ! ID: " + json.getInt("id"), Toast.LENGTH_SHORT).show();
                        } else if (json.has("error")) {
                            Toast.makeText(this, "Erreur : " + json.getString("error"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Erreur lors du parsing JSON", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Log.e("VOLLEY", "Erreur : " + error.toString())
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nom", nomStr);
                params.put("prenom", prenomStr);
                params.put("ville", villeStr);
                params.put("sexe", sexeStr);
                return params;
            }
        };

        requestQueue.add(request);
    }
}