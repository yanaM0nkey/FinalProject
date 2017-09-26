package com.gmail.ioanna.finalapp;


import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;

import com.gmail.ioanna.domain.entity.ProfileModel;
import com.gmail.ioanna.domain.entity.ResponseAnswerModel;
import com.gmail.ioanna.domain.interactions.CreateProfileUseCase;
import com.gmail.ioanna.finalapp.base.BaseViewModel;

import io.reactivex.observers.DisposableObserver;

public class RegistryViewModel implements BaseViewModel {

    public ObservableField<String> login2reg = new ObservableField<>();
    public ObservableField<String> firstPassword = new ObservableField<>();
    public ObservableField<String> secondPassword = new ObservableField<>();

    public String password2reg;
    private int status;
    private String login;
    private int id;
    private String token;

    public CreateProfileUseCase createProfileUseCase = new CreateProfileUseCase();

    @Override
    public void init() {}

    @Override
    public void release() {}

    @Override
    public void resume() {}

    public void onSignUpClick() {
        if (firstPassword.get().equals(secondPassword.get())) {
            password2reg = secondPassword.get();

            Log.e("Otpraavliaem: ", login2reg.get());
            Log.e("Otpraavliaem: ", password2reg);

            ProfileModel profileModel = new ProfileModel();
            profileModel.setLogin(login2reg.get());
            profileModel.setPassword(password2reg);

            createProfileUseCase.execute(profileModel, new DisposableObserver<ResponseAnswerModel>() {
                @Override
                public void onNext(@NonNull ResponseAnswerModel responseAnswerModel) {
                    status = responseAnswerModel.getStatus();
                    login = responseAnswerModel.getResponseProfileModel().getLogin();
                    id = responseAnswerModel.getResponseProfileModel().getUserId();
                    token = responseAnswerModel.getResponseProfileModel().getToken();

                    Log.e("Otvet status: ", String.valueOf(status));
                    Log.e("Otvet login: ", login);
                    Log.e("Otvet id: ", String.valueOf(id));
                    Log.e("Otvet token: ", token);
                }

                @Override
                public void onError(@NonNull Throwable e) {}

                @Override
                public void onComplete() {}
            });
        } else {
            Log.e("Error", "Registry password mismatch");
        }
    }

    @Override
    public void pause() {
        createProfileUseCase.dispose();
    }
}
