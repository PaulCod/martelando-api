package com.martelando.martelandoapp.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FindProductByText (
        @JsonProperty("title")
        String title
){}
