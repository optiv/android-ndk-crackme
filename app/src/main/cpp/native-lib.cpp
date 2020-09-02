#include <jni.h>
#include <string>
#include <map>

extern "C" JNIEXPORT jstring JNICALL
Java_com_optiv_ndkcrackme_MainActivity_a(
        JNIEnv* env,
        jobject jobj) {
    std::string hello = "This string retrieved from C++!";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jboolean JNICALL
Java_com_optiv_ndkcrackme_MainActivity_b(
        JNIEnv* env,
        jobject jobj,
        jstring password) {
    std::string correct = "optiv";
    std::string entered = env->GetStringUTFChars(password, 0);
    return correct.compare(entered) == 0;
}

std::map<std::string, std::string> dataStore;

extern "C" JNIEXPORT void JNICALL
Java_com_optiv_ndkcrackme_MainActivity_c(
        JNIEnv* env,
        jobject jobj) {
    dataStore.clear();

    dataStore["flag"] = "hooray";
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_optiv_ndkcrackme_MainActivity_d(
        JNIEnv* env,
        jobject jobj,
        jstring key) {
    std::string ckey = env->GetStringUTFChars(key, 0);
    if (dataStore.count(ckey) == 0)
        return env->NewStringUTF("");
    else
        return env->NewStringUTF(dataStore[ckey].c_str());
}

extern "C" JNIEXPORT void JNICALL
Java_com_optiv_ndkcrackme_MainActivity_e(
        JNIEnv* env,
        jobject jobj,
        jstring key,
        jstring value) {
    std::string ckey = env->GetStringUTFChars(key, 0);
    std::string cval = env->GetStringUTFChars(value, 0);
    dataStore[ckey] = cval;
}
