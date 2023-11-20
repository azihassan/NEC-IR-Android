package com.pingfrommorocco.remoteir

enum class NecCode(val code: Int) {
    ADDRESS(0xfe01),

    UP(0x19),
    RIGHT(0x1c),
    DOWN(0x1a),
    LEFT(0x1b),
    OK(0x1d),

    POWER(0x10),
    CHANNEL_UP(0xc),
    CHANNEL_DOWN(0xd),
    VOLUME_UP(0xe),
    VOLUME_DOWN(0xf),
    MUTE(0x17),

    SOURCE(0x12),
    DISPLAY(0xb),
    MENU(0x15),
    EXIT(0x16),

    MEDIA(0x42),
    RECORD(0x43),
    PLAY(0x57),
    STOP(0x58),
    FAST_FORWARD(0x52),
    FAST_BACKWARD(0x53),
    PREVIOUS(0x1e),
    NEXT(0x49),

    KEY_0(0x0),
    KEY_1(0x1),
    KEY_2(0x2),
    KEY_3(0x3),
    KEY_4(0x4),
    KEY_5(0x5),
    KEY_6(0x6),
    KEY_7(0x7),
    KEY_8(0x8),
    KEY_9(0x9),
}