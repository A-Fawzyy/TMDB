//
// Created by Abdelhamid Nasser on 09/12/2022.
//

#include <jni.h>
#include <string>


/*
 * Todo(Appsec): Need to encrypt the apiKey here and decrypt it in the runtime
 */
extern "C" JNIEXPORT jstring
JNICALL
Java_com_fawry_tmdb_util_Native_apiKey(JNIEnv *env, jobject object) {
    std::string api_key = "c50f5aa4e7c95a2a553d29b81aad6dd0";
        return env->NewStringUTF(api_key.c_str());
}
