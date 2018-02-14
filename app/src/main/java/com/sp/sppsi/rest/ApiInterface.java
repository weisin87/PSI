package com.sp.sppsi.rest;


import com.sp.sppsi.model.PsiModel;
import com.sp.sppsi.model.PsiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("environment/psi")
    Call<PsiResponse> getPSI();
}
