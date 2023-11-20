package com.pingfrommorocco.remoteir

class NecTransformer {
    fun transformMessage(address: Int, command: Int): List<Int> {
        val pulses = mutableListOf<Int>()
        pulses.addAll(initMessage())
        pulses.addAll(transformByte(address and 0xff))
        pulses.addAll(transformByte(address and 0xff00 shr 8))
        pulses.addAll(transformByte(command))
        pulses.addAll(transformByte(command.inv()))
        pulses.addAll(finishMessage())
        pulses.add(560)
        return pulses
    }

    private fun initMessage(): List<Int> {
        return listOf(9000, 4500)
    }

    private fun transformByte(value: Int): MutableList<Int> {
        var current = value
        val pulses = mutableListOf<Int>()
        for (i in 0 .. 7) {
            if (current and 1 == 1) {
                pulses.addAll(mark())
            } else {
                pulses.addAll(space())
            }
            current = current shr 1
        }
        return pulses
    }

    private fun finishMessage(): List<Int> {
        return listOf(560)
    }

    private fun mark(): List<Int> {
        return listOf(560, 1680)
    }

    private fun space(): List<Int> {
        return listOf(560, 560)
    }
}