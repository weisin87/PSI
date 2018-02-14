package com.sp.sppsi.rest;

import com.sp.sppsi.model.PsiResponse;

import java.util.List;

/**
 * Created by Eujane Chong on 19/12/2017.
 */

public class ApiResponse {

    private PsiResponse psiResponses;
    private Throwable error;

    public ApiResponse(PsiResponse psiResponses){
        this.psiResponses = psiResponses;
        this.error = null;
    }

    public ApiResponse(Throwable error){
        this.error = error;
        this.psiResponses = null;
    }

    public PsiResponse getPsiResponse() {
        return psiResponses;
    }


    public Throwable getError() {
        return error;
    }

}
