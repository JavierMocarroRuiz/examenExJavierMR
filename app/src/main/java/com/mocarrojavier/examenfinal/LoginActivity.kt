package com.mocarrojavier.examenfinal

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.textservice.SentenceSuggestionsInfo
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mocarrojavier.examenfinal.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private lateinit var googleLauncher: ActivityResultLauncher<Intent>

    private lateinit var firebaseAuth: FirebaseAuth

    private  lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()

        firebaseAuth = Firebase.auth
        sharedPreferences = getSharedPreferences("SESSION_PREFERENCES", MODE_PRIVATE)
        googleLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                try{
                    val account = task.getResult(ApiException::class.java)
                    signInFirebaseWithGoogle(account.idToken)
                }  catch (e: Exception){

                }
            }
        }
    }

    private fun signInFirebaseWithGoogle(idToken: String?) {
        val authCredential = GoogleAuthProvider.getCredential(idToken,null)

        firebaseAuth.signInWithCredential(authCredential)
            .addOnCompleteListener (){
                if(it.isSuccessful){
                    val user: FirebaseUser = firebaseAuth.currentUser!!
                    sharedPreferences.edit().apply{
                        putString(EMAIL, user.email)
                            .apply()
                    }
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else{
                    Toast.makeText(this,"Ocurrio un error",Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun setupViews(){
        binding.tilEmail.editText?.addTextChangedListener {text ->
            binding.btnLogin.isEnabled= validateInputs(text.toString(), binding.tilPassword.editText?.text.toString())
        }

        binding.tilPassword.editText?.addTextChangedListener {text ->
            binding.btnLogin.isEnabled= validateInputs(binding.tilEmail.editText?.text.toString(), text.toString())
        }

        binding.btnLogin.setOnClickListener {
            Toast.makeText(this,"Login iniciado",Toast.LENGTH_SHORT).show()
            /*val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()*/
            val password = binding.tilPassword.editText.toString()
            val email = binding.tilEmail.editText.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginWithEmailAndPassword(email, password)
            }
            else {
                Toast.makeText(this,"Campos vacios",Toast.LENGTH_SHORT).show()
            }
            signInWithEmailPassword()
        }

        binding.btnGoogle.setOnClickListener{
            signInWithGoogle()
        }

        binding.btnRegistrar.setOnClickListener{
            val password = binding.tilPassword.editText.toString()
            val email = binding.tilEmail.editText.toString()
            signUpWithEmailAndPassword(email,password)
        }
    }

    private fun authenticateWithFirebase(idToken: String) {
        val authCredentials = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(authCredentials)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
    }

    private fun signUpWithEmailPassword() {
        val email = binding.tilEmail?.editText.toString()
        val password = binding.tilPassword?.editText.toString()
        if(validateInputs(email, password)){
            firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){
                    if(it.isSuccessful){
                        Toast.makeText(this,"Usuario Creado",Toast.LENGTH_SHORT).show()
                    } else{
                        Toast.makeText(this,"El usuario no se creo",Toast.LENGTH_SHORT).show()
                    }

                }
        }
    }
    private fun signUpWithEmailAndPassword(email: String, password: String) {
        val email = binding.tilEmail?.editText.toString()
        val password = binding.tilPassword?.editText.toString()
        if (validateInputs(email, password)) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){ task ->
                    if (task.isSuccessful) {
                        val user = firebaseAuth.currentUser
                    }
                }
        }
        else {

        }
    }

    private fun signInWithEmailPassword() {
        val email = binding.tilEmail?.editText.toString()
        val password = binding.tilPassword?.editText.toString()
        signInFirebaseWithEmail(email,password)

    }

    private fun signInFirebaseWithEmail(email: String, password: String) {

        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    val user = firebaseAuth.currentUser
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()

                }else{
                    Toast.makeText(this,"El usuario no se encontro",Toast.LENGTH_SHORT).show()
                }
            }

    }

    private fun loginWithEmailAndPassword(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else {
                }
            }
    }

    private fun signInWithGoogle() {
        val googleSingOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.web_client_id))
            .requestEmail().build()

        val client: GoogleSignInClient = GoogleSignIn.getClient(this,googleSingOptions)

        val intent= client.signInIntent

        googleLauncher.launch(intent)

    }

    private fun validateInputs(email: String, password: String): Boolean {
        val isEmailOk = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordOk = password.length >= 6
        return isPasswordOk  && isEmailOk
    }

    companion object{
        const val SESSION_PREFERENCE: String ="SESSION_PREFERENCES"
        const val EMAIL: String ="EMAIL"
    }

}