package com.abhishikha.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.net.URL;
import java.time.Duration;
import java.time.Instant;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
public class LoginResult {

    private final @NonNull Instant loginTs;
    private final @NonNull String authToken;
    private final @NonNull Duration tokenValidity;
    private final @NonNull URL tokenRefreshUrl;
}