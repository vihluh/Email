package isabel.vitoria.email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);
        //Definição da ação do click do botão
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Obtendo dados digitados pelo usuario
                EditText etEmail = (EditText) findViewById(R.id.etEmail);
                String email = etEmail.getText().toString();

                EditText etAssunto = (EditText) findViewById(R.id.etAssunto);
                String assunto = etAssunto.getText().toString();

                EditText etTexto = (EditText) findViewById(R.id.etTexto);
                String texto = etTexto.getText().toString();

                Intent i = new Intent(Intent.ACTION_SENDTO);

                //definimos que estamos interessados somente em apps que resolvem o UR
                i.setData(Uri.parse("mailto:"));

                //preenchendo o Intent com os dados que queremos enviar para app externa que vai enviar o e-mail.
                String[] emails = new String[]{email};
                i.putExtra(Intent.EXTRA_EMAIL, emails);
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);
                i.putExtra(Intent.EXTRA_TEXT, texto);

                try { //tentamos executar o intent
                    startActivity(Intent.createChooser(i, "Escolha o APP")); // faz o menu aparecer
                } catch (ActivityNotFoundException e) { // se não tiver app vem para ca
                    Toast.makeText(MainActivity.this, "Não há nenhum app que possa realizar essa operação",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}