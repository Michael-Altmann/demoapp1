package com.maltmannapp.demoapp1

class MyClass(): MyCallback {
    override fun callbackFunction() {
        println("MyClass is working")
    }
}

interface MyCallback {
    fun callbackFunction()
}

class Worker(val callback: MyCallback) {
    fun process() {
        println("Worker is Working")
        callback.callbackFunction()
    }
}