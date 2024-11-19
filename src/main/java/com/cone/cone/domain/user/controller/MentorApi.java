package com.cone.cone.domain.user.controller;

import com.cone.cone.domain.room.entity.Room;
import com.cone.cone.domain.user.dto.request.LoginRequest;
import com.cone.cone.domain.user.dto.response.LoginResponse;
import com.cone.cone.global.response.ResponseTemplate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MentorApi {
    ResponseEntity<ResponseTemplate<List<Room>>> getRoomsById(Long id);
}