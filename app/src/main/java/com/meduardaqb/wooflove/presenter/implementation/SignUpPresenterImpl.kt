package com.meduardaqb.wooflove.presenter.implementation

import android.R
import android.widget.ArrayAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.GsonBuilder
import com.meduardaqb.wooflove.exception.BlankFieldException
import com.meduardaqb.wooflove.model.DogBreed
import com.meduardaqb.wooflove.model.User
import com.meduardaqb.wooflove.presenter.SignUpPresenter
import com.meduardaqb.wooflove.util.JsonParser
import com.meduardaqb.wooflove.view.SignUpView
import org.jetbrains.anko.toast
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.meduardaqb.wooflove.util.DatabaseNode
import com.meduardaqb.wooflove.view.activity.HomeActivity
import org.jetbrains.anko.startActivity


class SignUpPresenterImpl : SignUpPresenter {

    private lateinit var view: SignUpView

    override fun onCreate() {
        this.populateSpinner()
    }

    override fun setView(view: SignUpView) {
        this.view = view
    }

    override fun populateSpinner() {
        val gson = GsonBuilder().create()
        val jsonResponse = gson.fromJson(JsonParser.readJSONFromAsset(this.view.getContext()), DogBreed::class.java)
        view.getSpinner().adapter = ArrayAdapter<String>(this.view.getContext(), R.layout.simple_spinner_dropdown_item, jsonResponse.dogs)
    }

    override fun signUp() {

        try {
            this.verifyFields()

            val user = User(name = view.getPetName(), email = view.getEmail(),
                    city = view.getCity(), message = view.getMessage(), password = view.getPassword(),
                    breed = view.getSpinner().getItemAtPosition(view.getSpinner().selectedItemPosition).toString())
            this.registerUserIntoAuth(user)

        } catch (e: BlankFieldException){
            view.getContext().toast("Você precisa preencher todos os campos")
        }
    }

    override fun verifyFields() {
         if (view.getPetName().isNullOrBlank() ||
                view.getEmail().isNullOrBlank() ||
                view.getCity().isNullOrBlank() ||
                view.getMessage().isNullOrBlank() ||
                view.getPassword().isNullOrBlank()){
             throw BlankFieldException("Um ou mais campos é nulo")
         }
    }

    override fun registerUserIntoAuth(user: User) {
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener { task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        this.registerUserIntoDatabase(user)
                    } else {
                        view.getContext().toast("Ocorreu um erro ao tentar criar um usuário")
                    }
        }
    }

    override fun registerUserIntoDatabase(user: User) {

        FirebaseDatabase.getInstance().reference.child(DatabaseNode.USER.node).child(user.email.replace("@", "").replace(".", "")).setValue(user)
        view.getContext().startActivity<HomeActivity>()
    }
}