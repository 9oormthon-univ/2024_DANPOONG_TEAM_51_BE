package com.cone.cone.socketio.service.type;

public record SignalingData(
    Object candidate,
    Object sdp
) {}